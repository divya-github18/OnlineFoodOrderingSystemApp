package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.exception.ResturantCodeException;
import com.example.demo.models.Resturant;
import com.example.demo.repository.IResturantRepository;



/**
 * Resturant Code Generation class * @Component is used to explicitly declare a
 * single bean
 * 
 * @author dboyapalli
 *
 */
@Component
public class ResturantCodeGeneration {
	@Autowired
	IResturantRepository iResturantRepository;
	private static final Logger logger = LoggerFactory.getLogger(ResturantCodeGeneration.class);

	/**
	 * Restaurant code generation
	 * 
	 * @param resturant
	 * @return resturantCode
	 */
	public String resturantCodeGeneration(Resturant resturant) {

		String startCode = "FD";
		String resturantName = resturant.getResturantName().trim();
		String firstTwochar = "";
		String[] words = resturantName.split(" ");
		if (words.length >= 2) {
			firstTwochar = words[0].charAt(0) + "" + words[1].charAt(0); 
		} else {
			firstTwochar = resturantName.substring(0, 2).toUpperCase();
		}
		int empty = iResturantRepository.findempty();
		if (empty == 0) {

			logger.info("first resturant");
			String resturantCode = startCode + "001" + firstTwochar.toUpperCase();
			return resturantCode;
		} else {

			logger.info("not a first resturant");
			Resturant recentResturant = iResturantRepository.findRecentDate();
			String recentResturantCode = recentResturant.getResturantCode();
			int num = Character.getNumericValue(recentResturantCode.charAt(4));
			String incrementValue;
			if (num < 9) {
				incrementValue = "00" + String.valueOf(num + 1);
			} else if (num >= 9 && num <= 99) {
				incrementValue = "0" + String.valueOf(num + 1);
			} else if (num > 99 && num < 1000) {
				incrementValue = String.valueOf(num); 
			} else {
				throw new ResturantCodeException("Resturant Code generation Exception");
			}
			String resturantCode = startCode + incrementValue + firstTwochar.toUpperCase();
			logger.info("framing completed of resturent code");
			return resturantCode;
		}

	}

}
