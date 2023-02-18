package me.springprojects.petshopbackend.entities.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PetDTO {

    private String name;
    private String breed;
    private String type;
    private BigDecimal price;
}
