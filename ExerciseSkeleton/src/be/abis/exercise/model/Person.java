package be.abis.exercise.model;

import be.abis.exercise.exception.EmailNotCorrectException;
import be.abis.exercise.exception.PersonNotBornYetException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Objects;

public class Person implements Instructor, CourseParticipant, Comparable<CourseParticipant> {

	private static int counter = 0;

	private int personNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String password;
	private Company company;

	public Person(String firstName, String lastName, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		personNumber = ++counter;
	}

	public Person(String firstName, String lastName,LocalDate birthDate, Company company) {
		this(firstName, lastName, birthDate);
		this.company = company;
	}
	
	public Person(String firstName, String lastName, LocalDate birthDate, String email,
			String password, Company company) {
		this(firstName,lastName,birthDate, company);
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

	public Person(String firstName, String lastName, LocalDate birthDate, String email,
			String password) {
		this(firstName,lastName, birthDate);
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

    public Person() {

    }

	public boolean isEmailCorrect(){
		return email.matches("\\w*\\.?\\w{2,}@\\w+\\.\\w{2,3}");
	}


    public long calculateAge() throws PersonNotBornYetException {
		if (birthDate == null){throw new PersonNotBornYetException("Birth year is null");}
		long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
		if (age <= 0 ) {
			throw new PersonNotBornYetException("Person does not exist");
		} else {
			return age;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return firstName.equals(person.firstName) && lastName.equals(person.lastName) && birthDate.equals(person.birthDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate);
	}

	public int getPersonNumber() {
		return personNumber;
	}
	
	public void setPersonNumber(int personNumber) {
		this.personNumber=personNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		lastName = lName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static int getNumberOfPersons() {
		return counter;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
	


	public void teach(Course course) {
		System.out.println(this + " teaches " + course.getTitle());
	}

	public void attendCourse(Course course) {
		System.out.println(this + " follows " + course.getTitle());
	}
	
	@Override
	public int compareTo(CourseParticipant o) {
		return this.lastName.compareTo(((Person)o).lastName);
	}

    public static class FirstNameComparator implements Comparator<CourseParticipant>{
		@Override
		public int compare(CourseParticipant o1, CourseParticipant o2) {
			return ((Person)o1).getFirstName().compareToIgnoreCase(((Person)o2).getFirstName());
		}
    	 
    }
    
}