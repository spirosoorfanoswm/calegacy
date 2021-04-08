package eu.ark.creditark.services.creditarkservices.api;

import java.util.Date;

import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.exceptions.ExceptionResponse;

@RestControllerAdvice
@RestController
public class ExceptionCtrl extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		logger.error("handleAllExceptions", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "",
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(DatabaseConnectionException.class)
	public final ResponseEntity<Object> handleDatabaseConnectionException(
			DatabaseConnectionException ex,
			WebRequest request) {
		logger.error("handling DatabaseConnectionException", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), null!=ex.getMessage()?ex.getMessage():"",
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}



		
	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("handling MethodArgumentNotValid", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final  ResponseEntity<Object> handlAccessDeniedException(AccessDeniedException ex,
																WebRequest request) {
		logger.error("handling AccessDeniedException", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "You are not authorized: Access Denied",
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ScenarioException.class)
	public final  ResponseEntity<Object> handlScenarioException(ScenarioException ex,
																	WebRequest request) {
		logger.error("handling ScenarioException", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), null!=ex.getMessage()?ex.getMessage():"",
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ScenarioRuntimeException.class)
	public final  ResponseEntity<Object> handlScenarioException(ScenarioRuntimeException ex,
																WebRequest request) {
		logger.error("handling ScenarioRuntimeException", ex);
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), null!=ex.getMessage()?ex.getMessage():"",
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	//ScenarioRuntimeException

	
}
