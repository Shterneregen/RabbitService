package com.shterneregen.controllers;

import com.shterneregen.models.ErrorNotification;
import com.shterneregen.models.Request;
import com.shterneregen.models.Response;
import com.shterneregen.models.SupportRequest;
import com.shterneregen.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponseController {

    private final RequestService service;

    @Autowired
    public ResponseController(RequestService service) {
        this.service = service;
    }

    @RequestMapping({"${response.path}/{requestId}"})
    public @ResponseBody
    ResponseEntity<Object> getById(@PathVariable String requestId) {
        Request request = service.getByRequestId(requestId);
        if (request == null) {
            return new ResponseEntity<>(
                    new Response(HttpStatus.NOT_FOUND, "Request not processed"),
                    HttpStatus.NOT_FOUND);
        } else {
            if (request instanceof SupportRequest) {
                SupportRequest supportRequest = ((SupportRequest) request);
                return new ResponseEntity<>(
                        supportRequest,
                        HttpStatus.OK);
            } else if (request instanceof ErrorNotification) {
                ErrorNotification errorNotification = ((ErrorNotification) request);
                return new ResponseEntity<>(
                        errorNotification,
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(
                    new Response(HttpStatus.NOT_FOUND, "Request not processed"),
                    HttpStatus.NOT_FOUND);
        }
    }

}
