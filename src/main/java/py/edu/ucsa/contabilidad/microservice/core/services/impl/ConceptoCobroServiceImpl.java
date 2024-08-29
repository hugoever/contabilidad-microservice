package py.edu.ucsa.contabilidad.microservice.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.microservice.core.dao.ConceptoCobroDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.ConceptoCobro;
import py.edu.ucsa.contabilidad.microservice.core.services.ConceptoCobroService;

@Service("conceptoCobroService")
@Transactional
public class ConceptoCobroServiceImpl implements ConceptoCobroService {
	
	@Autowired
	private ConceptoCobroDao conceptoCobroDao;

	@Override
	public List<ConceptoCobro> listar() {
		return conceptoCobroDao.listar();
	}

	@Override
	public ConceptoCobro getById(Long id) {
		return conceptoCobroDao.getById(id);
	}

	@Override
	public ConceptoCobro persistir(ConceptoCobro entity) {
		return conceptoCobroDao.persistir(entity);
	}

	@Override
	public ConceptoCobro actualizar(ConceptoCobro entity) {
		return conceptoCobroDao.actualizar(entity);
	}

	@Override
	public void eliminar(ConceptoCobro entity) {
		conceptoCobroDao.eliminar(entity);

	}

	@Override
	public ConceptoCobro getByCodigoYCuentaContable(String codigo, Long idCuenta) {
		return conceptoCobroDao.getByCodigoYCuentaContable(codigo, idCuenta);
	}

}
