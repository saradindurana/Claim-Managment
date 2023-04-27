package com.cts.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cts.entity.ClaimDetails;
import com.cts.model.AmountResponse;
import com.cts.model.UpdateModel;
@Repository
public interface ClaimDetailsRepo extends JpaRepository<ClaimDetails, String>{
//    @Query("select p from ClaimDetails p where year(p.DateOfAccident) = ?1 and month(p.DateOfAccident) = ?2")
	@Query("select c from ClaimDetails c where year(c.DateOfAccident)=:year and month(c.DateOfAccident)=:month and c.ClaimStatus=true")
    List<ClaimDetails> findAllByShipmentDate(int year, int month);
	@Query("select c from ClaimDetails c where year(c.DateOfAccident)=:year and month(c.DateOfAccident)=:month")
    List<ClaimDetails> amtApprovedBySurveyor(int year, int month);
	@Query("select c from ClaimDetails c where c.surveyor!=null")
	List<ClaimDetails> surveyorAllocated();
	@Query("select c from ClaimDetails c where c.surveyor=null")
	List<ClaimDetails> surveyorNotAllocated();
}
