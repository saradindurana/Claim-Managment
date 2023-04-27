package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Fees;

@Repository
public interface FeesRepo extends JpaRepository<Fees, Integer>{
}
