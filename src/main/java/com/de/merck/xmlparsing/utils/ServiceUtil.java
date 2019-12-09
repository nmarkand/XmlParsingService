package com.de.merck.xmlparsing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.de.merck.xmlparsing.exception.XmlParsingServiceException;

public class ServiceUtil {
	
	public MultipartFile loadMultipartFile(String fileName, String fileLocation) {
		MultipartFile file;		
		try {
			file = new MockMultipartFile(fileName, new FileInputStream(new File(fileLocation)));
		} catch (IOException e) {
			throw new XmlParsingServiceException("Exception while loading xml file ", e);
		}
		return file;
	}
	
	public XPathExpression compileXpath(String xpathExpression) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expr;
		try {
			expr = xpath.compile(xpathExpression);
		} catch (XPathExpressionException e) {
			throw new XmlParsingServiceException("Error in xpath compilation ", e);
		}
		return expr;
	}
	
	public NodeList evaluateXpath(XPathExpression xpathExpression, Document document) {		  
        NodeList nodes;
		try {
			nodes = (NodeList) xpathExpression.evaluate(document, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			throw new XmlParsingServiceException("Error in evaluating document ", e);
		}
		return nodes;
	}

	public List<String> toElementsValues(NodeList nodes) {
		List<String> elementValues = new ArrayList<>();		
		Stream.iterate(0, i -> i + 1)
		.limit (nodes.getLength())
		.map (nodes::item).map(i -> elementValues.add(i.getTextContent()))
		.collect(Collectors.toList());
		return elementValues;
	}
}
