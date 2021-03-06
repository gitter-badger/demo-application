package com.github.kazuki43zoo.api.date;

import java.io.Serializable;

import org.joda.time.DateTime;

@lombok.Data
public class DateResource implements Serializable {
    private static final long serialVersionUID = 1L;
    private DateTime dateTime;
}
