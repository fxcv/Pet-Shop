package me.springprojects.petshopbackend.services;

import lombok.AllArgsConstructor;
import me.springprojects.petshopbackend.entities.Pet;
import me.springprojects.petshopbackend.entities.dto.PetDTO;
import me.springprojects.petshopbackend.entities.enums.*;
import me.springprojects.petshopbackend.exceptions.InvalidPetException;
import me.springprojects.petshopbackend.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public void addPet(PetDTO petDTO){
        String petName = petDTO.getName();
        PetType petType = valueOfPetType(petDTO.getType());
        String petBreed = petDTO.getBreed();
        BigDecimal petPrice = petDTO.getPrice();

        if(petName == null || petName.length()<3) throw new InvalidPetException("Please provide a pet's name of the minimum length: 3");
        else if(petBreed == null || petBreed.length()<4) throw new InvalidPetException("Please provide a correct pet's breed");
        else if(petPrice == null || petPrice.intValue()<10) throw new InvalidPetException("Please provide pet's price higher than 10.0");

        Pet pet = new Pet();
        pet.setName(petName);
        pet.setBreed(petBreed);
        pet.setType(petType);
        pet.setPrice(petPrice);

        petRepository.save(pet);
    }

    public List<PetDTO> fetchAllPets(){
        return petRepository.findAll().stream()
                                      .map(pet -> {
                                          PetDTO petDTO = new PetDTO();
                                          petDTO.setName(pet.getName());
                                          petDTO.setBreed(pet.getBreed());
                                          petDTO.setType(pet.getType().toString());
                                          petDTO.setPrice(pet.getPrice());
                                          return petDTO;
                                      })
                                      .collect(Collectors.toList());
    }

    public PetDTO getPetById(int id){
        return petRepository.findById(id)
                            .map(pet -> {
                                PetDTO petDTO = new PetDTO();
                                petDTO.setName(pet.getName());
                                petDTO.setBreed(pet.getBreed());
                                petDTO.setType(pet.getType().toString());
                                petDTO.setPrice(pet.getPrice());
                                return petDTO;
                            })
                            .orElseThrow(() -> new InvalidPetException("Pet not found"));
    }

    private PetType valueOfPetType(String s){
        try{
            if(s==null) throw new InvalidPetException("Please provide pet's type");
            return PetType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidPetException("Please provide correct pet's type");
        }
    }
}
