package random.shterneregen.rabbitmqdemo.repositories;

import random.shterneregen.rabbitmqdemo.models.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RequestRepository extends CrudRepository<Request, Long> {

	@Query("select r from Request r where r.requestId = :requestId")
	Request getByRequestId(@Param("requestId") String requestId);
}
