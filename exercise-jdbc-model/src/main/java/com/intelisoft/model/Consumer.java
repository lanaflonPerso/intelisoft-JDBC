package com.intelisoft.model;

import java.sql.Date;
import java.util.List;

public final class Consumer extends Model {

	private String firstName;

	private String lastName;

	private Date birthDate;

	private String country;

	private String city;

	private List<Car> carList;

	public Consumer() {
	}

	public Consumer(String firstName, String lastName, Date birthDate, String country, String city, List<Car> carList) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.country = country;
		this.city = city;
		this.carList = carList;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Consumer [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", country=");
		builder.append(country);
		builder.append(", city=");
		builder.append(city);
		builder.append(", carList=");
		builder.append(carList);
		builder.append("]");
		return builder.toString();
	}
}
