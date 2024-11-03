package com.automation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CreateBookingResponsePojo {
    int bookingid;
    CreateBookingRequestPojo booking;
}
