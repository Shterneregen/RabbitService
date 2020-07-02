package random.shterneregen.rabbitmqdemo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("SR")
public class SupportRequest extends Request {

	@Column(name = "description")
	private String description;
	@Column(name = "department")
	private String department;

	public SupportRequest() {
	}

	public SupportRequest(String requestId) {
		super(requestId);
	}

	public SupportRequest(String requestId, String description, String department) {
		super(requestId);
		this.description = description;
		this.department = department;
	}
}
