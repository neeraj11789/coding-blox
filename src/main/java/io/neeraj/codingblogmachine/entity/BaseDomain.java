package io.neeraj.codingblogmachine.entity;

import lombok.Data;

import java.util.UUID;

@Data
class BaseDomain {

    private long id;

    private String externalId = UUID.randomUUID().toString();

    // other fields like createdby, date etc
}
