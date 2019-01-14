package gestionCvs.beans;

import java.util.List;

import javax.ejb.Stateful;
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

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConnectManagerImpl implements ConnectManager{
	
	@PersistenceContext(unitName = "cvUnit")
    EntityManager em;

	private Person pperson=new Person();
	
	@Override
	public Person login(String email, String password) throws NoResultException{
		Query query = null;
		query = em
				.createQuery(
						"SELECT loginperson FROM Person loginperson WHERE loginperson.email=:email AND loginperson.password=:password")
				.setParameter("email", email).setParameter("password", password);
		if (query.getResultList().size() == 1)
			pperson = (Person) query.getSingleResult();
		else
			logout();
		return pperson;
	}

	@Override
	public Person logout() {
		pperson=null;
		return pperson;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity createActivity(Activity activity) {
		em.persist(activity);
		return this.findActivity(activity);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity updateActivity(Activity activity) {
		return em.merge(activity);
	}

	@Override
	public Activity findActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getId());
		return foundActivity;
	}

	@Override
	public Activity deleteActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getId());
		em.remove(foundActivity);
		return foundActivity;
	}

	@Override
	public void deleteAllActivities(Integer id) {
		Query query = null;
		if (id != null) {
			try {				
				query = em.createQuery("delete FROM Activity a WHERE a.person.idPerson = :id")
						.setParameter("id", id);
			} catch (Exception e) {
			}
			if (query != null) {
				query.executeUpdate();
			}
		}
		
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

	@Override
	public List<Activity> findAllActivity() {
		return em.createQuery("Select a From Activity a", Activity.class)
                .getResultList();
	}

}
