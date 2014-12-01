package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import util.JpaUtil;
import dao.EquipeDAO;


/**
 * The persistent class for the equipe database table.
 * 
 */
@Entity
public class Equipe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	@Column(name="nome_padrinho")
	private String nomePadrinho;

	//bi-directional many-to-one association to Cor
	@ManyToOne
	@JoinColumn(name="id_cor")
	private Cor cor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario_lider")
	private Usuario usuarioLider;

	//bi-directional many-to-one association to Gincana
	@ManyToOne
	@JoinColumn(name="id_gincana")
	private Gincana gincana;

	//bi-directional many-to-one association to Membroequipe
	@OneToMany(mappedBy="equipe")
	private List<MembroEquipe> membrosEquipe;

	//bi-directional many-to-one association to Resultadoequipeprova
	@OneToMany(mappedBy="equipe")
	private List<ResultadoEquipeProva> resultadosProvas;

	public Equipe() {
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
		Equipe other = (Equipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public boolean salvar() {

		EntityManager entityManager = new JpaUtil().getEntityManager();
		EquipeDAO dao = new EquipeDAO(entityManager);

		return dao.salvaEquipe(this);

	}

	public List<Equipe> getEquipes() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		EquipeDAO dao = new EquipeDAO(entityManager);
		
		return dao.getEquipes();
	
	}
	
	public List<Equipe> getEquipes(Usuario usuario) {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		EquipeDAO dao = new EquipeDAO(entityManager);
		
		return dao.getEquipes(usuario);
	
	}
	
	public List<Equipe> getEquipes(Gincana gincana) {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		EquipeDAO dao = new EquipeDAO(entityManager);
		
		return dao.getEquipes(gincana);
		
	}
	
	public List<Equipe> getEquipes(Prova prova) {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		EquipeDAO dao = new EquipeDAO(entityManager);
		
		return dao.getEquipesPorProvaFeita(prova);
		
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Usuario getUsuarioLider() {
		return usuarioLider;
	}


	public void setUsuarioLider(Usuario usuarioLider) {
		this.usuarioLider = usuarioLider;
	}


	public void setMembrosEquipe(List<MembroEquipe> membrosEquipe) {
		this.membrosEquipe = membrosEquipe;
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomePadrinho() {
		return this.nomePadrinho;
	}

	public void setNomePadrinho(String nomePadrinho) {
		this.nomePadrinho = nomePadrinho;
	}

	public Cor getCor() {
		return this.cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Usuario getUsuario() {
		return this.usuarioLider;
	}

	public void setUsuario(Usuario usuario) {
		this.usuarioLider = usuario;
	}

	public Gincana getGincana() {
		return this.gincana;
	}

	public void setGincana(Gincana gincana) {
		this.gincana = gincana;
	}

	public List<MembroEquipe> getMembrosEquipe() {
		return this.membrosEquipe;
	}

	public void setMembroequipes(List<MembroEquipe> membrosEquipe) {
		this.membrosEquipe = membrosEquipe;
	}

	public boolean addMembroEquipe(MembroEquipe membroEquipe) {
		boolean adicionou = false;
		if(getMembrosEquipe().size() < gincana.getQtdLimiteMembrosEquipe()){
			getMembrosEquipe().add(membroEquipe);
			membroEquipe.setEquipe(this);
			adicionou = true;
		}else
			adicionou =  false;
		return adicionou;
	}

	public MembroEquipe removeMembroEquipe(MembroEquipe membroEquipe) {
		getMembrosEquipe().remove(membroEquipe);
		membroEquipe.setEquipe(null);

		return membroEquipe;
	}

	public List<ResultadoEquipeProva> getResultadosProvas() {
		return this.resultadosProvas;
	}

	public void setResultadosProvas(List<ResultadoEquipeProva> resultadosProvas) {
		this.resultadosProvas = resultadosProvas;
	}

	public boolean addResultadoProva(ResultadoEquipeProva resultadoProva) {
			getResultadosProvas().add(resultadoProva);
			resultadoProva.setEquipe(this);
		return true;	
	}

	public ResultadoEquipeProva removeResultadoProva(ResultadoEquipeProva resultadoProva) {
		getResultadosProvas().remove(resultadoProva);
		resultadoProva.setEquipe(null);

		return resultadoProva;
	}





	

}