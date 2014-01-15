package Model;

public class Company {
	private String LastName;
	private String FirstName;
	private String Email;
	private String Company;
	private String Password;
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	private String Description;
	private int ID;
	private ProfilComp P;
	
	public ProfilComp getP() {
		return P;
	}
	public void setP(ProfilComp p) {
		P = p;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Company(String string, String string2, String string3,
			String string4, String string5,String string6) {
		this.LastName=string;
		this.FirstName=string2;
		this.Email=string3;
		this.Company=string4;
		this.Password=string5;
		this.Description=string6;
	}
	public Company() {
		// TODO Auto-generated constructor stub
	}
	public int getID() {
		return ID;
	}
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
		return Company;
	}
	public void setSchool(String comp) {
		Company = comp;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
