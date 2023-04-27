package com.cts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.entity.ClaimDetails;
import com.cts.entity.Fees;
import com.cts.entity.Policy;
import com.cts.entity.Surveyor;
import com.cts.model.AmountResponse;
import com.cts.model.ClaimResponse;
import com.cts.model.PolicyResponse;


public interface InsuranceService {
public List<ClaimDetails> getAllClaims();
public List<Surveyor> getAllSurveyors();
public ClaimDetails addNewClaim( ClaimResponse claimResponse);
public ClaimDetails updateClaim( ClaimResponse claimResponse,String id);
public Policy addNewPolicy(PolicyResponse policyResponse);
public List<Policy> getAllPolicies();
public List<ClaimDetails> getAllPendingClaimsByDate(@RequestParam int month,@RequestParam int year);
public List<AmountResponse> getAllAmountProvided(@RequestParam int month,@RequestParam int year);
//public String raiseClaimRequest(@PathVariable String id);
public List<Surveyor> findEligibleSurveyors(int amount);
public ClaimDetails getClaimById( String id);
public  List<ClaimDetails> getClaimsWithoutSurveyor();
public List<ClaimDetails>  getClaimsAllocatedSurveyor();
public Fees releaseFees( String id);
public ClaimDetails releasefund(String id);

}
