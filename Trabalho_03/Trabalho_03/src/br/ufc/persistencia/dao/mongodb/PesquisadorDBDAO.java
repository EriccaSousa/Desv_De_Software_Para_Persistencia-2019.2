package br.ufc.persistencia.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.model.Pesquisador;

public class PesquisadorDBDAO extends GenericMongoDBDAO<Pesquisador> implements FindDAO<Pesquisador> {

	public PesquisadorDBDAO() {
		super("pesquisadores");
	}

	@Override
	public Pesquisador findById(Object id) {
		BasicDBObject query = new BasicDBObject();

		query.put("_id", new ObjectId(id.toString()));
		Document doc = collection.find(query).first();

		return Pesquisador.fromDocument(doc);
	}

	@Override
	public List<Pesquisador> findByNome(String nome) {
		BasicDBObject query = new BasicDBObject();

		query.put("nome", new ObjectId(nome.toString()));
		Document doc = collection.find(query).first();
		List<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();

		return pesquisadores;
	}

	@Override
	public List<Pesquisador> findAll() {
		List<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();

		MongoIterable<Document> docs = collection.find();

		for (Document doc : docs) {
			pesquisadores.add(Pesquisador.fromDocument(doc));
		}
		return pesquisadores;
	}

}
