package com.example.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.models.Category;
import com.example.demo.models.Clasification;
import com.example.demo.models.Items;
import com.example.demo.models.MenuList;
import com.example.demo.repository.IMenuRepository;

@Component
public class MenuServiceMethods {
	@Autowired
	IMenuRepository iMenuRepository;
	public static final String createdBy = "divya";
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceMethods.class);
	/**
	 * this method is used to generate ids
	 * @param menuList
	 */
		public void generateIds(MenuList menuList) {
		menuList.setMenuListId(UUID.randomUUID());
		List<Clasification> clasifications = menuList.getClasification();
		for (int i = 0; i < clasifications.size(); i++) {
			Clasification clasification = clasifications.get(i);
			clasification.setClasificationID(UUID.randomUUID());
			clasification.setCreatedBy(createdBy);
			clasification.setCreatedTime(LocalDateTime.now());
			clasification.setUpdatedBy(null);
			clasification.setUpdatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
			List<Category> categories = clasification.getCategory();
			for (int j = 0; j < categories.size(); j++) {
				Category category = categories.get(j);
				category.setCategoryId(UUID.randomUUID());
				category.setCreatedBy(createdBy);
				category.setCreatedTime(LocalDateTime.now());
				category.setUpdatedBy(null);
				category.setUpdatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
				List<Items> items = category.getItems();
				for (int k = 0; k < items.size(); k++) {
					Items item = items.get(k);
					item.setItemId(UUID.randomUUID());
				}
			}
		}	
	}
	/**
	 * this method is used to insert the menulist values in menulist table
	 * @param menuList
	 */
	public void insertMenu(MenuList menuList) {
		
		menuList.setCreatedBy(createdBy);
		menuList.setCreatedTime(LocalDateTime.now());
		menuList.setUpdatedBy(null);
		menuList.setUpdatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
		iMenuRepository.insertMenuValues(menuList.getMenuListId(), menuList.getResturantCode(),menuList.getCreatedBy(),menuList.getCreatedTime(),menuList.getUpdatedBy(),menuList.getUpdatedTime());
	}
	/**
	 * this method is used to insert the clasification values in Clasification table
	 * @param menuList
	 */
	public void insertClasification(MenuList menuList) {
		UUID id=menuList.getMenuListId();
		List<Clasification> clasifications=menuList.getClasification();
		for(Clasification clasification:clasifications)
		{
			clasification.setCreatedBy(createdBy);
			clasification.setCreatedTime(LocalDateTime.now());
			clasification.setUpdatedBy(null);
			clasification.setUpdatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
			iMenuRepository.insertClasificationValues(clasification.getClasificationID(),
					clasification.getClasificationType(),id,clasification.getCreatedBy(),clasification.getCreatedTime(),clasification.getUpdatedBy(),clasification.getUpdatedTime());
			logger.info("clasification values inserted");
		}
	}
	/**
	 * this method is used to insert category values in category table
	 * @param menuList
	 */
	public void insertCategory(MenuList menuList) {
		
		List<Clasification> clasifications=menuList.getClasification();
		for(Clasification clasification:clasifications)
		{
			List<Category> categories=clasification.getCategory();
			for(Category category:categories) {
				
				category.setCreatedBy(createdBy);
				category.setCreatedTime(LocalDateTime.now());
				category.setUpdatedBy(null);
				category.setUpdatedTime(LocalDateTime.of(1, 1, 1, 1, 1));
				iMenuRepository.insertCategoryValues(category.getCategoryId(), category.getCategoryName(),
						clasification.getClasificationID(),category.getCreatedBy(),category.getCreatedTime(),category.getUpdatedBy(),category.getUpdatedTime());
				logger.info("category values inserted");
			}
		}
	}
	/**
	 * this method is used to insert item values in items table
	 * @param menuList
	 */
	public void insertItem(MenuList menuList) {
		
		List<Clasification> clasifications=menuList.getClasification();
		for(Clasification clasification:clasifications)
		{
			List<Category> categories=clasification.getCategory(); 
			for(Category category:categories) {
				List<Items> items=category.getItems();
				for(Items item:items)
				{
					iMenuRepository.itemsInsert(item.getItemId(), item.getItemName(), item.getItemCost(),
							category.getCategoryId());
					logger.info("item values inserted");
				}
			}
		}
	}
	public void setMenuListDetails(MenuList menuListDetails, MenuList menu) {
		iMenuRepository.updateMenuDetails(menu.getMenuListId(),menuListDetails.getUpdatedBy(),menuListDetails.getUpdatedTime());
	}
/**
 * this method is used to update the clasifications
 * @param oldClasifications
 * @param menuListDetails
 */
	public void setClasifiactions(List<Clasification> oldClasifications, MenuList menuListDetails) {
		List<Clasification>newClasifications=menuListDetails.getClasification();
		List<String> oldclasificationTypes= new ArrayList<>();		
		for (Clasification oldClasification : oldClasifications) {
			String oldClasificationtype=oldClasification.getClasificationType();
			oldclasificationTypes.add(oldClasificationtype);

		}
		
		
		for(Clasification clasification:newClasifications) {
		if (!oldclasificationTypes.contains(clasification.getClasificationType())) {
			
			clasification.setClasificationID(UUID.randomUUID());
			clasification.setCreatedTime(LocalDateTime.now());
			clasification.setCreatedBy(createdBy);
			clasification.setUpdatedBy("divya");
			clasification.setUpdatedTime(LocalDateTime.now());
			iMenuRepository.insertClasificationValues(clasification.getClasificationID(),
					clasification.getClasificationType(),menuListDetails.getMenuListId(),clasification.getCreatedBy(),clasification.getCreatedTime(),clasification.getUpdatedBy(),clasification.getUpdatedTime());
			
		} else {
			clasification.setClasificationID(oldClasifications.get(oldclasificationTypes.indexOf(clasification.getClasificationType())).getClasificationID());
			clasification.setCreatedBy(oldClasifications.get(oldclasificationTypes.indexOf(clasification.getClasificationType())).getCreatedBy());
			clasification.setCreatedTime(oldClasifications.get(oldclasificationTypes.indexOf(clasification.getClasificationType())).getCreatedTime());
			clasification.setUpdatedBy("divya");
			clasification.setUpdatedTime(LocalDateTime.now());
			iMenuRepository.updateClasification(clasification.getClasificationID(),clasification.getUpdatedBy(),clasification.getUpdatedTime());
		}
		}
		
	}
/**
 * this method is used update the categories
 * @param oldClasifications
 * @param totalOldCategories
 * @param menuListDetails
 */
	public void setCategories(List<Clasification> oldClasifications, List<Category> totalOldCategories, MenuList menuListDetails) {
		List<Clasification>newClasifications=menuListDetails.getClasification();
		
		List<String> oldCategoryNames= new ArrayList<>();
		for (Clasification oldClasification : oldClasifications) {
			List<Category>  oldcategories = oldClasification.getCategory();
			for (Category oldCategory : oldcategories) {
				oldCategoryNames.add(oldCategory.getCategoryName());
			}
		}
		
		
	for (Clasification newClasification : newClasifications) {
	
		List<Category> newCategories=newClasification.getCategory();
		for (Category category : newCategories) {
		
			if (!oldCategoryNames.contains(category.getCategoryName())) {
				category.setCategoryId(UUID.randomUUID());
				category.setCreatedBy(createdBy);
				category.setCreatedTime(LocalDateTime.now());
				category.setUpdatedBy("divya");
				category.setUpdatedTime(LocalDateTime.now());
				iMenuRepository.insertCategoryValues(category.getCategoryId(), category.getCategoryName(),
						newClasification.getClasificationID(),category.getCreatedBy(),category.getCreatedTime(),category.getUpdatedBy(),category.getUpdatedTime());
			} else {
				category.setCategoryId(totalOldCategories.get(oldCategoryNames.indexOf(category.getCategoryName())).getCategoryId());
				category.setCreatedBy(totalOldCategories.get(oldCategoryNames.indexOf(category.getCategoryName())).getCreatedBy());
				category.setCreatedTime(totalOldCategories.get(oldCategoryNames.indexOf(category.getCategoryName())).getCreatedTime());
				category.setUpdatedBy("divya");
				category.setUpdatedTime(LocalDateTime.now());
				iMenuRepository.updateCategory(category.getCategoryId(),category.getUpdatedBy(),category.getUpdatedTime());
			}

		}

	}
	}
/**
 * this method is used to update the items
 * @param oldClasifications
 * @param totalOldItems
 * @param menuListDetails
 */
	public void setItems(List<Clasification> oldClasifications, List<Items> totalOldItems, MenuList menuListDetails) {
		List<Clasification>newClasifications=menuListDetails.getClasification();
		List<String> oldItemNames= new ArrayList<>();
		for (Clasification oldClasification : oldClasifications) {
			List<Category> oldcategories = oldClasification.getCategory();
			for (Category oldCategory : oldcategories) {
				List<Items> oldItems=oldCategory.getItems();
				for (Items item : oldItems) {
					oldItemNames.add(item.getItemName());
				}
			}
		}
		
		
		for (int i = 0; i < newClasifications.size(); i++) {
			Clasification clasification = newClasifications.get(i);
			List<Category> categories = clasification.getCategory();
			for (int j = 0; j < categories.size(); j++) {
				Category category = categories.get(j);
				List<Items> items = category.getItems();
				for (int k = 0; k < items.size(); k++) {
					Items item = items.get(k);
					if (!oldItemNames.contains(item.getItemName())) {
						item.setItemId(UUID.randomUUID());
						iMenuRepository.itemsInsert(item.getItemId(), item.getItemName(), item.getItemCost(),
								category.getCategoryId());
					} else {
							
						item.setItemId(totalOldItems.get(oldItemNames.indexOf(item.getItemName())).getItemId());
					}

				}

			}

	}
		
	
	}
/**
 * remove the items,categories and clasifications which not have in new updated list
 * @param oldClasifications
 * @param menuListDetails
 */
	public void removeOperations(List<Clasification> oldClasifications, MenuList menuListDetails) {
		
		removeItems(oldClasifications,menuListDetails);
		removeCategories(oldClasifications,menuListDetails);
		removeClasifications(oldClasifications,menuListDetails);
}
/**
 * remove the clasifications which are not have in updated list
 * @param oldClasifications
 * @param menuListDetails
 */
private void removeClasifications(List<Clasification> oldClasifications, MenuList menuListDetails) {
	List<Clasification> newclasifications = menuListDetails.getClasification();
	ArrayList<UUID> clasificationids=new ArrayList<>();
	for (int i = 0; i < newclasifications.size(); i++) {
		
		for (Clasification clasification : newclasifications) {
			clasificationids.add(clasification.getClasificationID());
			
		}
	}
	for (int i = 0; i < oldClasifications.size(); i++) {
		Clasification oldclasification = oldClasifications.get(i);
		if(!clasificationids.contains(oldclasification.getClasificationID()))
		{
			iMenuRepository.deleteclasificationById(oldclasification.getClasificationID());
			logger.info("@@@@ "+oldclasification.getClasificationType());
		}

	}

	
}
/**
 * remove categories which are not in updated list
 * @param oldClasifications
 * @param menuListDetails
 */
private void removeCategories(List<Clasification> oldClasifications, MenuList menuListDetails) {
	List<Clasification> newclasifications = menuListDetails.getClasification();
	ArrayList<UUID> categoryIds=new ArrayList<>();
	for (int i = 0; i < newclasifications.size(); i++) {
		Clasification newclasification = newclasifications.get(i);
		List<Category> newcategories = newclasification.getCategory();
		for (Category Category : newcategories) {
			categoryIds.add(Category.getCategoryId());
		}

	}
	for (int i = 0; i < oldClasifications.size(); i++) {
		
		Clasification oldclasification = oldClasifications.get(i);
		List<Category> oldcategories = oldclasification.getCategory();
		for (Category category : oldcategories) {
			if(!categoryIds.contains(category.getCategoryId()))
			{
				iMenuRepository.deleteCategoryById(category.getCategoryId());
				logger.info("@@@@ "+category.getCategoryName());
			}
		}
		
	}
	
}
/**
 * remove items which are not in updated list
 * @param oldClasifications
 * @param menuListDetails
 */
private void removeItems(List<Clasification> oldClasifications, MenuList menuListDetails) {
	List<Clasification> newclasifications = menuListDetails.getClasification();
	ArrayList<UUID> itemids=new ArrayList<>();
	for (int i = 0; i < newclasifications.size(); i++) {
		Clasification newclasification = newclasifications.get(i);
		List<Category> newcategories = newclasification.getCategory();
		for (int j = 0; j < newcategories.size(); j++) {
			Category newcategory = newcategories.get(j);
			
			List<Items> newitems = newcategory.getItems();
			for (Items item : newitems) {
				itemids.add(item.getItemId());
			}
			

		}
	}
	for (int i = 0; i < oldClasifications.size(); i++) {
		
		Clasification oldclasification = oldClasifications.get(i);
		List<Category> oldcategories = oldclasification.getCategory();
		for (Category category : oldcategories) {
			List<Items> items=category.getItems();
			for (Items item : items) {
				if(!itemids.contains(item.getItemId())){
					iMenuRepository.deleteItemById(item.getItemId());
					logger.info("@@@ "+item.getItemName());
				}
				
			}
		}
		
	}
	
}

public void deleteMenuBasedOnId(UUID menuListId) {
	MenuList menu=iMenuRepository.findById(menuListId).orElseThrow();
	List<Clasification> clasifications=menu.getClasification();
	for (Clasification clasification : clasifications) {
		List<Category> categories=clasification.getCategory();
		for ( Category category : categories) {
			List<Items> items=category.getItems();
			for (Items item : items) {
				iMenuRepository.deleteItemById(item.getItemId());
			}
			iMenuRepository.deleteCategoryById(category.getCategoryId());
		}
		iMenuRepository.deleteclasificationById(clasification.getClasificationID());
	}
	 iMenuRepository.deleteMenuBasedOnId(menuListId);
}
public void deleteMenuBasedOnResturantName(String resturantName) {
	MenuList menu=iMenuRepository.getMenuDetailsBasedOnResturantName(resturantName);
	deleteMenuBasedOnId(menu.getMenuListId());
}




}
	
	
