package br.ufc.persistencia.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.model.Secretario;

public class SecretarioDBDAO extends GenericMongoDBDAO<Secretario> implements FindDAO<Secretario> {

	public SecretarioDBDAO() {
		super("secretarios");
	}

	@Override
	public Secretario findById(Object id) {
		try {
			BasicDBObject query = new BasicDBObject();

			query.put("_id", new ObjectId(id.toString()));
			Document doc = collection.find(query).first();

			return Secretario.fromDocument(doc);
		} catch (Exception e) {
			System.out.println("\nErro na busca.\n");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Secretario> findByNome(String nome) {
		BasicDBObject query = new BasicDBObject();

		query.put("nome", new ObjectId(nome.toString()));
		Document doc = collection.find(query).first();
		List<Secretario> secretarios = new ArrayList<Secretario>();

		return secretarios;
	}

	@Override
	public List<Secretario> findAll() {
		try {
			List<Secretario> secretarios = new ArrayList<Secretario>();

			MongoIterable<Document> docs = collection.find();

			for (Document doc : docs) {
				secretarios.add(Secretario.fromDocument(doc));
			}
			return secretarios;
		} catch (Exception e) {
			System.out.println("\nErro na busca.\n");
			e.printStackTrace();
		}
		return null;
	}

}
