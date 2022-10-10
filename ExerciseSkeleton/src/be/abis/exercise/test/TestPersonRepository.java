package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestPersonRepository {

    PersonRepository repo = null;

    @BeforeEach
    public void setUp() throws IOException {
        repo = new FilePersonRepository();
    }

    @Test
    public void koenIsFoundById() throws PersonNotFoundException {
        assertEquals("Koen", repo.findPersonById(2).getFirstName());
    }

    @Test
    public void idIsNotFound() {
        assertThrows(PersonNotFoundException.class, () -> repo.findPersonById(25) );
    }

    @Test
    public void michelIsFoundByEmailAndPass() throws PersonNotFoundException {
        assertEquals("Michel", repo.findPerson("michel.dupont@bnpparibasfortis.com", "somepass5").getFirstName());
    }

    @Test
    public void michelEmailAndPassDontMatch() {
        assertThrows(PersonNotFoundException.class, () -> repo.findPerson("michel.dupont@bnpparibasfortis.com", "whatever123"));
    }
}
