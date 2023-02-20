package me.springprojects.petshopbackend.services;

import me.springprojects.petshopbackend.entities.Pet;
import me.springprojects.petshopbackend.entities.dto.PetDTO;
import me.springprojects.petshopbackend.entities.enums.PetType;
import me.springprojects.petshopbackend.exceptions.InvalidPetException;
import me.springprojects.petshopbackend.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

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

    @Test
    public void fetchesAllPets(){
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        pet1.setName("Max");
        pet2.setName("Mary");
        pet1.setBreed("Labrador");
        pet2.setBreed("Labrador");
        pet1.setType(PetType.DOG);
        pet2.setType(PetType.DOG);
        pet1.setPrice(BigDecimal.valueOf(150));
        pet2.setPrice(BigDecimal.valueOf(300));
        given(petRepository.findAll()).willReturn(List.of(pet1, pet2));

        List<PetDTO> pets = petService.fetchAllPets();

        PetDTO petDTO1 = pets.get(0);
        PetDTO petDTO2 = pets.get(1);
        assertEquals(pet1.getName(), petDTO1.getName());
        assertEquals(pet2.getName(), petDTO2.getName());
        assertEquals(pet1.getBreed(), petDTO1.getBreed());
        assertEquals(pet2.getBreed(), petDTO2.getBreed());
        assertEquals(pet1.getType().toString(), petDTO1.getType());
        assertEquals(pet2.getType().toString(), petDTO2.getType());
        assertEquals(pet1.getPrice(), petDTO1.getPrice());
        assertEquals(pet2.getPrice(), petDTO2.getPrice());
    }

}