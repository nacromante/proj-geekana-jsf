package model;

import java.io.Serializable;
import javax.persistence.*;

import util.JpaUtil;
import dao.CorDAO;
import dao.GincanaDAO;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the cor database table.
 * 
 */
@Entity
public class Cor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	public Cor() {
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
		Cor other = (Cor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public List<Cor> getCores(Gincana gincana) {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		CorDAO corDAO = new CorDAO(entityManager);
		if(gincana != null){
			List<Cor> lista = retiraCoresDeOutrasEquipes(corDAO.getCores(gincana),gincana);
			System.out.println("lsita de cores: " + lista);
			System.out.println("cores da gincana: " + gincana.getCores());
			System.out.println("cores de outras equipes: " + corDAO.getCores(gincana));
			return lista;
		}else
			return corDAO.getCores();
	}
	
	public List<Cor> getCores() {
		EntityManager entityManager = new JpaUtil().getEntityManager();
		CorDAO corDAO = new CorDAO(entityManager);
		return corDAO.getCores();
	}
	
	public List<Cor> retiraCoresDeOutrasEquipes(List<Cor> cores, Gincana gincana) {
		List<Cor> coresIneditas = new ArrayList<Cor>();
		if(!cores.isEmpty()){
			for (Cor cor : gincana.getCores()) {
				if(!cores.contains(cor))
					coresIneditas.add(cor);
			}
		}else
			coresIneditas.addAll(gincana.getCores());
		System.out.println("cores da gincana: " + gincana.getCores());
		return coresIneditas;
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
}