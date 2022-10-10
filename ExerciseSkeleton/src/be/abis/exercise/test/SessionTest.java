package be.abis.exercise.test;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.model.PublicSession;
import be.abis.exercise.repository.FileCompanyRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class SessionTest {

    @Test
    public void messageIsInRo(){
        PublicSession ps = new PublicSession(Course.JAVA_PROGRAMMING, LocalDate.of(2022,6,7),
                new FileCompanyRepository().getCompanies().get(2), new Person("Helen", "Smith", LocalDate.of(1990,1,23)));

        assertEquals("Sesiunea", ps.toString(new Locale("ro")).substring(0,8));
    }
}
