package com.pack.Policy.dao;

import org.springframework.data.repository.CrudRepository;

import com.pack.Policy.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
