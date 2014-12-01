package model;

import java.math.BigDecimal;

public class ResultadoTotal {
	
	private Equipe equipe;
	private BigDecimal pontuacaoAtingida;
	
	public ResultadoTotal(Equipe equipe, BigDecimal pontuacaoAtingida) {
		this.equipe = equipe;
		this.pontuacaoAtingida = pontuacaoAtingida;
	}
	
	

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public BigDecimal getPontuacaoAtingida() {
		return pontuacaoAtingida;
	}

	public void setPontuacaoAtingida(BigDecimal pontuacaoAtingida) {
		this.pontuacaoAtingida = pontuacaoAtingida;
	}
	
	

}
