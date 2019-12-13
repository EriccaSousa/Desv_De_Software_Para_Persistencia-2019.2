package br.ufc.persistencia.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.model.Projeto;
import br.ufc.persistencia.model.Secretario;

public class ProjetoDBDAO extends GenericMongoDBDAO<Projeto> implements FindDAO<Projeto> {

	public ProjetoDBDAO() {
		super("projeto");
		// TODO Auto-generated constructor stub
	}

	public Projeto findById(Object id) {
		try {
			BasicDBObject query = new BasicDBObject();

			query.put("_id", new ObjectId(id.toString()));
			Document doc = collection.find(query).first();

			return Projeto.fromDocument(doc);
		} catch (Exception e) {
			System.out.println("\nErro na busca.\n");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Projeto> findByNome(String nome) {
		BasicDBObject query = new BasicDBObject();

		query.put("nome", new ObjectId(nome.toString()));
		Document doc = collection.find(query).first();
		List<Projeto> projeto = new ArrayList<Projeto>();

		return projeto;
	}

	@Override
	public List<Projeto> findAll() {
		try {
			List<Projeto> projeto = new ArrayList<Projeto>();

			MongoIterable<Document> docs = collection.find();

			for (Document doc : docs) {
				projeto.add(Projeto.fromDocument(doc));
			}
			return projeto;
		} catch (Exception e) {
			System.out.println("\nErro na busca.\n");
			e.printStackTrace();
		}
		return null;
	}
	

}
