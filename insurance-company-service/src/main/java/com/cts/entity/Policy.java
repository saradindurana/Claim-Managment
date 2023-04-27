package com.cts.entity;



import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity

public class Policy {
@Id
@Length(max=7,min=7)
@Column(name="PK_PolicyNo")
private String PolicyNo;
@Length(min=5)
private String InsuredFirstName;
@Length(min=5)
private String InsuredLastName;
@PastOrPresent(message = "Date of insurance must not be earlier than current date")
private Date DateOfInsurance;
private String EmailId;
private String VehicleNo;
@Column(columnDefinition = "boolean default true")
private boolean status;

@JsonIgnore
@OneToMany(fetch=FetchType.EAGER,mappedBy="policy")
private List<ClaimDetails> claimDetails;

public String getPolicyNo() {
	return PolicyNo;
}
public void setPolicyNo(String policyNo) {
	PolicyNo = policyNo;
}
public String getInsuredFirstName() {
	return InsuredFirstName;
}
public void setInsuredFirstName(String insuredFirstName) {
	InsuredFirstName = insuredFirstName;
}
public String getInsuredLastName() {
	return InsuredLastName;
}
public void setInsuredLastName(String insuredLastName) {
	InsuredLastName = insuredLastName;
}
public Date getDateOfInsurance() {
	return DateOfInsurance;
}
public void setDateOfInsurance(Date dateOfInsurance) {
	DateOfInsurance = dateOfInsurance;
}
public String getEmailId() {
	return EmailId;
}
public void setEmailId(String emailId) {
	EmailId = emailId;
}
public String getVehicleNo() {
	return VehicleNo;
}
public void setVehicleNo(String vehicleNo) {
	VehicleNo = vehicleNo;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public List<ClaimDetails> getClaimDetails() {
	return claimDetails;
}
public void setClaimDetails(List<ClaimDetails> claimDetails) {
	this.claimDetails = claimDetails;
}
@Override
public String toString() {
	return "Policy [PolicyNo=" + PolicyNo + ", InsuredFirstName=" + InsuredFirstName + ", InsuredLastName="
			+ InsuredLastName + ", DateOfInsurance=" + DateOfInsurance + ", EmailId=" + EmailId + ", VehicleNo="
			+ VehicleNo + ", status=" + status + ", claimDetails=" + claimDetails + "]";
}
public Policy(@Length(max = 7, min = 7) String policyNo, @Length(min = 5) String insuredFirstName,
		@Length(min = 5) String insuredLastName,
		@PastOrPresent(message = "Date of insurance must not be earlier than current date") Date dateOfInsurance,
		String emailId, String vehicleNo, boolean status, List<ClaimDetails> claimDetails) {
	super();
	PolicyNo = policyNo;
	InsuredFirstName = insuredFirstName;
	InsuredLastName = insuredLastName;
	DateOfInsurance = dateOfInsurance;
	EmailId = emailId;
	VehicleNo = vehicleNo;
	this.status = status;
	this.claimDetails = claimDetails;
}
public Policy() {
	super();
	// TODO Auto-generated constructor stub
}
}
