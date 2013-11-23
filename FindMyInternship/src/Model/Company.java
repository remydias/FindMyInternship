package Model;

public class Company {
	private String lastName;
	private String firstName;
	private String Email;
	private String Company;
	private String Password;
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	private int ID;

	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	
	public Company(String lastName, String firstName, String email,
			String company,String pwd) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		Email = email;
		Company = company;
		this.Password=pwd;
	}
	public Company() {
		// TODO Auto-generated constructor stub
	}

}
