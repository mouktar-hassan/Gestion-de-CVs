package gestionCvs.beans;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.NoResultException;

import gestionCvs.entities.Person;

@Local
public interface PersonManager {
	
	
	
	Person savePerson(Person p);
	Person login(String email, String password) throws NoResultException;
	Person logout();
	List<Person> findPersons();
	Person findPerson(Person person);	
	Person findById(Person person);	
	List<Person> findByName(String name) throws NoResultException;
	List<Person> findByFirstname(String fname);
	void updatePerson(Person p);		
	void deletePerson(Person p);
    Person showPerson(Person person);	
	Person showPersonByEmail(String email);
	List<Person> findMulticriteria(Person person) throws NoResultException;

}
