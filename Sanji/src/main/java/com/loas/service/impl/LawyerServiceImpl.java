package com.loas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loas.model.Lawyer;
import com.loas.repository.LawyerRepository;
import com.loas.service.LawyerService;

@Service("lawyerService")
public class LawyerServiceImpl implements LawyerService {

	@Autowired
	private LawyerRepository lawyerRepository;
	
	@Override
	public void addLawyer(Lawyer lawyer) {
		lawyerRepository.save(lawyer);

	}

	@Override
	public Iterable<Lawyer> findAllLawyers() {
		return lawyerRepository.findAll();
		
	}

	@Override
	public List<Lawyer> getAllLawyers() {
		return lawyerRepository.findAll();
	}

//	@Override
//	public List<Lawyer> search(String q) {
//		return lawyerRepository.findByLawyerLastName(q);
//	}

//	@Override
//	public Lawyer findLawyerById(int id) {
//		return lawyerRepository.findByLawyer_Id(id);
//	}

	@Override
	public Lawyer findOne(int id) {
		return lawyerRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		lawyerRepository.delete(id);

	}

	@Transactional
	@Override
	public void save(Lawyer lawyer) {
		lawyerRepository.saveAndFlush(lawyer);

	}

	@Transactional
	@Override
	public Page<Lawyer> findAllPageable(Pageable pageable) {
		return lawyerRepository.findAll(pageable);
	}

}
