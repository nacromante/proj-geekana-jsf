package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Cor;
import model.Prova;
import model.Gincana;
import util.MensagemUtil;

@ManagedBean
@ViewScoped
public class ProvaBean{
	private Prova prova = new Prova();
	
	public String salvar() {
		String pag = "";
		if(prova.salvar()){
			MensagemUtil.getMensagemDeSucesso("Prova cadastrada com sucesso!");
			pag = "";
		}	
		prova = new Prova();
		
		return pag;
	}
	
	public List<Gincana> getGincanas() {
		return new Gincana().getGincanas();
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}
	
	
	
	

}
