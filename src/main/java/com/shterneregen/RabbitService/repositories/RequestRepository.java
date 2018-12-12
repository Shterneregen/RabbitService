package com.shterneregen.RabbitService.repositories;

import com.shterneregen.RabbitService.models.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Yuriy on 11.12.2018.
 */
public interface RequestRepository extends CrudRepository<Request, Long> {

    @Query("select r from Request r where r.requestId = :requestId")
    public Request getByRequestId(@Param("requestId") String requestId);
}
