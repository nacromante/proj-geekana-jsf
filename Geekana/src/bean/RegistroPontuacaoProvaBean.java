package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Equipe;
import model.Gincana;
import model.Prova;
import model.ResultadoEquipeProva;
import util.MensagemUtil;

@ManagedBean
@ViewScoped
public class RegistroPontuacaoProvaBean{
	private ResultadoEquipeProva resultadoEquipeProva = new ResultadoEquipeProva();
	private Gincana gincana = new Gincana();
	
	public String salvar() {
		String pag = "";
				if(resultadoEquipeProva.salvar()){
					MensagemUtil.getMensagemDeSucesso("Equipe cadastrada com sucesso!");
					pag = "";
				}
				resultadoEquipeProva = new ResultadoEquipeProva();
				gincana = new Gincana();
				return pag;
	}
	
	public List<Gincana> getGincanas() {
		return new Gincana().getGincanas();
	}
	
	
	public List<Equipe> getEquipes() {
		Equipe equipe = new Equipe();
		if(gincana.getId() == null)
			return equipe.getEquipes();
		return equipe.getEquipes(gincana);
	}
	
	public List<Prova> getProvas() {
		Prova prova = new Prova();
		if(gincana.getId() == null)
			return prova.getProvas();
		return prova.getProvas(gincana);
	}

	public ResultadoEquipeProva getResultadoEquipeProva() {
		return resultadoEquipeProva;
	}

	public void setResultadoEquipeProva(ResultadoEquipeProva resultadoEquipeProva) {
		this.resultadoEquipeProva = resultadoEquipeProva;
	}

	public Gincana getGincana() {
		return gincana;
	}

	public void setGincana(Gincana gincana) {
		this.gincana = gincana;
	}
	
	
	
	
	

}
