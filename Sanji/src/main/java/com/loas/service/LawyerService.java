package com.loas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.loas.model.Lawyer;

public interface LawyerService {
	void addLawyer(Lawyer lawyer);

	Iterable<Lawyer> findAllLawyers();

	List<Lawyer> getAllLawyers();

//	List<Lawyer> search(String q);

//	Lawyer findLawyerById(int id);

	Lawyer findOne(int id);

	void delete(int id);

	void save(Lawyer lawyer);

	Page<Lawyer> findAllPageable(Pageable pageable);

}
