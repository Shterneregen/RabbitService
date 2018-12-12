package com.shterneregen.RabbitService.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yuriy on 11.12.2018.
 */
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "ErrorNotification[" +
                "requestId=" + requestId
                + ", " + "description=" + description
                + ", " + "department=" + department
                + "]";
    }
}
