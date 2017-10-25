package com.loas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.loas.model.LawType;
import com.loas.model.Lawyer;

@Repository("lawyerRepository")
public interface LawyerRepository extends JpaRepository<Lawyer, Integer>, PagingAndSortingRepository<Lawyer, Integer> {
	
}
