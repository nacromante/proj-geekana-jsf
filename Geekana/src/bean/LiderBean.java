package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import util.MensagemUtil;

import model.Usuario;

@ManagedBean
@ViewScoped
public class LiderBean{
	private Usuario usuario = new Usuario();
	private String senhaRepetida;
	
	public String salvar() {
		String pag = "";
		if(usuario.validaSenha(senhaRepetida) && usuario.salvar()){
			MensagemUtil.getMensagemDeSucesso("Líder cadastrado com sucesso!");
			pag = "login?faces-redirect=true";
		}	
		usuario = new Usuario();
		return pag;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaRepetida() {
		return senhaRepetida;
	}

	public void setSenhaRepetida(String senhaRepetida) {
		this.senhaRepetida = senhaRepetida;
	}
}
