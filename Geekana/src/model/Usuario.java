package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Email;

import util.JpaUtil;
import util.MensagemUtil;
import bean.LiderBean;
import dao.UsuarioDAO;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Email(message="E-mail inválido")
	private String email;
	
//	private String login;

	private String nome;
//	@Length(min=4, max=8,message="Deverá ser informado no mínimo 4 caracteres alfanuméricos.")
	private String senha;

	private String telefone;

	//bi-directional many-to-one association to Tipousuario
	@ManyToOne
	@JoinColumn(name="id_tipo_acesso")
	private TipoUsuario tipoUsuario;
	
	public boolean salvar() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(entityManager);
		
		
		return dao.salvaUsuario(this);
	}
	
	public boolean validaSenha(String senhaRepetida) {
		if(senha.length() < 4){
			MensagemUtil.getMensagemDeErro("Deverá ser informado no mínimo 4 caracteres alfanuméricos.");
			return false;
		}else 
		if(!senha.equals(senhaRepetida)){
			MensagemUtil.getMensagemDeErro("A repetição da senha não correspondente com a senha anteriormente informada.");
			return false;
		}else{
			return true;
		}	
	}
	
	public Usuario() {
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getLogin() {
//		return login;
//	}
//
//	public void setLogin(String login) {
//		this.login = login;
//	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}	
}