package br.ufc.qxd.persist.conteudo_8_mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.ufc.qxd.persist.conteudo_8_mongodb.model.User;

public class Principal {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost");
		
		
		// exemplo01ListarDatabasesECollectionsDeUmBanco(mongoClient);
		// exemplo02InserirUm(mongoClient);
		// exemplo03InserirMuitos(mongoClient);
		// exemplo04Buscar(mongoClient);
		// exemplo05BuscarPorId(mongoClient);
		// exemplo06Atualizar(mongoClient);
		// exemplo07Deletar(mongoClient);
		exemplo08PegarOId(mongoClient);
		
		
		mongoClient.close();
		System.out.println("Fim MongoDB");
	}
	
	public static void exemplo08PegarOId(MongoClient mongoClient){
		MongoDatabase db = mongoClient.getDatabase("per");		
		MongoCollection<Document> coll = db.getCollection("users");
		
		Document doc = coll.find(Document.parse("{nome: 'Anderson'}")).first();
		
		ObjectId id = doc.getObjectId("_id");
		String idString = id.toString();
		ObjectId novoId = new ObjectId(idString);
		
		System.out.println(id);
		System.out.println(novoId);
	}
	
	public static void exemplo07Deletar(MongoClient mongoClient){
		MongoDatabase db = mongoClient.getDatabase("per");		
		MongoCollection<Document> coll = db.getCollection("users");
		
		coll.deleteOne(Document.parse("{nome: 'Ronaldo'}"));
	}
	
	public static void exemplo06Atualizar(MongoClient mongoClient) {
		MongoDatabase db = mongoClient.getDatabase("per");		
		MongoCollection<Document> coll = db.getCollection("users");
		
		// buscar um usuario por id
		/*
		Document doc = coll.find(Document.parse("{_id: new ObjectId('5dc03b4d500245581e0283a8')}")).first();
		User user = User.fromDocument(doc);
		*/
		
		User user = new User("Mary", "m@outlook.com", 19);
		
		Document update = new Document();
		update.append("$set", user.toDocument());
		
		coll.updateOne(Document.parse("{nome: 'Mary'}"), update);
	}
	
	public static void exemplo05BuscarPorId(MongoClient mongoClient) {
		MongoDatabase db = mongoClient.getDatabase("per");		
		MongoCollection<Document> coll = db.getCollection("users");
		
		
		for(Document doc : coll.find(Document.parse("{_id: new ObjectId('5dc03b4d500245581e0283a8')}"))) {
		// for(Document doc : coll.find(Document.parse("{nome: 'Ronaldo'}"))) {
			User user = User.fromDocument(doc);
			System.out.println(user);
		}
	}
	
	public static void exemplo04Buscar(MongoClient mongoClient) {
		
		MongoDatabase db = mongoClient.getDatabase("per");		
		MongoCollection<Document> coll = db.getCollection("users");
		
		System.out.println("Número de elementos: " + coll.countDocuments());
		
		for(Document doc : coll.find()) {
			User user = User.fromDocument(doc);
			System.out.println(user);
		}
	}
	
	public static void exemplo03InserirMuitos(MongoClient mongoClient) {
		
		MongoDatabase db = mongoClient.getDatabase("per");		
		MongoCollection<Document> coll = db.getCollection("users");
		
		List<Document> docs = new ArrayList<Document>();
		
		docs.add(new User("Fernanda", "f@gmail.com", 23).toDocument());
		docs.add(new User("Allisson", "a@gmail.com", 22).toDocument());
		docs.add(new User("Anderson", "anderson@gmail.com", 25).toDocument());
		
		coll.insertMany(docs);
	}
	
	public static void exemplo02InserirUm(MongoClient mongoClient) {
		
		MongoDatabase db = mongoClient.getDatabase("per");
		
		MongoCollection<Document> coll = db.getCollection("users");
		
		/*
		User user = new User("Ronaldo", "ronaldinho10@hotmail.com", 20);
		Document doc = Document.parse(user.toJson());
		coll.insertOne(doc);
		*/
		
		coll.insertOne(new User("Mary", "m@outlook.com", 19).toDocument());
	}
	
	public static void exemplo01ListarDatabasesECollectionsDeUmBanco(MongoClient mongoClient) {
		for(String nomeDB : mongoClient.listDatabaseNames()) {
			System.out.println(nomeDB);
			MongoDatabase db = mongoClient.getDatabase(nomeDB);
			for(String s : db.listCollectionNames()) {
				System.out.println("\t" + s);
			}
		}
	}
}
