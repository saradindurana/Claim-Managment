package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.entity.Surveyor;
@Repository
public interface SurveyorRepo extends JpaRepository<Surveyor, Integer>{
	@Query("select c from Surveyor c where EstimateLimit>=:amount")
    List<Surveyor> findEligibleSurveyors(int amount);
}
