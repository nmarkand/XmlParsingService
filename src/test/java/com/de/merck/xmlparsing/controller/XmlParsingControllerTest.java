package com.de.merck.xmlparsing.controller;

import com.de.merck.xmlparsing.domain.XmlParsingRequest;
import com.de.merck.xmlparsing.domain.XmlParsingResponse;
import com.de.merck.xmlparsing.service.XmlParsingService;
import io.prometheus.client.Counter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class XmlParsingControllerTest {
	
	private static final String FILE_NAME = "Merck_TaskToSolve.docx";
	private static final String FILE_LOCATION = "src/test/resources/Merck_TaskToSolve.docx";
	private static final String XPATH = "/root/x1/x2[@id='a1']";
	
	XmlParsingService xmlParsingService;
	XmlParsingController xmlParsingController;

	@Mock
	Counter localCounter;

	@Mock
	Counter globalCounter;

	@Before
    public void setUp() {
		xmlParsingService = mock(XmlParsingService.class);
		xmlParsingController = new XmlParsingController(xmlParsingService, localCounter, globalCounter);
    }

	@Test
	public void parseXmlByX2ElementXpath() {
		when(xmlParsingService.parseXmlByX2ElementXpath()).thenReturn(new ArrayList<String>());
        
        ResponseEntity<XmlParsingResponse> result = xmlParsingController.parseXmlByX2ElementXpath();
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void parseXmlByXpath() throws Exception {
		MultipartFile multipartFile = new MockMultipartFile(FILE_NAME, new FileInputStream(new File(FILE_LOCATION)));		
		XmlParsingRequest xmlParsingRequest = new XmlParsingRequest(multipartFile, XPATH);
		
		when(xmlParsingService.parseXmlByXpath(multipartFile, XPATH)).thenReturn(new ArrayList<String>());
        
        ResponseEntity<XmlParsingResponse> result = xmlParsingController.parseXmlByXpath(xmlParsingRequest);
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
