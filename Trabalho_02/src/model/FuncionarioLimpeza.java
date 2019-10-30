package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "funcionario_id")
public class FuncionarioLimpeza extends Funcionario {

	private String cargo;
	private String jornadaTrab;
	@ManyToOne
    @JoinColumn(name="responsavel_id")
    private FuncionarioLimpeza responsavel;

	public FuncionarioLimpeza() {

	}

	public FuncionarioLimpeza(String nome, String endereco, String sexo, String dataAniversario, double salario,
			List<Dependente> dependentes, Departamento departamento, String cargo, String jornadaTrab, FuncionarioLimpeza responsavel) {
		this(0, nome, endereco, sexo, dataAniversario, salario, dependentes, departamento, cargo, jornadaTrab);
		this.cargo = cargo;
		this.jornadaTrab = jornadaTrab;
		this.responsavel = responsavel;
	}

	public FuncionarioLimpeza(int id, String nome, String endereco, String sexo, String dataAniversario, double salario,
			List<Dependente> dependentes, Departamento departamento, String cargo, String jornadaTrab) {
		super(id, nome, endereco, sexo, dataAniversario, salario, dependentes, departamento);
		this.cargo = cargo;
		this.jornadaTrab = jornadaTrab;
		this.responsavel = responsavel;
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
				+ "\nDepartamento" + this.getDepartamento() + "\nRespons�vel" + this.responsavel;
	}

}
