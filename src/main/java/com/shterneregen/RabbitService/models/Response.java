package com.shterneregen.RabbitService.models;

import org.springframework.http.HttpStatus;

/**
 * Created by Yuriy on 12.12.2018.
 */
public class Response {

    private HttpStatus status;
    private String message;

    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
