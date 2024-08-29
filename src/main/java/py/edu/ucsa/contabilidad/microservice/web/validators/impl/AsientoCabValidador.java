package py.edu.ucsa.contabilidad.microservice.web.validators.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.microservice.web.dto.ErrorDto;
import py.edu.ucsa.contabilidad.microservice.web.validators.Validador;

public class AsientoCabValidador implements Validador<AsientoCab> {
	@Override
	public List<ErrorDto> validar(AsientoCab obj){
		List<ErrorDto> errores = new ArrayList<>();
		if(Objects.isNull(obj.getDescripcion())) {
			errores.add(new ErrorDto("Debe ingresar la descripción del asiento."));
		}
		if(Objects.isNull(obj.getEstado())) {
			errores.add(new ErrorDto("Debe especificar el estado del asiento."));
		}
		if(Objects.isNull(obj.getNroAsiento())) {
			errores.add(new ErrorDto("Debe ingresar el número de asiento."));
		}
		if(Objects.isNull(obj.getFechaAsiento())) {
			errores.add(new ErrorDto("Debe ingresar la fecha del asiento."));
		}
		if(Objects.isNull(obj.getFechaRegistro())) {
			errores.add(new ErrorDto("Debe ingresar la fecha de registro del asiento."));
		}
		if(errores.isEmpty()) {
			obj.setDescripcion(obj.getDescripcion().toUpperCase());
			obj.setEstado(obj.getEstado().toUpperCase());
		}
		return errores;
		
	}

}
