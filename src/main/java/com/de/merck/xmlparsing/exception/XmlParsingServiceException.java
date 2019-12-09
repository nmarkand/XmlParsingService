package com.de.merck.xmlparsing.exception;

public class XmlParsingServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public XmlParsingServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public XmlParsingServiceException(String message) {
		super(message);
	}
}