package be.abis.exercise.test;


import be.abis.exercise.exception.InvoiceException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.CompanySession;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Instructor;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

public class TestCompanySession {


    @Mock
    private Company comp;

    @Mock
    private Instructor instr;

    private CompanySession cs;
    private Course c1;

    @BeforeEach
    private void setUp(){
        c1 = Course.JAVA_PROGRAMMING;
        cs = new CompanySession(c1, LocalDate.of(2022,10,18),comp,instr,comp, 5 );
    }

    @Test
    void coursePriceShouldBe7500() throws InvoiceException {
        assertEquals(cs.invoice(), 7500.0);
    }

    @Test
    void coursePriceThrowsInvoiceExceptionTooLow(){
        Course c2 = Course.INTERNET_ENABLING;
        CompanySession cs2 = new CompanySession(c2,LocalDate.of(2022, 10, 2), comp, instr, comp, 1);
        assertThrows(InvoiceException.class, () -> cs2.invoice());
    }

    @Test
    void coursePriceThrowsInvoiceExceptionTooHigh(){
        cs.setNumberOfPersons(100);
        assertThrows(InvoiceException.class, () -> cs.invoice());
    }


}
