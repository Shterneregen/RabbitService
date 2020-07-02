package random.shterneregen.rabbitmqdemo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import random.shterneregen.rabbitmqdemo.models.ErrorNotification;
import random.shterneregen.rabbitmqdemo.models.Request;
import random.shterneregen.rabbitmqdemo.models.SupportRequest;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("${request.path}")
@RestController
public class RequestController {

	@Value("${message.queue}")
	private String messageQueue;
	@Value("${topic.name}")
	private String topicName;

	private final RabbitTemplate rabbitTemplate;

	@GetMapping(value = {"/support/{requestId}/{description}/{department}"})
	public @ResponseBody
	ResponseEntity<Object> supportRequest(
			@PathVariable String requestId, @PathVariable String description, @PathVariable String department) {
		log.debug("supportRequest executing. requestId: [{}]; description: [{}]; department: [{}]",
				requestId, description, department);
		SupportRequest request = new SupportRequest(requestId, description, department);
		sendRequest(request);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = {"/error-notification/{requestId}/{errorMsg}"})
	public @ResponseBody
	ResponseEntity<Object> errorNotification(@PathVariable String requestId, @PathVariable String errorMsg) {
		log.debug("errorNotification executing. requestId: [{}]; errorMsg: [{}]", requestId, errorMsg);
		ErrorNotification request = new ErrorNotification(requestId, errorMsg, new Date());
		sendRequest(request);
		return ResponseEntity.ok().build();
	}

	private void sendRequest(Request request) {
		rabbitTemplate.convertAndSend(topicName, messageQueue, request);
	}
}
