package zoologico;

import java.util.Date;

public class Animal {
	private int codAnimal;
	private String nomeAnimal;
	private String especies;
	private String sexo;
	private Date dataNasc;
	private int quantJaula;
	private String alimentacao1;
	private String alimentacao2;
	private String alimentacao3;
	private int halimentacao1;//horario
	private int halimentacao2;//horario
	private int halimentacao3;//horario
	public int getCodAnimal() {
		return codAnimal;
	}
	public void setCodAnimal(int codAnimal) {
		this.codAnimal = codAnimal;
	}
	public String getNomeAnimal() {
		return nomeAnimal;
	}
	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}
	public String getEspecies() {
		return especies;
	}
	public void setEspecies(String especies) {
		this.especies = especies;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public int getQuantJaula() {
		return quantJaula;
	}
	public void setQuantJaula(int quantJaula) {
		this.quantJaula = quantJaula;
	}
	public String getAlimentacao1() {
		return alimentacao1;
	}
	public void setAlimentacao1(String alimentacao1) {
		this.alimentacao1 = alimentacao1;
	}
	public String getAlimentacao2() {
		return alimentacao2;
	}
	public void setAlimentacao2(String alimentacao2) {
		this.alimentacao2 = alimentacao2;
	}
	public String getAlimentacao3() {
		return alimentacao3;
	}
	public void setAlimentacao3(String alimentacao3) {
		this.alimentacao3 = alimentacao3;
	}
	public int getHalimentacao1() {
		return halimentacao1;
	}
	public void setHalimentacao1(int halimentacao1) {
		this.halimentacao1 = halimentacao1;
	}
	public int getHalimentacao2() {
		return halimentacao2;
	}
	public void setHalimentacao2(int halimentacao2) {
		this.halimentacao2 = halimentacao2;
	}
	public int getHalimentacao3() {
		return halimentacao3;
	}
	public void setHalimentacao3(int halimentacao3) {
		this.halimentacao3 = halimentacao3;
	}

	public Animal(int codAnimal, String nomeAnimal, String especies, String sexo, Date dataNasc, int quantJaula,
			String alimentacao1, String alimentacao2, String alimentacao3, int halimentacao1, int halimentacao2,
			int halimentacao3) {
		super();
		this.codAnimal = codAnimal;
		this.nomeAnimal = nomeAnimal;
		this.especies = especies;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
		this.quantJaula = quantJaula;
		this.alimentacao1 = alimentacao1;
		this.alimentacao2 = alimentacao2;
		this.alimentacao3 = alimentacao3;
		this.halimentacao1 = halimentacao1;
		this.halimentacao2 = halimentacao2;
		this.halimentacao3 = halimentacao3;
	}
	public Animal() {
		super();
	}
	@Override
	public String toString() {
		return "Animal [codAnimal=" + codAnimal + ", nomeAnimal=" + nomeAnimal + ", especies=" + especies + ", sexo=" + sexo
				+ ", dataNasc=" + dataNasc + ", quantJaula=" + quantJaula + ", alimentacao1=" + alimentacao1
				+ ", alimentacao2=" + alimentacao2 + ", alimentacao3=" + alimentacao3 + ", halimentacao1=" + halimentacao1
				+ ", halimentacao2=" + halimentacao2 + ", halimentacao3=" + halimentacao3 + "]";
	}


}