package com.cts.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraintvalidation.*;

@Entity
public class Fees {
@Id
@Column(name="PK_FeeId")
private int FeeId;
private int EstimatedStartLimit;
private int EstimateEndLimit;
private int fees;
public int getFeeId() {
	return FeeId;
}
public void setFeeId(int feeId) {
	FeeId = feeId;
}
@Column(columnDefinition = "int default 5000")
public int getEstimatedStartLimit() {
	return EstimatedStartLimit;
}
public void setEstimatedStartLimit(int estimatedStartLimit) {
	EstimatedStartLimit = estimatedStartLimit;
}
public int getEstimateEndLimit() {
	return EstimateEndLimit;
}
public void setEstimateEndLimit(int estimateEndLimit) {
	EstimateEndLimit = estimateEndLimit;
}
public int getFees() {
	return fees;
}
public void setFees(int fees) {
	this.fees = fees;
}

}
