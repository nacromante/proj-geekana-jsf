package model;

import java.io.Serializable;
import javax.persistence.*;

import util.JpaUtil;

import dao.TipoUsuarioDAO;

import java.util.List;


/**
 * The persistent class for the tipousuario database table.
 * 
 */
@Entity
public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	public TipoUsuario() {
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoUsuario other = (TipoUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public List<TipoUsuario> getTiposUsuario() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		TipoUsuarioDAO dao = new TipoUsuarioDAO(entityManager);
		return dao.getTiposUsuario();
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
