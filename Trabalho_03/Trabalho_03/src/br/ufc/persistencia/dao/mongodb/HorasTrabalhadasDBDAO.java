package br.ufc.persistencia.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;
import br.ufc.persistencia.dao.FindDAO;
import br.ufc.persistencia.model.HorasTrabalhadas;

public class HorasTrabalhadasDBDAO extends GenericMongoDBDAO<HorasTrabalhadas> implements FindDAO<HorasTrabalhadas>{

	public HorasTrabalhadasDBDAO() {
		super("horastrabalhadas");
		// TODO Auto-generated constructor stub
	}

	@Override
	public HorasTrabalhadas findById(Object id) {
		// TODO Auto-generated method stub
		BasicDBObject query = new BasicDBObject();

		query.put("_id", new ObjectId(id.toString()));
		Document doc = collection.find(query).first();

		return HorasTrabalhadas.fromDocument(doc);
	}

	@Override
	public List<HorasTrabalhadas> findByNome(String nome) {
		BasicDBObject query = new BasicDBObject();

		query.put("nome", new ObjectId(nome.toString()));
		Document doc = collection.find(query).first();
		List<HorasTrabalhadas> horastrabalhadas = new ArrayList<HorasTrabalhadas>();

		return horastrabalhadas;
	}

	@Override
	public List<HorasTrabalhadas> findAll() {
		List<HorasTrabalhadas> horastrabalhadas = new ArrayList<HorasTrabalhadas>();

		MongoIterable<Document> docs = collection.find();

		for (Document doc : docs) {
			horastrabalhadas.add(HorasTrabalhadas.fromDocument(doc));
		}
		return horastrabalhadas;
	}

}
