package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.*;
import be.abis.exercise.repository.FileCompanyRepository;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
//import jdk.vm.ci.meta.Local;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) {

        /*
        LocalDate now = LocalDate.now();
        LocalDate calc = now.plusYears(3).plusMonths(2).plusDays(15);
        System.out.println("The date in 3 years, 2 months and 15 days will be: "+ calc.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        Locale locRO = new Locale("ro");
        LocalDate bday = LocalDate.of(1990,2,21);
        System.out.println("You were born on a " + bday.getDayOfWeek());

        LocalDate nextB = LocalDate.of(2023,2,21);
        Period diff = Period.between(now, nextB);
        System.out.println("Days till next bday: " + ChronoUnit.DAYS.between(now,nextB));
        System.out.println("You are " + ChronoUnit.DAYS.between(bday, now) + " days old");

        //TreeSet<String> zones = new TreeSet(ZoneId.getAvailableZoneIds());
        //System.out.println(zones);
        ZonedDateTime timeInCalcutta = ZonedDateTime.now(ZoneId.of("Asia/Calcutta"));
        System.out.println(timeInCalcutta.getHour() + ":" + timeInCalcutta.getMinute());
        System.out.println("time diff is " + Duration.between(LocalTime.now(), ZonedDateTime.now(ZoneId.of("Asia/Calcutta"))));


         */

        PersonRepository psr = new FilePersonRepository();


        PublicSession ps = new PublicSession(Course.JAVA_PROGRAMMING, LocalDate.of(2022,6,7),
                new FileCompanyRepository().getCompanies().get(1), new Person("Helen", "Smith", LocalDate.of(1990,1,23)));

        try {
            ps.addEnrolment(psr.findPersonById(1));
            ps.addEnrolment(psr.findPersonById(2));
            ps.addEnrolment(psr.findPersonById(3));
            ps.addEnrolment(psr.findPersonById(4));
            System.out.println(psr.findPersonById(1).isEmailCorrect());
        } catch (PersonNotFoundException e) {
            throw new RuntimeException(e);
        }

        //ps.printListOfParticipants();





    }
}
