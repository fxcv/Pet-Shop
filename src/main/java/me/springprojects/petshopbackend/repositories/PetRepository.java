package me.springprojects.petshopbackend.repositories;

import me.springprojects.petshopbackend.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
