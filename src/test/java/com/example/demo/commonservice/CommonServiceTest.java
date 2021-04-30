package com.example.demo.commonservice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.example.demo.BaseClass;
import com.example.demo.exception.ValidationExceptions;
import com.example.demo.models.Address;
import com.example.demo.models.Category;
import com.example.demo.models.Clasification;
import com.example.demo.models.Items;
import com.example.demo.models.MenuList;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Resturant;
import com.example.demo.service.MenuService;
import com.example.demo.service.OrderStatusService;
import com.example.demo.service.ResturantService;
import com.example.demo.util.StandardReleaseResponse;


class CommonServiceTest extends BaseClass {
	

	@InjectMocks
	private CommonService commonsService;
	
	@Mock
	private ResturantService resturantService;
	
	@Mock
	private MenuService menuService;
	
	@Mock
	private CommonValidateService commonValidateService;
	
	@Mock
	private OrderStatusService OrderStatusService;
	
	@Test
	@DisplayName("create resturant with positive scenario in common service layer")
	void resturantProcessEntitiesPositiveTest() {
		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID();  
		Address address = new Address(addressId, "hyd", "kphb", "kphb", "india", "278997",  2335637, 67678807);
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		ResponseEntity<StandardReleaseResponse> expected=commonsService.processEntities(resturant);
		List<String> response=commonValidateService.validate(resturant);
		Mockito.when(commonValidateService.validate(resturant)).thenReturn(response);
		Assertions.assertTrue(response.isEmpty());
		ResponseEntity<StandardReleaseResponse> result=resturantService.handleUserActions(resturant);
		Mockito.when(resturantService.handleUserActions(resturant)).thenReturn(result);
		Assertions.assertEquals(expected, result);	
	}
	@Test
	@DisplayName("create resturant with negitive scenario address object missing test case in common service layer")
	void resturantProcessEntitiesAddressObjMissingTest() {
		String resturantId = UUID.randomUUID().toString();
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(null);
		resturant.setCreatedBy("divya");
		List<String> validationList = new ArrayList<String>();
		validationList.add("Address cannot be null");
		ResponseEntity<StandardReleaseResponse> response=commonsService.processEntities(resturant);
		Mockito.when(commonValidateService.validate(resturant)).thenReturn(validationList);
		Assertions.assertThrows(ValidationExceptions.class, () -> commonsService.processEntities(resturant));
		
		
	}
	@Test
	@DisplayName("create menu with positive scenario in common service layer")
	void menuProcessEntitiesPositiveTest() {
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters=new  Category(UUID.randomUUID(),"Kerala starters", starterItems);
		Category Maincourse=new  Category(UUID.randomUUID(),"Kerala mainCourse",mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications=new ArrayList<Clasification>();
		Clasification clasification=new Clasification(UUID.randomUUID(),"keralaType",categories);
		clasifications.add(clasification);
		MenuList menuList=new MenuList(UUID.randomUUID(),"FD001AA",clasifications);
		ResponseEntity<StandardReleaseResponse> expected=commonsService.menuProcessEntities(menuList);
		List<String> response=commonValidateService.validateMenu(menuList);
		Mockito.when(commonValidateService.validateMenu(menuList)).thenReturn(response);
		Assertions.assertTrue(response.isEmpty());
		ResponseEntity<StandardReleaseResponse> result=menuService.handleUserActions(menuList);
		Mockito.when(menuService.handleUserActions(menuList)).thenReturn(result);
		Assertions.assertEquals(expected, result);
	}
	@Test
	@DisplayName("create menu with negitive scenario category object missing in common service layer")
	void menuProcessEntitiesCategoryObjMissingTest() {
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Clasification> clasifications=new ArrayList<Clasification>();
		Clasification clasification=new Clasification(UUID.randomUUID(),"keralaType",null);
		clasifications.add(clasification);
		MenuList menuList=new MenuList(UUID.randomUUID(),"",clasifications);
		List<String> validationList = new ArrayList<String>();
		validationList.add("category cannot be null");
		List<String> response=commonValidateService.validateMenu(menuList);
		Mockito.when(commonValidateService.validateMenu(menuList)).thenReturn(validationList);
		Assertions.assertThrows(ValidationExceptions.class, () -> commonsService.menuProcessEntities(menuList));
		
	}
	

	@Test
	@DisplayName("create orderStatus with positive scenario in common service layer")
	void orderProcessEntitiesPositiveTest() {
		List<OrderStatus> orderStatuses=new ArrayList<OrderStatus>();
		OrderStatus orderStatus=new OrderStatus(UUID.randomUUID().toString(),"order recived");
		orderStatuses.add(orderStatus);
		ResponseEntity<StandardReleaseResponse> expected=commonsService.orderStatusProcessEntities(orderStatus);
		List<String> response=commonValidateService.validateOrderStatus(orderStatus);
		Mockito.when(commonValidateService.validateOrderStatus(orderStatus)).thenReturn(response);
		ResponseEntity<StandardReleaseResponse> result=OrderStatusService.handleUserActions(orderStatus);
		Mockito.when(OrderStatusService.handleUserActions(orderStatus)).thenReturn(result);
		Assertions.assertEquals(expected, result);
	}

	@Test
	@DisplayName("create orderStatus with negitive scenario status message missing in common service layer")
	void orderProcessEntitiesNegitiveTest() {
		List<OrderStatus> orderStatuses=new ArrayList<OrderStatus>();
		OrderStatus orderStatus=new OrderStatus();
		orderStatus.setOrderStatusId(UUID.randomUUID().toString());
		orderStatus.setOrderStatusMesssage(null);
		orderStatuses.add(orderStatus);
		List<String> validationList = new ArrayList<String>();
		validationList.add("message cannot be blank");
		List<String> response=commonValidateService.validateOrderStatus(orderStatus);
		Mockito.when(commonValidateService.validateOrderStatus(orderStatus)).thenReturn(validationList);
		Assertions.assertThrows(ValidationExceptions.class, () -> commonsService.orderStatusProcessEntities(orderStatus));
	}

}
