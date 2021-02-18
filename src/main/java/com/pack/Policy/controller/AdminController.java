package com.pack.Policy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.Policy.dao.AdminRepository;
import com.pack.Policy.dao.PolicyRepository;
import com.pack.Policy.model.Admin;
import com.pack.Policy.model.Policy;




@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AdminController {
	@Autowired	
	AdminRepository repository;
	@Autowired
   PolicyRepository policyrepository;
	@PostMapping(value = "/customers")
	public ResponseEntity<Admin> postCustomer(@RequestBody Admin admin) {
	  try {
	    Admin _customer = repository.save(new Admin(admin.getFirstname(),admin.getLastname(),admin.getAge(),admin.getGender(),admin.getContactno(),admin.getPassword()));
	    return new ResponseEntity<>(_customer, HttpStatus.CREATED);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	  }
	}
	
	@PostMapping(value = "/customers/policy")
	public ResponseEntity<Policy> postPolicy(@RequestBody Policy policy) {
	  try {
	    Policy _policy = policyrepository.save(new Policy(policy.getName(),policy.getType(),policy.getDuration(),policy.getAmount()));
	    return new ResponseEntity<>(_policy, HttpStatus.CREATED);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	  }
	}
	@GetMapping("/customers/policy")
	public ResponseEntity<List<Policy>> getAllPolicies()
	{
		List<Policy> customers=new ArrayList<Policy>();
		try{
			policyrepository.findAll().forEach(customers:: add);
			if(customers.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(customers,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/customers/{id}")
	public ResponseEntity<Policy> getPolicyById(@PathVariable("id") long id)
	{
		Optional<Policy> customerData=policyrepository.findById(id);
		if(customerData.isPresent())
		{
			return new ResponseEntity<>(customerData.get(),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping(value="/customers/update")
	public Policy updatePolicy(@RequestBody Policy policy)
	{
		Policy policy1=policyrepository.save(new Policy(policy.getId(),policy.getName(),policy.getType(),policy.getDuration(),policy.getAmount()));
		
		return policy1;
	}
}
