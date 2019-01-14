package gestionCvs.beans;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.NoResultException;

import gestionCvs.entities.Activity;
import gestionCvs.entities.Person;


@Local
public interface ConnectManager {

    Person login(String email, String password) throws NoResultException;
	
	Person logout();	

	Activity createActivity(Activity activity);

	Activity updateActivity(Activity activity);

	Activity findActivity(Activity activity);

	Activity deleteActivity(Activity activity);
	
	void deleteAllActivities(Integer id);

	List<Activity> showActivities(Person person);
	
	List<Activity> findAllActivity();
}
