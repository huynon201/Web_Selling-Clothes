package Model;

import java.util.Arrays;

public class Brand {
	private int id;
	private String name;
	private String des;
	private byte[] image;
	private String country;
	private String website;
	private String contact_email;
	private String contact_phone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", des=" + des + ", image=" + Arrays.toString(image)
				+ ", country=" + country + ", website=" + website + ", contact_email=" + contact_email
				+ ", contact_phone=" + contact_phone + "]";
	}
	
}
