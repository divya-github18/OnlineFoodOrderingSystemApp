package com.example.demo.menu;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.example.demo.BaseClass;
import com.example.demo.models.Address;
import com.example.demo.models.Category;
import com.example.demo.models.Clasification;
import com.example.demo.models.Items;
import com.example.demo.models.MenuList;
import com.example.demo.models.Resturant;
import com.example.demo.repository.IMenuRepository;
import com.example.demo.service.MenuService;
import com.example.demo.util.EntitlementUtils;
import com.example.demo.util.MenuServiceMethods;
import com.example.demo.util.StandardReleaseResponse;

class MenuServiceTest extends BaseClass {
	@InjectMocks
	MenuService menuService;

	@Mock
	IMenuRepository iMenuRepository;

	@Mock
	MenuServiceMethods menuServiceMethods;

	@Test
	@DisplayName("create menu test case in service layer")
	 void createMenuTest() {
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

		ResponseEntity<StandardReleaseResponse> expected = new EntitlementUtils().createResponseEntity(201, "created");
		MenuList menuList = new MenuList();
		menuList.setMenuListId(UUID.randomUUID());
		menuList.setResturantCode("FD001TR");
		menuList.setClasification(clasifications);
		ResponseEntity<StandardReleaseResponse> response = menuService.handleUserActions(menuList);
		Assert.assertEquals(expected.getStatusCodeValue(), response.getStatusCodeValue());
		Mockito.verify(menuServiceMethods).generateIds(menuList);
		Mockito.verify(menuServiceMethods).insertMenu(menuList);
		Mockito.verify(menuServiceMethods).insertItem(menuList);
		Mockito.verify(menuServiceMethods).insertCategory(menuList);
		Mockito.verify(menuServiceMethods).insertClasification(menuList);

	}

	@Test
	@DisplayName("get menu list based on menu id test case in service layer")
	 void getMenuListBasedOnId() {
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters = new Category(UUID.randomUUID(), "Kerala starters", starterItems);
		Category Maincourse = new Category(UUID.randomUUID(), "Kerala mainCourse", mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications = new ArrayList<Clasification>();
		Clasification clasification = new Clasification(UUID.randomUUID(), "keralaType", categories);
		clasifications.add(clasification);
		MenuList menuList = new MenuList(UUID.randomUUID(), "", clasifications);
		Mockito.when(iMenuRepository.getMenuList(menuList.getMenuListId())).thenReturn(menuList);
		MenuList result = menuService.getMenuListBasedOnId(menuList.getMenuListId());
		Assert.assertEquals(result, menuList);

	}

	@Test
	@DisplayName("update menulist based on menu id test case in service layer ")
	 void updateMenuListBasedOnId() {
		List<Items> starterItems = new ArrayList<Items>();
		Items keralaManchuria = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		starterItems.add(keralaManchuria);
		List<Items> mainCourse = new ArrayList<Items>();
		Items keralaBiryani = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani);
		List<Category> categories = new ArrayList<Category>();
		Category starters = new Category(UUID.randomUUID(), "Kerala starters", starterItems);
		Category Maincourse = new Category(UUID.randomUUID(), "Kerala mainCourse", mainCourse);
		categories.add(starters);
		categories.add(Maincourse);
		List<Clasification> clasifications = new ArrayList<Clasification>();
		Clasification clasification = new Clasification(UUID.randomUUID(), "keralaType", categories);
		clasifications.add(clasification);
		MenuList menuList = new MenuList(UUID.randomUUID(), "FD001AS", clasifications);

		List<Items> starterItems1 = new ArrayList<Items>();
		Items keralaManchuria1 = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		Items pongal = new Items(UUID.randomUUID(), "pongal", 30);
		starterItems.add(keralaManchuria1);
		starterItems.add(pongal);
		List<Items> mainCourse1 = new ArrayList<Items>();
		Items keralaBiryani1 = new Items(UUID.randomUUID(), "kerala Manchuria", 30);
		mainCourse.add(keralaBiryani1);
		List<Category> categories1 = new ArrayList<Category>();
		Category starters1 = new Category(UUID.randomUUID(), "Kerala starters", starterItems1);
		Category Maincourse1 = new Category(UUID.randomUUID(), "Kerala mainCourse", mainCourse1);
		categories.add(starters1);
		categories.add(Maincourse1);
		List<Clasification> clasifications1 = new ArrayList<Clasification>();
		Clasification clasification1 = new Clasification(UUID.randomUUID(), "keralaType", categories1);
		clasifications.add(clasification1);
		MenuList menu = new MenuList(menuList.getMenuListId(), "FD001AS", clasifications1);
		Mockito.when(iMenuRepository.findById(menuList.getMenuListId())).thenReturn(Optional.of(menuList));
		ResponseEntity<StandardReleaseResponse> response = menuService.updateMenuListBasedOnId(menu,
				menuList.getMenuListId());
		Assert.assertEquals(201, response.getStatusCodeValue());

	}
	@Test
	@DisplayName("get menu list based on resturant name test case in service layer")
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
		
		Mockito.when(iMenuRepository.getMenuDetailsBasedOnResturantName(resturant.getResturantName())).thenReturn(menuList);
		MenuList result=menuService.getMenuListBasedOnResturantName(resturant.getResturantName());
		Assertions.assertEquals(menuList, result);
		
		
	}
	@Test
	@DisplayName("get items based on category test case in service layer")
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
		Mockito.when(iMenuRepository.getItemsBasedonCategory(starters.getCategoryName())).thenReturn(expected);
		List<Object[]> result=menuService.getItemsBasedonCategory(starters.getCategoryName());
		Assertions.assertEquals(expected, result);

		
	}
	@Test
	@DisplayName("get resturant names based on clasification name test case in service layer")
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
		Mockito.when(iMenuRepository.getResturantsListBasedOnClasificationtype(clasification.getClasificationType())).thenReturn(expected);
		List<Object[]> result=menuService.getResturantsListBasedOnClasificationtype(clasification.getClasificationType());
		Assertions.assertEquals(expected, result);
		
	}
	@Test
	@DisplayName("get resturant list based on item name test case in service layer")
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
		Mockito.when(iMenuRepository.getResturantsOnItemName(keralaManchuria.getItemName())).thenReturn(expected);
		List<Object[]> result=menuService.getResturantsListBasedOnItemName(keralaManchuria.getItemName());
		Assertions.assertEquals(expected, result);
		
	}
	@Test
	@DisplayName("delete menu based on id test case in service layer")
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
		menuService.deleteMenuBasedOnId(menuList.getMenuListId());
		Mockito.verify(menuServiceMethods).deleteMenuBasedOnId(menuList.getMenuListId());

		
		
	}
	@Test
	@DisplayName("delete menu based on resturant name test case in service layer")
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
		menuService.deleteMenuBasedOnResturantName(resturant.getResturantName());
		Mockito.verify(menuServiceMethods).deleteMenuBasedOnResturantName(resturant.getResturantName());
		
		
		
	}
}
