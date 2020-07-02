package random.shterneregen.rabbitmqdemo.models;

import lombok.Data;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "REQUEST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public class Request implements Serializable {

	@Id
	@GeneratedValue
	protected Long id;
	protected String requestId;

	public Request() {
	}

	public Request(String requestId) {
		this.requestId = requestId;
	}
}
