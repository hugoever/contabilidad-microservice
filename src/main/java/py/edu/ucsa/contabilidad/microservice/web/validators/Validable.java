package py.edu.ucsa.contabilidad.microservice.web.validators;

import java.util.List;

import py.edu.ucsa.contabilidad.microservice.web.dto.ErrorDto;

/**
 * Interface que será implementada por aquellas clases que 
 * necesitan ser validadas
 * @param <T> tipo del validador
 */
public interface Validable<T> {
	/**
	 * Método que se encargará de rrecorrer los validadores
	 * agregados al objeto Validable y ejecutará el método
	 * validar de cada uno de dichos validadores
	 * @return Lista con errores generados por la validación
	 */
	List<ErrorDto> validar();
	
	/**
	 * Método que se encargará de agregar validadores
	 * a la lista de validadores que debe existir
	 * en el objeto validable
	 * @param v Validador a ser agregado
	 */
	void agregarValidador(Validador<T> v);

}
