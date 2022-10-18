package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotBornYetException;
import be.abis.exercise.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


public class TestPerson {


    @Test
    public void p1IsEqualToP2(){
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,3));
        Person p2 = new Person("John", "Smith", LocalDate.of(1990, 12,3));
        assertEquals(p1,p2);
    }

    @Test
    public void p1IsNotEqualToP2() {
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4));
        Person p2 = new Person("John", "Smith", LocalDate.of(1990, 12,3));
        assertEquals(false, p1.equals(p2));
    }

    @Test
    public void samePersonIsNotAddedToHashSet(){
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4));
        Person p2 = new Person("John", "Smith", LocalDate.of(1990, 12,3));
        Person p3 = new Person("Mary", "Poppins", LocalDate.of(1976, 2,2));
        Person p4 = new Person("Mary", "Poppins", LocalDate.of(1976, 2,2));

        HashSet<Person> hsp = new HashSet<>();
        hsp.add(p1);
        hsp.add(p2);
        hsp.add(p3);
        hsp.add(p4);

        assertEquals(3, hsp.size());
    }

    @Tag("calculateAgeTest")
    @DisplayName("Testing if the age of John correctly comes out as 32")
    @Test
    public void johnIS32YearsOld() throws PersonNotBornYetException {
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4));
        assertEquals(31, p1.calculateAge());
    }

    @Tag("calculateAgeTest")
    @DisplayName("The age of John should not be negative")
    @Test
    public void johnIsNotBornYet(){
        Person p1 = new Person("John", "Smith", LocalDate.of(2022, 12,4));
        assertThrows(PersonNotBornYetException.class, p1::calculateAge);
    }

    @Tag("calculateAgeTest")
    @DisplayName("The age of John should not be null")
    @Test
    public void johnHasNullAge(){
        Person p1 = new Person("John", "Smith", null);
        assertThrows(PersonNotBornYetException.class, p1::calculateAge);
    }

    @Test
    public void emailIsCorrectWithoutDotInUsername(){
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4), "johnsmith@yahoo.com","1234pass" );
        assertTrue(p1::isEmailCorrect);
    }

    @Test
    public void emailIsCorrectWithDotInUsername(){
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4), "john.smith@yahoo.com","1234pass" );
        assertTrue(p1::isEmailCorrect);
    }

    @Test
    public void emailHasNoAtSymbol(){
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4), "johnsmith.yahoo.com","1234pass" );
        assertFalse(p1::isEmailCorrect);
    }

    @Test
    public void emailHasNoDomainExtension(){
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4), "johnsmith@yahoo","1234pass" );
        assertFalse(p1::isEmailCorrect);
    }

    @Test
    public void emailHasTooLongDomainExtension(){
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4), "johnsmith@yahoo.colorado","1234pass" );
        assertFalse(p1::isEmailCorrect);
    }

}
