package com.example.demo.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.example.demo.BaseClass;
import com.example.demo.models.Category;
import com.example.demo.models.Clasification;
import com.example.demo.models.Items;
import com.example.demo.models.MenuList;
import com.example.demo.repository.IMenuRepository;

class MenuServiceMethodsTest extends BaseClass{
	
	
	@InjectMocks
	MenuServiceMethods menuServiceMethods;
	@Mock
	IMenuRepository iMenuRepository;
	
	@Test 
	@DisplayName("generate ids test case for menu list")
	void generateIdsTest()
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
		menuServiceMethods.generateIds(menuList);
		
		
	}
	@Test 
	@DisplayName("inseert menu values in menu table test case")
	void insertMenuTest()
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
		menuList.setCreatedTime(LocalDateTime.now());
		menuList.setCreatedBy("divya");
		menuServiceMethods.insertMenu(menuList);
		Mockito.verify(iMenuRepository).insertMenuValues(menuList.getMenuListId(), menuList.getResturantCode(), menuList.getCreatedBy(), menuList.getCreatedTime(), menuList.getUpdatedBy(), menuList.getUpdatedTime());
		
		
	}
	@Test 
	@DisplayName("inseert category values in category table test case")
	void insertCategoryTest()
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
		menuList.setCreatedTime(LocalDateTime.now());
		menuList.setCreatedBy("divya");
		menuServiceMethods.insertCategory(menuList);
		Mockito.verify(iMenuRepository).insertCategoryValues(starters.getCategoryId(), starters.getCategoryName(), clasification.getClasificationID(), starters.getCreatedBy(), starters.getCreatedTime(),starters.getUpdatedBy(), starters.getUpdatedTime());
		
		
	}
	@Test 
	@DisplayName("inseert clasification values in clasification table test case")
	void insertClasificationTest()
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
		clasification.setCreatedBy("divya");
		clasification.setCreatedTime(LocalDateTime.now());
		clasifications.add(clasification);
		MenuList menuList = new MenuList();
		menuList.setMenuListId(UUID.randomUUID());
		menuList.setResturantCode("FD001TR");
		menuList.setClasification(clasifications);
		menuList.setCreatedTime(LocalDateTime.now());
		menuList.setCreatedBy("divya");
		menuServiceMethods.insertClasification(menuList);
		Mockito.verify(iMenuRepository).insertClasificationValues(clasification.getClasificationID(), clasification.getClasificationType(), menuList.getMenuListId(), clasification.getCreatedBy(), menuList.getCreatedTime(),clasification.getUpdatedBy(),clasification.getUpdatedTime());
		
	}
	@Test 
	@DisplayName("inseert item values in item table test case")
	 void inserItemTest()
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
		clasification.setCreatedBy("divya");
		clasification.setCreatedTime(LocalDateTime.now());
		clasifications.add(clasification);
		MenuList menuList = new MenuList();
		menuList.setMenuListId(UUID.randomUUID());
		menuList.setResturantCode("FD001TR");
		menuList.setClasification(clasifications);
		menuList.setCreatedTime(LocalDateTime.now());
		menuList.setCreatedBy("divya");
		menuServiceMethods.insertItem(menuList);
		Mockito.verify(iMenuRepository).itemsInsert(keralaManchuria.getItemId(), keralaManchuria.getItemName(), keralaManchuria.getItemCost(),starters.getCategoryId());
	}


}
