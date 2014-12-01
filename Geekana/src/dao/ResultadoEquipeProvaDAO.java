package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.Equipe;
import model.Gincana;
import model.Prova;
import model.ResultadoEquipeProva;
import model.ResultadoTotal;

public class ResultadoEquipeProvaDAO {
	
	
	private EntityManager em;
	
	public ResultadoEquipeProvaDAO(EntityManager em) {
		this.em = em;
	}
	

	public boolean salvaResultadoEquipeProva(ResultadoEquipeProva resultadoEquipeProva){
		System.out.println("entrou no mudaSenhaDAO");
		try{
			em.getTransaction().begin();
			em.persist(resultadoEquipeProva);

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}	
	
	public boolean excluirResultadoEquipeProva(ResultadoEquipeProva resultadoEquipeProva){
		//	    em= new JpaUtil().getEntityManager();
		System.out.println("entrou no excluir");
		try{
			em.getTransaction().begin();

			em.remove(em.merge(resultadoEquipeProva));

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}
	
	
	
	
	public List<ResultadoEquipeProva> getResultadoEquipeProvas() {

		List<ResultadoEquipeProva> resultadoEquipeProvas = null;
		try{
			Query q = em
					.createQuery(
							"from ResultadoEquipeProva");

			resultadoEquipeProvas = new ArrayList<ResultadoEquipeProva>();
			resultadoEquipeProvas = q.getResultList();
			System.out.println("a lista e: "+ resultadoEquipeProvas);
		}catch(NoResultException ex){
			resultadoEquipeProvas = new ArrayList<ResultadoEquipeProva>();
		}
		return resultadoEquipeProvas;
	}
	
	public List<ResultadoEquipeProva> getResultadoEquipeProvasPorEquipe(Prova prova) {
		List<ResultadoEquipeProva> resultadoEquipeProvas = null;
		try{
			Query q = em
					.createQuery(
							"from ResultadoEquipeProva rep where rep.prova = :prova group by rep.equipe");

			q.setParameter("prova", prova);

			resultadoEquipeProvas = new ArrayList<ResultadoEquipeProva>();
			resultadoEquipeProvas = q.getResultList();
			System.out.println("a lista e: "+ resultadoEquipeProvas);
		}catch(NoResultException ex){
			resultadoEquipeProvas = new ArrayList<ResultadoEquipeProva>();
		}
		return resultadoEquipeProvas;
	}
	

	


	public boolean atualizaResultadoEquipeProva(ResultadoEquipeProva resultadoEquipeProva) {
		try{
			em.getTransaction().begin();

			em.merge(resultadoEquipeProva);

			em.getTransaction().commit();
			em.close();
			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}

}
