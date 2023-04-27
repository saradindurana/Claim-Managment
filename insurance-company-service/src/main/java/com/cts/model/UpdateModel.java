package com.cts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModel {
	
	private int SurveyorId;
	private boolean ClaimStatus;
	private boolean InsuranceCompanyApproval;

}
