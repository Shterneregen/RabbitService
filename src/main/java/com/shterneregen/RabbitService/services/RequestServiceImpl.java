package com.shterneregen.RabbitService.services;

import com.shterneregen.RabbitService.models.Request;
import com.shterneregen.RabbitService.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy on 11.12.2018.
 */
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

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
