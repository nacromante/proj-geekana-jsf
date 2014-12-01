package bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Cor;
import model.Equipe;
import model.Gincana;
import util.BeanUtil;
import util.MensagemUtil;

@ManagedBean
@ViewScoped
public class EquipeBean{
	private Equipe equipe = new Equipe();
	
	public String salvar() {
		String pag = "";
		if(equipe.getGincana().addEquipe(equipe)){
			if(equipe.getGincana().dentroDoPeriodoDeInscricaoes()){
				equipe.setUsuarioLider(BeanUtil.getLoginBean().getUsuario());
				if(equipe.salvar()){
					MensagemUtil.getMensagemDeSucesso("Equipe cadastrada com sucesso! Agora cadastre os membros para a equipe.");
					pag = "";
				}
			}else{
				Date dataInicio = equipe.getGincana().getDataInicioInscricoes(); Date dataFim = equipe.getGincana().getDataFimInscricoes();
				String dataInicioFormatada = BeanUtil.formataData(dataInicio); String dataFimFormatada = BeanUtil.formataData(dataFim);
				MensagemUtil.getMensagemDeErro("O periodo de inscrições é de " + dataInicioFormatada +" até " + dataFimFormatada);
			}
		}else
			MensagemUtil.getMensagemDeErro("Limite de equipes para essa gincana já foi atingido!");
		equipe = new Equipe();

		return pag;
	}
	
	public List<Gincana> getGincanas() {
		return new Gincana().getGincanas();
	}
	
	public List<Cor> getCores() {
		
		return new Cor().getCores(equipe.getGincana());
	}
	

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
	
	
	

}
