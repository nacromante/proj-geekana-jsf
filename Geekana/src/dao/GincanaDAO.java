package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.Gincana;

public class GincanaDAO {
	
	
	private EntityManager em;
	
	public GincanaDAO(EntityManager em) {
		this.em = em;
	}
	

	public boolean salvaGincana(Gincana gincana){
		System.out.println("entrou no mudaSenhaDAO");
		try{
			em.getTransaction().begin();
			em.persist(gincana);

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			pe.printStackTrace();
			return false;
		}
	}	
	
	public boolean excluirGincana(Gincana gincana){
		//	    em= new JpaUtil().getEntityManager();
		System.out.println("entrou no excluir");
		try{
			em.getTransaction().begin();

			em.remove(em.merge(gincana));

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}
	
	
	
//	public Gincana getGincana(Gincana gincana) {
//		System.out.println("gincana: " + gincana.getTipoGincana().getId());
//		Gincana gincanaEncontrado = null;
//		try{
//			Query q = em
//					.createQuery(
//							"from Gincana u where "
//									+ "u.email = :email and " 
//									+ "u.senha = :senha and " 
//									+ "u.tipoGincana = :tipoGincana");
//			
//			q.setParameter("email", gincana.getEmail());
//			q.setParameter("tipoGincana", gincana.getTipoGincana());
//			q.setParameter("senha", gincana.getSenha());
//
//			gincanaEncontrado = (Gincana) q.getSingleResult();
//			System.out.println("gincana: " + gincanaEncontrado.getNome());
//		}catch(NoResultException ex){
//			gincanaEncontrado = null;
//		}
//		return gincanaEncontrado;
//	}
	
	public List<Gincana> getGincanas() {
		List<Gincana> gincanas = null;
		try{
			Query q = em
					.createQuery(
							"from Gincana");

			gincanas = new ArrayList<Gincana>();
			gincanas = q.getResultList();
			System.out.println("a lista e: "+ gincanas);
		}catch(NoResultException ex){
			gincanas = new ArrayList<Gincana>();
		}
		return gincanas;
	}
	
	public Gincana getGincana(Integer ano) {
		Gincana gincana = null;
		try{
			Query q = em
					.createQuery(
							"Select g from Gincana g where g.ano = :ano group by g.ano");

			gincana = new Gincana();
			q.setParameter("ano", ano);
			gincana = (Gincana) q.getSingleResult();
			System.out.println("a gincana e: "+ gincana);
		}catch(NoResultException ex){
			gincana = new Gincana();
		}
		return gincana;
	}
	
	public List<Gincana> getGincanasPorCliente(String cliente) {

		Query q = em
				.createQuery(
						"from Gincana u where u.cliente.codigo = :cliente");

		q.setParameter("cliente", cliente);

		List<Gincana> lista = new ArrayList<Gincana>();
		lista = q.getResultList();
		System.out.println("a lista e: "+ lista);
		return  lista;
	}
	
	public Gincana getGincanaPorCliente(String cliente, String login) {

		Gincana gincana = null;
		try{
			Query q = em
					.createQuery(
							"from Gincana u where u.cliente.codigo = :cliente and " +
							"u.login = :login");

			q.setParameter("cliente", cliente);
			q.setParameter("login", login);

			gincana = (Gincana) q.getSingleResult();
			System.out.println("o gincana e: "+ gincana);
		}catch(NoResultException ex){
			gincana = null;
		}
		return gincana;
	}


	public boolean atualizaGincana(Gincana gincana) {
		try{
			em.getTransaction().begin();

			em.merge(gincana);

			em.getTransaction().commit();
			em.close();
			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}

}
