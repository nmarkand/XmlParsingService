package com.de.merck.xmlparsing.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.de.merck.xmlparsing.domain.XmlParsingRequest;
import com.de.merck.xmlparsing.domain.XmlParsingResponse;
import com.de.merck.xmlparsing.service.XmlParsingService;

@RestController
@RequestMapping("/parseXml")
public class XmlParsingController {
	
	private static final Logger log = LoggerFactory.getLogger(XmlParsingController.class);
  
	XmlParsingService xmlParsingService;
    
    @Autowired
    public XmlParsingController(XmlParsingService xmlParsingService) {
        this.xmlParsingService = xmlParsingService;
    }
    
    @RequestMapping(path = "byX2ElementXpath", method = RequestMethod.GET)
    public ResponseEntity<XmlParsingResponse> parseXmlByX2ElementXpath() {
        log.info("Controller parseXmlByX2ElementXpath");
        List<String> parseResponse = xmlParsingService.parseXmlByX2ElementXpath();
        log.info("Received parsing response");
        XmlParsingResponse response = new XmlParsingResponse(parseResponse, parseResponse.size());
        log.info("Returning parsing response");
        return ResponseEntity.ok().body(response);
    }  
    
    @RequestMapping(path = "byXpath", method = RequestMethod.POST)
    public ResponseEntity<XmlParsingResponse> parseXmlByXpath(@RequestBody final XmlParsingRequest xmlParsingRequest) {
        log.info("Controller parseXmlByXpath");
        List<String> parseResponse = xmlParsingService.parseXmlByXpath(xmlParsingRequest.getFile(), xmlParsingRequest.getXpathString());
        log.info("Received parsing response");
        XmlParsingResponse response = new XmlParsingResponse(parseResponse, parseResponse.size());
        log.info("Returning parsing response");
        return ResponseEntity.ok().body(response);
    }   
}