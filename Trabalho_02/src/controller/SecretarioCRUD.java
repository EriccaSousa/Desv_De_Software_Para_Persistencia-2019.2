package controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.SecretarioDAO;
import dao.SecretarioJPA_DAO;
import model.Secretario;

public class SecretarioCRUD {
	static Scanner read = new Scanner(System.in);

	public static Secretario findByNome() {
		Secretario secretario = null;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
		EntityManager em = emf.createEntityManager();

		System.out.println("Informe o nome: ");
		String nome = read.nextLine();
		
		try {
			secretario = (Secretario) em.createQuery("SELECT s FROM Secretario s WHERE s.nome LIKE :nome")
					.setParameter("nome", nome + "%").getSingleResult();
		} catch (Exception e) {
			if(secretario == null) {
				System.out.println("Não existe secretario cadastrado no banco ou não foi encontrado");
			}
			e.printStackTrace();
		}

		return secretario;
	}

	public static void findAll() {
		SecretarioDAO secretarioDAO = new SecretarioJPA_DAO();

		List<Secretario> secretarios = secretarioDAO.findAll();
		secretarioDAO.close();
		if(secretarios == null) {
			System.out.println("Não existe nenhum secretário cadastrado no banco");
			return;
		}
		System.out.println("\n");
		for (Secretario secretario : secretarios) {
			System.out.println(secretario);
		}
		System.out.println("\n");
	}

}
