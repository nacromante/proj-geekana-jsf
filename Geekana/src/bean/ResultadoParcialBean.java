package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;	
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import model.Gincana;
import model.Prova;
import model.ResultadoTotal;

import util.JpaUtil;

import dao.ResultadoParcialDAO;

@ManagedBean
@ViewScoped
public class ResultadoParcialBean {
	
	private List<Prova> provas;
	private Gincana gincana;
	
	public ResultadoParcialBean() {
		setProvas(getProvasPorResultado());
		
//		gincana = getGincanaPorAno();
	}

	public List<Prova> getProvasPorResultado() {
		System.out.println("passou pelo provas resultado");
		EntityManager entityManager = new JpaUtil().getEntityManager();
		ResultadoParcialDAO dao = new ResultadoParcialDAO(entityManager);
		if(gincana != null)
			return dao.getProvasPorResultado(gincana);
		else
			return new ArrayList<Prova>();
	}
	
	public List<ResultadoTotal> getResultadosTotais() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		ResultadoParcialDAO dao = new ResultadoParcialDAO(entityManager);
		return dao.getResultadosTotais(gincana);
	}
	
	public List<Gincana> getGincanas() {
		return new Gincana().getGincanas();
	}
	
	public Gincana getGincanaPorAno() {
		Calendar data = Calendar.getInstance(new Locale("pt","br"));
		int ano = data.get(Calendar.YEAR);
		System.out.println("ano do calendar: " +ano);
		return new Gincana().getGincana(ano);
	}

	public List<Prova> getProvas() {
		return getProvasPorResultado();
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public Gincana getGincana() {
		return gincana;
	}

	public void setGincana(Gincana gincana) {
		this.gincana = gincana;
	}
	
	
	

}
