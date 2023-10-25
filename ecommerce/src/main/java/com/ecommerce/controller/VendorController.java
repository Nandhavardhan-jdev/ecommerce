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

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ItemDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.VendorDto;
import com.ecommerce.service.VendorService;


@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	VendorService vendorService;
	
//	Vendor
	
	@PostMapping("/add/vendor")
	public VendorDto addVendor(@RequestBody VendorDto vendorDto) {
		return vendorService.addVendor(vendorDto);
	}
	@DeleteMapping("/delete/vendor/{id}")
	public Object deleteVendor(@PathVariable long id) {
		return vendorService.deleteVendor(id);
	}
	@GetMapping("/get/vendor/{id}")
	public VendorDto getVendor(@PathVariable long id) {
		return vendorService.getVendor(id);
	}
	@PutMapping("/update/vendor")
	public VendorDto updateVendor(@RequestBody VendorDto vendorDto) {
		return vendorService.updateVendor(vendorDto);
	}
	
//	Category
	
	@PostMapping("/add/category")
	public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
		return vendorService.addCategory(categoryDto);
	}
	@GetMapping("/get/category/{id}")
	public CategoryDto getCategory(@PathVariable long id) {
		return vendorService.getCategory(id);
	}
	@GetMapping("/get/all/categories/{id}")
	public List<CategoryDto> getAllCategoriesByVendorId(@PathVariable long id){
		return vendorService.getAllCategoriesByVendorId(id);
	}
	@PutMapping("/update/category")
	public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {
		return vendorService.updateCategory(categoryDto);
	}
	
//	Item
	
	 @PostMapping("/add/item")
	 public ItemDto addItem(@RequestBody ItemDto itemDto) {
		 return vendorService.addItem(itemDto);
	 }
	 @GetMapping("/get/item/{id}")
	 public ItemDto getItem(@PathVariable long id) {
		 return vendorService.getItem(id);
	 }
	 @GetMapping("/get/all/items/{id}")
	 public List<ItemDto> getAllItemByCatId(@PathVariable long id) {
		 return vendorService.getAllItemsByCatId(id);
	 }
	 @PutMapping("/update/item")
  	 public ItemDto updateItem(@RequestBody ItemDto itemDto) {
		 return vendorService.updateItem(itemDto);
	 }
	 
//	 History
	 
	 @GetMapping("/get/order/{vid}/{oid}")
	 public OrderDto getOrderByVidAndOid(@PathVariable long vid, @PathVariable long oid) {
		 return vendorService.getOrderByVidAndOid(vid,oid);
	 }
	 @GetMapping("/get/all/orders/{vid}")
	 public List<OrderDto> getAllOrdersByVid(@PathVariable long vid){
		 return vendorService.getAllOrdersByVid(vid);
	 }
	 
	 
}
