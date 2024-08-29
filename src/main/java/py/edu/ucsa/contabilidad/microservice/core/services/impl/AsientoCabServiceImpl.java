package py.edu.ucsa.contabilidad.microservice.core.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.microservice.core.dao.AsientoCabDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.AsientoDetDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.CuentaContableDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.microservice.core.services.AsientoCabService;
import py.edu.ucsa.contabilidad.microservice.web.dto.AsientoCabDto;

@Service("asientoCabService")
public class AsientoCabServiceImpl implements AsientoCabService {

	@Autowired
	private AsientoCabDao asientoCabDao;

	@Autowired
	private AsientoDetDao asientoDetDao;

	@Autowired
	private CuentaContableDao cuentaContableDao;

	@Override
	public List<AsientoCab> listar() {
		return asientoCabDao.listar();
	}

	@Override
	public AsientoCab getById(Long id) {
		return asientoCabDao.getById(id);

	}

	@Override
	@Transactional(dontRollbackOn = Exception.class)
	public AsientoCab persistir(AsientoCab entity) {
//		AsientoCab cabeceraInsertada = asientoCabDao.persistir(entity);
//		//Long idCabeceraInsertada = cabeceraInsertada.getId();
//		for (AsientoDet detalle : entity.getAsientosDetalles()) {
//			detalle.setAsientoCab(cabeceraInsertada);
//			asientoDetDao.persistir(detalle);
//		}
//		 return cabeceraInsertada;
		
		for (AsientoDet detalle : entity.getAsientosDetalles()) {
			detalle.setAsientoCab(entity);
			
		}
//		
//		return asientoCabDao.persistir(entity);
	    return asientoCabDao.persistir(entity);  // Persistir la cabecera, los detalles se gestionan automáticamente

		
	}

	@Override
	public AsientoCab actualizar(AsientoCab entity) {
		return asientoCabDao.persistir(entity);
	}

	@Override
	public void eliminar(AsientoCab entity) {
		asientoCabDao.eliminar(entity);

	}

	@Override
	public List<AsientoCabDto> getAll() {
		List<AsientoCab> asientosCab = asientoCabDao.listar();
		List<AsientoCabDto> asientosCabDto = new ArrayList<>();
		for (AsientoCab asientoCab : asientosCab) {
			AsientoCabDto asientoCabDto = new AsientoCabDto(asientoCab.getId(), asientoCab.getDescripcion(),
					asientoCab.getEstado(), asientoCab.getFechaAsiento(), asientoCab.getFechaRegistro(),
					asientoCab.getNroAsiento());
			asientosCabDto.add(asientoCabDto);
		}
		return asientosCabDto;
	}

	@Override
	public boolean isExisteAsientoCab(String nroAsiento) {
		return Objects.nonNull(getAsientoCabByNroAsiento(nroAsiento));

	}

	private Object getAsientoCabByNroAsiento(String nroAsiento) {
		return asientoCabDao.getAsientoCabByNroAsiento(nroAsiento);
	}

}
