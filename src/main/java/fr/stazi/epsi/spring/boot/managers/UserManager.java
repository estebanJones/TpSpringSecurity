package fr.stazi.epsi.spring.boot.managers;

import java.util.Optional;

import fr.stazi.epsi.spring.boot.entity.user.User;
import fr.stazi.epsi.spring.boot.exception.NotFoundException;
import fr.stazi.epsi.spring.boot.repository.UserRepository;


public class UserManager {
	private UserRepository userRepo;
	
	public UserManager(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User getUserById(Long userId) throws NotFoundException{
		Optional<User> user = this.userRepo.findById(userId);
		if(user.isPresent()) {
			return user.get(); 
		} else  {
			throw new NotFoundException(); 
		}
	}
}
