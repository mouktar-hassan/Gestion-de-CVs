package gestionCvs.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.sun.istack.NotNull;



@Entity
public class Activity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id() @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Temporal(TemporalType.DATE)
	@Column
	@NotNull
	private Date year;
	
	@Column
	@NotNull
	private Nature nature;
	@Column
	@NotNull
	private String title;
	@NotNull
	private String description;
	@Column
	private String webAdress;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    @ForeignKey(name="FK_PERSON_ACTIVITY")
    private Person person;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	

	
	
	

}
