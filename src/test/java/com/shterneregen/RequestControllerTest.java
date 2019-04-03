package com.shterneregen;

import com.shterneregen.controllers.RequestController;
import com.shterneregen.models.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class RequestControllerTest {

    @Spy
    @InjectMocks
    private RequestController controller;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Test
    public void supportRequestShouldReturnOk() {
        ResponseEntity<Object> response = controller.supportRequest("1", "Help me", "helpers");
        Assert.assertEquals(response, getOkResponse("Support request is received by service"));
    }

    private ResponseEntity<Object> getOkResponse(String message) {
        return new ResponseEntity<>(
                new Response(HttpStatus.OK, message),
                HttpStatus.OK);
    }
}
