package random.shterneregen.rabbitmqdemo.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("EN")
public class ErrorNotification extends Request {

	@Column(name = "error_msg")
	private String errorMsg;
	@Column(name = "error_time")
	private Date errorTime;

	public ErrorNotification() {
	}

	public ErrorNotification(String requestId) {
		super(requestId);
	}

	public ErrorNotification(String requestId, String errorMsg, Date errorTime) {
		super(requestId);
		this.errorMsg = errorMsg;
		this.errorTime = errorTime;
	}
}
