package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Category;
import com.example.demo.models.Clasification;
import com.example.demo.models.Items;
import com.example.demo.models.MenuList;
import com.example.demo.models.Resturant;
import com.example.demo.repository.IMenuRepository;
import com.example.demo.util.EntitlementUtils;
import com.example.demo.util.MenuServiceMethods;
import com.example.demo.util.StandardReleaseResponse;

@Service
@Transactional
public class MenuService {

	@Autowired
	MenuServiceMethods menuServiceMethods;

	@Autowired
	IMenuRepository iMenuRepository;

	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

	/**
	 * HandleUserActions Method
	 * @param menuList
	 * @return StandardReleaseResponse
	 * @throws Exception
	 */
	public ResponseEntity<StandardReleaseResponse> handleUserActions(MenuList menuList) {

		return createMenu(menuList);
	}

	/**
	 * CreateMenu list method
	 * 
	 * @param menulist
	 * @return response entity
	 * @throws Exception
	 */

	private ResponseEntity<StandardReleaseResponse> createMenu(MenuList menuList) {
		menuServiceMethods.generateIds(menuList);
		menuServiceMethods.insertMenu(menuList);
		menuServiceMethods.insertClasification(menuList);
		menuServiceMethods.insertCategory(menuList);
		menuServiceMethods.insertItem(menuList);
		logger.info("reached to menulist");
		return new EntitlementUtils().createResponseEntity(201, "created");
	}

	/**
	 * this method is used to get the menu list based on menu id
	 * @param menuListId
	 * @return response entity
	 */
	public MenuList getMenuListBasedOnId(UUID menuListId) {

		return iMenuRepository.getMenuList(menuListId);
	}

	/**
	 * this method is used to update the menulist based on menu id 
	 * @param menuListDetails
	 * @param menuListId
	 * @return response entity
	 */
	public ResponseEntity<StandardReleaseResponse> updateMenuListBasedOnId(MenuList menuListDetails, UUID menuListId) {

		MenuList menu = iMenuRepository.findById(menuListId).orElseThrow();
		menuListDetails.setUpdatedBy("divya");
		menuListDetails.setUpdatedTime(LocalDateTime.now());
		List<Clasification> oldClasifications = menu.getClasification();
		List<Category> totalOldCategories = new ArrayList<>();
		List<Items> totalOldItems = new ArrayList<>();
		for (Clasification oldClasification : oldClasifications) {
			List<Category> oldCurrentCategories = oldClasification.getCategory();
			for (Category category : oldCurrentCategories) {
				totalOldCategories.add(category);
				List<Items> oldItems = category.getItems();
				for (Items items : oldItems) {
					totalOldItems.add(items);
				}}
		}
		menuServiceMethods.setMenuListDetails(menuListDetails,menu);
		menuServiceMethods.setClasifiactions(oldClasifications, menuListDetails);
		menuServiceMethods.setCategories(oldClasifications, totalOldCategories, menuListDetails);
		menuServiceMethods.setItems(oldClasifications, totalOldItems, menuListDetails);
		menuServiceMethods.removeOperations(oldClasifications, menuListDetails);
		return new EntitlementUtils().createResponseEntity(201, "updated");

	}
	/**
	 * this method is used to get the menulist based on resturant name
	 * @param resturantName
	 * @return MenuList
	 */
	public MenuList getMenuListBasedOnResturantName(String resturantName) {

		return iMenuRepository.getMenuDetailsBasedOnResturantName(resturantName);
	}
	/**
	 * this method is used to get the resturants list based on item names
	 * @param itemName
	 * @return list of resturants
	 */
	public List<Object[]> getResturantsListBasedOnItemName(String itemName) {

		return iMenuRepository.getResturantsOnItemName(itemName);
	}
	/**
	 * this method is used to get the items based on category name
	 * @param categoryName
	 * @return list of items
	 */
	public List<Object[]> getItemsBasedonCategory(String categoryName) {

		return iMenuRepository.getItemsBasedonCategory(categoryName);

	}
	/**
	 * this is used to get the resturants list based on clasifiication
	 * @param clasificationType
	 * @return list of resturants
	 */
	public List<Object[]> getResturantsListBasedOnClasificationtype(String clasificationType) {

		return iMenuRepository.getResturantsListBasedOnClasificationtype(clasificationType);
	}
	/**
	 * this method is used to delete menu based on id
	 * @param menuListId
	 * @return response entitiy
	 */

	public ResponseEntity<StandardReleaseResponse> deleteMenuBasedOnId(UUID menuListId) {
		
		menuServiceMethods.deleteMenuBasedOnId(menuListId);
		 return new EntitlementUtils().createResponseEntity(201, "deleted");
	}
	
	/**
	 * this method is used to delete menu based on resturant name
	 * @param resturantName
	 * @return response entitiy
	 */
	public ResponseEntity<StandardReleaseResponse> deleteMenuBasedOnResturantName(String resturantName) {
		menuServiceMethods.deleteMenuBasedOnResturantName(resturantName);
		return new EntitlementUtils().createResponseEntity(201, "deleted");
	}

}
