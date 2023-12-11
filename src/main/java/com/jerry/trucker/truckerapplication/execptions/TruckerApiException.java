package com.jerry.trucker.truckerapplication.execptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class TruckerApiException extends RuntimeException {

    private HttpStatus status;
    private String message;
}
