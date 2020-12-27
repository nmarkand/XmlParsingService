package com.de.merck.xmlparsing.controller;

import com.de.merck.xmlparsing.domain.XmlParsingRequest;
import com.de.merck.xmlparsing.domain.XmlParsingResponse;
import com.de.merck.xmlparsing.service.XmlParsingService;
import io.prometheus.client.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parseXml")
public class XmlParsingController {

	private static final Logger log = LoggerFactory.getLogger(XmlParsingController.class);
    private Counter localCounter;
    private Counter globalCounter;
    private XmlParsingService xmlParsingService;
    
    @Autowired
    public XmlParsingController(XmlParsingService xmlParsingService, Counter localCounter, Counter globalCounter) {
        this.xmlParsingService = xmlParsingService;
        this.localCounter = localCounter;
        this.globalCounter = globalCounter;
    }

    @RequestMapping(path = "byX2ElementXpath", method = RequestMethod.GET)
    public ResponseEntity<XmlParsingResponse> parseXmlByX2ElementXpath() {
        // Both global and local counter increase their values here.
        // localCounter is not available outside the scope of XmlParsingService as it is a singleton bean.
        localCounter.inc();
        globalCounter.inc();
        log.info("Controller parseXmlByX2ElementXpath");
        List<String> parseResponse = xmlParsingService.parseXmlByX2ElementXpath();
        log.info("Received parsing response");
        XmlParsingResponse response = new XmlParsingResponse(parseResponse, parseResponse.size());
        log.info("Returning parsing response");
        return ResponseEntity.ok().body(response);
    }  
    
    @RequestMapping(path = "byXpath", method = RequestMethod.POST)
    public ResponseEntity<XmlParsingResponse> parseXmlByXpath(@RequestBody final XmlParsingRequest xmlParsingRequest) {
        localCounter.inc();
        globalCounter.inc();
        log.info("Controller parseXmlByXpath");
        List<String> parseResponse = xmlParsingService.parseXmlByXpath(xmlParsingRequest.getFile(), xmlParsingRequest.getXpathString());
        log.info("Received parsing response");
        XmlParsingResponse response = new XmlParsingResponse(parseResponse, parseResponse.size());
        log.info("Returning parsing response");
        return ResponseEntity.ok().body(response);
    }
}
