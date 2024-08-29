package py.edu.ucsa.contabilidad.microservice.core.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.microservice.core.dao.CuentaContableDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.CuentaContable;
import py.edu.ucsa.contabilidad.microservice.core.services.CuentaContableService;

@Service("cuentaContableService")
@Transactional
public class CuentaContableServiceImpl implements CuentaContableService {

	@Autowired
	private CuentaContableDao cuentaContableDao;

	@Override
	public List<CuentaContable> listar() {
		return cuentaContableDao.listar();
	}

	@Override
	public CuentaContable getById(Long id) {
		return cuentaContableDao.getById(id);
	}
//	@Override
//	public CuentaContable getById(Integer id) {
//	    return cuentaContableDao.getById(id);
//	}

	@Override
	public CuentaContable persistir(CuentaContable entity) {
		return cuentaContableDao.persistir(entity);
	}

	@Override
	public CuentaContable actualizar(CuentaContable entity) {
		return cuentaContableDao.persistir(entity);
	}

	@Override
	public void eliminar(CuentaContable entity) {
		cuentaContableDao.eliminar(entity);

	}

	@Override
	public List<CuentaContable> getCuentasByNivel(Integer nivel) {
		return cuentaContableDao.getCuentasByNivel(nivel);
	}

	@Override
	public List<CuentaContable> getCuentasAsentables() {
		return cuentaContableDao.getCuentasAsentables();
	}

	@Override
	public CuentaContable getCuentaByNroCuenta(String nroCuenta) {
		return cuentaContableDao.getCuentaByNroCuenta(nroCuenta);
	}

	@Override
	public boolean isExisteCuentaContable(String nroCuenta) {
		return Objects.nonNull(getCuentaByNroCuenta(nroCuenta));
	}

	@Override
	public Optional<CuentaContable> getCuentaByCodigo(String codigo) {
		return cuentaContableDao.getCuentaByCodigo(codigo);
	}

	@Override
	public List<CuentaContable> getCuentasByTipo(String tipoCuenta) {
		return cuentaContableDao.getCuentasByTipo(tipoCuenta);
	}

	@Override
	public List<CuentaContable> getCuentasHijas(Long id) {
		return cuentaContableDao.getCuentasHijas(id);
	}

}
