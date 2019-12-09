package com.de.merck.xmlparsing.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.de.merck.xmlparsing.service.XmlParsingService;

public class XmlParsingServiceTest {
	
	private static final String FILE_NAME = "Merck_TaskToSolve.docx";
	private static final String FILE_LOCATION = "src/test/resources/Merck_TaskToSolve.docx";
	private static final String XPATH = "/root/x1/x2[@id='a1']";
	
	XmlParsingService xmlParsingService;
	MultipartFile multipartFile;
	
	 @Before
	 public void setUp() throws Exception {
		 xmlParsingService = new XmlParsingService();
		 multipartFile = new MockMultipartFile(FILE_NAME, new FileInputStream(new File(FILE_LOCATION)));			
	 }

	@Test
	public void parseXmlByX2ElementXpath() {
		List<String> elementValues = xmlParsingService.parseXmlByX2ElementXpath();
		
		assertNotNull(elementValues);
		assertTrue(!elementValues.isEmpty());
		assertEquals(3, elementValues.size());
		assertTrue(Arrays.asList(new String[] { "a", "c", "h"})
				.stream().anyMatch(element -> elementValues.contains(element)));
	}
	
	@Test
	public void parseXmlByXpath() {
		List<String> elementValues = xmlParsingService.parseXmlByXpath(multipartFile, XPATH);
		
		assertNotNull(elementValues);
		assertTrue(!elementValues.isEmpty());
		assertEquals(3, elementValues.size());
		assertTrue(Arrays.asList(new String[] { "a", "c", "h"})
				.stream().anyMatch(element -> elementValues.contains(element)));
	}
}
