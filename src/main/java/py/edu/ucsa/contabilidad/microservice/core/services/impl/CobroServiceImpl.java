package py.edu.ucsa.contabilidad.microservice.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Priority;
import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.microservice.core.dao.CobroDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.CobroDetConceptoDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.CobroDetMedioPagoDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.Cobro;
import py.edu.ucsa.contabilidad.microservice.core.entities.CobroDetConcepto;
import py.edu.ucsa.contabilidad.microservice.core.entities.CobroDetMedioPago;
import py.edu.ucsa.contabilidad.microservice.core.services.CobroService;

@Service("cobroService")
@Transactional
@Priority(0)
public class CobroServiceImpl implements CobroService {
	
	@Autowired
	private CobroDao cobroDao;
	@Autowired
	private CobroDetConceptoDao cobroDetConceptoDao;
	
	@Autowired
	private CobroDetMedioPagoDao cobroDetMedioPagoDao;

	@Override
	public List<Cobro> listar() {
		return cobroDao.listar();
	}

	@Override
	public Cobro getById(Long id) {
		return cobroDao.getById(id);
	}

	@Override
	public Cobro persistir(Cobro cobro)  {
		Cobro cobroInsertado = cobroDao.persistir(cobro);
		Long idCobroInsertado = cobroInsertado.getId();
		

	        return null;
	}

	@Override
	public Cobro actualizar(Cobro entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Cobro entity) {
		// TODO Auto-generated method stub

	}


}
