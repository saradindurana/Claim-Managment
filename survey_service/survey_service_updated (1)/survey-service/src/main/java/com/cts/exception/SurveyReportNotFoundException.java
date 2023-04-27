package com.cts.exception;

public class SurveyReportNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SurveyReportNotFoundException() {
		super();
	
	}

	public SurveyReportNotFoundException(String message) {
		super(message);
		
	}

}
