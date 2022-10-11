package be.abis.exercise.test;


import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main3 {

    public static void main(String[] args) {


        PersonRepository pr = new FilePersonRepository();

        List<Person> people = pr.getPersons();

        Person p = new Person("John", "Smith", LocalDate.of(1990,12,2));
        ((FilePersonRepository) pr).addPerson(p);
    }

}
