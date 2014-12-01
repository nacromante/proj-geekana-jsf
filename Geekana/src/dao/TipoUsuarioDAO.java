package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.TipoUsuario;
import model.Usuario;

public class TipoUsuarioDAO {
	
	
	private EntityManager em;
	
	public TipoUsuarioDAO(EntityManager em) {
		this.em = em;
	}
	
	public List<TipoUsuario> getTiposUsuario() {
		List<TipoUsuario> tiposUsuario = null;
		try{
			Query q = em
					.createQuery(
							"from TipoUsuario");

			tiposUsuario = q.getResultList();
			System.out.println("a lista e: "+ tiposUsuario);
		}catch(NoResultException ex){
			tiposUsuario = new ArrayList<TipoUsuario>();
		}
		return tiposUsuario;
	}
	
	
}
