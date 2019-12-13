package br.ufc.persistencia.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.model.Dependente;
import br.ufc.persistencia.model.FuncionarioLimpeza;

public class DependenteDBDAO extends GenericMongoDBDAO<Dependente> implements FindDAO<Dependente> {

	public DependenteDBDAO() {
		super("dependentes");
	}

	@Override
	public Dependente findById(Object id) {
		// TODO Auto-generated method stub
		BasicDBObject query = new BasicDBObject();

		query.put("_id", new ObjectId(id.toString()));
		Document doc = collection.find(query).first();

		return Dependente.fromDocument(doc);
	}

	@Override
	public List<Dependente> findByNome(String nome) {
		BasicDBObject query = new BasicDBObject();

		query.put("nome", new ObjectId(nome.toString()));
		Document doc = collection.find(query).first();
		List<Dependente> dependentes = new ArrayList<Dependente>();

		return dependentes;
	}

	@Override
	public List<Dependente> findAll() {
		List<Dependente> dependentes = new ArrayList<Dependente>();

		MongoIterable<Document> docs = collection.find();

		for (Document doc : docs) {
			dependentes.add(Dependente.fromDocument(doc));
		}
		return dependentes;
	}
}
