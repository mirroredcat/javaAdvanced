package be.abis.exercise.test;

import be.abis.exercise.model.*;
import be.abis.exercise.repository.FileCompanyRepository;
import be.abis.exercise.repository.FilePersonRepository;
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



        PublicSession ps = new PublicSession(Course.JAVA_PROGRAMMING, LocalDate.of(2022,6,7),
                new FileCompanyRepository().getCompanies().get(2), new Person("Helen", "Smith", LocalDate.of(1990,1,23)));

        ps.addEnrolment(new Person("Mikael", "Akerfield", LocalDate.of(1978, 3,15)));
        ps.addEnrolment(new Person("John", "Smith", LocalDate.of(1990, 12,4)));
        ps.addEnrolment(new Person("Mary", "Poppins", LocalDate.of(1976, 2,2)));
        ps.addEnrolment(new Person("Jikk", "Saalti", LocalDate.of(1998, 3,7)));

        System.out.println(ps.calculateRevenue());
        System.out.println(ps);
        System.out.println(ps.toString(new Locale("ro")));
        System.out.println(ps.toString(new Locale("nl")));

    }
}
