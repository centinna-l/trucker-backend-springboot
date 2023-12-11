package com.jerry.trucker.truckerapplication.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bols")
public class Bol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String bolId;

    private boolean status;

    @OneToOne(mappedBy = "bol", cascade = CascadeType.ALL)
    @JsonIgnore
    private Order order;
}
