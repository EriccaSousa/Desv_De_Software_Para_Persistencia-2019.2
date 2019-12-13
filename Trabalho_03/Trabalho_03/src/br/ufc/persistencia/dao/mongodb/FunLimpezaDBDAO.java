package br.ufc.persistencia.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.model.FuncionarioLimpeza;

public class FunLimpezaDBDAO extends GenericMongoDBDAO<FuncionarioLimpeza> implements FindDAO<FuncionarioLimpeza> {

	public FunLimpezaDBDAO() {
		super("funcionariosLimpeza");
	}

	@Override
	public FuncionarioLimpeza findById(Object id) {
		// TODO Auto-generated method stub
		BasicDBObject query = new BasicDBObject();

		query.put("_id", new ObjectId(id.toString()));
		Document doc = collection.find(query).first();

		return FuncionarioLimpeza.fromDocument(doc);
	}

	@Override
	public List<FuncionarioLimpeza> findByNome(String nome) {
		BasicDBObject query = new BasicDBObject();

		query.put("nome", new ObjectId(nome.toString()));
		Document doc = collection.find(query).first();
		List<FuncionarioLimpeza> funlimpeza = new ArrayList<FuncionarioLimpeza>();

		return funlimpeza;
	}

	@Override
	public List<FuncionarioLimpeza> findAll() {
		List<FuncionarioLimpeza> funlimpeza = new ArrayList<FuncionarioLimpeza>();

		MongoIterable<Document> docs = collection.find();

		for (Document doc : docs) {
			funlimpeza.add(FuncionarioLimpeza.fromDocument(doc));
		}
		return funlimpeza;
	}

}
