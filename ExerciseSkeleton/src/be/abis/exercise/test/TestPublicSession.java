package be.abis.exercise.test;

import be.abis.exercise.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TestPublicSession {

    private PublicSession ps;

    @Mock private Company c;
    @Mock private Instructor i;

    @Mock private Person p1;
    @Mock private Person p2;
    @Mock private Person p3;
    @Mock private Person p4;
    @Mock private Person p5;

    @BeforeEach
    public void setUp(){
        ps = new PublicSession(Course.JAVA_ADVANCED, LocalDate.of(2022,10,18), c,i);
    }

    @Test
    void addEnrolmentAddsOnePerson(){
        ps.addEnrolment(p1);
        assertEquals(ps.getEnrolments().size(),1);
        ps.addEnrolment(p2);
        ps.addEnrolment(p3);
        assertEquals(ps.getEnrolments().size(),3);
    }

    @Test
    void addEnrolmentAdds3People(){
        ps.addEnrolment(p1);
        assertEquals(ps.getEnrolments().size(),1);
        ps.addEnrolment(p2,p3,p4);
        assertEquals(ps.getEnrolments().size(),4);
    }

    @Test
    void revenueReturns3792(){
        ps.addEnrolment(p1,p2,p3,p4);
        assertEquals(ps.calculateRevenue(),"€3792,00");
    }

    @Test
    void removeEnrolmentRemovesOnePerson(){
        ps.addEnrolment(p1,p2,p3,p4);
        assertEquals(ps.getEnrolments().size(),4);
        ps.removeEnrolment(p2);
        assertEquals(ps.getEnrolments().size(),3);
    }

    @Test
    void removeEnrolmentRemoves2People(){
        ps.addEnrolment(p1,p2,p3,p4);
        assertEquals(ps.getEnrolments().size(),4);
        ps.removeEnrolment(p1,p2);
        assertEquals(ps.getEnrolments().size(),2);
    }

}
