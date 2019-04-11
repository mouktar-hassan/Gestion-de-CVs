package gestionCvs.web;

import java.io.Serializable;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.codec.Base64.OutputStream;

@ManagedBean
@SessionScoped
public class PrintController implements Serializable{
	
	public PrintController() {
		
	}
	
	//creer un pdf
	public void createPDF() {
		FacesContext facesContext=FacesContext.getCurrentInstance();
		ExternalContext externalContext=facesContext.getExternalContext();
		HttpSession session=(HttpSession) externalContext.getSession(true);
		String url="http://localhost:8080/GestionCvs/myCvConsult.xhtml;jsessionid="+session.getId()+"?pdf=true";
		
		try {
			
			ITextRenderer renderer=new ITextRenderer();
			renderer.setDocument(new URL(url).toString());
			renderer.layout();
			HttpServletResponse response=(HttpServletResponse) externalContext.getResponse();
			response.reset();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=\"print-file.dpf\"");
			ServletOutputStream outputStream=response.getOutputStream();
			renderer.createPDF(outputStream);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		facesContext.responseComplete();
	}

}
