package py.edu.ucsa.contabilidad.microservice.core.services;

import py.edu.ucsa.contabilidad.microservice.core.entities.ConceptoCobro;

public interface ConceptoCobroService extends GenericService<Long, ConceptoCobro> {
	public ConceptoCobro getByCodigoYCuentaContable(String codigo,Long idCuenta);
}
