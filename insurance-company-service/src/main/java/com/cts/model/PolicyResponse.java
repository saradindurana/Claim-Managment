package com.cts.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.cts.entity.Surveyor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PolicyResponse {
	private String PolicyNo;

	private String InsuredFirstName;

	private String InsuredLastName;

	private Date DateOfInsurance;
	private String EmailId;
	private String VehicleNo;
	private boolean status;

	


}
