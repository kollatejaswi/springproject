package com.pack.Policy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.Policy.dao.AdminRepository;
import com.pack.Policy.dao.PolicyRepository;
import com.pack.Policy.dao.UserRepository;
import com.pack.Policy.model.Admin;
import com.pack.Policy.model.Policy;
import com.pack.Policy.model.User;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/apii")
public class UserController {
	@Autowired	
	UserRepository repository;
	@Autowired
	   PolicyRepository policyrepository;
	@PostMapping(value = "/customers")
	public ResponseEntity<User> postCustomer(@RequestBody User user) {
	  try {
	    User _customer = repository.save(new User(user.getFirstname(),user.getLastname(),user.getAge(),user.getGender(),user.getContactno(),user.getPassword()));
	    return new ResponseEntity<>(_customer, HttpStatus.CREATED);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	  }
	}
	@GetMapping(value = "customers/type/{type}")
	public ResponseEntity<List<Policy>> findByType(@PathVariable String type) {
	  try {
	    List<Policy> customers = policyrepository.findByName(type);

	    if (customers.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(customers, HttpStatus.OK);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
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
}
