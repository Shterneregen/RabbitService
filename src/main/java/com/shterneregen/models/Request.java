package com.shterneregen.models;

import lombok.Data;

import javax.persistence.*;
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
