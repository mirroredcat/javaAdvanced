package be.abis.exercise.model;

import be.abis.exercise.exception.ZipCodeNotCorrectException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {
	
	private String street;
	private String nr;
	private String zipCode;
	private String town;
	private String country;
	private String countryCode;
	
	public Address(){
		
	}
	
	public Address(String street, String nr, String zipCode, String town, String country, String countryCode) {
		this();
		this.street = street;
		this.nr = nr;
		this.zipCode = zipCode;
		this.town = town;
		this.country = country;
		this.countryCode = countryCode;
	}

	public void checkZipCode() throws ZipCodeNotCorrectException {
		if(this.countryCode==null || this.zipCode == null){
			throw new ZipCodeNotCorrectException("Country or zip code is null");
		}

		if (this.countryCode.equals("BE")){
			Pattern p = Pattern.compile("[1-9]\\d{3}");
			Matcher m = p.matcher(this.zipCode);
			if (!m.matches()) throw new ZipCodeNotCorrectException("Zip code for BE not correct");
		}

		if (this.countryCode.equals("NL")) {
			if (!this.zipCode.matches("[1-9]\\d{3}\\s?\\w{2}")) throw new ZipCodeNotCorrectException("Zip code for NL not correct");
		}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	public String toString(){
		return street + " " + nr + ", " + countryCode + " - " +zipCode+ " " + town;
	}
	

}
