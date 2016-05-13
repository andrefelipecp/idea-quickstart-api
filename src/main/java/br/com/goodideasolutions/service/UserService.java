package br.com.goodideasolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.goodideasolutions.entity.User;
import br.com.goodideasolutions.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	public User one(Long userId) {
		return userRepository.findOne(userId);
	}

}
