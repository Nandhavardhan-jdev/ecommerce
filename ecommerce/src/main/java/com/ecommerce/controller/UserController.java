package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dao.UserDto;
import com.ecommerce.dto.CartDto;
import com.ecommerce.dto.CartDto2;
import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ItemDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.UserFavAcDto;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
//	Cart
	
	@PostMapping("/add/cart")
	public CartDto2 addCart(@RequestBody CartDto cartDtos) {
		return userService.addCart(cartDtos);
	}
	
//	User
	
	@PostMapping("/add/user")
	public UserDto addUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}
	@DeleteMapping("/delete/user/{id}")
	public Object deleteUser(@PathVariable long id) {
		return userService.deleteUser(id);
	}
	@GetMapping("/get/user/{id}")
	public UserDto getUser(@PathVariable long id) {
		return userService.getUser(id);
	}
	@PutMapping("/update/user")
	public UserDto updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}
	
//	Category
	
	@GetMapping("/get/category/{id}")
	public CategoryDto getCategory(@PathVariable long id) {
		return userService.getCategory(id);
	}
	@GetMapping("/get/all/categories/{id}")
	public List<CategoryDto> getAllCategoriesByVendorId(@PathVariable long id){
		return userService.getAllCategoriesByVendorId(id);
	}
	
//	Item
	
	@GetMapping("/get/item/{id}")
	public ItemDto getItem(@PathVariable long id) {
		return userService.getItem(id);
	}
	@GetMapping("/get/all/items/{id}")
	public List<ItemDto> getAllItemByCatId(@PathVariable long id) {
		return userService.getAllItemByCatId(id);
	}
	
//	Order
	
	@PostMapping("/add/order")
	public OrderDto addOrder(@RequestBody OrderDto orderDto) {
		return userService.addOrder(orderDto);
	}
	
//	UserFavAc
	
	@PostMapping("/add/ac")
	public UserFavAcDto addAc(@RequestBody UserFavAcDto userFavAcDto) {
		return userService.addAc(userFavAcDto);
	}
	@GetMapping("/get/ac/{id}")
	public UserFavAcDto getAc(@PathVariable long id) {
		return userService.getAc(id);
	}
	@DeleteMapping("/delete/ac/{id}")
	public Object deleteAc(@PathVariable long id) {
		return userService.deleteAc(id);
	}
	
//	History
	
	@GetMapping("/get/order/{uid}/{oid}")
	public OrderDto getOrderByUidAndOid(@PathVariable long uid, @PathVariable long oid) {
		return userService.getOrderByUidAndOid(uid,oid);
	}
	@GetMapping("/get/all/orders/{uid}")
	public List<OrderDto> getAllOrdersByUid(@PathVariable long uid){
		return userService.getAllOrdersByUid(uid);
	}
}
