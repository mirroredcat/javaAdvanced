package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FilePersonRepository implements PersonRepository{

    private List<Person> allPersons = new ArrayList<Person>();
    private static final String FILELOCATION = "ExerciseSkeleton/src/be/abis/exercise/resources/persons.csv";
    private Logger exceptionLogger = LogManager.getLogger("exceptionLogger");
    private static final Logger log = LogManager.getLogger(FilePersonRepository.class.getName());

    public FilePersonRepository() {

        this.readFile();

    }

    public void readFile() {

        if (allPersons.size() != 0) allPersons.clear();
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(FILELOCATION));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines) {
                Person p = convertToPersonObj(line);

                allPersons.add(p);
            }
        }


    public static Person convertToPersonObj(String attributes) {
        String[] vals = attributes.split(";");
        if (!vals[0].equals("")) {
            Address a = new Address();
            a.setStreet(!vals[7].equals("null") ? vals[7] : null);
            a.setNr(!vals[8].equals("null") ? vals[8] : null);
            a.setZipCode(!vals[9].equals("null") ? vals[9] : null);
            a.setTown(!vals[10].equals("null") ? vals[10] : null);
            a.setCountry(!vals[11].equals("null") ? vals[11] : null);
            a.setCountryCode(!vals[12].equals("null") ? vals[12] : null);

            Company c = new Company();
            c.setName(!vals[6].equals("null") ? vals[6] : null);
            c.setAddress(a);

            Person p = new Person();
            p.setPersonNumber(!vals[0].equals("null") ? Integer.parseInt(vals[0]) : 0);
            p.setFirstName(!vals[1].equals("null") ? vals[1] : null);
            p.setLastName(!vals[2].equals("null") ? vals[2] : null);

            p.setBirthDate(!vals[3].equals("null") ? LocalDate.parse(vals[3], DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null);
            p.setEmail(!vals[4].equals("null") ? vals[4] : null);
            p.setPassword(!vals[5].equals("null") ? vals[5] : null);

            if (c.getName() != null) p.setCompany(c);
            return p;
        }
        return null;
    }

    public static String convertPersonToString(Person p){
        StringBuilder sb = new StringBuilder();
        Company c = p.getCompany();
        sb.append(p.getPersonNumber()).append(";")
                .append(p.getFirstName()).append(";")
                .append(p.getLastName()).append(";")
                .append(!(p.getBirthDate()==null)? p.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")):"null").append(";")
                .append(!(p.getEmail()==null)?p.getEmail():"null").append(";")
                .append(!(p.getPassword()==null)?p.getPassword():"null").append(";")
                .append(!(c==null)? p.getCompany().getName():"null").append(";")
                .append(!(c==null)? p.getCompany().getAddress().getStreet(): "null").append(";")
                .append(!(c==null)? p.getCompany().getAddress().getNr():"null").append(";")
                .append(!(c==null)? p.getCompany().getAddress().getZipCode() : "null").append(";")
                .append(!(c==null)? p.getCompany().getAddress().getTown() : "null").append(";")
                .append(!(c==null)? p.getCompany().getAddress().getCountry():"null").append(";")
                .append(!(c==null)? p.getCompany().getAddress().getCountryCode():"null");
      return sb.toString();
    }

    public void addPerson(Person p){
        allPersons.add(p);
        log.info("A new person, "+ p.getFirstName() + " "+ p.getLastName() + ", has been added.");
    }

    @Override
    public List<Person> getPersons() {
        return allPersons;
    }

    @Override
    public void writeAllPersonsToFile(String fileAddress){
        try(PrintWriter pw = new PrintWriter(fileAddress)){
            for (Person p : allPersons){
                String sb = convertPersonToString(p);
                pw.println(sb.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person findPersonById(int id) throws PersonNotFoundException {
        Person foundPerson = allPersons.stream()
                .filter(person -> person.getPersonNumber() == id)
                .findFirst().orElseThrow(() -> {
                    exceptionLogger.error("Person with id " + id + " not found.");
                    return new PersonNotFoundException("Person with id " + id + " not found.");

                });
        return foundPerson;
    }

    @Override
    public Person findPerson(String email, String password) throws PersonNotFoundException {
        return allPersons.stream()
                .filter((p -> p.getEmail().equals(email) && p.getPassword().equals(password)))
                .findFirst().orElseThrow( () -> {
                    exceptionLogger.error("Person with email " + email + " was not found or pass did not match.");
                    return new PersonNotFoundException("Person with email " + email + " was not found or password did not match");
                });
    }


}
