package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import model.Gincana;
import model.Prova;
import model.ResultadoEquipeProva;
import model.ResultadoTotal;

public class ResultadoParcialDAO {
	
	
	private EntityManager em;
	
	public ResultadoParcialDAO(EntityManager em) {
		this.em = em;
	}
	

	
	public List<Prova> getProvasPorResultado(Gincana gincana){

		List<Prova> provas = null;
		try{
			Query q = em
					.createQuery(
							"select distinct rep.prova from ResultadoEquipeProva rep where rep.prova.gincana = :gincana");

			provas = new ArrayList<Prova>();
			q.setParameter("gincana", gincana);
			provas = q.getResultList();
			System.out.println("a lista e: "+ provas);
		}catch(NoResultException ex){
			provas = new ArrayList<Prova>();
		}
		return provas;
	}
	
	public List<ResultadoTotal> getResultadosTotais(Gincana gincana) {
		List<ResultadoTotal> resultadosTotais = null;
		try{
			Query q = em
					.createQuery(
							"select new model.ResultadoTotal(rep.equipe, SUM(rep.pontuacaoAtingida))from ResultadoEquipeProva rep " +
							"where rep.equipe.gincana = :gincana group by rep.equipe");

			q.setParameter("gincana", gincana);

			resultadosTotais = new ArrayList<ResultadoTotal>();
			resultadosTotais = q.getResultList();
//			for (ResultadoTotal resultadoTotal : resultadosTotais) {
//				
//				System.out.println("a lista e: "+ resultadoTotal.get);
//			}
		}catch(NoResultException ex){
			resultadosTotais = new ArrayList<ResultadoTotal>();
		}
		return resultadosTotais;
	}
	
	
}
