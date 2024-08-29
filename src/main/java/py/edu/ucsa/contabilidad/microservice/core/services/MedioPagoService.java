package py.edu.ucsa.contabilidad.microservice.core.services;

import py.edu.ucsa.contabilidad.microservice.core.entities.MedioPago;

public interface MedioPagoService extends GenericService<Long, MedioPago> {
	public MedioPago getByCodigoYCuentaContable(String codigo,Long idCuenta);
}
