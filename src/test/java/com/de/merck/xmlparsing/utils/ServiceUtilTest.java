package com.de.merck.xmlparsing.utils;

import static org.junit.Assert.*;

import java.util.List;

import javax.xml.xpath.XPathExpression;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.NodeList;

public class ServiceUtilTest {

	private static final String FILE_NAME = "test.docx";
	private static final String FILE_LOCATION = "src/test/resources/test.docx";
	private static final String XPATH = "/root/y1";
	
	ServiceUtil serviceUtil;
	XmlDocumentUtil xmlDocumentUtil;
	
	 @Before
	 public void setUp() {
		 serviceUtil = new ServiceUtil();
		 xmlDocumentUtil = new XmlDocumentUtil();
	 }

	@Test
	public void loadMultipartXmlFile() {		
		MultipartFile multipartFile = serviceUtil.loadMultipartFile(FILE_NAME, FILE_LOCATION);
		
		assertNotNull(multipartFile);
		assertTrue(!multipartFile.isEmpty());
		assertEquals(FILE_NAME, multipartFile.getName());
	}

	@Test
	public void compileXpath() {		
		XPathExpression xpathExpression = serviceUtil.compileXpath(XPATH);
		
		assertNotNull(xpathExpression);
	}
			
	@Test
	public void evaluateXpath() {		
		NodeList nodes = serviceUtil.evaluateXpath(serviceUtil.compileXpath(XPATH),xmlDocumentUtil.toDocument(serviceUtil.loadMultipartFile(FILE_NAME, FILE_LOCATION)));
		
		assertNotNull(nodes);
		assertNotNull(nodes.getLength());
		assertEquals(1, nodes.getLength());
	}
	
	@Test
	public void toElementsValues() {
		NodeList nodes = serviceUtil.evaluateXpath(serviceUtil.compileXpath(XPATH),xmlDocumentUtil.toDocument(serviceUtil.loadMultipartFile(FILE_NAME, FILE_LOCATION)));
		
		List<String> elementValues = serviceUtil.toElementsValues(nodes);
		
		assertNotNull(elementValues);
		assertTrue(!elementValues.isEmpty());
		assertEquals(1, elementValues.size());		
	}
}
