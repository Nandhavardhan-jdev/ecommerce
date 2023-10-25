package com.ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.dao.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

	Category findByNameAndVendorIdId(String name, long vendorId);
	
	Category findById(long id);

	List<Category> findByVendorIdIdAndVendorIdAvailability(long id, String string);

}
