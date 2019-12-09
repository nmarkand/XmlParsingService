package com.de.merck.xmlparsing.utils;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.de.merck.xmlparsing.exception.XmlParsingServiceException;

public class XmlDocumentUtil {
	
	 public Document toDocument(MultipartFile file) {
        DocumentBuilderFactory factory = getDocumentBuilderFactory();
        Document doc = parseFile(file, factory);
        return doc;
	}
	 
	public DocumentBuilderFactory getDocumentBuilderFactory() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
		return factory;
	}
	
	public DocumentBuilder toDocumentBuilder(DocumentBuilderFactory factory) {
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XmlParsingServiceException("Error while getting DocumentBuilder ", e);
		}
		return builder;
	}
	
	public Document parseFile(MultipartFile file, DocumentBuilderFactory factory) {
		DocumentBuilder builder = toDocumentBuilder(factory);		
        Document doc;
		try {
			doc = builder.parse(file.getInputStream());
		} catch (SAXException e) {
			throw new XmlParsingServiceException("Error while paring file ", e);
		} catch (IOException e) {
			throw new XmlParsingServiceException("Error while paring file ", e);
		}
		return doc;
	}
}
