package com.de.merck.xmlparsing.service;

import com.de.merck.xmlparsing.utils.ServiceUtil;
import com.de.merck.xmlparsing.utils.XmlDocumentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Service
public class XmlParsingService {
	
	private static final Logger log = LoggerFactory.getLogger(XmlParsingService.class);
	
	XmlDocumentUtil xmlDocumentUtil;
	ServiceUtil serviceUtil;
		
	@Autowired
	public XmlParsingService() {
		this.xmlDocumentUtil = new XmlDocumentUtil();
		this.serviceUtil = new ServiceUtil();
	}
	
	/**
	 * 
	 * @return List<String> xml elements values where element parsed for nodes with name “x2” and id=“a1” that are direct children 
		of nodes with name “x1”.
	 */
	public List<String> parseXmlByX2ElementXpath() {		
		String xpath = "/root/x1/x2[@id='a1']";
		String fileName = "Merck_TaskToSolve.docx";
		String fileLocation = "src/main/resources/Merck_TaskToSolve.docx";
		
		log.info("Loading MultipartFile, contains data to parse");
		MultipartFile file = serviceUtil.loadMultipartFile(fileName, fileLocation);
		return parseXmlByXpath(file, xpath);
    }

	/**
	 * 
	 * @param file
	 * @param xpath
	 * @return List<String> xml elements values
	 */
	public List<String> parseXmlByXpath(MultipartFile file, String xpath) {
		log.info("Compile xpath");
        XPathExpression xpathExpression = serviceUtil.compileXpath(xpath);    
        log.info("Transform MultipartFile to Document");
        Document document = xmlDocumentUtil.toDocument(file);
        log.info("Evaluating xpath on xml document");      
        NodeList nodes = serviceUtil.evaluateXpath(xpathExpression, document);                      
		log.info("Finding xml element's values by node list");
        return serviceUtil.toElementsValues(nodes);
    }
}