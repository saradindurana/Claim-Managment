package com.cts.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@JsonDeserialize
public class ClaimDetails {
@Length(max=10)
@Id
@Column(name="PK_ClaimId")
@JsonIgnore
private String ClaimId;

@Positive(message = "Estimate loss must be a positive")
@JsonIgnore
private int EstimatedLoss;
@JsonIgnore
private Date DateOfAccident;
@JsonIgnore
private boolean ClaimStatus;

@Column(columnDefinition = "int default 0")
@JsonIgnore
private int AmountApprovedBySurveyor;
@Column(columnDefinition = "boolean default false")

@JsonIgnore
private boolean InsuranceCompanyApproval;
@Column(columnDefinition = "boolean default false")
private boolean WithdrawClaim;
private int SurveyorFees;

@JsonIgnore
@ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
@JoinColumn(name = "FK_Surveyor")
private Surveyor surveyor;


@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "FK_PolicyNo")
private Policy policy;

public String getClaimId() {
	return ClaimId;
}

public void setClaimId(String claimId) {
	ClaimId = claimId;
}

public int getEstimatedLoss() {
	return EstimatedLoss;
}

public void setEstimatedLoss(int estimatedLoss) {
	EstimatedLoss = estimatedLoss;
}

public Date getDateOfAccident() {
	return DateOfAccident;
}

public void setDateOfAccident(Date dateOfAccident) {
	DateOfAccident = dateOfAccident;
}

public boolean isClaimStatus() {
	return ClaimStatus;
}

public void setClaimStatus(boolean claimStatus) {
	ClaimStatus = claimStatus;
}

public int getAmountApprovedBySurveyor() {
	return AmountApprovedBySurveyor;
}

public void setAmountApprovedBySurveyor(int amountApprovedBySurveyor) {
	AmountApprovedBySurveyor = amountApprovedBySurveyor;
}

public boolean isInsuranceCompanyApproval() {
	return InsuranceCompanyApproval;
}

public void setInsuranceCompanyApproval(boolean insuranceCompanyApproval) {
	InsuranceCompanyApproval = insuranceCompanyApproval;
}

public boolean isWithdrawClaim() {
	return WithdrawClaim;
}

public void setWithdrawClaim(boolean withdrawClaim) {
	WithdrawClaim = withdrawClaim;
}

public int getSurveyorFees() {
	return SurveyorFees;
}

public void setSurveyorFees(int surveyorFees) {
	SurveyorFees = surveyorFees;
}

public Surveyor getSurveyor() {
	return surveyor;
}

public void setSurveyor(Surveyor surveyor) {
	this.surveyor = surveyor;
}

public Policy getPolicy() {
	return policy;
}

public void setPolicy(Policy policy) {
	this.policy = policy;
}

public ClaimDetails(@Length(max = 10) String claimId,
		@Positive(message = "Estimate loss must be a positive") int estimatedLoss, Date dateOfAccident,
		boolean claimStatus, int amountApprovedBySurveyor, boolean insuranceCompanyApproval, boolean withdrawClaim,
		int surveyorFees, Surveyor surveyor, Policy policy) {
	super();
	ClaimId = claimId;
	EstimatedLoss = estimatedLoss;
	DateOfAccident = dateOfAccident;
	ClaimStatus = claimStatus;
	AmountApprovedBySurveyor = amountApprovedBySurveyor;
	InsuranceCompanyApproval = insuranceCompanyApproval;
	WithdrawClaim = withdrawClaim;
	SurveyorFees = surveyorFees;
	this.surveyor = surveyor;
	this.policy = policy;
}

public ClaimDetails() {
	super();
	// TODO Auto-generated constructor stub
}


}
