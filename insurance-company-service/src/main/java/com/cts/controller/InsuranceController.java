package com.cts.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.ClaimDetails;
import com.cts.entity.Fees;
import com.cts.entity.Policy;
import com.cts.entity.Surveyor;
import com.cts.model.AmountResponse;
import com.cts.model.ClaimResponse;
import com.cts.model.PolicyResponse;
import com.cts.service.InsuranceService;
import com.cts.service.MyUserDetailService;
import com.cts.model.AuthenticationRequest;
import com.cts.model.AuthenticationResponse;
import com.cts.util.*;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class InsuranceController{
@Autowired
private InsuranceService insuranceService;

@Autowired
private AuthenticationManager authManager;
@Autowired
private JwtUtil jwtUtil;

@Autowired
private MyUserDetailService service;

//
//@GetMapping("/hello")
//public String hello()
//{
//	return "Hello World";
//}


@PostMapping("/authenticate")
public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest req) throws Exception
{
	try
	{
		authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));
	}
	catch(Exception e)
	{
		throw new Exception("Incorrect Username and Password",e);
	}
	final UserDetails userDetails=service.loadUserByUsername(req.getUsername());
	final String jwt=jwtUtil.generateToken(userDetails);
	return ResponseEntity.ok(new AuthenticationResponse(jwt));
}









@PostMapping("/claims/addClaim")
public ClaimDetails addNewClaim(@RequestBody ClaimResponse claimResponse) {
	log.info("claimResponse");
	log.info("inside addClaim");
	return insuranceService.addNewClaim(claimResponse);
	
}

@GetMapping("/claims")
public List<ClaimDetails> getAllClaims() {
	
	return insuranceService.getAllClaims();
}
@GetMapping("claims/surveyors/{amount}")
public List<Surveyor> getAllSurveyor(@PathVariable int amount) {
	return insuranceService.findEligibleSurveyors(amount);
}
@PutMapping("/claims/{id}/update")
public ClaimDetails updateClaim(@RequestBody ClaimResponse claimResponse,@PathVariable String id) {
	return insuranceService.updateClaim(claimResponse,id);
}

@GetMapping("/claims/{id}")
public ClaimDetails getClaimById(@PathVariable String id) {
	return insuranceService.getClaimById(id);
}
@GetMapping("/releasefund/{id}")
public ClaimDetails releaseFund(@PathVariable String id) {
	return insuranceService.releasefund(id);
}
@GetMapping("/surveyorfees/{id}")
public Fees releaseFees(@PathVariable String id) {
	return insuranceService.releaseFees(id);
}

@GetMapping("/surveyors")
public List<Surveyor> getAllSurveyor() {
	return insuranceService.getAllSurveyors();
}

//Not needed for me
//@PostMapping("/policies/new")
//public Policy addNewPolicy(@RequestBody PolicyResponse policyResponse) {
//	System.out.println(policyResponse);
//	return insuranceService.addNewPolicy(policyResponse);
//}

//Not needed for me
//@GetMapping("/policies")
//public List<Policy> getAllPolicies() {
//	return insuranceService.getAllPolicies();
//}
//For IRDA
//@GetMapping("/claims/date")
//public List<ClaimDetails> getAllPendingClaimsByDate(@RequestParam int month,@RequestParam int year){
//	return insuranceService.getAllPendingClaimsByDate(month,year);
//}
//For IRDA
//@GetMapping("/claims/amount/date")
//public List<AmountResponse> getAllAmountProvided(@RequestParam int month,@RequestParam int year){
//	return insuranceService.getAllAmountProvided(month,year);
//}
//@PostMapping("/policies/{id}")
//public String raiseClaimRequest(@PathVariable String id) {
//	return insuranceService.raiseClaimRequest(id);
//}

@GetMapping("/claims/unsurvyed")
public List<ClaimDetails>  getClaimsWithoutSurveyor() {
	return insuranceService.getClaimsWithoutSurveyor();
}
@GetMapping("/claims/survyed")
public List<ClaimDetails>  getClaimsAllocatedSurveyor() {
	return insuranceService.getClaimsAllocatedSurveyor();
}
}
