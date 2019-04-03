package com.shterneregen.listener;

import com.shterneregen.models.Request;
import com.shterneregen.repositories.RequestRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestListener {
    private static final Logger log = LogManager.getLogger(RequestListener.class);

    private final RequestRepository requestRepository;

    @Autowired
    public RequestListener(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void process(Request request) throws InterruptedException {
        Thread.sleep(1000);

        if (request != null) {
            Request oldRequest = requestRepository.getByRequestId(request.getRequestId());
            if (oldRequest != null) {
                log.info("Request with requestId = " + request.getRequestId() + " already registered!");
                return;
            }

            requestRepository.save(request);
        }
        log.info("Request processed!");
    }

}
