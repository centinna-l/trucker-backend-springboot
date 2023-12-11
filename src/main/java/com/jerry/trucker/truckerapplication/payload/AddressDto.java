package com.jerry.trucker.truckerapplication.payload;


import com.jerry.trucker.truckerapplication.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private long id;
    private String street1;
    private String street2;

    private String pincode;

    private String country;

    private String city;

    private long phoneNo;
}
