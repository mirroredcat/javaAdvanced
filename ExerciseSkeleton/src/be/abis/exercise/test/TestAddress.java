package be.abis.exercise.test;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestAddress {


    @Test
    void beZipCodeIsCorrect() {
        Address a1 = new Address("Street", "21", "2140", "Antwerpen", "Belgium", "BE");

        assertDoesNotThrow(a1::checkZipCode);
    }

    @Test
    void beZipCodeIsTooLong(){
        Address a2 = new Address("Street2", "2", "54748939", "Antwerpen", "Belgium", "BE");

        assertThrows( ZipCodeNotCorrectException.class, a2::checkZipCode);
    }

    @Test
    void beZipCodeSTartsWith0 () {
        Address a3 = new Address("Street2", "2", "0214", "Antwerpen", "Belgium", "BE");
        assertThrows( ZipCodeNotCorrectException.class, a3::checkZipCode);
    }

    @Test
    void beZipCodeIsEmpty (){
        Address a4 = new Address("Street2", "2", "", "Antwerpen", "Belgium", "BE");
        assertThrows( ZipCodeNotCorrectException.class, a4::checkZipCode);
    }

    @Test
    void beZipCodeIsTooShort () {
        Address a5 = new Address("Street2", "2", "123", "Antwerpen", "Belgium", "BE");
        assertThrows( ZipCodeNotCorrectException.class, a5::checkZipCode);
    }

    @Test
    void beZipCodeHasLetters(){
        Address a6 = new Address("Street2", "2", "abcs", "Antwerpen", "Belgium", "BE");
        assertThrows( ZipCodeNotCorrectException.class, a6::checkZipCode);
    }

    @Test
    void beZipCodeIsNull(){
        Address a7 = new Address("Street2", "2", null, "Antwerpen", "Belgium", "BE");
        assertThrows( ZipCodeNotCorrectException.class, a7::checkZipCode);
    }

    //-----------------
    @Test
    void nlZipCodeIsCorrectWithSpace(){
        Address a = new Address("Street3", "32", "1234 AB", "Utrecht", "Netherlands", "NL");
        assertDoesNotThrow(a::checkZipCode);
    }

    @Test
    void nlZipCodeIsCorrectWithoutSpace(){
        Address a = new Address("Street3", "32", "1234AB", "Utrecht", "Netherlands", "NL");
        assertDoesNotThrow(a::checkZipCode);
    }


    @Test
    void nlZipCodeIsTooLong(){
        Address a = new Address("Street3", "32", "1234 ABC", "Utrecht", "Netherlands", "NL");
        assertThrows( ZipCodeNotCorrectException.class, a::checkZipCode);
    }

    @Test
    void nlZipCodeSTartsWith0 () {
        Address a = new Address("Street3", "32", "0234 ABC", "Utrecht", "Netherlands", "NL");
        assertThrows( ZipCodeNotCorrectException.class, a::checkZipCode);
    }

    @Test
    void nlZipCodeIsEmpty (){
        Address a = new Address("Street3", "32", "", "Utrecht", "Netherlands", "NL");
        assertThrows( ZipCodeNotCorrectException.class, a::checkZipCode);
    }

    @Test
    void nlZipCodeHasTooFewNumbers () {
        Address a = new Address("Street3", "32", "123 AB", "Utrecht", "Netherlands", "NL");
        assertThrows( ZipCodeNotCorrectException.class, a::checkZipCode);
    }

    @Test
    void nlZipCodeHasTooFewLetters () {
        Address a = new Address("Street3", "32", "123 A", "Utrecht", "Netherlands", "NL");
        assertThrows( ZipCodeNotCorrectException.class, a::checkZipCode);
    }

    @Test
    void nlZipCodeHasLettersInsteadOfNumbers(){
        Address a = new Address("Street2", "2", "abcd AB", "Antwerpen", "Belgium", "BE");
        assertThrows( ZipCodeNotCorrectException.class, a::checkZipCode);
    }

    @Test
    void nlZipCodeIsNull(){
        Address a7 = new Address("Street2", "2", null, "Antwerpen", "Belgium", "BE");
        assertThrows( ZipCodeNotCorrectException.class, a7::checkZipCode);
    }

}
