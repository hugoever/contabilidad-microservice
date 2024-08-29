package py.edu.ucsa.contabilidad.microservice.core.dao;

import py.edu.ucsa.contabilidad.microservice.core.entities.ConceptoCobro;

public interface ConceptoCobroDao extends GenericDao<Long, ConceptoCobro> {
	public ConceptoCobro getByCodigoYCuentaContable(String codigo,Long idCuenta);
}
