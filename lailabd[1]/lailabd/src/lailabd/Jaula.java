package zoologico;

import java.util.Date;

public class Jaula {
	private int numJaula;
	private int tamanho;
	private Date limpeza;//ultima limpeza
	private String manutencao;
	public int getNumJaula() {
		return numJaula;
	}
	public void setNumJaula(int numJaula) {
		this.numJaula = numJaula;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public Date getLimpeza() {
		return limpeza;
	}
	public void setLimpeza(Date limpeza) {
		this.limpeza = limpeza;
	}
	public String getManutencao() {
		return manutencao;
	}
	public void setManutencao(String manutencao) {
		this.manutencao = manutencao;
	}

	public Jaula(int numJaula, int tamanho, Date limpeza, String manutencao) {
		super();
		this.numJaula = numJaula;
		this.tamanho = tamanho;
		this.limpeza = limpeza;
		this.manutencao = manutencao;
	}
	public Jaula() {
		super();
	}
	@Override
	public String toString() {
		return "Jaula [numJaula=" + numJaula + ", tamanho=" + tamanho + ", limpeza=" + limpeza + ", manutencao="
				+ manutencao + "]";
	}

}