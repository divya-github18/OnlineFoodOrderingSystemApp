package com.example.demo.commonservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.models.MenuList;
import com.example.demo.models.OrderStatus;
import com.example.demo.models.Resturant;
import com.example.demo.repository.IResturantRepository;

/**
 * @Component is used to explicitly declare a single bean
 * @author dboyapalli Common validate service is used to validate the data
 */
@Component
public class CommonValidateService {

	@Autowired
	IResturantRepository iResturantRepository;
	private static final Logger logger = LoggerFactory.getLogger(CommonValidateService.class);

	/**
	 * Validate restaurant method is used to valid resturant data
	 * @param resturant
	 * @return ExceptionList
	 * @throws Exception
	 */
	public List<String> validate(Resturant resturant) {

		List<String> exceptionList = new ArrayList<>();
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.usingContext().getValidator();
		Set<ConstraintViolation<Resturant>> violations = validator.validate(resturant);
		String resturantName = resturant.getResturantName();
		if (violations.isEmpty()) {
			logger.info("violations empty");
			if (iResturantRepository.findResturantName(resturantName)==null) {
				logger.info("inserting unique resturant name"); 
			} else {
				exceptionList.add("resturant name must be unique");
			}

		} else {
			logger.info("violations are present");
			Iterator<ConstraintViolation<Resturant>> it = violations.iterator();
			while (it.hasNext()) {
				exceptionList.add(it.next().getMessage());
			}
		}
		return exceptionList;

	}

	/**
	 * ValidateMenuMethod
	 * @param menu
	 * @return ExceptionList
	 * @throws Exception
	 */
	public List<String> validateMenu(MenuList menu) {
		List<String> exceptionList = new ArrayList<>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<MenuList>> violations = validator.validate(menu);
		if (violations.isEmpty()) {

			logger.info("violations empty ");

		} else {

			logger.info("violations are  present ");
			Iterator<ConstraintViolation<MenuList>> it = violations.iterator();
			while (it.hasNext()) {
				exceptionList.add(it.next().getMessage());
			}

		}
		return exceptionList;

	}

	/**
	 * ValidateOrderStatus
	 * @param orderStatus
	 * @return exception list
	 */

	public List<String> validateOrderStatus(OrderStatus orderStatus) {
		List<String> exceptionList = new ArrayList<>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<OrderStatus>> violations = validator.validate(orderStatus);
		if (violations.isEmpty()) {

			logger.info("violations empty ");

		} else {

			logger.info("violations are  present ");
			Iterator<ConstraintViolation<OrderStatus>> it = violations.iterator();
			while (it.hasNext()) {
				exceptionList.add(it.next().getMessage());
			}

		}
		return exceptionList;
	}

}
