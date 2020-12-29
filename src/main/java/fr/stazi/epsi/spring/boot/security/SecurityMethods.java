package fr.stazi.epsi.spring.boot.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import fr.stazi.epsi.spring.boot.repository.UserRepository;
import javassist.NotFoundException;

@Service
public class SecurityMethods {
	private  UserRepository  userRepo;
	
	public SecurityMethods(UserRepository  userRepo) {
		this.userRepo = userRepo;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean canManager(User userDetails, Long cellId) throws NotFoundException  {
		 fr.stazi.epsi.spring.boot.entity.user.User user = 
				 userRepo.findByUsername(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("L'utilisateur  avec ce username n'existe pas."));
		 
		 return user.getCells().stream().anyMatch(cell -> cell.getId() == cellId);
	}
}
