package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.dao.UserFavAccount;

public interface UserFavAcRepo extends JpaRepository<UserFavAccount, Long>{
	
	UserFavAccount findById(long id);
}
