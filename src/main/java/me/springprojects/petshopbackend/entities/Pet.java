package me.springprojects.petshopbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.springprojects.petshopbackend.entities.enums.PetType;

import java.math.BigDecimal;

@Entity
@Table(name = "pets")
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String breed;
    @Enumerated(EnumType.STRING)
    private PetType type;
    private BigDecimal price;
}
