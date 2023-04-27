package com.cts.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SurveyReport {
	
	@Id
	private String claim_Id;
	private String policy_No;
	@Min(value = 0, message = "Labour charges cannot be negative")
	private int labour_Charges;
	@Min(value = 0, message = "Spare part cost cannot be negative")
	private int parts_Cost;
	@Min(value = 1, message = "Policy class should be greater than 0")
	private int policy_Class;
	private int depreciation_Cost;
	@Min(value = 0, message = "Total claim amount cannot be negative")
	private int total_Amount;
	
//	@AssertTrue(message = "Labour charges cannot be greater than the spare parts cost")
//	public boolean isLabourChargesValid() {
//		return Labour_Charges <= Parts_Cost;
//	}

}
