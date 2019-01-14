package gestionCvs.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import gestionCvs.entities.Person;

public class TestPersonManager {
	
	@EJB
    PersonManager pm;
	
	
	Person person;
	Person person2;
	
	@Before
	public void setUp() throws Exception {
	        EJBContainer.createEJBContainer().getContext().bind("inject", this);
	        assertNotNull(pm);
	        person=new Person();
	        person2=new Person();
	        
	}
	
	@After
	public void tearDown() throws Exception {
	        EJBContainer.createEJBContainer().close();
	}
	
	@Test
	public void testFindPersons() {	    	
	        assertNotNull(pm.findPersons());
	}
	
	@Test
	public void testSavePerson() {
		person=new Person();
		person.setName("Sara");
		person.setFirstname("BOUSTANI");
		person.setBirthday(new Date(11/02/1994));
		person.setEmail("sara.boustani@etu.univ-amu.fr");
		person.setWebsite("www.sara.fr");
		person.setPassword("ss");
		
		person2=new Person();
		person2.setName("EL GHARNAJI");
		person2.setFirstname("Yasir");
		person2.setBirthday(new Date(24/01/1994));
		person2.setEmail("yasir.el-gharnaji@etu.univ-amu.fr");
		person2.setWebsite("www.yasir.fr");
		person2.setPassword("yy");
			
		pm.savePerson(person);
		pm.savePerson(person2);
		assertNotNull(pm);
		
	}
	
	@Test
	public void testupdatePerson() {
		
		person=new Person();
		person.setId(2);
		person.setName("HASSAN");
		person.setFirstname("Mouktar");
		person.setBirthday(new Date(19/8/1994));
		person.setEmail("mouktar.HASSAN-FARAH@etu.univ-amu.fr");
		person.setWebsite("www.mouktar.fr");
		person.setPassword("mm");		
		pm.updatePerson(person);
		assertNotNull(pm);
	}
	
	@Test
	public void testfindPerson() {
		person.setId(2);;
		Person p = pm.findById(person);
		
		System.out.println("Go"+p.getId());
		System.out.println("First Name : " + p.getFirstname());
		System.out.println("Name : " + p.getName());
		
		assertNotNull(p);
		assertTrue(p.getName().equals("HASSAN"));
	}
	
	public void testfindByName() {
		List<Person> listPerson = pm.findByName("HASSAN");
		System.out.println("Go"+listPerson.size());
		for (int i = 0; i < listPerson.size(); i++) {
			System.out.println("First Name : " + listPerson.get(i).getFirstname());
			System.out.println("Name : " + listPerson.get(i).getName());
		}
		assertNotNull(listPerson);
		assertTrue(listPerson.get(0).getFirstname().equals("Mouktar"));
	}
	
	@Test
	public void testfindByFirstname() {
		List<Person> listPerson = pm.findByFirstname("Mouktar");
		System.out.println("Go"+listPerson.size());
		for (int i = 0; i < listPerson.size(); i++) {
			System.out.println("First Name : " + listPerson.get(i).getFirstname());
			System.out.println("Name : " + listPerson.get(i).getName());
		}
		assertNotNull(listPerson);
		assertTrue(listPerson.get(0).getName().equals("HASSAN"));
	}
	
	@Test
	public void testFindMulticriteria() {
		Person p = new Person();
		p.setFirstname("Yasir");
		p.setName("EL GHARNAJI");
		p.setBirthday(new Date(24/01/1994));
		List<Person> listPerson = pm.findMulticriteria(p);
		System.out.println(listPerson.size());
	}
	
	
	
	
	@Ignore
	@Test
	public void testDeletePerson() {
		person=new Person();
		person.setId(1);      	
   	   pm.deletePerson(person);
	}
	
	
	
	

}
