package com.crud.springcrud.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable

public class AddressModel implements Serializable {

    private String street;
    private String complement;
    private Integer number;
    private String city;
    private String zipcode;
    private String country;

}
