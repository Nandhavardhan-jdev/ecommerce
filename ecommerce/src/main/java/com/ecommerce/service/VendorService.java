package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ItemDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.VendorDto;

public interface VendorService {

//	Vendor
	
	VendorDto addVendor(VendorDto vendorDto);

	Object deleteVendor(long id);

	VendorDto getVendor(long id);

	VendorDto updateVendor(VendorDto vendorDto);

	CategoryDto addCategory(CategoryDto categoryDto);

	CategoryDto getCategory(long id);

	List<CategoryDto> getAllCategoriesByVendorId(long id);

	CategoryDto updateCategory(CategoryDto categoryDto);

	ItemDto addItem(ItemDto itemDto);

	ItemDto getItem(long id);

	List<ItemDto> getAllItemsByCatId(long id);

	ItemDto updateItem(ItemDto itemDto);

	OrderDto getOrderByVidAndOid(long vid, long oid);

	List<OrderDto> getAllOrdersByVid(long vid);

}
