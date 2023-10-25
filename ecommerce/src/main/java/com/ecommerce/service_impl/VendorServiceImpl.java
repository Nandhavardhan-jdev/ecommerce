package com.ecommerce.service_impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.Category;
import com.ecommerce.dao.Item;
import com.ecommerce.dao.Order;
import com.ecommerce.dao.Vendor;
import com.ecommerce.dto.CategoryDto;
import com.ecommerce.dto.ItemDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.VendorDto;
import com.ecommerce.repo.CategoryRepo;
import com.ecommerce.repo.ItemRepo;
import com.ecommerce.repo.OrderRepo;
import com.ecommerce.repo.VendorRepo;
import com.ecommerce.service.VendorService;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRepo vendorRepo;
	@Autowired
	VendorServiceImpl vendorServiceImpl;
	Map<Object, Object> map = new HashMap<>();
	
//	Vendor
	
	@Override
	public VendorDto addVendor(VendorDto vendorDto) {
		Vendor name = vendorRepo.findByNameAndStatus(vendorDto.getName(),true);
		if (name == null) {
			Vendor eMailId = vendorRepo.findByEmailIdAndStatus(vendorDto.getEmailId(),true);
			if (eMailId == null) {
				Vendor mobileNo = vendorRepo.findByMobileNoAndStatus(vendorDto.getMobileNo(),true);
				if (mobileNo == null) {
					Vendor vendor = new Vendor();
					vendor.setName(vendorDto.getName());
					vendor.setEmailId(vendorDto.getEmailId());
					vendor.setMobileNo(vendorDto.getMobileNo());
					vendor.setBalance(new BigDecimal(0));
					vendor.setStatus(true);
					vendor.setAvailability(vendorDto.getAvailability());
					vendorRepo.save(vendor);
					return vendorDaoToDto(vendor);
				}else {
					throw new RuntimeException("mobile no: "+vendorDto.getMobileNo()+" is already exist");
				}
			}else {
				throw new RuntimeException("eMailId: "+vendorDto.getEmailId()+" is already exist");
			}
		}else {
			throw new RuntimeException("vendor name: "+vendorDto.getName()+" is already exist");
		}
	}
	public VendorDto vendorDaoToDto(Vendor vendor) {
		VendorDto vendorDto = new VendorDto();
		vendorDto.setId(vendor.getId());
		vendorDto.setName(vendor.getName());
		vendorDto.setEmailId(vendor.getEmailId());
		vendorDto.setMobileNo(vendor.getMobileNo());
		vendorDto.setBalance(vendor.getBalance());
		vendorDto.setStatus(vendor.getStatus());
		vendorDto.setDoj(vendor.getDoj());
		vendorDto.setCreatedOn(vendor.getCreatedOn());
		vendorDto.setUpdatedOn(vendor.getUpdatedOn());
		vendorDto.setAvailability(vendor.getAvailability());
		return vendorDto;
	}
	@Override
	public Object deleteVendor(long id) {
		Vendor vendor = vendorRepo.findByIdAndStatus(id,true);
		if (vendor != null) {
			vendor.setStatus(false);
			vendorRepo.save(vendor);
			map.clear();
			map.put("status", "vendorId: "+id+" deleted");
			return map;
		}else {
			throw new RuntimeException("vendorId: "+id+" doesn't exist");
		}
	}
	@Override
	public VendorDto getVendor(long id) {
		Vendor vendor = vendorRepo.findByIdAndStatus(id,true);
		if (vendor != null) {
			return vendorDaoToDto(vendor);
		}else {
			throw new RuntimeException("vendorId: "+id+" doesn't exist");
		}
	}
	@Override
	public VendorDto updateVendor(VendorDto vendorDto) {
		Vendor vendor = vendorRepo.findByIdAndStatus(vendorDto.getId(), true);
		if (vendor != null) {
			if (vendorDto.getName().equalsIgnoreCase(vendor.getName())) {
				if (vendorDto.getEmailId().equals(vendor.getEmailId())) {
					if (vendorDto.getMobileNo().equals(vendor.getMobileNo())) {
						return vendorUpdateDaoAndDto(vendor,vendorDto);
					}
					//vendor MobileNos aren't equal
					else {
						Vendor vendor2 = vendorRepo.findByMobileNoAndStatus(vendorDto.getMobileNo(), true);
						if (vendor2 == null) {
							return vendorUpdateDaoAndDto(vendor,vendorDto);
						}else {
							throw new RuntimeException("mobile no: "+vendorDto.getMobileNo()+" already exist");
						}
					}
				}
				//vendor emailIds aren't equal
				else {
					Vendor vendor2 = vendorRepo.findByEmailIdAndStatus(vendorDto.getEmailId(), true);
					if (vendor2 == null) {
						if (vendorDto.getMobileNo().equals(vendor.getMobileNo())) {
							return vendorUpdateDaoAndDto(vendor,vendorDto);
						}
						//mobile nos aren't equal
						else {
							Vendor vendor3 = vendorRepo.findByMobileNoAndStatus(vendorDto.getMobileNo(), true);
							if (vendor3 == null) {
								return vendorUpdateDaoAndDto(vendor,vendorDto);
							}else {
								throw new RuntimeException("mobile no: "+vendorDto.getMobileNo()+" already exist");
							}
						}
					}else {
						throw new RuntimeException("eMailId: "+vendorDto.getEmailId()+" is already exist");
					}
				}
			}
			//vendorNames aren't equal
			else {
				Vendor vendor2 = vendorRepo.findByNameAndStatus(vendorDto.getName(), true);
				if (vendor2 == null) {
					if (vendor.getEmailId().equals(vendorDto.getEmailId())) {
						if (vendor.getMobileNo().equals(vendorDto.getMobileNo())) {
							return vendorUpdateDaoAndDto(vendor,vendorDto);
						}
						//mobilenos aren't equal
						else {
							Vendor vendor3 = vendorRepo.findByMobileNoAndStatus(vendorDto.getMobileNo(), true);
							if (vendor3 == null) {
								return vendorUpdateDaoAndDto(vendor,vendorDto);
							}else {
								throw new RuntimeException("mobile no: "+vendorDto.getMobileNo()+" already exist");
							}
						}
					}
					//emailIds aren't equal
					else {
						Vendor vendor3 = vendorRepo.findByEmailIdAndStatus(vendorDto.getEmailId(), true);
						if (vendor3 == null) {
							if (vendorDto.getMobileNo().equals(vendor.getMobileNo())) {
								return vendorUpdateDaoAndDto(vendor,vendorDto);
							}
							//mobile nos aren't equal
							else {
								Vendor vendor4 = vendorRepo.findByMobileNoAndStatus(vendorDto.getMobileNo(), true);
								if (vendor4 == null) {
									return vendorUpdateDaoAndDto(vendor,vendorDto);
								}else {
									throw new RuntimeException("mobile no: "+vendorDto.getMobileNo()+" already exist");
								}
							}
						}else {
							throw new RuntimeException("eMailId: "+vendorDto.getEmailId()+" is already exist");
						}
					}
				}else {
					throw new RuntimeException("vendor name: "+vendorDto.getName()+" is already exist");
				}
			}
		}else {
			throw new RuntimeException("vendorId: "+vendorDto.getId()+" doesn't exist");
		}
	}
	private VendorDto vendorUpdateDaoAndDto(Vendor vendor, VendorDto vendorDto) {
		vendor.setEmailId(vendorDto.getEmailId());
		vendor.setMobileNo(vendorDto.getMobileNo());
		vendor.setName(vendorDto.getName());
		vendor.setAvailability(vendorDto.getAvailability());
		vendorRepo.save(vendor);
		return vendorDaoToDto(vendor);
	}
	
//	Category
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Vendor vendor = vendorRepo.findByIdAndStatus(categoryDto.getVendorId(), true);
		if (vendor != null) {
			Category category = categoryRepo.findByNameAndVendorIdId(categoryDto.getName(),categoryDto.getVendorId());
			if (category == null) {
				Category category2 = new Category();
				category2.setName(categoryDto.getName());
				category2.setVendorId(vendor);
				category2.setStatus(true);
				categoryRepo.save(category2);
				return categoryDaoToDto(category2);
			}else {
				throw new RuntimeException("category name: "+categoryDto.getName()+" already exist for the vendorId: "+categoryDto.getVendorId());
			}
		}else {
			throw new RuntimeException("vendorId: "+categoryDto.getVendorId()+" doesn't exist");
		}
	}
	private CategoryDto categoryDaoToDto(Category category2) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category2.getId());
		categoryDto.setName(category2.getName());
		categoryDto.setVendorId(category2.getVendorId().getId());
		categoryDto.setStatus(category2.getStatus());
		categoryDto.setUpdatedOn(category2.getUpdatedOn());
		categoryDto.setCreatedOn(category2.getCreatedOn());
		return  categoryDto;
	}
	@Override
	public CategoryDto getCategory(long id) {
		Category category = categoryRepo.findById(id);
		if (category != null) {
			return categoryDaoToDto(category);
		}else {
			throw new RuntimeException("category Id: "+id+" doesn't exist");
		}
	}
	@Override
	public List<CategoryDto> getAllCategoriesByVendorId(long id) {
		Vendor vendor = vendorRepo.findByIdAndStatus(id, true);
		if (vendor != null) {
			List<Category> categories = categoryRepo.findByVendorIdIdAndVendorIdAvailability(id,"yes");
			if (!categories.isEmpty()) {
				List<CategoryDto> categoryDtos = categories.stream()
						.map(vendorServiceImpl::categoryDaoToDto)
						.toList();
				return categoryDtos;
			}else {
				throw new RuntimeException("no data exists");
			}
			
		}else {
			throw new RuntimeException("vendorId: "+id+" doesn't exist");
		}
	}
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		Category category = categoryRepo.findById(categoryDto.getId());
		if (category != null) {
			if (categoryDto.getName().equalsIgnoreCase(category.getName())) {
				category.setName(categoryDto.getName());
				category.setStatus(categoryDto.getStatus());
				categoryRepo.save(category);
				return categoryDaoToDto(category);
			}
//			category name aren't equal
			else {
				Category category2 = categoryRepo.findByNameAndVendorIdId(categoryDto.getName(), categoryDto.getVendorId());
				if (category2 == null) {
					category.setName(categoryDto.getName());
					category.setStatus(categoryDto.getStatus());
					categoryRepo.save(category);
					return categoryDaoToDto(category);
				}else {
					throw new RuntimeException("category name: "+categoryDto.getName()+" already exist for vendorId: "+categoryDto.getVendorId());
				}
			}
		}else {
			throw new RuntimeException("categoryId: "+categoryDto.getId()+" doesn't exist");
		}
	}
	
//	Item
	
	@Autowired
	ItemRepo itemRepo;

	@Override
	public ItemDto addItem(ItemDto itemDto) {
		 Category category = categoryRepo.findById(itemDto.getCatId());
		 Item item = itemRepo.findByNameAndVendorIdId(itemDto.getName(), category.getVendorId().getId());
		if (item == null) {
			Item item2 = new Item();
			item2.setName(itemDto.getName());
			item2.setDescription(itemDto.getDescription());
			item2.setAmount(itemDto.getAmount());
			item2.setStatus(itemDto.getStatus());
			item2.setCatId(category);
			item2.setVendorId(category.getVendorId());
			itemRepo.save(item2);
			return itemDaoToDto(item2);
		}else {
			throw new RuntimeException("Item name: "+itemDto.getName()
			+" already exist for the vendor: "+ category.getVendorId().getId());
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
	public ItemDto getItem(long id) {
		Item item = itemRepo.findById(id);
		if (item != null) {
			return itemDaoToDto(item);
		}
		else {
			throw new RuntimeException("Item id: "+id+" doesn't exist");
		}
	}
	@Override
	public List<ItemDto> getAllItemsByCatId(long id) {
		List<Item> items = itemRepo.findByCatIdId(id);
		if (!items.isEmpty()) {
			List<ItemDto> itemDtos = items.stream().map(vendorServiceImpl::itemDaoToDto).toList();
			return itemDtos;
		}else {
			throw new RuntimeException("no data exist");
		}
	}
	@Override
	public ItemDto updateItem(ItemDto itemDto) {
		Item item = itemRepo.findById(itemDto.getId());
		if (item != null) {
			if (item.getName().equalsIgnoreCase(itemDto.getName())) {
				item.setName(itemDto.getName());
				item.setDescription(itemDto.getDescription());
				item.setAmount(itemDto.getAmount());
				item.setStatus(itemDto.getStatus());
				itemRepo.save(item);
				return itemDaoToDto(item);
			}else {
				Item item2 = itemRepo.findByNameAndVendorIdId(itemDto.getName(), item.getVendorId().getId());
				if (item2 == null) {
					item.setName(itemDto.getName());
					item.setDescription(itemDto.getDescription());
					item.setAmount(itemDto.getAmount());
					item.setStatus(itemDto.getStatus());
					itemRepo.save(item);
					return itemDaoToDto(item);
				}else {
					throw new RuntimeException("Item name: "+itemDto.getName()+" already exist for the vendorId: "+item.getVendorId().getId());
				}
			}
		}else {
			throw new RuntimeException("Item id: "+itemDto.getId()+" doesn't exist");
		}
	}
	
//	History
	
	@Autowired
	OrderRepo orderRepo;
	
	@Override
	public OrderDto getOrderByVidAndOid(long vid, long oid) {
		Order order = orderRepo.findByUserIdIdAndId(vid,oid);
		if (order != null) {
			return orderDaoToDto(order);
		}else {
			throw new RuntimeException("no data exist");
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
	@Override
	public List<OrderDto> getAllOrdersByVid(long vid) {
		List<Order> orders = orderRepo.findByUserIdId(vid);
		if (!orders.isEmpty()) {
			List<OrderDto> orderDtos = orders.stream().map(vendorServiceImpl::orderDaoToDto).toList();
			return orderDtos;
		}else {
			throw new RuntimeException("no data exist");
		}
	}
	
}
