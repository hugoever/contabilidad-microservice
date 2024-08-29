package py.edu.ucsa.contabilidad.microservice.core.dao;

import java.util.List;
import java.util.Optional;

import py.edu.ucsa.contabilidad.microservice.core.entities.CuentaContable;

public interface CuentaContableDao extends GenericDao<Long, CuentaContable> {
	public List<CuentaContable> getCuentasByNivel(Integer nivel);
	public List<CuentaContable> getCuentasAsentables();
	public CuentaContable getCuentaByNroCuenta(String nroCuenta);
	public Optional<CuentaContable> getCuentaByCodigo(String codigo);
	public List<CuentaContable> getCuentasByTipo(String tipoCuenta);
	public List<CuentaContable> getCuentasHijas(Long id);
	

}
