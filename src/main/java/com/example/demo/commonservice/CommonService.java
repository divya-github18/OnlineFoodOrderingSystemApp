package com.example.demo.commonservice;

import java.util.List;
import javax.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.exception.ValidationExceptions;
import com.example.demo.models.MenuList;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Resturant;
import com.example.demo.service.MenuService;
import com.example.demo.service.OrderStatusService;
import com.example.demo.service.ResturantService;
import com.example.demo.util.StandardReleaseResponse;

/**
 * @Component is used to explicitly declare a single bean
 * @author dboyapalli CommonSerivice class used to map incoming requests to
 *         respective service classes
 */
@Component
public class CommonService {

	@Autowired
	private MenuService menuService;
	@Autowired
	private CommonValidateService commonValidateService;
	@Autowired
	private ResturantService resturantService;
	@Autowired
	private OrderStatusService orderStatusService;
	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

	/**
	 * Process entities method is used to send the Resturant service class if no
	 * violations
	 * 
	 * @param resturant
	 * @return StandardReleaseResponse
	 * @throws Exception
	 */
	public ResponseEntity<StandardReleaseResponse> processEntities(Resturant resturant) {

		logger.info("Sending data to Common Validate Service class for validation");
		List<String> violationsList = commonValidateService.validate(resturant);
		logger.info("validation completed");
		if (violationsList.isEmpty()) {
			logger.info("validation sucessfull send the data to resturant service class");
			return resturantService.handleUserActions(resturant);

		} else {
			logger.info("validation failed");
			throw new ValidationExceptions(violationsList.toString()); 
			 
		}

	}

	/**
	 * Process entities method is used to send the Menu service class if no
	 * violations
	 * 
	 * @param menuList
	 * @return StandardReleaseResponse
	 * @throws Exception
	 */
	public ResponseEntity<StandardReleaseResponse> menuProcessEntities(MenuList menuList) {

		logger.info("Send to Common Validate Service class for validate");
		List<String> violationsList = commonValidateService.validateMenu(menuList);
		logger.info("validation completed ");
		if (violationsList.isEmpty()) {
			logger.info("validation sucessfull send to handler user actions ");
			return menuService.handleUserActions(menuList);

		} else {
			logger.info("validation failed ");
			throw new ValidationExceptions(violationsList.toString());

		}
	}
	/**
	 * OrderStatusProcessEntities method
	 * @param orderStatus
	 * @return
	 */
	public ResponseEntity<StandardReleaseResponse> orderStatusProcessEntities(OrderStatus orderStatus) {
		logger.info("Send to Common Validate Service class for validate");
		List<String> violationsList = commonValidateService.validateOrderStatus(orderStatus);
		logger.info("validation completed ");
		if (violationsList.isEmpty()) {
			logger.info("validation sucessfull send to handler user actions ");
			return orderStatusService.handleUserActions(orderStatus);

		} else {
			logger.info("validation failed ");
			throw new ValidationExceptions(violationsList.toString());

		}
	}

}
