package com.shterneregen.repositories;

import com.shterneregen.models.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RequestRepository extends CrudRepository<Request, Long> {

    @Query("select r from Request r where r.requestId = :requestId")
    public Request getByRequestId(@Param("requestId") String requestId);
}
