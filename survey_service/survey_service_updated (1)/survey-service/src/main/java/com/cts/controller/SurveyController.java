package com.cts.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.SurveyReport;
import com.cts.service.SurveyReportService;

@CrossOrigin
@RestController
public class SurveyController {
	
	@Autowired
	private SurveyReportService surveyReportService;
	
	@PostMapping("/api/surveyors/new")
    public ResponseEntity<String> addSurvey(@RequestBody SurveyReport surveyReport) {
		String result = surveyReportService.addSurvey(surveyReport);
		ResponseEntity<String> res= new ResponseEntity<>(result,HttpStatus.CREATED);
		return res;	
    }
	
	@GetMapping("/api/survey/{claim_Id}")
	public SurveyReport getSurvey(@PathVariable String claim_Id) {
		return surveyReportService.getServey(claim_Id);
	}
	
	@PutMapping("/api/survey/{claim_Id}")
	public SurveyReport updateClaim(@PathVariable String claim_Id, @RequestBody SurveyReport surveyReport) {
		System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		System.out.println(surveyReport);
		
		return surveyReportService.updateSurvey(claim_Id,surveyReport);
	}
}
