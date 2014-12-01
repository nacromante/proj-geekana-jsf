package bean;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import model.TipoUsuario;
import model.Usuario;
import util.JpaUtil;
import util.MensagemUtil;
import dao.UsuarioDAO;



@ManagedBean
@SessionScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
//	private static final String urlInicial = "lider?faces-redirect=true";
	
	public LoginBean() {

	}



	public String efetuarLogin() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		Usuario usuarioLogado = dao.getUsuario(usuario);
		
		if(usuarioLogado != null){
			usuario = usuarioLogado;
			return urlPorTipoUsuario();
		}else{
			MensagemUtil.getMensagemDeErro("Login ou senha incorretos");
			return "";
		}	
	}
	
	private String urlPorTipoUsuario() {
		if(usuario.getTipoUsuario().getDescricao().equals("Administrador")){
			return "painelAdministrador?faces-redirect=true";
		}else
			return "painelLider?faces-redirect=true";
	}



	public boolean mostraMenuLider() {
		boolean ehLider = (usuario.getNome() != null) && (usuario.getTipoUsuario().getId().intValue() == 2);
		return ehLider;
	}
	
	public boolean mostraMenuAdm() {
		return usuario.getNome() != null && usuario.getTipoUsuario().getId() == 1;
	}
	
	public List<TipoUsuario> getTiposUsuario() {
		return new TipoUsuario().getTiposUsuario();
	}
	
	public String logout() {
		usuario = new Usuario();
		return "login?faces-redirect=true";
	}


	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
















