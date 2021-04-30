package com.example.demo.controller;



import java.util.List;
import java.util.UUID;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commonservice.CommonService;
import com.example.demo.models.MenuList;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Resturant;

import com.example.demo.service.MenuService;
import com.example.demo.service.ResturantService;
import com.example.demo.util.StandardReleaseResponse;


/**
 * @RestController annotation in order to simplify the creation of restful web services
 * @RequestMapping annotation is used to map web requests 
 * @author dboyapalli
 *
 */
@RestController
@RequestMapping("/v1/onlinefoodprocessing")
public class ResturantReleaseController {
	@Autowired
	CommonService commonService;
	
	@Autowired
	ResturantService resturantService;
	
	@Autowired
	MenuService menuService;
	private static final Logger logger = LoggerFactory.getLogger(ResturantReleaseController.class);

	/**
	 * This method is used to create resturant using post mapping
	 * @param resturant
	 * @return response message and response Code
	 */
	@PostMapping("/resturants")
	public ResponseEntity<StandardReleaseResponse> createResturant(@RequestBody Resturant resturant) {
		logger.info("sending Resturant data to commonservice"); 
		return commonService.processEntities(resturant);  
	}
	/**
	 * This method is used to create menu using post mapping
	 * @param menuList
	 * @return response message and response code
	 */
	@PostMapping("/menu")
	public ResponseEntity<StandardReleaseResponse> createMenu(@RequestBody MenuList menuList)  {
		logger.info("Sending Menu data to commonservice");
		return commonService.menuProcessEntities(menuList);

	}
	/**
	 * This method is used to create order status messages
	 * @param orderStatus
	 * @return response message and response code
	 */
	@PostMapping("/orderstatus")
	public ResponseEntity<StandardReleaseResponse> createOrderStatuses(@RequestBody OrderStatus orderStatus)  {
		logger.info("Sending Menu data to commonservice");
		return commonService.orderStatusProcessEntities(orderStatus);

	}
	
	//get mapping
	
	/**
	 * This method is used to get the menulist based on id using get mapping
	 * @param menuListId
	 * @return response message and response code
	 */
	@GetMapping("/menudetails/{menuListId}")
	public MenuList getMenuDeatailsBasedOnMenuId(@PathVariable(value = "menuListId") UUID menuListId )  {
		
		
		return menuService.getMenuListBasedOnId(menuListId);
		 
	}
	/**
	 * this method is used to get the  menu details based on restaurant name
	 * @param resturantName
	 * @return menulist
	 */
	@GetMapping("/menudetailsonresturantname/{resturantName}")
	public MenuList getMenuDeatailsBasedOnResturantName(@PathVariable(value = "resturantName") String resturantName )  {
		
		return menuService.getMenuListBasedOnResturantName(resturantName);
		 
	}
	/**
	 *  this method is used to get the list of restaurant names  based on item name
	 * @param itemName
	 * @return list of restaurants
	 */
	@GetMapping("/resturantlistsonitemname/{itemName}")
	public List<Object[]> getResturantsListBasedOnItemName(@PathVariable(value = "itemName") String itemName )  {
		
		return menuService.getResturantsListBasedOnItemName(itemName); 
		 
	}
	/**
	 * this method is used to get the list of items based on category name
	 * @param categoryName
	 * @return list of items
	 */
	@GetMapping("/itemsbasedonCategory/{categoryName}")
	public List<Object[]> getItemsBasedonCategory(@PathVariable(value = "categoryName") String categoryName )  {
		
		return menuService.getItemsBasedonCategory(categoryName);
		 
	}
	/**
	 * this method is used to list of resturants based on clasification type
	 * @param clasificationType
	 * @return list of rsturants
	 */
	@GetMapping("/resturantslistbasedonclasification/{clasificationType}")
	public List<Object[]> getResturantsListBasedOnClasificationtype(@PathVariable(value = "clasificationType") String clasificationType )  {
		
		return menuService.getResturantsListBasedOnClasificationtype(clasificationType);
		 
	}
	
	//put mapping
	
	
	/**
	 * this method is used update the menulist based on id using put mapping
	 * @param menuListDetails
	 * @param menuListId
	 * @return response message and response code
	 */
	@PutMapping("/menudetails/{menuListId}")
	public  ResponseEntity<StandardReleaseResponse> updateMenuDeatailsBasedOnMenuId(@RequestBody MenuList menuListDetails ,@PathVariable(value = "menuListId") UUID menuListId )  {
		
		return menuService.updateMenuListBasedOnId(menuListDetails,menuListId); 
		 
	}
	/**
	 * this method is used to delete menu based on menu id
	 * @param menuListId
	 * @return response message and response code
	 */
	@DeleteMapping("/deletemenu/{menuListId}")
	public ResponseEntity<StandardReleaseResponse> deleteMenuBasedOnId(@PathVariable(value = "menuListId") UUID menuListId){
		
		return menuService.deleteMenuBasedOnId(menuListId);
	}
	/**
	 * this method is used to delete menu based on restaurant name
	 * @param resturantName
	 * @return response message and response code
	 */
	@DeleteMapping("/deletemenuonresturantname/{resturantName}")
	public ResponseEntity<StandardReleaseResponse> deleteMenuBasedOnResturantName(@PathVariable(value = "resturantName") String resturantName){
		
		return menuService.deleteMenuBasedOnResturantName(resturantName);
	}
	/**
	 * this method is used to delete resturant based on restaurant id
	 * @param resturantId
	 * @return response message and response code
	 */
	@DeleteMapping("/deleteresturant/{resturantId}")
	public ResponseEntity<StandardReleaseResponse> deleteResturantBasedOnId(@PathVariable(value = "resturantId") String resturantId){
		
		return resturantService.deleteResturantBasedOnId(resturantId);
	}
	/**
	 * this method is used to delete resturant based on resturant name
	 * @param resturantName
	 * @return response message and response code
	 */
	@DeleteMapping("/deleteresturantonresturantname/{resturantName}")
	public ResponseEntity<StandardReleaseResponse> deleteResturantBasedOnResturantName(@PathVariable(value = "resturantName") String resturantName){
		
		return resturantService.deleteResturantBasedOnResturantName(resturantName);
	}
	
	
	
}
