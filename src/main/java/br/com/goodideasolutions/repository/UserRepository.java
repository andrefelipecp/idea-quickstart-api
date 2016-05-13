package br.com.goodideasolutions.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.goodideasolutions.entity.User;

public interface UserRepository  extends CrudRepository<User, Long> {

	List<User> findByUsername(String username);
	
}
