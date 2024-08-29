package py.edu.ucsa.contabilidad.microservice.core.services;

import java.util.List;
import java.util.Optional;

import py.edu.ucsa.contabilidad.microservice.core.entities.CuentaContable;

public interface CuentaContableService extends GenericService<Long, CuentaContable> {
	public List<CuentaContable> getCuentasByNivel(Integer nivel);
	public List<CuentaContable> getCuentasAsentables();
	public CuentaContable getCuentaByNroCuenta(String nroCuenta);
	public boolean isExisteCuentaContable(String nroCuenta);
	public Optional<CuentaContable> getCuentaByCodigo(String codigo);
	public List<CuentaContable> getCuentasByTipo(String tipoCuenta);
	public List<CuentaContable> getCuentasHijas(Long id);
}

