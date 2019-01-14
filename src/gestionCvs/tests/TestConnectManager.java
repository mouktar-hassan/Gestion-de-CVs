package gestionCvs.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import gestionCvs.beans.ConnectManager;
import gestionCvs.beans.PersonManager;
import gestionCvs.entities.Activity;
import gestionCvs.entities.Nature;
import gestionCvs.entities.Person;

public class TestConnectManager {
	@EJB
    PersonManager pm;
	
	@EJB
	ConnectManager cm;	
	
	Person person,person2,person3;
	
	
	Activity activity,activity2;
	
	@Before
	public void setUp() throws Exception {
	        EJBContainer.createEJBContainer().getContext().bind("inject", this);
	        assertNotNull(cm);
	        activity=new Activity();
	        activity2=new Activity();
	        
	        person=new Person();
	        person2=new Person();
	        person3=new Person();
	}
	
	@After
	public void tearDown() throws Exception {
	        EJBContainer.createEJBContainer().close();
	}
	
	@Test
	public void testlogin() {		
		cm.login(person.getEmail(), person.getPassword());
		assertNotNull(cm);
	}
	
	@Test 
	public void testlogout() {		
		//pm.logout();
		//assertNull(pm);
	}
	
	
	
	
	@Test
	public void testcreateActivity() {
		//insertion des personnes
		Person person_p=new Person();
		Person person_p2=new Person();
		Person person_p3=new Person();
		//Personne 1
		person=new Person();
		
		person.setName("Sara");
		person.setFirstname("BOUSTANI");
		person.setBirthday(new Date(11/02/1994));
		person.setEmail("sara.boustani@etu.univ-amu.fr");
		person.setWebsite("www.sara.fr");
		person.setPassword("ss");
		
		//Personne 2
		person2=new Person();
		
		person2.setName("EL GHARNAJI");
		person2.setFirstname("Yasir");
		person2.setBirthday(new Date(24/01/1994));
		person2.setEmail("yasir.el-gharnaji@etu.univ-amu.fr");
		person2.setWebsite("www.yasir.fr");
		person2.setPassword("yy");
		
		//Personne 3
		person3=new Person();
		
		person3.setName("HASSAN");
		person3.setFirstname("Mouktar");
		person3.setBirthday(new Date(19/8/1994));
		person3.setEmail("mouktar.HASSAN-FARAH@etu.univ-amu.fr");
		person3.setWebsite("www.mouktar.fr");
		person3.setPassword("mm");
		
		//ajoute
		person_p=pm.savePerson(person);
		person_p2=pm.savePerson(person2);
		person_p3=pm.savePerson(person3);
		//insertion des activités
		
		//insertion d'une activité pour la personne 1
		activity=new Activity();	
		
		activity.setYear(new Date(12/1/2019));
		activity.setNature(Nature.EXPERIENCE_PROFESSIONNELLE);
		activity.setTitle("Ingénieuse en BI");
		activity.setDescription("Stage ");
		activity.setWebAdress("www.sara.fr");
		activity.setPerson(person_p);
		cm.createActivity(activity);
		
		//insertion d'une activité pour la personne 2
        activity=new Activity();	
		
		activity.setYear(new Date(12/1/2019));
		activity.setNature(Nature.EXPERIENCE_PROFESSIONNELLE);
		activity.setTitle("Ingénieur Logiciel");
		activity.setDescription("Stage ");
		activity.setWebAdress("www.yasir.fr");
		activity.setPerson(person_p2);
		cm.createActivity(activity);
		
		//insertion d'une activité pour la personne 2
		activity=new Activity();
		
		activity.setYear(new Date(14/1/2019));
		activity.setNature(Nature.EXPERIENCE_PROFESSIONNELLE);
		activity.setTitle("Ingénieur Logiciel");
		activity.setDescription("Stage ");
		activity.setWebAdress("www.mouktar.fr");
		activity.setPerson(person_p2);
		cm.createActivity(activity);
	}
	
	
	
	@Test
	public void testfindActivity() {
		Activity activity = new Activity();
		activity.setId(2);
		Activity a = cm.findActivity(activity);
		System.out.println(a.getTitle());
		
		assertTrue(a.getTitle().equals("Ingénieuse en BI"));
		System.out.println("Title : "+a.getTitle());
	}
	
	@Test
	public void testfindAllActivity() {
		assertNotNull(cm.findAllActivity());		
	}
	
	@Test
	public void testupdateActivity() {
		Person person_p3=new Person();
		
		person3=new Person();
		
		person3.setName("HASSAN");
		person3.setFirstname("Mouktar");
		person3.setBirthday(new Date(19/8/1994));
		person3.setEmail("mouktar.HASSAN-FARAH@etu.univ-amu.fr");
		person3.setWebsite("www.mouktar.fr");
		person3.setPassword("mm");
		
		person_p3=pm.savePerson(person3);
		
		activity=new Activity();
		
		activity.setYear(new Date(13/1/2019));
		activity.setNature(Nature.FORMATION);
		activity.setTitle("Architecture Application");
		activity.setDescription("Enseignement en M2 ");
		activity.setWebAdress("www.mouktar.fr");
		activity.setPerson(person_p3);
		cm.updateActivity(activity);	
		assertNotNull(activity);
		assertTrue(activity.getTitle().equals("Architecture Application"));		
	}
	
	@Ignore
	@Test 
	public void deleteActivity() {
		activity=new Activity();
		activity.setId(1);	
   	    cm.deleteActivity(activity);
	}
	
	
	
	
	
	

}
