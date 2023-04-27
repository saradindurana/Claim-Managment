package com.cts.model;

import java.util.Date;

import com.cts.entity.Policy;
import com.cts.entity.Surveyor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClaimResponse {
	private String ClaimId;
	private String PolicyNo;
	private int EstimatedLoss;
	private Date DateOfAccident;
	private boolean ClaimStatus;
//	private int SurveyorId;
	private int AmountApprovedBySurveyor;
	private boolean InsuranceCompanyApproval;
	private boolean WithdrawClaim;
	private int SurveyorFees;
	private Surveyor surveyor;
	private Policy policy;

}
