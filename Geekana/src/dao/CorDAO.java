package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.Cor;
import model.Gincana;

public class CorDAO {
	
	
	private EntityManager em;
	
	public CorDAO(EntityManager em) {
		this.em = em;
	}
	

	public boolean salvaCor(Cor cor){
		System.out.println("entrou no mudaSenhaDAO");
		try{
			em.getTransaction().begin();
			em.persist(cor);

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}	
	
	public boolean excluirCor(Cor cor){
		//	    em= new JpaUtil().getEntityManager();
		System.out.println("entrou no excluir");
		try{
			em.getTransaction().begin();

			em.remove(em.merge(cor));

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}
	
	
	
//	public Cor getCor(Cor cor) {
//		System.out.println("cor: " + cor.getTipoCor().getId());
//		Cor corEncontrado = null;
//		try{
//			Query q = em
//					.createQuery(
//							"from Cor u where "
//									+ "u.email = :email and " 
//									+ "u.senha = :senha and " 
//									+ "u.tipoCor = :tipoCor");
//			
//			q.setParameter("email", cor.getEmail());
//			q.setParameter("tipoCor", cor.getTipoCor());
//			q.setParameter("senha", cor.getSenha());
//
//			corEncontrado = (Cor) q.getSingleResult();
//			System.out.println("cor: " + corEncontrado.getNome());
//		}catch(NoResultException ex){
//			corEncontrado = null;
//		}
//		return corEncontrado;
//	}
	
	public List<Cor> getCores(Gincana gincana) {
		List<Cor> cores = null;
		try{
			Query q = em
//					.createQuery("from Cor");
//					.createQuery("from Cor c where c not in (select e.cor from Equipe e group by e.cor)");
					.createQuery("select e.cor from Equipe e where e.gincana = :gincana group by e.cor");
//					.createQuery("from Gincana g where g = :gincana and g.cores not in (select e.cor from Equipe e group by e.cor)");
			q.setParameter("gincana", gincana);
			cores = new ArrayList<Cor>();
			cores = q.getResultList();
			for (Cor cor : cores) {
				System.out.println("a lista cores q nao pode : "+ cor.getDescricao());
				
			}
		}catch(NoResultException ex){
			cores = new ArrayList<Cor>();
		}
		return cores;
	}
	
	public List<Cor> getCores() {

		Query q = em
				.createQuery(
						"from Cor");


		List<Cor> lista = new ArrayList<Cor>();
		lista = q.getResultList();
		System.out.println("a lista e: "+ lista);
		return  lista;
	}
	
	public Cor getCorPorCliente(String cliente, String login) {

		Cor cor = null;
		try{
			Query q = em
					.createQuery(
							"from Cor u where u.cliente.codigo = :cliente and " +
							"u.login = :login");

			q.setParameter("cliente", cliente);
			q.setParameter("login", login);

			cor = (Cor) q.getSingleResult();
			System.out.println("o cor e: "+ cor);
		}catch(NoResultException ex){
			cor = null;
		}
		return cor;
	}


	public boolean atualizaCor(Cor cor) {
		try{
			em.getTransaction().begin();

			em.merge(cor);

			em.getTransaction().commit();
			em.close();
			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}

}
