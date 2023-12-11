package com.jerry.trucker.truckerapplication.payload;


import com.jerry.trucker.truckerapplication.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private long id;
    private Date pickupDate;
    private Date dropoffDate;
    private Address pickupLocation;
    private Address dropoffLocation;
    private UserDto userDto;
    private Status status;
}
