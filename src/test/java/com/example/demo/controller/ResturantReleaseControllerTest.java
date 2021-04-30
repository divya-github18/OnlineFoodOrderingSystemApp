package com.example.demo.controller;

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
import com.example.demo.commonservice.CommonService;
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
import com.example.demo.util.EntitlementUtils;
import com.example.demo.util.StandardReleaseResponse;



class ResturantReleaseControllerTest extends BaseClass {
	
	@InjectMocks
	ResturantReleaseController releaseController;
	@Mock
	CommonService commonService;
	@Mock
	MenuService menuservice;
	@Mock
	ResturantService resturantService;
	@Mock
	OrderStatusService orderStatusService;
	
	@Test
	@DisplayName("create resturant test case in controller layer")
	void cretaeResturantTest()
	{

		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID(); 
		Address address = new Address(addressId, "hyd", "kphb", "kphb", "india", "278997",  2335637, 67678807);
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		ResponseEntity<StandardReleaseResponse> expected = new EntitlementUtils().createResponseEntity(201, "created");
		Mockito.when(commonService.processEntities(resturant)).thenReturn(expected);
		ResponseEntity<StandardReleaseResponse> result = releaseController.createResturant(resturant);
		Mockito.verify(commonService).processEntities(resturant);
		Assertions.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
		
		
	}
		
	
	@Test
	@DisplayName("create menu test case in controller layer")
	 void createMenuTest()
	{

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
		ResponseEntity<StandardReleaseResponse> expected = new EntitlementUtils().createResponseEntity(201, "created");
		Mockito.when( commonService.menuProcessEntities(menuList)).thenReturn(expected); 
		ResponseEntity<StandardReleaseResponse> result=releaseController.createMenu(menuList);
		Mockito.verify(commonService).menuProcessEntities(menuList);
		Assertions.assertEquals(expected.getStatusCodeValue(), result.getStatusCodeValue());
		
	}
	@Test
	@DisplayName("get menu list based on id test case in controller layer")
	 void getMenuListBasedOnIdTest()
	{


		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters=new  Category(UUID.randomUUID(),"Kerala starters",starterItems);
		Category Maincourse=new  Category(UUID.randomUUID(),"Kerala mainCourse",mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications=new ArrayList<Clasification>();
		Clasification clasification=new Clasification(UUID.randomUUID(),"keralaType",categories);
		clasifications.add(clasification);
		MenuList menuList=new MenuList(UUID.randomUUID(),"",clasifications);
		MenuList response = releaseController.getMenuDeatailsBasedOnMenuId(menuList.getMenuListId());
		Mockito.when(menuservice.getMenuListBasedOnId(menuList.getMenuListId())).thenReturn(response);
		Mockito.verify(menuservice).getMenuListBasedOnId(menuList.getMenuListId());
	}
	
	@Test
	@DisplayName("update menu list based on id test case in controller layer")
	 void updateMenuListBasedOnId()
	{
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters=new  Category(UUID.randomUUID(),"Kerala starters",starterItems);
		Category Maincourse=new  Category(UUID.randomUUID(),"Kerala mainCourse",mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications=new ArrayList<Clasification>();
		Clasification clasification=new Clasification(UUID.randomUUID(),"keralaType",categories);
		clasifications.add(clasification);
		MenuList menuList=new MenuList(UUID.randomUUID(),"FD001AS",clasifications);
		
		List<Items> starterItems1 = new ArrayList<Items>();
		Items keralaManchuria1 = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		Items pongal = new Items(UUID.randomUUID(), "pongal", 30);
		starterItems.add(keralaManchuria1);
		starterItems.add(pongal);
		List<Items> mainCourse1 = new ArrayList<Items>();
		Items keralaBiryani1 = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani1);
		List<Category> categories1 = new ArrayList<Category>();
		Category starters1=new  Category(UUID.randomUUID(),"Kerala starters",starterItems1);
		Category Maincourse1=new  Category(UUID.randomUUID(),"Kerala mainCourse",mainCourse1);
		categories.add(starters1);
		categories.add(Maincourse1);
		List<Clasification> clasifications1=new ArrayList<Clasification>();
		Clasification clasification1=new Clasification(UUID.randomUUID(),"keralaType",categories1);
		clasifications.add(clasification1);
		MenuList menu=new MenuList(menuList.getMenuListId(),"FD001AS",clasifications1);
		ResponseEntity<StandardReleaseResponse> expected = new EntitlementUtils().createResponseEntity(201, "updated");
		Mockito.when(menuservice.updateMenuListBasedOnId(menu, menuList.getMenuListId())).thenReturn(expected);
		ResponseEntity<StandardReleaseResponse> response = releaseController.updateMenuDeatailsBasedOnMenuId(menu, menuList.getMenuListId());
		Assertions.assertEquals(expected, response);
	
		
		
	}
	@Test
	@DisplayName("create order status messages test case in controller layer")
	 void createOrderStatus() {
		List<OrderStatus> orderStatuses=new ArrayList<OrderStatus>();
		OrderStatus orderStatus=new OrderStatus(UUID.randomUUID().toString(),"order recived");
		orderStatuses.add(orderStatus);
		ResponseEntity<StandardReleaseResponse> expected = new EntitlementUtils().createResponseEntity(201, "created");
		Mockito.when(commonService.orderStatusProcessEntities(orderStatus)).thenReturn(expected);
		ResponseEntity<StandardReleaseResponse> response = releaseController.createOrderStatuses(orderStatus);
		Assertions.assertEquals(expected, response);
		
	}
	@Test
	@DisplayName("get menu list based on resturant name test case in controller layer")
	 void getMenuListBasedOnResturantNameTest()
	{
		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID(); 
		Address address = new Address();
		address.setAddressId(addressId);
		address.setCity("hyd");
		address.setCountry("india");
		address.setLatitude(27.8997);
		address.setLongitude(23.5637);
		address.setState("ts");
		address.setStreet("kphb");
		address.setZipCode("278997"); 
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items();
		keralaManchuria.setItemId(UUID.randomUUID());
		keralaManchuria.setItemName("kerala Manchuria");
		keralaManchuria.setItemCost(300);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters = new Category();
		starters.setCategoryId(UUID.randomUUID());
		starters.setCategoryName("Kerala starters");
		starters.setItems(starterItems);
		Category Maincourse = new Category(UUID.randomUUID(), "Kerala mainCourse", mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications = new ArrayList<Clasification>();
		Clasification clasification = new Clasification();
		clasification.setClasificationID(UUID.randomUUID());
		clasification.setClasificationType("keralaType");
		clasification.setCategory(categories);
		clasifications.add(clasification);
		MenuList menuList = new MenuList();
		menuList.setMenuListId(UUID.randomUUID());
		menuList.setResturantCode("FD001TR");
		menuList.setClasification(clasifications);
		
		Mockito.when(menuservice.getMenuListBasedOnResturantName(resturant.getResturantName())).thenReturn(menuList);
		MenuList menu=releaseController.getMenuDeatailsBasedOnResturantName(resturant.getResturantName());
		Assertions.assertEquals(menu, menuList);
		
	}
	@Test
	@DisplayName("get items based on category test case in controller layer")
	 void getItemsBasedOnCategoryTest()
	{
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items();
		keralaManchuria.setItemId(UUID.randomUUID());
		keralaManchuria.setItemName("kerala Manchuria");
		keralaManchuria.setItemCost(300);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters = new Category();
		starters.setCategoryId(UUID.randomUUID());
		starters.setCategoryName("Kerala starters");
		starters.setItems(starterItems);
		Category Maincourse = new Category(UUID.randomUUID(), "Kerala mainCourse", mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications = new ArrayList<Clasification>();
		Clasification clasification = new Clasification();
		clasification.setClasificationID(UUID.randomUUID());
		clasification.setClasificationType("keralaType");
		clasification.setCategory(categories);
		clasifications.add(clasification);

		MenuList menuList = new MenuList(); 
		menuList.setMenuListId(UUID.randomUUID());
		menuList.setResturantCode("FD001TR");
		menuList.setClasification(clasifications);
		List<Object[]> expected=new ArrayList<Object[]>();
		Mockito.when(menuservice.getItemsBasedonCategory(starters.getCategoryName())).thenReturn(expected);
		List<Object[]> result=releaseController.getItemsBasedonCategory(starters.getCategoryName());
		Assertions.assertEquals(expected, result);
		
	}
	@Test
	@DisplayName("get resturant name based on clasification test case")
	 void getResturantNamesBasedOnClasificationTest()
	{
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items();
		keralaManchuria.setItemId(UUID.randomUUID());
		keralaManchuria.setItemName("kerala Manchuria");
		keralaManchuria.setItemCost(300);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters = new Category();
		starters.setCategoryId(UUID.randomUUID());
		starters.setCategoryName("Kerala starters");
		starters.setItems(starterItems);
		Category Maincourse = new Category(UUID.randomUUID(), "Kerala mainCourse", mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications = new ArrayList<Clasification>();
		Clasification clasification = new Clasification();
		clasification.setClasificationID(UUID.randomUUID());
		clasification.setClasificationType("keralaType");
		clasification.setCategory(categories);
		clasifications.add(clasification);

		MenuList menuList = new MenuList();
		menuList.setMenuListId(UUID.randomUUID());
		menuList.setResturantCode("FD001TR");
		menuList.setClasification(clasifications);
		List<Object[]> expected=new ArrayList<Object[]>();
		Mockito.when(menuservice.getResturantsListBasedOnClasificationtype(clasification.getClasificationType())).thenReturn(expected);
		List<Object[]> result=releaseController.getResturantsListBasedOnClasificationtype(clasification.getClasificationType());
		Assertions.assertEquals(expected, result);
		
	}
	@Test
	@DisplayName("get resturant list based on item name test case in controller layer")
	 void getResturantsListBasedOnItemName()
	{
		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID(); 
		Address address = new Address();
		address.setAddressId(addressId);
		address.setCity("hyd");
		address.setCountry("india");
		address.setLatitude(27.8997);
		address.setLongitude(23.5637);
		address.setState("ts");
		address.setStreet("kphb");
		address.setZipCode("278997"); 
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items();
		keralaManchuria.setItemId(UUID.randomUUID());
		keralaManchuria.setItemName("kerala Manchuria");
		keralaManchuria.setItemCost(300);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters = new Category();
		starters.setCategoryId(UUID.randomUUID());
		starters.setCategoryName("Kerala starters");
		starters.setItems(starterItems);
		Category Maincourse = new Category(UUID.randomUUID(), "Kerala mainCourse", mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications = new ArrayList<Clasification>();
		Clasification clasification = new Clasification();
		clasification.setClasificationID(UUID.randomUUID());
		clasification.setClasificationType("keralaType");
		clasification.setCategory(categories);
		clasifications.add(clasification);

		MenuList menuList = new MenuList();
		menuList.setMenuListId(UUID.randomUUID());
		menuList.setResturantCode("FD001TR");
		menuList.setClasification(clasifications);
		
		List<Object[]> expected=new ArrayList<Object[]>();
		Mockito.when(menuservice.getResturantsListBasedOnItemName(keralaManchuria.getItemName())).thenReturn(expected);
		List<Object[]> result=releaseController.getResturantsListBasedOnItemName(keralaManchuria.getItemName());
		Assertions.assertEquals(expected, result);
		
	}
	@Test
	@DisplayName("delete menu based on menu id test case in controller layer")
	 void deleteMenuBasedOnIdTest()
	{

		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters=new  Category(UUID.randomUUID(),"Kerala starters",starterItems);
		Category Maincourse=new  Category(UUID.randomUUID(),"Kerala mainCourse",mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications=new ArrayList<Clasification>();
		Clasification clasification=new Clasification(UUID.randomUUID(),"keralaType",categories);
		clasifications.add(clasification);
		MenuList menuList=new MenuList(UUID.randomUUID(),"",clasifications);
		ResponseEntity<StandardReleaseResponse> expected=new EntitlementUtils().createResponseEntity(201, "deleted");
		Mockito.when(menuservice.deleteMenuBasedOnId(menuList.getMenuListId())).thenReturn(expected);
		ResponseEntity<StandardReleaseResponse> result = releaseController.deleteMenuBasedOnId(menuList.getMenuListId());
		Assertions.assertEquals(expected, result);
		
		
	}
	@Test
	@DisplayName("delete menu based on resturant name test case in controller layer")
	 void deleteMenuBasedOnResturantNameTest()
	{
		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID(); 
		Address address = new Address(addressId, "hyd", "kphb", "kphb", "india", "278997",  2335637, 67678807);
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		resturant.setResturantCode("FD001TR");
		ResponseEntity<StandardReleaseResponse> expected=new EntitlementUtils().createResponseEntity(201, "deleted");
		Mockito.when(menuservice.deleteMenuBasedOnResturantName(resturant.getResturantName())).thenReturn(expected);
		ResponseEntity<StandardReleaseResponse> result = releaseController.deleteMenuBasedOnResturantName(resturant.getResturantName());
		Assertions.assertEquals(expected, result);
	}
	@Test
	@DisplayName("delete resturant based on resturant id test case in controller layer")
	  void deleteResturantBasedOnIdTest()
	{
		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID(); 
		Address address = new Address(addressId, "hyd", "kphb", "kphb", "india", "278997",  2335637, 67678807);
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		resturant.setResturantCode("FD001TR");
		ResponseEntity<StandardReleaseResponse> expected=new EntitlementUtils().createResponseEntity(201, "deleted");
		Mockito.when(resturantService.deleteResturantBasedOnId(resturant.getResturantId())).thenReturn(expected);
		ResponseEntity<StandardReleaseResponse> result = releaseController.deleteResturantBasedOnId(resturant.getResturantId());
		Assertions.assertEquals(expected, result);
		
		
	}
	@Test
	@DisplayName("delete resturant based on resturant name test case in controller layer")
	 void deleteResturantBasedOnResturantNameTest()
	{
		String resturantId = UUID.randomUUID().toString();
		UUID addressId = UUID.randomUUID(); 
		Address address = new Address(addressId, "hyd", "kphb", "kphb", "india", "278997",  2335637, 67678807);
		Resturant resturant = new Resturant();
		resturant.setResturantId(resturantId);
		resturant.setResturantName("treat"); 
		resturant.setResturantType("veg");
		resturant.setResturantAddress(address);
		resturant.setCreatedBy("divya");
		resturant.setResturantCode("FD001TR");
		ResponseEntity<StandardReleaseResponse> expected=new EntitlementUtils().createResponseEntity(201, "deleted");
		Mockito.when(resturantService.deleteResturantBasedOnResturantName(resturant.getResturantName())).thenReturn(expected);
		ResponseEntity<StandardReleaseResponse> result = releaseController.deleteResturantBasedOnResturantName(resturant.getResturantName());
		Assertions.assertEquals(expected, result);
	
	}

}
