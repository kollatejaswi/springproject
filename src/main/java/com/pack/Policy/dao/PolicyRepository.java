package com.pack.Policy.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pack.Policy.model.Policy;



public interface PolicyRepository extends CrudRepository<Policy, Long>{
	List<Policy> findByName(String type);
}
