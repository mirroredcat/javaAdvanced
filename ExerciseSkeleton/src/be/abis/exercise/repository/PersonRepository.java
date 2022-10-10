package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getPersons();

    void writeAllPersonsToFile(String fileAddress);

    Person findPersonById(int id) throws PersonNotFoundException;
    Person findPerson(String email, String password) throws PersonNotFoundException;

}
