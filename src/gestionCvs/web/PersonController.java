package gestionCvs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import gestionCvs.beans.ConnectManager;
import gestionCvs.beans.PersonManager;
import gestionCvs.entities.Activity;
import gestionCvs.entities.Nature;
import gestionCvs.entities.Person;

/*
 * 
 * Cette classe permet de controler et intéreagir entre les formulaires
 */
@ManagedBean
@SessionScoped
public class PersonController {

	@EJB
	PersonManager pm;

	@EJB
	ConnectManager cm;

	Person person, person2, person3;

	private List<Person> listPersons;

	private Person personConnected = new Person();

	private List<Activity> activities;
	private static List<Activity> activityTrouve;

	private ActivityValidator activityValidator = new ActivityValidator();

	private List<Nature> nature = new ArrayList<Nature>();

	private Activity activity;

	private PersonValidator personValidator = new PersonValidator();

	List<Person> listeOfPersonsFound;

	public List<Activity> getActivityTrouve() {
		return activityTrouve;
	}

	public void setActivityTrouve(List<Activity> activityTrouve) {
		this.activityTrouve = activityTrouve;
	}

	public List<Person> getListeOfPersonsFound() {
		return listeOfPersonsFound;
	}

	public void setListeOfPersonsFound(List<Person> listeOfPersonsFound) {
		this.listeOfPersonsFound = listeOfPersonsFound;
	}

	public Person getOtherPerson() {
		return otherPerson;
	}

	public void setOtherPerson(Person otherPerson) {
		this.otherPerson = otherPerson;
	}

	Person otherPerson = new Person();

	public PersonManager getPm() {
		return pm;
	}

	public void setPm(PersonManager pm) {
		this.pm = pm;
	}

	public ConnectManager getCm() {
		return cm;
	}

	public void setCm(ConnectManager cm) {
		this.cm = cm;
	}

	public Person getPersonConnected() {
		return personConnected;
	}

	public void setPersonConnected(Person personConnected) {
		this.personConnected = personConnected;
	}

	public ActivityValidator getActivityValidator() {
		return activityValidator;
	}

	public void setActivityValidator(ActivityValidator activityValidator) {
		this.activityValidator = activityValidator;
	}

	public List<Nature> getNature() {
		return nature;
	}

	public void setNature(List<Nature> nature) {
		this.nature = nature;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public PersonValidator getPersonValidator() {
		return personValidator;
	}

	public void setPersonValidator(PersonValidator personValidator) {
		this.personValidator = personValidator;
	}

	@PostConstruct
	public void init() {
		/*
		 * //Intialisation de la base //initialisation des certains personnes person=new
		 * Person(); person.setName("Sara"); person.setFirstname("BOUSTANI");
		 * person.setBirthday(new Date(11/02/1994));
		 * person.setEmail("sara.boustani@etu.univ-amu.fr");
		 * person.setWebsite("www.sara.fr"); person.setPassword("ss");
		 * 
		 * person2=new Person(); person2.setName("EL GHARNAJI");
		 * person2.setFirstname("Yasir"); person2.setBirthday(new Date(24/01/1994));
		 * person2.setEmail("yasir.el-gharnaji@etu.univ-amu.fr");
		 * person2.setWebsite("www.yasir.fr"); person2.setPassword("yy");
		 * 
		 * person3=new Person(); person3.setName("HASSAN");
		 * person3.setFirstname("Mouktar"); person3.setBirthday(new Date(19/8/1994));
		 * person3.setEmail("mouktar.HASSAN-FARAH@etu.univ-amu.fr");
		 * person3.setWebsite("www.mouktar.fr"); person3.setPassword("mm");
		 * 
		 * //ajout des personnes pm.savePerson(person); pm.savePerson(person2);
		 * pm.savePerson(person3);
		 */

		// initialisation de la liste Nature
		nature.add(Nature.AUTRE);
		nature.add(Nature.EXPERIENCE_PROFESSIONNELLE);
		nature.add(Nature.FORMATION);

		activityValidator.setIdActivity(null);
		activityValidator.setNature(null);
		activityValidator.setTitle(null);
		activityValidator.setDescription(null);
		activityValidator.setYear(null);

	}

	// Information pour la personne
	private String name;
	private String firstname;
	private String email;
	private String website;
	private Date birthday;
	private String password;
	private String passwordConfirmation;

	// Getters et Setters pour la personne
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

	// Méthode pour inscrire une personne
	public void save() {

		person = new Person();
		person.setName(name);
		person.setFirstname(firstname);
		person.setEmail(email);
		person.setWebsite(website);
		person.setBirthday(birthday);
		person.setPassword(password);
		Person ppp = pm.savePerson(person);
		
		if (ppp != null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Vous êtes maintenant inscrit M." + firstname + " " + name));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email existe deja !! "));

		}

	}

	// Méthode pour afficher la liste des personnes en base
	public List<Person> getListPersons() {
		return pm.findPersons();
	}

	// Méthode pour afficher la liste des activités
	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	// Méthode pour la connexion
	public String login() {
		FacesMessage message = null;
		FacesContext context = FacesContext.getCurrentInstance();

		if ((email != null) && (password != null) && (cm.login(email, password) != null)) {
			personConnected = cm.login(email, password);
			activities = cm.showActivities(personConnected);
			personConnected.setActivities(activities);

			// On va stocker la session pour réutiliser
			context.getExternalContext().getSessionMap().put("personSession", personConnected);

			return "profil";
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Accès refusé",
					"Veuillez bien vérifier votre email ou mot de passe");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}

	// Méthode pour ajouter une activité
	public void addActivity() {

		Activity activity = new Activity();
		if (activityValidator.getIdActivity() == null) {
			activity.setTitle(activityValidator.getTitle());
			activity.setDescription(activityValidator.getDescription());
			activity.setNature(activityValidator.getNature());
			activity.setYear(activityValidator.getYear());
			activity.setWebAdress(activityValidator.getWebAdress());
			activity.setPerson(personConnected);
			cm.createActivity(activity);
			// Pour rafraichir l'affichage des activités
			activities = cm.showActivities(personConnected);

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Activité ajouté", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	// Méthode pour afficher les infos des activités aux saisies de la formulaire
	// pour qu'on puisse changer la saisie de la formulaire
	public String showActivity(Activity activity) {

		this.activity = activity;
		this.activityValidator.setIdActivity(this.activity.getId());
		this.activityValidator.setNature(this.activity.getNature());
		this.activityValidator.setTitle(this.activity.getTitle());
		this.activityValidator.setYear(this.activity.getYear());
		this.activityValidator.setDescription(this.activity.getDescription());
		this.activityValidator.setWebAdress(this.activity.getWebAdress());
		return "modifyActivity";
	}

	public String modifyActivity() {

		Activity activity = new Activity();
		if ((activityValidator.getIdActivity() != null)) {
			activity.setId(activityValidator.getIdActivity());
			activity.setNature(activityValidator.getNature());
			activity.setTitle(activityValidator.getTitle());
			activity.setWebAdress(activityValidator.getWebAdress());
			activity.setYear(activityValidator.getYear());
			activity.setDescription(activityValidator.getDescription());
			activity.setPerson(personConnected);

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Activité modifiée avec succès!", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);

			cm.updateActivity(activity);

		}

		return "myCvConsult";
	}

	// Méthode pour supprimer l'activité
	public String deleteActivity(Activity activity) {
		cm.deleteActivity(activity);
		// Pour rafraichir l'affichage des activités
		activities = cm.showActivities(personConnected);
		return "myCvConsult";

	}

	// // Méthode pour modifier son profil
	// public String modifyProfil() {
	//
	// Person person = new Person();
	//
	// // pour récuperer l'id de la personne a modifier
	// person.setId(personConnected.getId());
	//
	// person.setName(person.getName());
	// person.setFirstname(person.getFirstname());
	// person.setBirthday(person.getBirthday());
	// person.setEmail(person.getEmail());
	// person.setWebsite(person.getWebsite());
	// person.setPassword(person.getPassword());
	//
	// pm.updatePerson(person);
	// this.personConnected = person;
	//
	// return "profil";
	// }

	// Méthode pour chercher une personne
	public String findPersonByEmail() {
		setOtherPerson(pm.showPersonByEmail(email));
		return "resultSerchPersonByEmail";
	}

	// Méthode pour chercher une personne
	public String findPersonByEmailConnected() {
		setOtherPerson(pm.showPersonByEmail(email));
		return "resultSerchPersonByEmailConnected";
	}

	// Méthode pour chercher une personne
	public String findPersonByName() {
		setListeOfPersonsFound(pm.findByName(name));
		return "resultSerchPersonByName";
	}

	// Méthode pour chercher une personne
	public String findPersonByNameConnected() {
		setListeOfPersonsFound(pm.findByName(name));
		return "resultSerchPersonByNameConnected";
	}

	// Méthode le cv
	public String seeCV(Person person) {
		setOtherPerson(pm.findById(person));
		return "seeCvOtherPerson";
	}

	// Méthode le cv
	public String seeCVConnected(Person person) {
		setOtherPerson(pm.findById(person));
		// setActivityTrouve(pm.showActivities(person));
		// activities = pm.showActivities(person);
		return "seeCvOtherPersonConnected";
	}

	// Méthode pour modifier son profil
	public String modifyProfil() {
		pm.updatePerson(personConnected);
		return "profil";
	}

	// Méthode pour modifier son profil
	public String deleteProfil() {
		pm.deletePerson(personConnected);
		setPersonConnected(cm.logout());
		return "accueil";
	}

	// Méthode pour déconnecter
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		personConnected = null;
		person = null;
		setPersonConnected(cm.logout());
		return "accueil";
	}

}
