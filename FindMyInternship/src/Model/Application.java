package Model;

public class Application {
	private int ID;	
	private int studID;
	private int OfferID;
	private String CoverLetter;
	private String Company;
	
	
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getCoverLetter() {
		return CoverLetter;
	}
	public void setCoverLetter(String coverLetter) {
		CoverLetter = coverLetter;
	}
	public int getStudID() {
		return studID;
	}
	public void setStudID(int studID) {
		this.studID = studID;
	}
	public int getOfferID() {
		return OfferID;
	}
	public void setOfferID(int offerID) {
		OfferID = offerID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}
