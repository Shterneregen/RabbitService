package random.shterneregen.rabbitmqdemo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequestController.class)
public class RequestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private RabbitTemplate rabbitTemplate;
	@Value("${request.path}")
	private String requestPath;

	@Test
	public void supportRequest() throws Exception {
		mockMvc.perform(get(requestPath + "/support/" + UUID.randomUUID().toString() + "/description/department")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void errorNotification() throws Exception {
		mockMvc.perform(get(requestPath + "/error-notification/" + UUID.randomUUID().toString() + "/errorMsg")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
