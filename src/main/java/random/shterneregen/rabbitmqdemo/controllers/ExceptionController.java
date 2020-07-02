package random.shterneregen.rabbitmqdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController extends AbstractErrorController {

	@Autowired
	public ExceptionController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@ResponseBody
	@RequestMapping(value = "${error.path}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> handleError() {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public String getErrorPath() {
		return "/${error.path}";
	}
}
