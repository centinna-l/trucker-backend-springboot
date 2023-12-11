package com.jerry.trucker.truckerapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    private long id;
    private String pickupDate;
    private String dropoffDate;
}
