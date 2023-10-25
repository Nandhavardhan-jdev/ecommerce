package com.ecommerce.service_impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.Cart;
import com.ecommerce.dao.Category;
import com.ecommerce.dao.Item;
import com.ecommerce.dao.Order;
import com.ecommerce.dao.User;
import com.ecommerce.dao.UserDto;
import com.ecommerce.dao.UserFavAccount;
import com.ecommerce.dao.Vendor;
import com.ecommerce.dto.CartDto;
import com.ecommerce.dto.CartDto2;
import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ItemDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.UserFavAcDto;
import com.ecommerce.repo.CartRepo;
import com.ecommerce.repo.CategoryRepo;
import com.ecommerce.repo.ItemRepo;
import com.ecommerce.repo.OrderRepo;
import com.ecommerce.repo.UserFavAcRepo;
import com.ecommerce.repo.UserRepo;
import com.ecommerce.repo.VendorRepo;
import com.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService{

//	Cart
	
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired CartRepo cartRepo;
	@Autowired
	ItemRepo itemRepo;
	
	@Override
	public CartDto2 addCart(CartDto cartDtos) {
		BigDecimal amount = new BigDecimal("0");
		List<Item> items = cartDtos.getItemIds();
		for (Item item : items) {
			Item item2 = itemRepo.findById(item.getId());
			BigDecimal itemamount = item2.getAmount();
			if (item.getQuantity() > 1) {
				itemamount = itemamount.multiply(BigDecimal.valueOf(item.getQuantity()));
				amount = amount.add(itemamount);
			}
			else {
				amount = amount.add(itemamount);
			}
		}
		Cart cart = new Cart();
		cart.setAmount(amount);
		cartRepo.save(cart);
		return cartDaoToDto(cart);
	}
	private CartDto2 cartDaoToDto(Cart cart) {
		CartDto2 cartDto = new CartDto2();
		cartDto.setId(cart.getId());
		cartDto.setAmount(cart.getAmount());
		return cartDto;
	}

//	User
	
	@Autowired
	UserRepo userRepo;
	Map<Object, Object> map = new HashMap<>(); 
	
	@Override
	public UserDto addUser(UserDto userDto) {
		User user = userRepo.findByEmailIdAndStatus(userDto.getEmailId(), true);
		if (user == null) {
			User user2 = userRepo.findByMobileNoAndStatus(userDto.getMobileNo(), true);
			if (user2 == null) {
				User user3 = new User();
				user3.setName(userDto.getName());
				user3.setEmailId(userDto.getEmailId());
				user3.setMobileNo(userDto.getMobileNo());
				user3.setGender(userDto.getGender());
				user3.setDob(userDto.getDob());
				user3.setBalance(new BigDecimal("100"));
				user3.setStatus(true);
				userRepo.save(user3);
				return userDaoToDto(user3);
			}else {
				throw new RuntimeException("mobileNo: "+userDto.getMobileNo()+" already exist");
			}
		}else {
			throw new RuntimeException("emailId: "+userDto.getEmailId()+" already exist");
		}
	}
	private UserDto userDaoToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmailId(user.getEmailId());
		userDto.setMobileNo(user.getMobileNo());
		userDto.setGender(user.getGender());
		userDto.setDob(user.getDob());
		userDto.setBalance(user.getBalance());
		userDto.setStatus(user.getStatus());
		userDto.setCreatedOn(user.getCreatedOn());
		userDto.setUpdatedOn(user.getUpdatedOn());
		return userDto;
	}
	@Override
	public Object deleteUser(long id) {
		User user = userRepo.findByIdAndStatus(id, true);
		if (user != null) {
			user.setStatus(false);
			userRepo.save(user);
			map.put("Status", "userId: "+id+" deleted");
			return map;
		}else {
			throw new RuntimeException("userId: "+id+" doesn't exist");
		}
	}
	@Override
	public UserDto getUser(long id) {
		User user = userRepo.findByIdAndStatus(id, true);
		if (user != null) {
			return userDaoToDto(user);
		}else {
			throw new RuntimeException("userId: "+id+" doesn't exist");
		}
	}
	@Override
	public UserDto updateUser(UserDto userDto) {
		User user = userRepo.findByIdAndStatus(userDto.getId(), true);
		if (user != null) {
			if (userDto.getEmailId().equals(user.getEmailId())) {
				if (userDto.getMobileNo().equals(user.getMobileNo())) {
					return userDtoToDao(user, userDto);
				}
//				mobileNo's are not equal
				else {
					User user2 = userRepo.findByMobileNoAndStatus(userDto.getMobileNo(), true);
					if (user2 == null) {
						return userDtoToDao(user, userDto);
					}else {
						throw new RuntimeException("mobileNo: "+userDto.getMobileNo()+" already exist");
					}
				}
			}
//			EmailId's are not equal
			else {
				User user2 = userRepo.findByEmailIdAndStatus(userDto.getEmailId(), true);
				if (user2 == null) {
					if (userDto.getMobileNo().equals(user.getMobileNo())) {
						return userDtoToDao(user, userDto);
					}
//					mobileNo's are not equal
					else {
						User user3 = userRepo.findByMobileNoAndStatus(userDto.getMobileNo(), true);
						if (user3 == null) {
							return userDtoToDao(user, userDto);
						}else {
							throw new RuntimeException("mobileNo: "+userDto.getMobileNo()+" already exist");
						}
					}
				}else {
					throw new RuntimeException("emailId: "+userDto.getEmailId()+" already exist");
				}
			}
		}else {
			throw new RuntimeException("userId: "+userDto.getId()+" doesn't exist");
		}
	}
	private UserDto userDtoToDao(User user, UserDto userDto) {
		user.setName(userDto.getName());
		user.setEmailId(userDto.getEmailId());
		user.setMobileNo(userDto.getMobileNo());
		user.setGender(userDto.getGender());
		user.setDob(userDto.getDob());
		userRepo.save(user);
		return userDaoToDto(user);
	}
	
//	Category
	
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	VendorRepo vendorRepo;
	
	@Override
	public CategoryDto getCategory(long id) {
		Category category = categoryRepo.findById(id);
		if (category != null) {
			return categoryDaoToDto(category);
		}else {
			throw new RuntimeException("category Id: "+id+" doesn't exist");
		}
	}
	private CategoryDto categoryDaoToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		categoryDto.setVendorId(category.getVendorId().getId());
		categoryDto.setStatus(category.getStatus());
		categoryDto.setUpdatedOn(category.getUpdatedOn());
		categoryDto.setCreatedOn(category.getCreatedOn());
		return  categoryDto;
	}
	@Override
	public List<CategoryDto> getAllCategoriesByVendorId(long id) {
		Vendor vendor = vendorRepo.findByIdAndStatus(id, true);
		if (vendor != null) {
			List<Category> categories = categoryRepo.findByVendorIdIdAndVendorIdAvailability(id,"yes");
			if (!categories.isEmpty()) {
				List<CategoryDto> categoryDtos = categories.stream()
						.map(userServiceImpl::categoryDaoToDto)
						.toList();
				return categoryDtos;
			}else {
				throw new RuntimeException("no data exists");
			}
			
		}else {
			throw new RuntimeException("vendorId: "+id+" doesn't exist");
		}
	}

//	Item
	
	@Override
	public ItemDto getItem(long id) {
		Item item = itemRepo.findById(id);
		if (item != null) {
			return itemDaoToDto(item);
		}
		else {
			throw new RuntimeException("Item id: "+id+" doesn't exist");
		}
	}
	private ItemDto itemDaoToDto(Item item2) {
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item2.getId());
		itemDto.setName(item2.getName());
		itemDto.setDescription(item2.getDescription());
		itemDto.setStatus(item2.getStatus());
		itemDto.setAmount(item2.getAmount());
		itemDto.setCatId(item2.getCatId().getId());
		itemDto.setVendorId(item2.getVendorId().getId());
		itemDto.setCreatedOn(item2.getCreatedOn());
		itemDto.setUpdatedOn(item2.getUpdatedOn());
		return itemDto;
	}
	@Override
	public List<ItemDto> getAllItemByCatId(long id) {
		List<Item> items = itemRepo.findByCatIdId(id);
		if (!items.isEmpty()) {
			List<ItemDto> itemDtos = items.stream().map(userServiceImpl::itemDaoToDto).toList();
			return itemDtos;
		}else {
			throw new RuntimeException("no data exist");
		}
	}
	
//	Order
	
	@Autowired
	OrderRepo orderRepo;
	
	@Override
	public OrderDto addOrder(OrderDto orderDto) {
		User user = userRepo.findByIdAndStatus(orderDto.getUserId(), true);
		if (user != null) {
			Vendor vendor = vendorRepo.findByIdAndStatus(orderDto.getVendorId(), true);
			if (vendor != null) {
				Cart cart = cartRepo.findById(orderDto.getCartId());
				if (cart != null) {
					BigDecimal amount = cart.getAmount();
					BigDecimal userBalance = user.getBalance();
					int comparsion = amount.compareTo(userBalance);
					if (comparsion<=0) {
						user.setBalance(userBalance.subtract(amount));
						vendor.setBalance(vendor.getBalance().add(amount));
						Order order = new Order();
						order.setAmount(amount);
						order.setDescription("Payment Success");
						order.setTransactionId(UUID.randomUUID().toString().replaceAll("-", ""));
						order.setCartId(cart);
						order.setUserId(user);
						order.setVendorId(vendor);
						order.setDeliveryAddress(orderDto.getDeliveryAddress());
						orderRepo.save(order);
						return orderDaoToDto(order);
					}else {
						throw new RuntimeException("Insufficient funds");
					}
				}else {
					throw new RuntimeException("cartId: "+orderDto.getCartId()+" doesn't exist");
				}
			}else {
				throw new RuntimeException("vendorId: "+orderDto.getVendorId()+" doesn't exist");
			}
		}else {
			throw new RuntimeException("userId: "+orderDto.getUserId()+" doesn't exist");
		}
	}
	private OrderDto orderDaoToDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setAmount(order.getAmount());
		orderDto.setDescription(order.getDescription());
		orderDto.setTransactionId(order.getTransactionId());
		orderDto.setCartId(order.getCartId().getId());
		orderDto.setUserId(order.getUserId().getId());
		orderDto.setVendorId(order.getVendorId().getId());
		orderDto.setCreatedOn(order.getCreatedOn());
		orderDto.setDeliveryAddress(order.getDeliveryAddress());
		return orderDto;
	}
	
//	UserFavAc
	
	@Autowired
	UserFavAcRepo userFavAcRepo;
	
	@Override
	public UserFavAcDto addAc(UserFavAcDto userFavAcDto) {
		User user = userRepo.findById(userFavAcDto.getUserId());
		Item item = itemRepo.findById(userFavAcDto.getItemId());
		UserFavAccount userFavAccount = new UserFavAccount();
		userFavAccount.setItemName(item.getName());
		userFavAccount.setDescription(item.getDescription());
		userFavAccount.setAmount(item.getAmount());
		userFavAccount.setItemStatus(item.getStatus());
		userFavAccount.setItemId(item);
		userFavAccount.setUserId(user);
		userFavAcRepo.save(userFavAccount);
		return UserFavAcDaoToDto(userFavAccount);
	}
	private UserFavAcDto UserFavAcDaoToDto(UserFavAccount userFavAccount) {
		UserFavAcDto userFavAcDto =new UserFavAcDto();
		userFavAcDto.setId(userFavAccount.getId());
		userFavAcDto.setItemId(userFavAccount.getItemId().getId());
		userFavAcDto.setItemName(userFavAccount.getItemName());
		userFavAcDto.setItemStatus(userFavAccount.isItemStatus());
		userFavAcDto.setUserId(userFavAccount.getUserId().getId());
		userFavAcDto.setDescription(userFavAccount.getDescription());
		userFavAcDto.setAmount(userFavAccount.getAmount());
		return userFavAcDto;
	}
	@Override
	public UserFavAcDto getAc(long id) {
		UserFavAccount userFavAccount = userFavAcRepo.findById(id);
		if (userFavAccount != null) {
			return UserFavAcDaoToDto(userFavAccount);
		}else {
			throw new RuntimeException("account id: "+id+" doesn't exist");
		}
	}
	@Override
	public Object deleteAc(long id) {
		UserFavAccount userFavAccount = userFavAcRepo.findById(id);
		if (userFavAccount != null) {
			userFavAcRepo.deleteById(id);
			map.clear();
			map.put("Status", "accountId: "+id+" deleted successfully");
			return map;
		}else {
			throw new RuntimeException("accountId: "+id+" doesn't exist");
		}
	}

//	History
	
	@Override
	public OrderDto getOrderByUidAndOid(long uid, long oid) {
		Order order = orderRepo.findByUserIdIdAndId(uid,oid);
		if (order != null) {
			return orderDaoToDto(order);
		}else {
			throw new RuntimeException("no data exist");
		}
	}
	@Override
	public List<OrderDto> getAllOrdersByUid(long uid) {
		List<Order> orders = orderRepo.findByUserIdId(uid);
		if (!orders.isEmpty()) {
			List<OrderDto> orderDtos = orders.stream().map(userServiceImpl::orderDaoToDto).toList();
			return orderDtos;
		}else {
			throw new RuntimeException("no data exist");
		}
	}
	
}
