package com.shterneregen.controllers;

import com.shterneregen.models.ErrorNotification;
import com.shterneregen.models.Request;
import com.shterneregen.models.Response;
import com.shterneregen.models.SupportRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class RequestController {

    private static final Logger log = LogManager.getLogger(RequestController.class);

    @Value("${message.queue}")
    private String messageQueue;
    @Value("${topic.name}")
    private String topicName;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping(value = {"${request.path}/{requestId}/{description}/{department}"})
    public @ResponseBody
    ResponseEntity<Object> supportRequest(
            @PathVariable String requestId,
            @PathVariable String description,
            @PathVariable String department
    ) {
        SupportRequest request = new SupportRequest(requestId, description, department);
        sendRequest(request);
        return getOkResponse("Support request is received by service");
    }

    @GetMapping(value = {"${request.path}/{requestId}/{errorMsg}"})
    public @ResponseBody
    ResponseEntity<Object> errorNotification(
            @PathVariable String requestId,
            @PathVariable String errorMsg
    ) {
        ErrorNotification request = new ErrorNotification(requestId, errorMsg, new Date());
        sendRequest(request);
        return getOkResponse("Error notification is received by service");
    }

    private void sendRequest(Request request) {
        rabbitTemplate.convertAndSend(topicName, messageQueue, request);
    }

    private ResponseEntity<Object> getOkResponse(String message) {
        return new ResponseEntity<>(
                new Response(HttpStatus.OK, message),
                HttpStatus.OK);
    }
}
