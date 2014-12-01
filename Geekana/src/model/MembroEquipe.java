package model;

import java.io.Serializable;
import javax.persistence.*;

import util.JpaUtil;
import dao.MembroEquipeDAO;


/**
 * The persistent class for the membroequipe database table.
 * 
 */
@Entity
public class MembroEquipe implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String matricula;

	private String nome;

	//bi-directional many-to-one association to Equipe
	@ManyToOne
	@JoinColumn(name="id_equipe")
	private Equipe equipe;

	public MembroEquipe() {
	}
	
	public boolean salvar() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		MembroEquipeDAO dao = new MembroEquipeDAO(entityManager);
		
//		equipe.addMembroEquipe(this);
		return dao.salvaMembroEquipe(this);

	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Equipe getEquipe() {
		return this.equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}