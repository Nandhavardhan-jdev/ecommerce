package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.dao.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{

	Cart findById(long id);
}
