package com.cts.service;

import java.util.*;

import javax.management.RuntimeErrorException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.entity.ClaimDetails;
import com.cts.entity.Fees;
import com.cts.entity.Policy;
import com.cts.entity.Surveyor;
import com.cts.model.AmountResponse;
import com.cts.model.ClaimResponse;
import com.cts.model.PolicyResponse;
import com.cts.repository.ClaimDetailsRepo;
import com.cts.repository.PolicyRepo;
import com.cts.repository.SurveyorRepo;

import lombok.extern.slf4j.Slf4j;

import com.cts.repository.FeesRepo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
@Slf4j
public class InsuranceServiceeImpl implements InsuranceService {
	@Autowired
	ClaimDetailsRepo claimDetailsRepo;
	@Autowired
	SurveyorRepo surveyorRepo;
	@Autowired
	PolicyRepo policyRepo;
	@Autowired
	FeesRepo feesRepo;

	@Override
	public List<ClaimDetails> getAllClaims() {
		return claimDetailsRepo.findAll();

	}

	@Override
	public List<Surveyor> getAllSurveyors() {
		return surveyorRepo.findAll();
	}

	@Override
	public ClaimDetails addNewClaim(ClaimResponse claimResponse) {
		ClaimDetails claimDetails = new ClaimDetails();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		String policyno = claimResponse.getPolicyNo().substring(0, 4);
		String year = dateFormat.format(claimResponse.getDateOfAccident()).substring(0, 4);
		String ClaimId = "CL" + policyno + year;
		claimResponse.setClaimId("CL" + policyno + year);
		String PolicyId = claimResponse.getPolicyNo();
		Optional<Policy> policy = policyRepo.findById(PolicyId);
		Policy policy1 = new Policy();
		policy1.setPolicyNo(policy.get().getPolicyNo());
		policy1.setInsuredFirstName(policy.get().getInsuredFirstName());
		policy1.setInsuredLastName(policy.get().getInsuredLastName());
		policy1.setDateOfInsurance(policy.get().getDateOfInsurance());
		policy1.setEmailId(policy.get().getEmailId());
		policy1.setVehicleNo(policy.get().getVehicleNo());
		policy1.setStatus(policy.get().isStatus());
		claimResponse.setPolicy(policy1);
		claimResponse.setClaimStatus(true);
		if (!policy.isPresent()) {
			throw new RuntimeException("Enter Policy Id");
		} else {
			Optional<ClaimDetails> claim = claimDetailsRepo.findById(ClaimId);
			if (claim.isPresent()) {
				throw new RuntimeException("MaximumClaimLimitReachedException");
			} else {
				BeanUtils.copyProperties(claimResponse, claimDetails);
				claimDetailsRepo.save(claimDetails);
				return claimDetails;
			}
		}
	}

	@Override
	public ClaimDetails updateClaim(ClaimResponse claimResponse, String id) {
		log.info(claimResponse.toString());
		ClaimDetails claimDetails = claimDetailsRepo.findById(id).get();
		claimDetails.setClaimStatus(claimResponse.isClaimStatus());
		claimDetails.setAmountApprovedBySurveyor(claimResponse.getAmountApprovedBySurveyor());
		claimDetails.setInsuranceCompanyApproval(claimResponse.isInsuranceCompanyApproval());
		if (claimResponse.getSurveyor() != null) {

			claimDetails.setSurveyor(claimResponse.getSurveyor());
		}
		if (claimResponse.getAmountApprovedBySurveyor() > 0) {

			claimDetails.setClaimStatus(false);
		}
		claimDetailsRepo.save(claimDetails);
		return claimDetails;
	}

	@Override
	public Policy addNewPolicy(PolicyResponse policyResponse) {
		Policy policy = new Policy();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		String lastname = policyResponse.getInsuredLastName().substring(0, 2);

		String vehicleno = policyResponse.getVehicleNo().substring(0, 3);

		String year = dateFormat.format(policyResponse.getDateOfInsurance()).substring(2, 4);
		policyResponse.setPolicyNo(lastname + vehicleno + year);
		BeanUtils.copyProperties(policyResponse, policy);

		policyRepo.save(policy);
		return policy;
	}

	@Override
	public List<Policy> getAllPolicies() {
		return policyRepo.findAll();
	}

	@Override
	public List<ClaimDetails> getAllPendingClaimsByDate(int month, int year) {
		// TODO Auto-generated method stub
		return claimDetailsRepo.findAllByShipmentDate(year, month);

	}

	@Override
	public List<AmountResponse> getAllAmountProvided(int month, int year) {
//		amountResponse=addclaimDetailsRepo.amtApprovedBySurveyor(year,month);

		AmountResponse amountResponse = new AmountResponse();
		List<AmountResponse> amount = new ArrayList<>();
//		amountResponse.add(null);
		List<ClaimDetails> ClaimDetails = claimDetailsRepo.amtApprovedBySurveyor(year, month);
		log.info(ClaimDetails.toString());
		for (int i = 0; i < ClaimDetails.size(); i++) {
			amountResponse.setAmountApprovedBySurveyor(ClaimDetails.get(i).getAmountApprovedBySurveyor());
			amount.add(amountResponse);
		}
		for (AmountResponse aa : amount) {
			log.info(aa.toString());
		}

		return amount;

	}

	@Override
	public List<Surveyor> findEligibleSurveyors(int amount) {
		return surveyorRepo.findEligibleSurveyors(amount);

	}

	@Override
	public ClaimDetails getClaimById(String id) {
		ClaimDetails claimDetails = claimDetailsRepo.findById(id).get();
		return claimDetails;
	}

	@Override
	public List<ClaimDetails> getClaimsWithoutSurveyor() {
		return claimDetailsRepo.surveyorNotAllocated();
	}

	@Override
	public List<ClaimDetails> getClaimsAllocatedSurveyor() {
		return claimDetailsRepo.surveyorAllocated();
	}

	@Override
	public Fees releaseFees(String id) {

		ClaimDetails claimDetails = claimDetailsRepo.findById(id).get();

		String lastSixDigits = id.substring(id.length() - 4);
		int num = Integer.parseInt(lastSixDigits);
		int amount = claimDetails.getAmountApprovedBySurveyor();
		int feeId = num + amount;

		if (claimDetails.getSurveyor() != null && claimDetails.getAmountApprovedBySurveyor() != 0) {
			Fees fees = new Fees();
			fees.setFeeId(feeId);
			fees.setFees(claimDetails.getSurveyorFees());

			int limit = claimDetails.getSurveyor().getEstimateLimit();
			if (limit > 5000 && limit < 10000) {

				fees.setFees(1000);
				fees.setEstimatedStartLimit(5000);
				claimDetails.setSurveyorFees(1000);
			} else if (limit >= 10000 && limit < 20000) {
				fees.setFees(2000);
				fees.setEstimatedStartLimit(1000);
				claimDetails.setSurveyorFees(2000);
			} else if (limit >= 20000 && limit <= 70000) {
				fees.setFees(7000);
				fees.setEstimatedStartLimit(20000);
				claimDetails.setSurveyorFees(7000);
			}
			fees.setEstimateEndLimit(claimDetails.getSurveyor().getEstimateLimit());
			feesRepo.save(fees);
			claimDetailsRepo.save(claimDetails);
			return fees;
		} else {

			throw new RuntimeErrorException(null, "Surveyor not allocated or amount not fixed by surveyor yet");
		}

	}

	@Override
	public ClaimDetails releasefund(String id) {

		ClaimDetails claimDetails = claimDetailsRepo.findById(id).get();

		claimDetails.setClaimStatus(false);
		claimDetails.setInsuranceCompanyApproval(true);

		claimDetailsRepo.save(claimDetails);
		return claimDetails;
	}

}
