package dao;

import java.util.List;

import model.Funcionario;
import model.Secretario;

public interface SecretarioDAO extends GenericDAO<Secretario> {

	public List<Secretario> findByNome(String nome);

	public List<Secretario> findById(int id);

	public void save(Funcionario funcionario);

}
