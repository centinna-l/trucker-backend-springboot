package com.jerry.trucker.truckerapplication.payload;


import com.jerry.trucker.truckerapplication.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BolDto {
    private long id;
    private String bolId;

    private boolean status;
    private Order order;
}
