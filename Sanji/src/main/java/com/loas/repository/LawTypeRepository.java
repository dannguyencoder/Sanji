package com.loas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loas.model.LawType;

@Repository("lawTypeRepository")
public interface LawTypeRepository extends JpaRepository<LawType, String> {

}
