package com.shterneregen.RabbitService.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yuriy on 11.12.2018.
 */
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

    public Long getId() {
        return id;
    }

    public String getRequestId() {
        return requestId;
    }
}
