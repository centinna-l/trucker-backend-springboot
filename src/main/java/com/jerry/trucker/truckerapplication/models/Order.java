package com.jerry.trucker.truckerapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pickup_date")
    private Date pickupDate;

    @Column(name = "dropoff_date")
    private Date dropoffDate;

    @ManyToOne
    @JoinColumn(name = "pickup_location_id")
    private Address pickupLocation;

    @ManyToOne
    @JoinColumn(name = "dropoff_location_id")
    private Address dropoffLocation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "bol_id")
    private Bol bol;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    // Getters and setters, other methods...
}
