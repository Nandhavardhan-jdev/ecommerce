package com.ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.dao.Item;
import com.ecommerce.dao.Vendor;

public interface ItemRepo extends JpaRepository<Item, Long>{

	Item findByNameAndVendorIdId(String name, long vendorId);
	Item findById(long id);
	List<Item> findByCatIdId(long id);

}
