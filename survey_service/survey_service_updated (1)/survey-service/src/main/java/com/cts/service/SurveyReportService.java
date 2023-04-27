package com.cts.service;

import com.cts.entity.SurveyReport;

public interface SurveyReportService {
	
	String addSurvey(SurveyReport surveyReport);
	
	SurveyReport getServey(String claim_Id);
	
	SurveyReport updateSurvey(String claim_Id, SurveyReport surveyReport);
}
