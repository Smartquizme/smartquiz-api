package me.smartquiz.api.domain;

import java.util.List;
import java.util.UUID;

public class Question {

	private UUID id;
	private String text;
	private List<String> options;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

}
