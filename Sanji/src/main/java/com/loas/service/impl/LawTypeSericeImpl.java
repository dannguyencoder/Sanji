package com.loas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loas.model.LawType;
import com.loas.repository.LawTypeRepository;
import com.loas.service.LawTypeService;

@Service("lawTypeService")
public class LawTypeSericeImpl implements LawTypeService {
	
	@Autowired
	private LawTypeRepository lawTypeRepository;

	@Override
	public List<LawType> getAllLawTypes() {
		return lawTypeRepository.findAll();
	}

}
