package com.shterneregen.RabbitService.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by Yuriy on 11.12.2018.
 */
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Date errorTime) {
        this.errorTime = errorTime;
    }

    @Override
    public String toString() {
        return "ErrorNotification[" +
                "requestId=" + requestId
                + ", " + "errorMsg=" + errorMsg
                + ", " + "errorTime=" + errorTime
                + "]";
    }
}
