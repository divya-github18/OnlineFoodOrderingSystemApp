package com.example.demo.resturant;

import java.util.UUID;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.example.demo.BaseClass;
import com.example.demo.models.Address;
import com.example.demo.models.Resturant;
import com.example.demo.repository.IResturantRepository;
import com.example.demo.service.MenuService;
import com.example.demo.service.ResturantService;
import com.example.demo.util.MenuServiceMethods;
import com.example.demo.util.ResturantCodeGeneration;
import com.example.demo.util.StandardReleaseResponse;

class ResturantServiceTest extends BaseClass {
	
	@InjectMocks
	ResturantService resturantService;
	@Mock
	IResturantRepository iResturantRepository;
	@Mock
	ResturantCodeGeneration resturantCodeGeneration;
	@Mock
	MenuServiceMethods menuServiceMethods;
	@Mock
	MenuService menuService;
	@Test
	@DisplayName("create resturant test case in service layer")
	 void createResturantTestCase() {

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
		ResponseEntity<StandardReleaseResponse> response = resturantService.handleUserActions(resturant);
		Mockito.when( resturantService.handleUserActions(resturant)).thenReturn(response);
		Mockito.verify(iResturantRepository).save(resturant);
		Assert.assertEquals(201, response.getStatusCodeValue()); 

	}
	@Test
	@DisplayName("delete resturant based on resturant id test case in service layer")
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
		Mockito.when(iResturantRepository.findById(resturantId)).thenReturn(resturant);
		resturantService.deleteResturantBasedOnId(resturantId);
		Mockito.verify(menuServiceMethods).deleteMenuBasedOnResturantName(resturant.getResturantName());
		Mockito.verify(iResturantRepository).deleteResturantOnId(resturant.getResturantId());
		Mockito.verify(iResturantRepository).deleteAddressOfResturantId(resturant.getResturantAddress().getAddressId());
	}
	@Test
	@DisplayName("delete resturant based on resturant name test case in service layer")
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
		Mockito.when(iResturantRepository.getResturantBasedOnresturantName(resturant.getResturantName())).thenReturn(resturant);
		resturantService.deleteResturantBasedOnResturantName(resturant.getResturantName());
		Mockito.verify(menuServiceMethods).deleteMenuBasedOnResturantName(resturant.getResturantName());
		Mockito.verify(iResturantRepository).deleteResturantOnId(resturant.getResturantId());
		Mockito.verify(iResturantRepository).deleteAddressOfResturantId(resturant.getResturantAddress().getAddressId());
	}
	
}
