package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Policy;
@Repository
public interface PolicyRepo extends JpaRepository<Policy, String>{
}
