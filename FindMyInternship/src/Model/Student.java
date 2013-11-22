package Model;

public class Student {
	private String lastName;
	private String firstName;
	private String Email;
	private String School;
	private int ID;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Student(String lastName, String firstName, String email,
			String school) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		Email = email;
		School = school;
	}
	public Student() {
		super();
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
}
