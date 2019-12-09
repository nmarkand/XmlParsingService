package com.de.merck.xmlparsing.domain;

import java.util.List;

public class XmlParsingResponse {

	private List<String> elementValues;
	private Integer count;
	
	public XmlParsingResponse(List<String> elementValues, Integer count) {
		this.elementValues = elementValues;
		this.count = count;
	}

	public List<String> getElementValues() {
		return elementValues;
	}

	public void setElementValues(List<String> elementValues) {
		this.elementValues = elementValues;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
