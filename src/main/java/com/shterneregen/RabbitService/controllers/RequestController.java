package com.shterneregen.RabbitService.controllers;

import com.shterneregen.RabbitService.models.ErrorNotification;
import com.shterneregen.RabbitService.models.Request;
import com.shterneregen.RabbitService.models.Response;
import com.shterneregen.RabbitService.models.SupportRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Yuriy on 11.12.2018.
 */
@Controller
public class RequestController {

    private static final Logger log = LogManager.getLogger(RequestController.class);

    private final Environment env;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RequestController(Environment env, RabbitTemplate rabbitTemplate) {
        this.env = env;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(value = {"${request.path}/{requestId}/{description}/{department}"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> supportRequest(
            @PathVariable String requestId,
            @PathVariable String description,
            @PathVariable String department
    ) {
        SupportRequest request = new SupportRequest(requestId, description, department);
        sendRequest(request);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK, "Support request is received by service"),
                HttpStatus.OK);
    }

    @RequestMapping(value = {"${request.path}/{requestId}/{errorMsg}"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Object> errorNotification(
            @PathVariable String requestId,
            @PathVariable String errorMsg
    ) {
        ErrorNotification request = new ErrorNotification(requestId, errorMsg, new Date());
        sendRequest(request);
        return new ResponseEntity<>(
                new Response(HttpStatus.OK, "Error notification is received by service"),
                HttpStatus.OK);
    }

    private void sendRequest(Request request) {
        rabbitTemplate.convertAndSend(
                env.getProperty("topic.name"),
                env.getProperty("message.queue"),
                request);
    }

}
