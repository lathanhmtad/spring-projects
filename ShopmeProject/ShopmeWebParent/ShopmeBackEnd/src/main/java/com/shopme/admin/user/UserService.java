package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> listAll() {
		return userRepo.findAll();
	}
	
	public List<Role> listRoles() {
		return roleRepo.findAll();
	}

	public void save(User user) {
		boolean isUpdatingUser = (user.getId() != null);
		
		if(isUpdatingUser) {
			User existingUser = userRepo.findById(user.getId()).get();
			
			if(user.getPassword().isEmpty()) {
				user.setPassword(existingUser.getPassword());
			} else {
				encodePassword(user);
			}
		} else {
			encodePassword(user);
		}
		userRepo.save(user);
		
	}
	
	private void encodePassword(User u) {
		String encodePassword = passwordEncoder.encode(u.getPassword());
		u.setPassword(encodePassword);
	}
	
	public boolean isEmailUnique(Long id, String email) {
		User userByEmail = userRepo.findByEmail(email);
		
		if(userByEmail == null) return true;
		
		boolean isCreatingNew = (id == null);
		
		if(isCreatingNew) {
			if(userByEmail != null) {
				return false;
			}
		} else {
			if(userByEmail.getId() != id) {
				return false;
			}
		}
		
		return true; 
	}

	public User getUserById(Long id) {
		User userById = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Could not find any user with ID " + id));
		return userById;
	}
	
	public void delete(Long id) {
		Long countById = userRepo.countById(id);
		if(countById == null || countById == 0) {
			throw new UserNotFoundException("Could not find any user with Id " + id);
		}
		userRepo.deleteById(id);
	}
	
	public void updateUserEnabledStatus(Long userId, boolean enabled) {
		userRepo.updateEnabledStatus(userId, enabled);
	}
}
