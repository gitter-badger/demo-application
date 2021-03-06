package com.github.kazuki43zoo.domain.model.account;

import java.io.Serializable;

import org.joda.time.DateTime;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
public class AccountPasswordLock implements Serializable {

    private static final long serialVersionUID = 1L;
    private String accountUuid;
    private int failureCount;
    private DateTime modifiedAt;

    public void countUpFailureCount() {
        failureCount++;
    }

}
