package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dao.UserDto;
import com.ecommerce.dto.CartDto;
import com.ecommerce.dto.CartDto2;
import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ItemDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.UserFavAcDto;

public interface UserService {

	CartDto2 addCart(CartDto cartDtos);

	UserDto addUser(UserDto userDto);

	Object deleteUser(long id);

	UserDto getUser(long id);

	UserDto updateUser(UserDto userDto);

	CategoryDto getCategory(long id);

	List<CategoryDto> getAllCategoriesByVendorId(long id);

	ItemDto getItem(long id);

	List<ItemDto> getAllItemByCatId(long id);

	OrderDto addOrder(OrderDto orderDto);

	UserFavAcDto addAc(UserFavAcDto userFavAcDto);

	UserFavAcDto getAc(long id);

	Object deleteAc(long id);

	OrderDto getOrderByUidAndOid(long uid, long oid);

	List<OrderDto> getAllOrdersByUid(long uid);

}
