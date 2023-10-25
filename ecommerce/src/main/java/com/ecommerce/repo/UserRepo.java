package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.dao.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByEmailIdAndStatus(String emailId, boolean b);

	User findByMobileNoAndStatus(String mobileNo, boolean b);

	User findByIdAndStatus(long id, boolean b);
	
	User findById(long id);

}
