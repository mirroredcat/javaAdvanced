package be.abis.exercise.test;

import be.abis.exercise.model.Person;
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

    @Test
    public void johnIS32YearsOld(){
        Person p1 = new Person("John", "Smith", LocalDate.of(1990, 12,4));
        assertEquals(31, p1.calculateAge());
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
