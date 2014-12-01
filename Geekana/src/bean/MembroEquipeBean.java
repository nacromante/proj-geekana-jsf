package bean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Equipe;
import model.MembroEquipe;
import model.Usuario;
import util.BeanUtil;
import util.MensagemUtil;

@ManagedBean
@ViewScoped
public class MembroEquipeBean{
	private MembroEquipe membroEquipe = new MembroEquipe();
	
	public String salvar() {
		String pag = "";
		if(membroEquipe.getEquipe().getGincana().dentroDoPeriodoDeInscricaoes()){
			if(membroEquipe.getEquipe().addMembroEquipe(membroEquipe)){
				if(membroEquipe.salvar()){
					MensagemUtil.getMensagemDeSucesso("Membro cadastrado com sucesso!");
					pag = "";
				}else{
					MensagemUtil.getMensagemDeErro("O membro já existe!");
				}
			}else
				MensagemUtil.getMensagemDeErro("Limite de membros para essa equipe já foi atingido!");
		}else{
			Equipe equipe = membroEquipe.getEquipe();
			Date dataInicio = equipe.getGincana().getDataInicioInscricoes(); Date dataFim = equipe.getGincana().getDataFimInscricoes();
			String dataInicioFormatada = BeanUtil.formataData(dataInicio); String dataFimFormatada = BeanUtil.formataData(dataFim);
			MensagemUtil.getMensagemDeErro("O periodo de inscrições é de " + dataInicioFormatada +" até " + dataFimFormatada);
		}

		membroEquipe = new MembroEquipe();

		return pag;
	}
	
	public List<Equipe> getEquipes() {
		Usuario usuario = BeanUtil.getLoginBean().getUsuario();
		return new Equipe().getEquipes(usuario);
	}

	public MembroEquipe getMembroEquipe() {
		return membroEquipe;
	}

	public void setMembroEquipe(MembroEquipe membroEquipe) {
		this.membroEquipe = membroEquipe;
	}

	
	
	
	

}
