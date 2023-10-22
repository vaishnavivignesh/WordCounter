package com.sample.wordcounter.model;

import java.util.List;

public class Word {

	public Word(String value, List<String> valueList) {
		this.value = value;
		this.valueList = valueList;
	}

	private String value;

	private List<String> valueList;

	public List<String> getValueList() {
		return valueList;
	}

	public void setValueList(List<String> valueList) {
		this.valueList = valueList;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
