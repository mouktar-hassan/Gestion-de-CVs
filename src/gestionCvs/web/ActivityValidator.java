package gestionCvs.web;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import gestionCvs.entities.Nature;

@ManagedBean
public class ActivityValidator {
	
    private Integer idActivity;	

	@Size(min = 4, max = 100, message = "Intervale des caratères du titre: entre 4 et 100  !")
	private String title;

	@NotNull(message = "La saisie de l'année est obligatoir!")
	private Date year;

	@Size(min = 30, max = 1000, message = "Intervale des caratères de la description: entre 30 et 1000 !")
	private String description;

	@Pattern(regexp = "(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?", message = "Faut respeter la forme standard des sites web!")
	private String webAdress;
	
	private Nature nature;

	public Integer getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(Integer idActivity) {
		this.idActivity = idActivity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebAdress() {
		return webAdress;
	}

	public void setWebAdress(String webAdress) {
		this.webAdress = webAdress;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}
	
	
}
