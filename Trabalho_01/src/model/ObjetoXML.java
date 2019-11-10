package model;

import java.util.List;

import model.Tag;

public class ObjetoXML {
	private List<Tag> tags;

	public ObjetoXML() {

	}

	public ObjetoXML(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "ObjetoXML [tags=" + tags + "]";
	}
}
