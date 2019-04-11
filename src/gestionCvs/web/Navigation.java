package gestionCvs.web;



import javax.annotation.ManagedBean;

@ManagedBean
public class Navigation{
	
	public String showHome() {
		return "accueil?faces-redirect=true";
	}
	
	

}
