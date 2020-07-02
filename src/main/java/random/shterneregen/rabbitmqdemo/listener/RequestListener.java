package random.shterneregen.rabbitmqdemo.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import random.shterneregen.rabbitmqdemo.models.Request;
import random.shterneregen.rabbitmqdemo.repositories.RequestRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class RequestListener {

	private final RequestRepository requestRepository;

	public void process(Request request) throws InterruptedException {
		Thread.sleep(1000);

		if (request != null) {
			Request oldRequest = requestRepository.getByRequestId(request.getRequestId());
			if (oldRequest != null) {
				log.warn("Request with requestId [{}] already registered!", request.getRequestId());
				return;
			}
			requestRepository.save(request);
		}
		log.info("Request processed!");
	}

}
