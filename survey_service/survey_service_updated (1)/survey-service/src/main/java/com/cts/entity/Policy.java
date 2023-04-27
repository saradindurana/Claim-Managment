package com.cts.entity;



import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.TableGenerator;
import jakarta.validation.constraints.PastOrPresent;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@OneToMany(fetch=FetchType.LAZY,mappedBy="policy")
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
}
