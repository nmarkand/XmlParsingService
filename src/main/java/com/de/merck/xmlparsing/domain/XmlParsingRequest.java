package com.de.merck.xmlparsing.domain;

import org.springframework.web.multipart.MultipartFile;

public class XmlParsingRequest {
	
	private MultipartFile file;
	private String xpathString;
	
	public XmlParsingRequest() {
	}

	public XmlParsingRequest(MultipartFile file, String xpathString) {
		this.file = file;
		this.xpathString = xpathString;
	}

	public MultipartFile getFile() {
		return file;
	}
	
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public String getXpathString() {
		return xpathString;
	}
	
	public void setXpathString(String xpathString) {
		this.xpathString = xpathString;
	}
}
