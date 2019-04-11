package gestionCvs.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionCvs.entities.Activity;
import gestionCvs.entities.Person;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonManagerImpl implements PersonManager{
	
	@PersistenceContext(unitName = "cvUnit")
    EntityManager em;
	
	private Person person=new Person();
	@PostConstruct()
	public void begin() {
		System.out.println("Beginning " + this);
	}

	@PreDestroy
	public void end() {
		System.out.println("Ending " + this);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Person savePerson(Person p) {
		if( showPersonByEmail(p.getEmail()) == null ){
			if(p.getId()==null){
				em.persist(p);
			}else{
				em.merge(p);
			}
			return p;
		}else {
			return null;
		}	
	}

	@Override
	public Person login(String email, String password) throws NoResultException{
		Query query = null;
		query = em
				.createQuery(
						"SELECT loginperson FROM Person loginperson WHERE loginperson.email=:email AND loginperson.password=:password")
				.setParameter("email", email).setParameter("password", password);
		if (query.getResultList().size() == 1)
			person = (Person) query.getSingleResult();
		else
			logout();
		return person;
	}

	@Override
	public Person logout() {
		person=null;
		return person;
	}

	@Override
	public List<Person> findPersons() {
		return em.createQuery("Select p From Person p", Person.class)
                .getResultList();
	}

	@Override
	public Person findPerson(Person person) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.id=:id")
				.setParameter("id", person.getId());
		if (query.getResultList().size() == 0)
			return null;
		Person displayperson = (Person) query.getSingleResult();
		return displayperson;
	}

	@Override
	public List<Person> findByName(String name) throws NoResultException{
		Query query = null;		
		try {
			query = em.createQuery("SELECT DISTINCT p FROM Person p WHERE p.name LIKE :name")
				.setParameter("name", "%"+name+"%");

		} catch (NoResultException e) {
			return null;
		}
		if (query != null) {
			@SuppressWarnings("unchecked")
			List<Person> persons = query.getResultList();
			
			return persons;
		}
		return null;
	}

	@Override
	public List<Person> findByFirstname(String fname) {
		Query query = null;		
		try {
			query = em.createQuery("SELECT DISTINCT p FROM Person p WHERE p.firstname LIKE :fname")
				.setParameter("fname", "%"+fname+"%");

		} catch (NoResultException e) {
			return null;
		}
		if (query != null) {
			@SuppressWarnings("unchecked")
			List<Person> persons = query.getResultList();
			
			return persons;
		}
		return null;
	}
	
	@Override
	public void updatePerson(Person p) {
		if (em.find(Person.class, p.getId()) != null)
			em.merge(p);		
	}	

	@Override
	public void deletePerson(Person p) {
		person = em.find(Person.class, p.getId());
		em.remove(person);		
	}
	
	@Override
	public Person showPerson(Person person) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.idPerson = :id")
				.setParameter("id", person.getId());
		if (query.getResultList().size() == 0)
			return null;
		Person shownPerson = (Person) query.getSingleResult();
		return shownPerson;
	}

	@Override
	public Person showPersonByEmail(String email) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email")
				.setParameter("email", email);
		if (query.getResultList().size() == 0)
			return null;
		Person shownPerson = (Person) query.getSingleResult();
		return shownPerson;
	}

	@Override
	public List<Person> findMulticriteria(Person p) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email OR p.firstname LIKE :fname OR p.name LIKE :name OR p.birthday LIKE :birthday")
				.setParameter("email", p.getEmail())
				.setParameter("fname", "%"+p.getFirstname()+"%")
				.setParameter("name", p.getName())
				.setParameter("birthday", p.getBirthday());
		if (query.getResultList().size() == 0)
			return null;
		return query.getResultList();
	}

	@Override
	public Person findById(Person person) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.id=:id")
				.setParameter("id", person.getId());
		if (query.getResultList().size() == 0)
			return null;
		Person displayperson = (Person) query.getSingleResult();
		return displayperson;
	}

	@Override
	public List<Activity> showActivities(Person person) {
        Query query = null;
		
		if (person.getId() != null) {
			try {
				query = em.createQuery("SELECT a FROM Activity a WHERE a.person.idPerson = :id")
				.setParameter("id", person.getId());
			} catch (Exception e) {
			}
			if (query != null) {
				List<Activity> activities = query.getResultList();				
				return activities;
				
			}
		}
		return null;
	}

	

	

}
