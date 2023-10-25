package com.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.dao.Vendor;


@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {

	Vendor findByIdAndStatus(long id, boolean b);

	Vendor findByNameAndStatus(String name, boolean b);

	Vendor findByEmailIdAndStatus(String emailId, boolean b);

	Vendor findByMobileNoAndStatus(String mobileNo, boolean b);


}
