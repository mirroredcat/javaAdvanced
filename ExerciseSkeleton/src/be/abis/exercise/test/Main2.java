package be.abis.exercise.test;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) {

        BiFunction<Double, Integer, Double> reduceByAmount = (val,perc) -> (val - (val* perc/100.0));
        PersonRepository repo = new FilePersonRepository();

        System.out.println(reduceByAmount.apply(100.0, 10));
        System.out.println(reduceByAmount.apply(500.0, 50));

        System.out.println("----  remove persons not linked to company  ----");
        repo.getPersons().stream()
                .filter(p -> p.getCompany() != null)
                .forEach(System.out::println);

        System.out.println("\n----  all persons whose last name starts with s, alphabetically sort by first name, last name in caps  ----");
        repo.getPersons().stream()
                .filter(p -> p.getLastName().substring(0,1).equals("S"))
                .sorted(Comparator.comparing(Person::getFirstName))
                .forEach(p -> System.out.println(p.getFirstName() + " " + p.getLastName().toUpperCase()));

        System.out.println("\n----  list of distinct companies  ----");
        repo.getPersons().stream()
                .filter(p -> p.getCompany() != null )
                .map(p -> p.getCompany().getName())
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n----  How many ppl work in Leuven?  ----");
        long pplInLeuven = repo.getPersons().stream()
                .filter(p -> p.getCompany() != null)
                .filter(p -> p.getCompany().getAddress().getTown().equals("Leuven"))
                .count();
        System.out.println(pplInLeuven);

        System.out.println("\n----  Who is the youngest person?  ----");
        Person youngest = repo.getPersons().stream()
                .sorted(Comparator.comparing(Person::calculateAge))
                .findFirst().get();

        System.out.println(youngest);

        System.out.println("\n----  first person of unsorted list that's older than 50  ----");
        Person over50 = repo.getPersons().stream()
                .filter(p -> p.calculateAge() > 50)
                .findFirst().get();
        System.out.println(over50);

        System.out.println("\n----  group persons per company ----");
        Map<Object, List<Person>> pplByCompany = repo.getPersons().stream()
                .filter(p -> p.getCompany() != null)
                .collect(Collectors.groupingBy(p -> p.getCompany().getName()));

        pplByCompany.forEach((k,v) -> System.out.println(k + "\n" + v));

        System.out.println("\n----  Persons per company  ----");
        pplByCompany.forEach((k,v)-> System.out.println("Total people in " + k + ": " + v.size() + (v.size()>1?" people.":" person.")));

        System.out.println("\n----  Average age of everybody  ----");
        Double averageAge = repo.getPersons().stream()
                .collect(Collectors.averagingLong(p -> p.calculateAge()));

        System.out.println(averageAge);

        repo.getPersons().removeIf(p -> p.getCompany()==null);

        try(Writer out = new BufferedWriter(new FileWriter("personsfilterd.txt"))) {
            Files.lines(Paths.get("ExerciseSkeleton/src/be/abis/exercise/resources/persons.csv"))
                    .map(s -> FilePersonRepository.convertToPersonObj(s))
                    .filter(p -> p.getCompany()!=null)
                    .filter(p -> p.getCompany().getAddress().getCountryCode().equals("BE") && p.calculateAge()>40)
                    .forEach(p-> {
                        try {
                            out.append(FilePersonRepository.convertPersonToString(p) + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
