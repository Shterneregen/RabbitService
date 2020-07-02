package random.shterneregen.rabbitmqdemo.services;

import random.shterneregen.rabbitmqdemo.models.Request;
import random.shterneregen.rabbitmqdemo.repositories.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

	private final RequestRepository requestRepository;

	@Override
	public List<Request> listAll() {
		List<Request> requests = new ArrayList<>();
		requestRepository.findAll().forEach(requests::add);
		return requests;
	}

	@Override
	public Request getById(Long id) {
		return requestRepository.findById(id).orElse(null);
	}

	@Override
	public Request getByRequestId(String requestId) {
		return requestRepository.getByRequestId(requestId);
	}

	@Override
	public Request saveOrUpdate(Request request) {
		requestRepository.save(request);
		return request;
	}

	@Override
	public void delete(Long id) {
		requestRepository.deleteById(id);
	}
}
