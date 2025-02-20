package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userNamHM = new User("nam@codejava.net", "nam2020", "Nam", "Ha Minh");
		userNamHM.addRole(roleAdmin);
		
		User savedUser = repo.save(userNamHM);
		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testCreateNewUserWithTwoRole() {
		User userRavi = new User("ravi@gmail.com", "ravi2020", "Ravi", "Kumar");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = repo.save(userRavi);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(System.out::println);
	}
	
	@Test 
	public void testGetUserById() {
		User userNam = repo.findById(1L).get();
		System.out.println(userNam);
		assertThat(userNam).isNotNull();
		
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userNam = repo.findById(1L).get();
		userNam.setEnabled(true);
		userNam.setEmail("namjavaprogrammer@gmail.com");
		
		repo.save(userNam);
		
	}
	
	@Test 
	public void testUpdateUserRoles() {
		User userRavi = repo.findById(2L).get();
		Role roleEditor = new Role(3);
		Role roleSelesperson = new Role(2);
		
		userRavi.getRoles().remove(roleEditor);
		userRavi.addRole(roleSelesperson);
		
	}
	
	@Test
	public void testDeleteUser() {
		Long userId = 2L;
		repo.deleteById(userId);
	}
	
	
	@Test
	public void testGetUserByEmail() {
		String email = "abc@gmail.com";
		User user = repo.findByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testCountById() {
		Long id = 5L;
		Long countById = repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	@Test
	public void testDisableUser() {
		Long id = 1L;
		repo.updateEnabledStatus(id, false);
	}
	
	@Test
	public void testEnableUser() {
		Long id = 3L;
		repo.updateEnabledStatus(id, true);
	}
}
