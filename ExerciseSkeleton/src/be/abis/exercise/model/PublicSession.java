package be.abis.exercise.model;


import be.abis.exercise.exception.InvoiceException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class PublicSession extends Session {
	
	public final static Company ABIS = new Company("Abis");
	private ArrayList<CourseParticipant> enrolments = new ArrayList<CourseParticipant>();

	public PublicSession(Course course, LocalDate date, Company location,
			Instructor instructor) {
		super(course, date, location, instructor);
	}

	@Override
	public Company getOrganizer() {
		return ABIS;
	}

	public ArrayList<CourseParticipant> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(ArrayList<CourseParticipant> enrolments) {
		this.enrolments = enrolments;
	}

	@Override
	public double invoice() throws InvoiceException {
		System.out.println("Invoice in PublicSession");
		return 500;
	}

	public String calculateRevenue(){

		BigDecimal dailyPrice = new BigDecimal(this.getCourse().getDailyPrice());
		BigDecimal total = BigDecimal.ONE;
		BigDecimal twentyONE = new BigDecimal("21");
		BigDecimal oneHundred = new BigDecimal("100");
		BigDecimal enrolmentsNr = new BigDecimal(this.enrolments.size());
		BigDecimal nrOfDays = new BigDecimal(this.getCourse().getDays());
		total = total.multiply(enrolmentsNr).multiply(nrOfDays).multiply(dailyPrice);
		BigDecimal rev = total.subtract(total.multiply(twentyONE.divide(oneHundred)));

		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("nl", "BE"));
		nf.setGroupingUsed(false);
		return nf.format(rev).replaceFirst("\\u00A0", "");
	}

	public void addEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			this.addEnrolment(c);
	}

	protected void addEnrolment(CourseParticipant cp) {
		if (!enrolments.contains(cp)) {
			enrolments.add(cp);
			System.out.println("Enrolment added to the list, now "
					+ enrolments.size() + " enrolments.");
			System.out.println("enrollee is: " + cp);
		} else {
			System.out.println("Couldn't add " + cp + " as enrollee, since he was already enrolled");
		}
	}

	public void removeEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			removeEnrolment(c);
	}

	protected void removeEnrolment(CourseParticipant cp) {
		if (enrolments.contains(cp)) {
			enrolments.remove(cp);
			System.out.println("Enrollee " + cp + " removed from the list, now "
					+ enrolments.size() + " enrolments.");
		} else {
			System.out.println("Couldn't remove enrolment.");
		}
	}
	
	public Iterator<CourseParticipant> getEnrolmentsIterator(){
		return enrolments.iterator();
	}
	
	

	
}