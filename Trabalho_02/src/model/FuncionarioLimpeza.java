package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class FuncionarioLimpeza extends Funcionario {

	private String cargo;
	private String jornadaTrab;

	public FuncionarioLimpeza() {

	}

	public FuncionarioLimpeza(String nome, String endereco, String sexo, String dataAniversario, double salario,
			String cargo, String jornadaTrab) {
		this(0, nome, endereco, sexo, dataAniversario, salario, cargo, jornadaTrab);
	}

	public FuncionarioLimpeza(int id, String nome, String endereco, String sexo, String dataAniversario, double salario,
			String cargo, String jornadaTrab) {
		super(id, nome, endereco, sexo, dataAniversario, salario);
		this.cargo = cargo;
		this.jornadaTrab = jornadaTrab;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getJornadaTrab() {
		return jornadaTrab;
	}

	public void setJornadaTrab(String jornadaTrab) {
		this.jornadaTrab = jornadaTrab;
	}

	@Override
	public String toString() {
		return "Função: Secretario" + "\nId: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereço: "
				+ this.getEndereco() + "\nSexo: " + this.getSexo() + "\nData Aniversário: " + this.getDataAniversario()
				+ "\nSalário: " + this.getSalario() + "\nCargo: " + this.cargo + "\nJornadaTrab: " + this.jornadaTrab
				+ "\n";
	}

}
