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
import model.Usuario;

public class EquipeDAO {
	
	
	private EntityManager em;
	
	public EquipeDAO(EntityManager em) {
		this.em = em;
	}
	

	public boolean salvaEquipe(Equipe equipe){
		System.out.println("entrou no mudaSenhaDAO");
		try{
			em.getTransaction().begin();
			em.persist(equipe);

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}	
	
	public boolean excluirEquipe(Equipe equipe){
		//	    em= new JpaUtil().getEntityManager();
		System.out.println("entrou no excluir");
		try{
			em.getTransaction().begin();

			em.remove(em.merge(equipe));

			em.getTransaction().commit();
			em.close();

			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}
	
	
	
//	public Equipe getEquipe(Equipe equipe) {
//		System.out.println("equipe: " + equipe.getTipoEquipe().getId());
//		Equipe equipeEncontrado = null;
//		try{
//			Query q = em
//					.createQuery(
//							"from Equipe u where "
//									+ "u.email = :email and " 
//									+ "u.senha = :senha and " 
//									+ "u.tipoEquipe = :tipoEquipe");
//			
//			q.setParameter("email", equipe.getEmail());
//			q.setParameter("tipoEquipe", equipe.getTipoEquipe());
//			q.setParameter("senha", equipe.getSenha());
//
//			equipeEncontrado = (Equipe) q.getSingleResult();
//			System.out.println("equipe: " + equipeEncontrado.getNome());
//		}catch(NoResultException ex){
//			equipeEncontrado = null;
//		}
//		return equipeEncontrado;
//	}
	
	public List<Equipe> getEquipes() {

		List<Equipe> equipes = null;
		try{
			Query q = em
//					.createQuery("from Equipe");
					.createQuery("from Equipe");

			equipes = new ArrayList<Equipe>();
			equipes = q.getResultList();
			System.out.println("a lista e: "+ equipes);
		}catch(NoResultException ex){
			equipes = new ArrayList<Equipe>();
		}
		return equipes;
	}
	
	public List<Equipe> getEquipes(Usuario usuario) {

		List<Equipe> equipes = null;
		try{
			Query q = em
//					.createQuery("from Equipe");
					.createQuery("from Equipe e where e.usuarioLider = :usuario");

			equipes = new ArrayList<Equipe>();
			q.setParameter("usuario", usuario);
			equipes = q.getResultList();
			System.out.println("a lista e: "+ equipes);
		}catch(NoResultException ex){
			equipes = new ArrayList<Equipe>();
		}
		return equipes;
	}

	
	public List<Equipe> getEquipes(Gincana gincana) {

		List<Equipe> equipes = null;
		try{
			Query q = em
					.createQuery(
							"from Equipe e where e.gincana = :gincana");
			q.setParameter("gincana", gincana);

			equipes = new ArrayList<Equipe>();
			equipes = q.getResultList();
			System.out.println("a lista e: "+ equipes);
		}catch(NoResultException ex){
			equipes = new ArrayList<Equipe>();
		}
		return equipes;
	}
	
	public List<Equipe> getEquipesPorProvaFeita(Prova prova) {

		List<Equipe> equipes = null;
		try{
			Query q = em
					.createQuery(
							"select distinct rep.equipe from ResultadoEquipeProva rep where rep.prova = :prova");
			q.setParameter("prova", prova);

			equipes = new ArrayList<Equipe>();
			equipes = q.getResultList();
			System.out.println("a lista e: "+ equipes);
		}catch(NoResultException ex){
			equipes = new ArrayList<Equipe>();
		}
		return equipes;
	}

	
	

	public boolean atualizaEquipe(Equipe equipe) {
		try{
			em.getTransaction().begin();

			em.merge(equipe);

			em.getTransaction().commit();
			em.close();
			return true;

		}catch(PersistenceException pe){
			return false;
		}
	}

}
