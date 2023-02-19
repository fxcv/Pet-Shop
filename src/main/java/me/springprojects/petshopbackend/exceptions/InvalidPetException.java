package me.springprojects.petshopbackend.exceptions;

public class InvalidPetException extends RuntimeException{

    public InvalidPetException(String message){
        super(message);
    }
}
