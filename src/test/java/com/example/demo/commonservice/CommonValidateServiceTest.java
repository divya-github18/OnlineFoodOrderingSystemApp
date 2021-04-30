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
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.repository.IResturantRepository;
import com.example.demo.util.StandardReleaseResponse;

class CommonValidateServiceTest extends BaseClass{
	
	@InjectMocks
	CommonValidateService commonValidateService;
	
	@Mock
	CommonService commonService;
	
	@Mock
	IResturantRepository iResturantRepository;
	@Test
	@DisplayName("resturant validation checking with positive scenario")
	void resturantValidationPositiveScenario() {
		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID(); 
		Address address = new Address(addressId, "hyd", "kphb", "kphb", "india", "278997",  2335637, 67678807);
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		Mockito.when(iResturantRepository.findResturantName(resturant.getResturantName())).thenReturn(null);
		List<String> response=commonValidateService.validate(resturant);
		Assertions.assertTrue(response.isEmpty());
	}
	@Test
	@DisplayName("resturant validation checking with negitive scenario have a duplicate resturant name")
	void resturantValidationNegitiveScenarioOfduplicateResturantName() {
		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID(); 
		Address address = new Address(addressId, "hyd", "kphb", "kphb", "india", "278997",  2335637, 67678807);
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		Resturant restaurantObj=new Resturant();
		restaurantObj.setResturantName("treat");
		Mockito.when(iResturantRepository.findResturantName(resturant.getResturantName())).thenReturn(restaurantObj);
		List<String> expected = commonValidateService.validate(resturant);
		Assertions.assertFalse(expected.isEmpty());
		
	}
	@Test
	@DisplayName("resturant validation checking with negitive scenario have a address object missing")
	void resturantValidationNegitiveScenario() {
		String resturantId = UUID.randomUUID().toString();
		Resturant resturant = new Resturant(); 
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(null);
		resturant.setCreatedBy("divya");
		Resturant resturants=iResturantRepository.findResturantName(resturant.getResturantName());
		Mockito.when(iResturantRepository.findResturantName(resturant.getResturantName())).thenReturn(resturants);
		List<String> response=commonValidateService.validate(resturant);
		Assertions.assertFalse(response.isEmpty());
	}
	@Test
	@DisplayName("men validation with positive scenario")
	 void menuValidationPositiveScenario() {
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
		MenuList menuList=new MenuList(UUID.randomUUID(),"FD004AE",clasifications);
		List<String> response=commonValidateService.validateMenu(menuList);
		Assertions.assertTrue(response.isEmpty());
	}
	@Test
	@DisplayName("menu validation with negitive scenario i.e resturant code blank")
	 void menuValidationNegitiveScenario() {
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
		MenuList menuList=new MenuList(UUID.randomUUID(),"",clasifications);
		List<String> response=commonValidateService.validateMenu(menuList);
		Assertions.assertFalse(response.isEmpty());
	}
	@Test
	@DisplayName("order status validations with positive scenario")
	void orderProcessEntitiesPositiveTest() {
		List<OrderStatus> orderStatuses=new ArrayList<OrderStatus>();
		OrderStatus orderStatus=new OrderStatus(UUID.randomUUID().toString(),"order recived");
		orderStatuses.add(orderStatus);
		List<String> response=commonValidateService.validateOrderStatus(orderStatus);
		Assertions.assertTrue(response.isEmpty());
	}
	@Test
	@DisplayName("order sstatus validatin with negitive scenario is order status message blank")
	void orderProcessEntitiesNegitiveTest() {
		List<OrderStatus> orderStatuses=new ArrayList<OrderStatus>();
		OrderStatus orderStatus=new OrderStatus(UUID.randomUUID().toString(),"");
		orderStatuses.add(orderStatus);
		List<String> response=commonValidateService.validateOrderStatus(orderStatus);
		Assertions.assertFalse(response.isEmpty());
	}
}
