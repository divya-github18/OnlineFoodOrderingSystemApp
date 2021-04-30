package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Category;
import com.example.demo.models.Clasification;
import com.example.demo.models.Items;
import com.example.demo.models.MenuList;


@Repository
public interface IMenuRepository extends JpaRepository<MenuList,UUID> {

	@Modifying
	@Query(value="insert into items (item_id,item_name,item_cost,items_category_id) values(:itemId,:itemName,:itemCost,:categoryId)",nativeQuery = true)
	void itemsInsert(UUID itemId, String itemName, int itemCost, UUID categoryId);

	
	@Modifying
	@Query(value="insert into category(category_id,category_name,category_clasification_id,created_by,created_time,updated_by,updated_time) values(:categoryId,:categoryName,:clasifiactionId,:createdBy,:createdTime,:updatedBy,:updatedTime)",nativeQuery = true)
	void insertCategoryValues(UUID categoryId, String categoryName, UUID clasifiactionId, String createdBy, LocalDateTime createdTime, String updatedBy, LocalDateTime updatedTime);

	
	@Modifying
	@Query(value="insert into clasification(clasification_id,clasification_Type,clasification_menu_list_id,created_by,created_time,updated_by,updated_time) values(:clasificationID,:clasificationType,:menuListId,:createdBy,:createdTime,:updatedBy,:updatedTime)",nativeQuery = true)
	void insertClasificationValues(UUID clasificationID, String clasificationType, UUID menuListId, String createdBy, LocalDateTime createdTime, String updatedBy, LocalDateTime updatedTime);


	@Modifying
	@Query(value="insert into menu_list(menu_list_id,resturant_code,created_by,created_time,updated_by,updated_time) values(:menuListId,:resturantCode,:createdBy,:createdTime,:updatedBy,:updatedTime)",nativeQuery = true)
	void insertMenuValues(UUID menuListId, String resturantCode, String createdBy, LocalDateTime createdTime, String updatedBy, LocalDateTime updatedTime);
	
	@Query(value = "select f from MenuList f where menuListId=?1")
	MenuList getMenuList(UUID menuListId);

	@Query(value = "select f from Clasification f where clasificationType=?1")
	Clasification findClasificationType(String clasificationType);

	@Query(value = "select f from Items f where itemName=?1")
	Items findItem(String itemName);


	@Query(value = "select f from Category f where categoryName=?1")
	Category findCategory(String categoryName);

	
	@Modifying
	@Query(value="delete from items i where i.item_id=?1",nativeQuery = true)
	void deleteItemById(UUID itemId);
	
	@Modifying
	@Query(value="delete from category i where i.category_id=?1",nativeQuery = true)
	void deleteCategoryById(UUID categoryId);
	
	@Modifying
	@Query(value="delete from clasification i where i.clasification_id=?1",nativeQuery = true)
	void deleteclasificationById(UUID clasificationID);

	@Query(value = "select f from MenuList f where resturantCode =(Select resturantCode from Resturant where resturantName=?1)")
	MenuList getMenuDetailsBasedOnResturantName(String resturantName);

	@Query(value = "select resturant.restaurant_name,items.item_name,items.item_cost from items inner join category on category.category_id=items.items_category_id inner join clasification on clasification.clasification_id=category.category_clasification_id"
			+ " inner join menu_list on menu_list.menu_list_id=clasification.clasification_menu_list_id inner join resturant on resturant.restaurant_code=menu_list.resturant_code where item_name=?1",nativeQuery = true)
	List<Object[]> getResturantsOnItemName(String itemName);

	@Query(value = "select items.item_name,items.item_cost,resturant.restaurant_name from items inner join category on category.category_id=items.items_category_id inner join clasification on clasification.clasification_id=category.category_clasification_id"
			+ " inner join menu_list on menu_list.menu_list_id=clasification.clasification_menu_list_id inner join resturant on resturant.restaurant_code=menu_list.resturant_code where category_name=?1",nativeQuery = true)
	List<Object[]> getItemsBasedonCategory(String categoryName);
	
	@Query(value = "select items.item_name,items.item_cost,resturant.restaurant_name,category.category_name from items inner join category on category.category_id=items.items_category_id inner join clasification on clasification.clasification_id=category.category_clasification_id "
			+ "inner join menu_list on menu_list.menu_list_id=clasification.clasification_menu_list_id inner join resturant on resturant.restaurant_code=menu_list.resturant_code where clasification_type=?1 ",nativeQuery = true)
	List<Object[]> getResturantsListBasedOnClasificationtype(String clasificationType);

	@Modifying
	@Query(value="delete from menu_list i where i.menu_list_id=?1",nativeQuery = true)
	void deleteMenuBasedOnId(UUID menuListId);

	@Modifying
	@Query(value="update menu_list set updated_by=?2,updated_time=?3 where menu_list_id=?1",nativeQuery = true)
	void updateMenuDetails(UUID menuListId, String updatedBy,LocalDateTime updatedTime);

	
	@Modifying
	@Query(value="update clasification set updated_by=?2,updated_time=?3 where clasification_id=?1",nativeQuery = true)
	void updateClasification(UUID clasificationID, String updatedBy, LocalDateTime updatedTime);

	@Modifying
	@Query(value="update category set updated_by=?2,updated_time=?3 where category_id=?1",nativeQuery = true)
	void updateCategory(UUID categoryId, String updatedBy, LocalDateTime updatedTime);
	



	
}
