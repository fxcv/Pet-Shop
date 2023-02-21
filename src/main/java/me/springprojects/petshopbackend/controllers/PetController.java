package me.springprojects.petshopbackend.controllers;

import lombok.AllArgsConstructor;
import me.springprojects.petshopbackend.entities.dto.PetDTO;
import me.springprojects.petshopbackend.services.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping(path = "/add")
    public void addPet(@RequestBody PetDTO petDTO){
        petService.addPet(petDTO);
    }

    @GetMapping(path = "/all")
    public List<PetDTO> fetchAllPets(){
        return petService.fetchAllPets();
    }

    @GetMapping(path = "/get")
    public PetDTO getPetById(@RequestParam(name = "id") int id){
        return petService.getPetById(id);
    }
}
