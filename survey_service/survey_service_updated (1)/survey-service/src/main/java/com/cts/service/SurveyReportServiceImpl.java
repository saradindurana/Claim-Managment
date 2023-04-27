package com.cts.service;


import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.exception.SurveyReportNotFoundException;
import com.cts.entity.ClaimDetails;
import com.cts.entity.SurveyReport;
import com.cts.repository.ClaimDetailsRepo;
import com.cts.repository.SurveyReportRepository;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SurveyReportServiceImpl implements SurveyReportService{
	
	@Autowired
	private SurveyReportRepository surveyReportRepository;
	@Autowired
	private ClaimDetailsRepo claimDetailsRepo;

	@Override
	public String addSurvey(SurveyReport surveyReport) {
		System.out.println(surveyReport);
		int totalAmount=surveyReport.getLabour_Charges()+
				surveyReport.getParts_Cost()-
				surveyReport.getPolicy_Class()-
				surveyReport.getDepreciation_Cost();
		System.out.println(totalAmount);
		System.out.println(surveyReport.getClaim_Id());
		System.out.println(surveyReport.getPolicy_No());
		surveyReport.setTotal_Amount(totalAmount);
		//System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		log.info("add survey");
		ClaimDetails claimDetails = claimDetailsRepo.findById(surveyReport.getClaim_Id()).get();
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		System.out.println(claimDetails);
		claimDetails.setAmountApprovedBySurveyor(totalAmount);
		claimDetails.setClaimStatus(true);
		claimDetailsRepo.save(claimDetails);
		surveyReportRepository.save(surveyReport);
		return surveyReport.getClaim_Id();
		
	}

	@Override
	public SurveyReport getServey(String claim_Id) {
		return surveyReportRepository
				.findById(claim_Id)
				.orElseThrow(()-> new SurveyReportNotFoundException("Survey not found"));
	}

	@Override
	public SurveyReport updateSurvey(String claim_Id, SurveyReport surveyReport) {
		Optional<SurveyReport> optSurvey = surveyReportRepository.findById(claim_Id);
		if(!optSurvey.isPresent()) {
			throw new SurveyReportNotFoundException("Survey not found");
		}
		
		SurveyReport survey = optSurvey.get();
		
		if(surveyReport.getDepreciation_Cost() != 0) {
			survey.setDepreciation_Cost(surveyReport.getDepreciation_Cost());
		}
		if(surveyReport.getLabour_Charges() != 0) {
			survey.setLabour_Charges(surveyReport.getLabour_Charges());
		}
		if(surveyReport.getParts_Cost() != 0) {
			survey.setParts_Cost(surveyReport.getParts_Cost());
		}
		if(surveyReport.getPolicy_Class() != 0) {
			survey.setPolicy_Class(surveyReport.getPolicy_Class());
		}
		
//		if(surveyReport.getTotal_Amount() != 0) {
//			survey.setTotal_Amount(surveyReport.getTotal_Amount());
//		}
//		if(surveyReport.getPolicy_No() != null) {
//			survey.setPolicy_No(surveyReport.getPolicy_No());
//		}
		int totalAmount=surveyReport.getLabour_Charges()+
				surveyReport.getParts_Cost()-
				surveyReport.getPolicy_Class()-
				surveyReport.getDepreciation_Cost();
		survey.setTotal_Amount(totalAmount);
		ClaimDetails claimDetails = claimDetailsRepo.findById(claim_Id).get();
		claimDetails.setAmountApprovedBySurveyor(totalAmount);
		claimDetailsRepo.save(claimDetails);	
		surveyReportRepository.save(survey);		
		return (survey);
	}

}
