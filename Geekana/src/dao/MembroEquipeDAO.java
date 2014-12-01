package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.sun.mail.handlers.message_rfc822;

import model.MembroEquipe;

public class MembroEquipeDAO {
	
	
	private EntityManager em;
	
	public MembroEquipeDAO(EntityManager em) {
		this.em = em;
	}
	

	public boolean salvaMembroEquipe(MembroEquipe membroEquipe){
		System.out.println("entrou no mudaSenhaDAO");
		try{
			em.getTransaction().begin();
			em.persist(membroEquipe);

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			pe.printStackTrace();
			return false;
		}
	}	
	
	public boolean excluirMembroEquipe(MembroEquipe membroEquipe){
		//	    em= new JpaUtil().getEntityManager();
		System.out.println("entrou no excluir");
		try{
			em.getTransaction().begin();

			em.remove(em.merge(membroEquipe));

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}
	
	
	
//	public MembroEquipe getMembroEquipe(MembroEquipe membroEquipe) {
//		System.out.println("membroEquipe: " + membroEquipe.getTipoMembroEquipe().getId());
//		MembroEquipe membroEquipeEncontrado = null;
//		try{
//			Query q = em
//					.createQuery(
//							"from MembroEquipe u where "
//									+ "u.email = :email and " 
//									+ "u.senha = :senha and " 
//									+ "u.tipoMembroEquipe = :tipoMembroEquipe");
//			
//			q.setParameter("email", membroEquipe.getEmail());
//			q.setParameter("tipoMembroEquipe", membroEquipe.getTipoMembroEquipe());
//			q.setParameter("senha", membroEquipe.getSenha());
//
//			membroEquipeEncontrado = (MembroEquipe) q.getSingleResult();
//			System.out.println("membroEquipe: " + membroEquipeEncontrado.getNome());
//		}catch(NoResultException ex){
//			membroEquipeEncontrado = null;
//		}
//		return membroEquipeEncontrado;
//	}
	
	public List<MembroEquipe> getMembroEquipes() {
		List<MembroEquipe> MembrosEquipe = null;
		try{
		Query q = em
				.createQuery(
						"from MembroEquipe");

		MembrosEquipe = new ArrayList<MembroEquipe>();
		MembrosEquipe = q.getResultList();
		System.out.println("a lista e: "+ MembrosEquipe);
		}catch(NoResultException ex){
			MembrosEquipe = new ArrayList<MembroEquipe>();
		}
		return MembrosEquipe;
	}
	
	public List<MembroEquipe> getMembroEquipesPorCliente(String cliente) {

		Query q = em
				.createQuery(
						"from MembroEquipe u where u.cliente.codigo = :cliente");

		q.setParameter("cliente", cliente);

		List<MembroEquipe> lista = new ArrayList<MembroEquipe>();
		lista = q.getResultList();
		System.out.println("a lista e: "+ lista);
		return  lista;
	}
	
	public MembroEquipe getMembroEquipePorCliente(String cliente, String login) {

		MembroEquipe membroEquipe = null;
		try{
			Query q = em
					.createQuery(
							"from MembroEquipe u where u.cliente.codigo = :cliente and " +
							"u.login = :login");

			q.setParameter("cliente", cliente);
			q.setParameter("login", login);

			membroEquipe = (MembroEquipe) q.getSingleResult();
			System.out.println("o membroEquipe e: "+ membroEquipe);
		}catch(NoResultException ex){
			membroEquipe = null;
		}
		return membroEquipe;
	}


	public boolean atualizaMembroEquipe(MembroEquipe membroEquipe) {
		try{
			em.getTransaction().begin();

			em.merge(membroEquipe);

			em.getTransaction().commit();
			em.close();
			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}

}
