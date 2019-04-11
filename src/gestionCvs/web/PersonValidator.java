package gestionCvs.web;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean
public class PersonValidator implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Size(min = 2, max = 30, message = "Intervale du nom: entre 2 et 30 caratères !")
	private String name;

	@Size(min = 2, max = 30, message = "Intervale de prénom: entre 2 et 30 caratères !")
	private String firstname;

	
	private String email;

	@Pattern(regexp = "(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?", message = "Faut respecter la forme standard des sites web")
	private String website;

	@Past
	private Date birthday;

	@Size(min = 3, max = 100, message = "Intervale de mot de passe: être entre 3 et 100 caratères !")
	private String password;
	
	@Size(min = 3, max = 100, message = "Intervale de la confirmation de mot de passe: entre 3 et 100 caratères !")
	private String passwordConfirmation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	

}
