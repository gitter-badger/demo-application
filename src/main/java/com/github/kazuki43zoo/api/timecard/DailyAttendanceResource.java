package com.github.kazuki43zoo.api.timecard;

import java.io.Serializable;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.kazuki43zoo.infra.jackson.map.EmptyStringSerialilzer;

@lombok.Data
@lombok.EqualsAndHashCode(callSuper = true)
@lombok.ToString(callSuper = true)
public class DailyAttendanceResource extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate targetDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime beginTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime finishTime;

    private boolean paidLeave;

    @JsonSerialize(nullsUsing = EmptyStringSerialilzer.class)
    private String specialWorkCode;

    @JsonSerialize(nullsUsing = EmptyStringSerialilzer.class)
    private String note;

    @JsonSerialize(nullsUsing = EmptyStringSerialilzer.class)
    private String workPlaceUuid;

    private int actualWorkingMinute;
    private int compensationMinute;
    private int midnightWorkingMinute;
    private boolean tardyOrEarlyLeaving;
    private boolean absence;
    private boolean holiday;

}
