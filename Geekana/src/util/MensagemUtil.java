package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtil {

	public static void getMensagemDeErro(String mensagem) {
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						mensagem, null));
		
	}
	
	public static void getMensagemDeSucesso(String mensagem) {
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						mensagem, null));
		
	}
	public static void getMensagemDeInformacao(String mensagem) {
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						mensagem, null));
		
	}

}
