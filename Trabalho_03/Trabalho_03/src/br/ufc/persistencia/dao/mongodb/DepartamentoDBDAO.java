package br.ufc.persistencia.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.model.Bean;
import br.ufc.persistencia.model.Departamento;
import br.ufc.persistencia.util.MongoDBUtil;

public class DepartamentoDBDAO extends GenericMongoDBDAO<Departamento> implements FindDAO<Departamento> {

	public DepartamentoDBDAO() {
		super("departamentos");
	}

	@Override
	public Departamento findById(Object id) {
		BasicDBObject query = new BasicDBObject();

		query.put("_id", new ObjectId(id.toString()));
		Document doc = collection.find(query).first();

		return Departamento.fromDocument(doc);
	}

	@Override
	public List<Departamento> findByNome(String nome) {
		BasicDBObject query = new BasicDBObject();

		query.put("nome", new ObjectId(nome.toString()));
		Document doc = collection.find(query).first();
		List<Departamento> departamentos = new ArrayList<Departamento>();

		return departamentos;
	}

	@Override
	public List<Departamento> findAll() {
		List<Departamento> departamentos = new ArrayList<Departamento>();

		MongoIterable<Document> docs = collection.find();

		for (Document doc : docs) {
			departamentos.add(Departamento.fromDocument(doc));
		}
		return departamentos;
	}

}
