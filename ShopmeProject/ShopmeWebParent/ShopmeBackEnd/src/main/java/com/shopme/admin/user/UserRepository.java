package com.shopme.admin.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shopme.common.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
	
	public Long countById(Long id);
	
	@Query("UPDATE User u Set u.enabled = ?2 where u.id = ?1")
	@Modifying
	public void updateEnabledStatus(Long id, boolean enabled);
}
