package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.TipoUsuario;
import model.Usuario;

public class UsuarioDAO {
	
	
	private EntityManager em;
	
	public UsuarioDAO(EntityManager em) {
		this.em = em;
	}
	

	public boolean salvaUsuario(Usuario usuario){
		System.out.println("entrou no mudaSenhaDAO");
		try{
			em.getTransaction().begin();
			TipoUsuario tipoUsuario = em.find(TipoUsuario.class, new Long(2));
			usuario.setTipoUsuario(tipoUsuario);
			em.persist(usuario);

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}	
	
	public boolean excluirUsuario(Usuario usuario){
		//	    em= new JpaUtil().getEntityManager();
		System.out.println("entrou no excluir");
		try{
			em.getTransaction().begin();

			em.remove(em.merge(usuario));

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}
	
	
	
	public Usuario getUsuario(Usuario usuario) {
		System.out.println("usuario: " + usuario.getTipoUsuario().getId());
		Usuario usuarioEncontrado = null;
		try{
			Query q = em
					.createQuery(
							"from Usuario u where "
									+ "u.email = :email and " 
									+ "u.senha = :senha and " 
									+ "u.tipoUsuario = :tipoUsuario");
			
			q.setParameter("email", usuario.getEmail());
			q.setParameter("tipoUsuario", usuario.getTipoUsuario());
			q.setParameter("senha", usuario.getSenha());

			usuarioEncontrado = (Usuario) q.getSingleResult();
			System.out.println("usuario: " + usuarioEncontrado.getNome());
		}catch(NoResultException ex){
			usuarioEncontrado = null;
		}
		return usuarioEncontrado;
	}
	
	public List<Usuario> getUsuarios() {

		Query q = em
				.createQuery(
						"from Usuario");

		List<Usuario> lista = new ArrayList<Usuario>();
		lista = q.getResultList();
		System.out.println("a lista e: "+ lista);
		return  lista;
	}
	
	public List<Usuario> getUsuariosPorCliente(String cliente) {

		Query q = em
				.createQuery(
						"from Usuario u where u.cliente.codigo = :cliente");

		q.setParameter("cliente", cliente);

		List<Usuario> lista = new ArrayList<Usuario>();
		lista = q.getResultList();
		System.out.println("a lista e: "+ lista);
		return  lista;
	}
	
	public Usuario getUsuarioPorCliente(String cliente, String login) {

		Usuario usuario = null;
		try{
			Query q = em
					.createQuery(
							"from Usuario u where u.cliente.codigo = :cliente and " +
							"u.login = :login");

			q.setParameter("cliente", cliente);
			q.setParameter("login", login);

			usuario = (Usuario) q.getSingleResult();
			System.out.println("o usuario e: "+ usuario);
		}catch(NoResultException ex){
			usuario = null;
		}
		return usuario;
	}


	public boolean atualizaUsuario(Usuario usuario) {
		try{
			em.getTransaction().begin();

			em.merge(usuario);

			em.getTransaction().commit();
			em.close();
			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}

}
