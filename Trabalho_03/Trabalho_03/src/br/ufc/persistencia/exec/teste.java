package br.ufc.persistencia.exec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufc.persistencia.CRUD.SecretarioCRUD;
import br.ufc.persistencia.controller.DepartamentoController;
import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.dao.GenericDAO;
import br.ufc.persistencia.dao.mongodb.GenericMongoDBDAO;
import br.ufc.persistencia.dao.mongodb.SecretarioDBDAO;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.model.Dependente;
import br.ufc.persistencia.model.Secretario;
import br.ufc.persistencia.util.DataUtil;

public class teste {

	static GenericDAO<Secretario> secretarioGenericDAO = new GenericMongoDBDAO<Secretario>("secretarios");
	static FindDAO<Secretario> secretarioFindDAO = new SecretarioDBDAO();
	public static void main(String[] args) {
		cadastrarSecretario();
		
		SecretarioCRUD.findAll(secretarioFindDAO);
		//System.out.println(SecretarioCRUD.findById(secretarioFindDAO, "5dddb2cc5017ee0175f2366c"));
	}
	
	public static void cadastrarSecretario() {
		try {

			Date dataAniver = DataUtil.formatData("14/10/2000");

			System.out.print("Departamento: ");
			Departamento departamento = DepartamentoController.findByIdDepartamento();

			
			Secretario secretario = new Secretario("Assucena", "Quixada", "F", dataAniver, 2000, null, departamento,
						"superior");
			SecretarioCRUD.cadastrar(secretarioGenericDAO, secretario);


		} catch (Exception e) {
			System.out.println("Erro!");
			e.printStackTrace();
		}
	}
	
	
	
}
