package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import util.JpaUtil;
import dao.ProvaDAO;
import dao.ProvaDAO;


/**
 * The persistent class for the prova database table.
 * 
 */
@Entity
public class Prova implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	@Column(name="pontuacao_maxima")
	private BigDecimal pontuacaoMaxima;

	//bi-directional many-to-one association to Statusprova
	@ManyToOne
	@JoinColumn(name="id_status")
	private StatusProva statusProva;
	
	@ManyToOne
	@JoinColumn(name="id_gincana")
	private Gincana gincana;

	public Prova() {
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prova other = (Prova) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	



	public boolean salvar() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		ProvaDAO dao = new ProvaDAO(entityManager);
		
		gincana.addProva(this);
		return dao.salvaProva(this);

	}
	
	public List<Prova> getProvas() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		ProvaDAO dao = new ProvaDAO(entityManager);
		
		return dao.getProvas();
	
	}
	
	public List<Prova> getProvas(Gincana gincana) {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		ProvaDAO dao = new ProvaDAO(entityManager);
		
		return dao.getProvas(gincana);
		
	}
	
//	public List<Equipe> getEquipes() {
//		return new Equipe().getEquipes(this);
//	}
	
	public List<ResultadoEquipeProva> getResultadosEquipeProva() {
		return new ResultadoEquipeProva().getResultadosPorEquipe(this);
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPontuacaoMaxima() {
		return this.pontuacaoMaxima;
	}

	public void setPontuacaoMaxima(BigDecimal pontuacaoMaxima) {
		this.pontuacaoMaxima = pontuacaoMaxima;
	}

	
	public StatusProva getStatusProva() {
		return statusProva;
	}

	public void setStatusProva(StatusProva statusProva) {
		this.statusProva = statusProva;
	}

	public Gincana getGincana() {
		return gincana;
	}

	public void setGincana(Gincana gincana) {
		this.gincana = gincana;
	}
	

	
}