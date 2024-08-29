package py.edu.ucsa.contabilidad.microservice.web.validators.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import py.edu.ucsa.contabilidad.microservice.core.entities.CuentaContable;
import py.edu.ucsa.contabilidad.microservice.web.dto.ErrorDto;
import py.edu.ucsa.contabilidad.microservice.web.validators.Validador;

public class CuentaContableValidador implements Validador<CuentaContable> {

	@Override
	public List<ErrorDto> validar(CuentaContable obj){
		List<ErrorDto> errores = new ArrayList<>();
		
		if(Objects.isNull(obj.getAsentable())) {
			errores.add(new ErrorDto("Se debe especificar si la cuenta es o no asentable"));
			}
		if(Objects.isNull(obj.getCodigo())) {
			errores.add(new ErrorDto("Se debe especificar el código de la cuenta contable"));
		}
		if(Objects.isNull(obj.getDescripcion())) {
			errores.add(new ErrorDto("Se debe especificar la Descripción de la cuenta contable"));
		}
		if(Objects.isNull(obj.getNivel())) {
			errores.add(new ErrorDto("El nivel de la cuenta contable es requerida"));
		}
		if(Objects.isNull(obj.getNroCuenta())) {
			errores.add(new ErrorDto("El número de cuenta contable es requerida"));
		}
		if(Objects.isNull(obj.getTipoCuenta())) {
			errores.add(new ErrorDto("El tipo de cuenta contable es requerida"));
		}
		
		if(errores.isEmpty()) {
			obj.setDescripcion(obj.getDescripcion().toUpperCase());
			obj.setTipoCuenta(obj.getTipoCuenta().toUpperCase());
		}
		return errores;
	}
}
