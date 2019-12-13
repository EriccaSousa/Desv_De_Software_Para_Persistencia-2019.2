package br.ufc.persistencia.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public abstract class Bean {

	protected ObjectId id;

	public Bean() {
	}

	public Bean(ObjectId id) {
		this.id = id;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public abstract String toJson();

	public Document toDocument() {
		return Document.parse(this.toJson());
	}
}
