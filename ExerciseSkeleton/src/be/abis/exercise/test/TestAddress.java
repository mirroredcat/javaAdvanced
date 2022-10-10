package be.abis.exercise.test;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestAddress {

    @BeforeEach
    void setup(){
    }

    @Test
    void beZipCodeIsCorrect() {
        Address a1 = new Address("Street", "21", "2140", "Antwerpen", "Belgium", "BE");

        assertDoesNotThrow(()-> a1.checkZipCode());
    }

    @Test
    void beZipCodeIsNotCorrect(){
        Address a2 = new Address("Street2", "2", "54748939", "Antwerpen", "Belgium", "BE");

        assertThrows( ZipCodeNotCorrectException.class, () -> a2.checkZipCode());
    }

    @Test
    void nlZipCodeIsCorrect(){
        Address a3 = new Address("Street3", "32", "1234 AB", "Utrecht", "Netherlands", "NL");

        assertDoesNotThrow(() -> a3.checkZipCode());
    }

    @Test
    void nlZipCodeIsNotCorrect(){
        Address a4 = new Address("Street4" , "67", "123 hg", "Utrecht", "Nederlands", "NL");

        assertThrows(ZipCodeNotCorrectException.class, () -> a4.checkZipCode());
    }

}
