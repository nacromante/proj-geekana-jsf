package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.JpaUtil;
import dao.GincanaDAO;


/**
 * The persistent class for the gincana database table.
 * 
 */
@Entity
public class Gincana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

//	@Temporal(TemporalType.DATE)
	private Integer ano;

	@Temporal(TemporalType.DATE)
	@Column(name="data_fim_inscricoes")
	private Date dataFimInscricoes;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio_inscricoes")
	private Date dataInicioInscricoes;

	@Column(name="qtd_limite_equipes")
	private Integer qtdLimiteEquipes;

	@Column(name="qtd_limite_membros_equipe")
	private Integer qtdLimiteMembrosEquipe;
	
	@OneToMany(mappedBy="gincana")
	private List<Prova> provas;

	//bi-directional many-to-one association to Equipe
	@OneToMany(mappedBy="gincana")
	private List<Equipe> equipes;
	
	@OneToMany
	@JoinTable(name = "gincanaCor", joinColumns = @JoinColumn(name = "id_gincana"), inverseJoinColumns = @JoinColumn(name = "id_cor"))	
	private List<Cor> cores;
	
//	@OneToMany(mappedBy="gincana")
//	private List<ResultadoEquipeProva> resultadoEquipesProvas;
	
	

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
		Gincana other = (Gincana) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Gincana() {
	}
	
	public boolean dentroDoPeriodoDeInscricaoes() {
		Date data = new Date();
		return !data.after(getDataFimInscricoes()) && !data.before(getDataInicioInscricoes());
	}
	
	public boolean salvar() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		GincanaDAO dao = new GincanaDAO(entityManager);
		return dao.salvaGincana(this);
	}
	

	public List<Gincana> getGincanas() {
	    EntityManager entityManager = new JpaUtil().getEntityManager();
		GincanaDAO gincanaDAO = new GincanaDAO(entityManager);
		return gincanaDAO.getGincanas();
	}
	
	public Gincana getGincana(Integer ano) {
	    EntityManager entityManager = new JpaUtil().getEntityManager();
		GincanaDAO gincanaDAO = new GincanaDAO(entityManager);
		return gincanaDAO.getGincana(ano);
	}

	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getQtdLimiteEquipes() {
		return qtdLimiteEquipes;
	}
	public void setQtdLimiteEquipes(Integer qtdLimiteEquipes) {
		this.qtdLimiteEquipes = qtdLimiteEquipes;
	}
	public Integer getQtdLimiteMembrosEquipe() {
		return qtdLimiteMembrosEquipe;
	}
	public void setQtdLimiteMembrosEquipe(Integer qtdLimiteMembrosEquipe) {
		this.qtdLimiteMembrosEquipe = qtdLimiteMembrosEquipe;
	}
	public Date getDataFimInscricoes() {
		return this.dataFimInscricoes;
	}

	public void setDataFimInscricoes(Date dataFimInscricoes) {
		this.dataFimInscricoes = dataFimInscricoes;
	}

	public Date getDataInicioInscricoes() {
		return this.dataInicioInscricoes;
	}

	public void setDataInicioInscricoes(Date dataInicioInscricoes) {
		this.dataInicioInscricoes = dataInicioInscricoes;
	}


	public List<Equipe> getEquipes() {
		return this.equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public boolean addEquipe(Equipe equipe) {
		boolean adicionou = false;
		if(equipes.size() < getQtdLimiteEquipes()){
			getEquipes().add(equipe);
			equipe.setGincana(this);
			adicionou =  true;
		}else
			adicionou =  false;
		return adicionou;
	}
	
	public boolean addProva(Prova prova) {
			getProvas().add(prova);
			prova.setGincana(this);
		return true;
	}
	
//	public boolean addResultadoEquipeProva(ResultadoEquipeProva resultadoEquipeProva) {
//		getResultadoEquipesProvas().add(resultadoEquipeProva);
//		resultadoEquipeProva.setGincana(this);
//		return true;
//	}

	public Equipe removeEquipe(Equipe equipe) {
		getEquipes().remove(equipe);
		equipe.setGincana(null);

		return equipe;
	}
	public List<Cor> getCores() {
		return cores;
	}
	public void setCores(List<Cor> cores) {
		this.cores = cores;
	}
	public List<Prova> getProvas() {
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
//	public List<ResultadoEquipeProva> getResultadoEquipesProvas() {
//		return resultadoEquipesProvas;
//	}
//	public void setResultadoEquipesProvas(
//			List<ResultadoEquipeProva> resultadoEquipesProvas) {
//		this.resultadoEquipesProvas = resultadoEquipesProvas;
//	}

	

	

	



}