package com.ecommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.dao.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

	Order findByUserIdIdAndId(long uid, long oid);

	List<Order> findByUserIdId(long uid);

}
