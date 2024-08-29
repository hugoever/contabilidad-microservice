package py.edu.ucsa.contabilidad.microservice.core.dao;

import py.edu.ucsa.contabilidad.microservice.core.entities.MedioPago;

public interface MedioPagoDao extends GenericDao<Long, MedioPago> {
	public MedioPago getByCodigoYCuentaContable(String codigo,Long idCuenta);
}
