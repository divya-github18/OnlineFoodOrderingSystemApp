package com.example.demo.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.models.Category;
import com.example.demo.models.Clasification;
import com.example.demo.models.Items;
import com.example.demo.models.MenuList;
import com.example.demo.repository.IMenuRepository;


@Component
public class EntitlementUtils {
	
	@Autowired
	IMenuRepository iMenuRepository;
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceMethods.class);
	public ResponseEntity<StandardReleaseResponse> createResponseEntity(int statusCode, String responseMessage)
	{
		StandardReleaseResponse standardreleaseresponse = new StandardReleaseResponse(statusCode, responseMessage);
		ResponseEntity<StandardReleaseResponse> responseEntity = new ResponseEntity<StandardReleaseResponse>(standardreleaseresponse,HttpStatus.valueOf(statusCode));
		return responseEntity; 
	}



		
	
}
