package me.springprojects.petshopbackend.services;

import me.springprojects.petshopbackend.entities.dto.PetDTO;
import me.springprojects.petshopbackend.exceptions.InvalidPetException;
import me.springprojects.petshopbackend.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class PetServiceTest {

    @Autowired
    private PetService petService;

    @MockBean
    private PetRepository petRepository;

    @Test
    public void addsPetCorrectly(){
        PetDTO petDTO = new PetDTO();
        petDTO.setName("Max");
        petDTO.setBreed("Golden Retriever");
        petDTO.setType("DOG");
        petDTO.setPrice(BigDecimal.valueOf(100));

        petService.addPet(petDTO);

        verify(petRepository, times(1)).save(any());
    }

    @Test
    public void throwsExceptionWhenIncorrectPetName(){
        PetDTO petDTO = new PetDTO();
        petDTO.setName("");
        petDTO.setBreed("Golden Retriever");
        petDTO.setType("DOG");
        petDTO.setPrice(BigDecimal.valueOf(100));

        assertThrows(InvalidPetException.class, () -> petService.addPet(petDTO));
    }

    @Test
    public void throwsExceptionWhenIncorrectPetBreed(){
        PetDTO petDTO = new PetDTO();
        petDTO.setName("Max");
        petDTO.setBreed("");
        petDTO.setType("DOG");
        petDTO.setPrice(BigDecimal.valueOf(100));

        assertThrows(InvalidPetException.class, () -> petService.addPet(petDTO));
    }

    @Test
    public void throwsExceptionWhenIncorrectPetType(){
        PetDTO petDTO = new PetDTO();
        petDTO.setName("Max");
        petDTO.setBreed("Golden Retriever");
        petDTO.setType("");
        petDTO.setPrice(BigDecimal.valueOf(100));

        assertThrows(InvalidPetException.class, () -> petService.addPet(petDTO));
    }

    @Test
    public void throwsExceptionWhenIncorrectPetPrice(){
        PetDTO petDTO = new PetDTO();
        petDTO.setName("Max");
        petDTO.setBreed("Golden Retriever");
        petDTO.setType("DOG");
        petDTO.setPrice(null);

        assertThrows(InvalidPetException.class, () -> petService.addPet(petDTO));
    }

}