package com.example.demo.service;


import java.time.LocalDateTime;

import java.util.UUID;

import javax.transaction.Transactional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Address;
import com.example.demo.models.Resturant;
import com.example.demo.repository.IMenuRepository;
import com.example.demo.repository.IResturantRepository;
import com.example.demo.util.EntitlementUtils;
import com.example.demo.util.MenuServiceMethods;
import com.example.demo.util.ResturantCodeGeneration;
import com.example.demo.util.StandardReleaseResponse;



@Service
@Transactional
public class ResturantService {
	@Autowired
	IResturantRepository iResturantRepository;
	@Autowired
	MenuServiceMethods menuServiceMethods;
	@Autowired
	ResturantCodeGeneration resturantCodeGeneration;
	
	public static final String createdBy = "divya";

	private static final Logger logger = LoggerFactory.getLogger(ResturantService.class);

	public ResponseEntity<StandardReleaseResponse> handleUserActions(Resturant resturant){

		logger.info("data send to createresturant method"); 
		return createResturant(resturant);

	}

	/**
	 * create Resaurant method
	 * @param resturant
	 * @return StandardReleaseResponse
	 * @throws Exception 
	 */
	private ResponseEntity<StandardReleaseResponse> createResturant(Resturant resturant) {
		
			resturant.setResturantId(UUID.randomUUID().toString());
			resturant.getResturantAddress().setAddressId(UUID.randomUUID()); 
			resturant.setCreatedTime(LocalDateTime.now());
			resturant.setCreatedBy(createdBy);
			String code = resturantCodeGeneration.resturantCodeGeneration(resturant);
			resturant.setResturantCode(code);
			iResturantRepository.save(resturant); 
			logger.info("resturant data inserted");
			return new EntitlementUtils().createResponseEntity(201, "created"); 
		
		
	}

	public ResponseEntity<StandardReleaseResponse> deleteResturantBasedOnId(String resturantId) {
		
		Resturant resturant=iResturantRepository.findById(resturantId);
		String resturantName=resturant.getResturantName();
		menuServiceMethods.deleteMenuBasedOnResturantName(resturantName);
		iResturantRepository.deleteResturantOnId(resturantId);
		Address address=resturant.getResturantAddress();
		iResturantRepository.deleteAddressOfResturantId(address.getAddressId());
		return new EntitlementUtils().createResponseEntity(201, "deleted");
	}

	public ResponseEntity<StandardReleaseResponse> deleteResturantBasedOnResturantName(String resturantName) {
		
		
		Resturant resturant=iResturantRepository.getResturantBasedOnresturantName(resturantName);
		menuServiceMethods.deleteMenuBasedOnResturantName(resturantName);
		String resturantId=resturant.getResturantId();
		iResturantRepository.deleteResturantOnId(resturantId);
		Address address=resturant.getResturantAddress();
		iResturantRepository.deleteAddressOfResturantId(address.getAddressId());
		return new EntitlementUtils().createResponseEntity(201, "deleted");
	}


}
