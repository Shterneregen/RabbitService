package random.shterneregen.rabbitmqdemo.services;

import random.shterneregen.rabbitmqdemo.models.Request;

import java.util.List;

public interface RequestService {

	List<Request> listAll();

	Request getById(Long id);

	Request getByRequestId(String requestId);

	Request saveOrUpdate(Request request);

	void delete(Long id);

}
