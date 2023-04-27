package com.cts.model;

import javax.persistence.Entity;

import com.cts.entity.Fees;

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
public class AmountResponse {
	private int AmountApprovedBySurveyor;
}
