package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Cor;
import model.Equipe;
import model.Gincana;
import util.MensagemUtil;

@ManagedBean
@ViewScoped
public class GincanaBean{
	private Gincana gincana = new Gincana();
	List<Cor> cores = new ArrayList<Cor>();
	
	public GincanaBean() {
//		cores = getCores();
		System.out.println("cores:"+ cores);
	}
	
	public String salvar() {
		String pag = "";
		System.out.println("qqdt cores: "+ gincana.getCores().size());
		if(gincana.getCores().size() == gincana.getQtdLimiteEquipes()){
			if(gincana.salvar()){
				MensagemUtil.getMensagemDeSucesso("Gincana cadastrada com sucesso!");
				pag = "";
			}	
		}else{
				MensagemUtil.getMensagemDeErro("Número de cores selecionadas não correspondem ao número de equipes");
		}

		gincana = new Gincana();
		return pag;
	}
	
	public List<Cor> getCores() {
		
		return new Cor().getCores();
	}

	public Gincana getGincana() {
		return gincana;
	}

	public void setGincana(Gincana gincana) {
		this.gincana = gincana;
	}

	public void setCores(List<Cor> cores) {
		this.cores = cores;
	}
	
	
	
	

}
