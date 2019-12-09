package com.de.merck.xmlparsing.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

public class XmlDocumentUtilTest {

	private static final String FILE_NAME = "test.docx";
	private static final String FILE_LOCATION = "src/test/resources/test.docx";
	
	XmlDocumentUtil xmlDocumentUtil;
	MultipartFile multipartFile;
		
	 @Before
	 public void setUp() throws Exception {
		 xmlDocumentUtil = new XmlDocumentUtil();
		 multipartFile = new MockMultipartFile(FILE_NAME, new FileInputStream(new File(FILE_LOCATION)));
	 }
	 
	@Test
	public void toDocument() throws Exception {
		Document document = xmlDocumentUtil.toDocument(multipartFile);
		
		assertNotNull(document);
		assertEquals("root", document.getDocumentElement().getTagName());
	}
	
	@Test
	public void getDocumentBuilderFactory() throws Exception {
		DocumentBuilderFactory factory = xmlDocumentUtil.getDocumentBuilderFactory();
		
		assertNotNull(factory);
		assertTrue(factory.isNamespaceAware());
	}
	
	@Test
	public void toDocumentBuilder() {
		DocumentBuilder builder = xmlDocumentUtil.toDocumentBuilder(xmlDocumentUtil.getDocumentBuilderFactory());
		
		assertNotNull(builder);
		assertTrue(builder.isNamespaceAware());
	}
	
	@Test
	public void parseFile() throws FileNotFoundException, IOException {
		DocumentBuilderFactory factory = xmlDocumentUtil.getDocumentBuilderFactory();
		
		Document document = xmlDocumentUtil.parseFile(multipartFile, factory);
		
		assertNotNull(document);
		assertEquals("root", document.getDocumentElement().getTagName());
	}
}
