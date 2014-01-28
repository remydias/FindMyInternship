package Model;

import java.util.ArrayList;

public class Student {
	private int ID;
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	private String LastName;
	private String FirstName;
	private String Email;
	private String School;
	private String Password;
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public Student(String string, String string2, String string3,
			String string4, String string5) {
		this.LastName=string;
		this.FirstName=string2;
		this.Email=string3;
		this.School=string4;
		this.Password=string5;
	}
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
}
