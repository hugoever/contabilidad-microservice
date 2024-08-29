package py.edu.ucsa.contabilidad.microservice.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import py.edu.ucsa.contabilidad.common.exceptions.CustomDuplicateKeyException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomMultipleResultsException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomNotFoundException;
import py.edu.ucsa.contabilidad.microservice.web.dto.ErrorMsgDto;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<ErrorMsgDto> handleCustomNotFoundException(CustomNotFoundException ex) {
		ErrorMsgDto error = new ErrorMsgDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(),"No existe registro");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomMultipleResultsException.class)
    public ResponseEntity<ErrorMsgDto> handleCustomMultipleResultsFoundException(CustomMultipleResultsException ex) {
		ErrorMsgDto error = new ErrorMsgDto(HttpStatus.CONFLICT.value(), ex.getMessage(),"Existen varios registros con los datos");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMsgDto> handleGeneralException(Exception ex) {
        ErrorMsgDto error = new ErrorMsgDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),"Error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	 @ExceptionHandler(CustomDuplicateKeyException.class)
	    public ResponseEntity<ErrorMsgDto> handleDuplicateKeyException(CustomDuplicateKeyException ex) {
	        ErrorMsgDto error = new ErrorMsgDto(HttpStatus.CONFLICT.value(), ex.getMessage(),"Duplicación de registros");
	        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	    }

}
