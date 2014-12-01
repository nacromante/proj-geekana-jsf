package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.Gincana;
import model.Prova;

public class ProvaDAO {
	
	
	private EntityManager em;
	
	public ProvaDAO(EntityManager em) {
		this.em = em;
	}
	

	public boolean salvaProva(Prova prova){
		System.out.println("entrou no mudaSenhaDAO");
		try{
			em.getTransaction().begin();
			em.persist(prova);

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}	
	
	public boolean excluirProva(Prova prova){
		//	    em= new JpaUtil().getEntityManager();
		System.out.println("entrou no excluir");
		try{
			em.getTransaction().begin();

			em.remove(em.merge(prova));

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}
	
	
	
//	public Prova getProva(Prova prova) {
//		System.out.println("prova: " + prova.getTipoProva().getId());
//		Prova provaEncontrado = null;
//		try{
//			Query q = em
//					.createQuery(
//							"from Prova u where "
//									+ "u.email = :email and " 
//									+ "u.senha = :senha and " 
//									+ "u.tipoProva = :tipoProva");
//			
//			q.setParameter("email", prova.getEmail());
//			q.setParameter("tipoProva", prova.getTipoProva());
//			q.setParameter("senha", prova.getSenha());
//
//			provaEncontrado = (Prova) q.getSingleResult();
//			System.out.println("prova: " + provaEncontrado.getNome());
//		}catch(NoResultException ex){
//			provaEncontrado = null;
//		}
//		return provaEncontrado;
//	}
	
	public List<Prova> getProvas() {
		List<Prova> provas = null;
		try{
		Query q = em
				.createQuery(
						"from Prova");

		provas = new ArrayList<Prova>();
		provas = q.getResultList();
		}catch(NoResultException nr){
			provas = new ArrayList<Prova>();
		}
		System.out.println("a lista e: "+ provas);
		return  provas;
	}
	
	public List<Prova> getProvas(Gincana gincana) {
		List<Prova> provas = null;
		try{
			Query q = em
					.createQuery(
							"from Prova p where p.gincana = :gincana");
			q.setParameter("gincana", gincana);
			provas = new ArrayList<Prova>();
			provas = q.getResultList();
		}catch(NoResultException nr){
			provas = new ArrayList<Prova>();
		}
		System.out.println("a lista e: "+ provas);
		return  provas;
	}
	
	public List<Prova> getProvasPorCliente(String cliente) {

		Query q = em
				.createQuery(
						"from Prova u where u.cliente.codigo = :cliente");

		q.setParameter("cliente", cliente);

		List<Prova> lista = new ArrayList<Prova>();
		lista = q.getResultList();
		System.out.println("a lista e: "+ lista);
		return  lista;
	}
	
	public Prova getProvaPorCliente(String cliente, String login) {

		Prova prova = null;
		try{
			Query q = em
					.createQuery(
							"from Prova u where u.cliente.codigo = :cliente and " +
							"u.login = :login");

			q.setParameter("cliente", cliente);
			q.setParameter("login", login);

			prova = (Prova) q.getSingleResult();
			System.out.println("o prova e: "+ prova);
		}catch(NoResultException ex){
			prova = null;
		}
		return prova;
	}


	public boolean atualizaProva(Prova prova) {
		try{
			em.getTransaction().begin();

			em.merge(prova);

			em.getTransaction().commit();
			em.close();
			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}

}
