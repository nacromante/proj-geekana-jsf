package model;

import java.io.Serializable;
import javax.persistence.*;

import util.JpaUtil;
import dao.ResultadoEquipeProvaDAO;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the resultadoequipeprova database table.
 * 
 */
@Entity
public class ResultadoEquipeProva implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private ResultadoEquipeProvaPK id;
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(name="pontuacao_atingida")
	private BigDecimal pontuacaoAtingida;

	//bi-directional many-to-one association to Equipe
	@ManyToOne
	@JoinColumn(name="id_equipe")
	private Equipe equipe;

	//bi-directional many-to-one association to Prova
	@ManyToOne
	@JoinColumn(name="id_prova")
	private Prova prova;
	
//	@ManyToOne
//	@JoinColumn(name="id_gincana")
//	private Gincana gincana;
	
	

	public ResultadoEquipeProva() {
	}

//	public ResultadoEquipeProvaPK getId() {
//		return this.id;
//	}
//
//	public void setId(ResultadoEquipeProvaPK id) {
//		this.id = id;
//	}
	
	public boolean salvar() {

		EntityManager entityManager = new JpaUtil().getEntityManager();
		ResultadoEquipeProvaDAO dao = new ResultadoEquipeProvaDAO(entityManager);
//		gincana.addResultadoEquipeProva(this);
		return dao.salvaResultadoEquipeProva(this);

	}
	
	public List<ResultadoEquipeProva> getResultadosPorEquipe(Prova prova) {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		ResultadoEquipeProvaDAO dao = new ResultadoEquipeProvaDAO(entityManager);
		return dao.getResultadoEquipeProvasPorEquipe(prova);
	}

	
	public BigDecimal getPontuacaoAtingida() {
		return this.pontuacaoAtingida;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPontuacaoAtingida(BigDecimal pontuacaoAtingida) {
		this.pontuacaoAtingida = pontuacaoAtingida;
	}

	public Equipe getEquipe() {
		return this.equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Prova getProva() {
		return this.prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

//	public Gincana getGincana() {
//		return gincana;
//	}
//
//	public void setGincana(Gincana gincana) {
//		this.gincana = gincana;
//	}
	
	

}