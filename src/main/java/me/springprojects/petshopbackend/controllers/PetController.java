package me.springprojects.petshopbackend.controllers;

import lombok.AllArgsConstructor;
import me.springprojects.petshopbackend.entities.dto.PetDTO;
import me.springprojects.petshopbackend.services.PetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/add")
    public void addPet(@RequestBody PetDTO petDTO){
        petService.addPet(petDTO);
    }
}
