package py.edu.ucsa.contabilidad.microservice.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.microservice.core.dao.MedioPagoDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.MedioPago;
import py.edu.ucsa.contabilidad.microservice.core.services.MedioPagoService;

@Service("medioPagoService")
@Transactional
public class MedioPagoServiceImpl implements MedioPagoService {

	@Autowired
	private MedioPagoDao medioPagoDao;

	@Override
	public List<MedioPago> listar() {
		return medioPagoDao.listar();
	}

	@Override
	public MedioPago getById(Long id) {
		return medioPagoDao.getById(id);
	}

	@Override
	public MedioPago persistir(MedioPago entity) {
		return medioPagoDao.persistir(entity);
	}
//	public MedioPago persistir(MedioPago medioPago) {
//        if (medioPagoDao.existsById(medioPago.getId())) {
//            throw new CustomDuplicateKeyException("El registro con el ID " + medioPago.getId() + " ya existe.");
//        }
//        return medioPagoDao.persistir(medioPago);
//    }

	@Override
	public MedioPago actualizar(MedioPago entity) {
		return medioPagoDao.actualizar(entity);
	}

	@Override
	public void eliminar(MedioPago entity) {
		medioPagoDao.eliminar(entity);

	}

	@Override
	public MedioPago getByCodigoYCuentaContable(String codigo, Long idCuenta) {
		return medioPagoDao.getByCodigoYCuentaContable(codigo, idCuenta);
	}

}
