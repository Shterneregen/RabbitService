package random.shterneregen.rabbitmqdemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import random.shterneregen.rabbitmqdemo.models.Request;
import random.shterneregen.rabbitmqdemo.services.RequestService;

@RequiredArgsConstructor
@RequestMapping("${response.path}")
@RestController
public class ResponseController {

	private final RequestService service;

	@RequestMapping({"/{requestId}"})
	public @ResponseBody
	ResponseEntity<Object> getById(@PathVariable String requestId) {
		Request request = service.getByRequestId(requestId);
		if (request == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
}
