package  com.example.demo.exception;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.util.EntitlementUtils;
import com.example.demo.util.StandardReleaseResponse;

//ControllerAdvice allows to handle exceptions across the whole application in one global handling component
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * ValidationException
	 * @param exception
	 * @return response entity
	 */
	@ExceptionHandler(ValidationExceptions.class)
	public ResponseEntity<StandardReleaseResponse> validationExceptions(ValidationExceptions exception) {
		 
		return new EntitlementUtils().createResponseEntity(401, exception.getMessage());  
	}
	/**
	 * ResturantCodeException
	 * @param exception 
	 * @return response entity
	 */
	@ExceptionHandler(ResturantCodeException.class)
	public ResponseEntity<StandardReleaseResponse> resturantCodeException(ResturantCodeException exception) {
		return new EntitlementUtils().createResponseEntity(501, exception.getMessage());
	} 
	/**
	 * Exception
	 * @param exception
	 * @return response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardReleaseResponse> exception(Exception exception) {
		return new EntitlementUtils().createResponseEntity(501, exception.getMessage());
	}
}
