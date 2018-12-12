package com.shterneregen.RabbitService.services;

import com.shterneregen.RabbitService.models.Request;

import java.util.List;

/**
 * Created by Yuriy on 11.12.2018.
 */
public interface RequestService {

    List<Request> listAll();

    Request getById(Long id);

    Request getByRequestId(String requestId);

    Request saveOrUpdate(Request request);

    void delete(Long id);

}
