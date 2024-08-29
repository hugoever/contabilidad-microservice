package py.edu.ucsa.contabilidad.microservice.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.common.exceptions.CustomMultipleResultsException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomNotFoundException;
import py.edu.ucsa.contabilidad.microservice.core.dao.AsientoDetDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.microservice.core.services.AsientoDetService;
import py.edu.ucsa.contabilidad.microservice.web.dto.AsientoDetDto;

@Service("asientoDetService")
@Transactional
public class AsientoDetServiceImpl implements AsientoDetService {

	@Autowired
	private AsientoDetDao asientoDetDao;

	@Override
	public List<AsientoDet> listar() {
		return asientoDetDao.listar();
	}

	@Override
	public AsientoDet getById(Long id) {

		return asientoDetDao.getById(id);

	}

	@Override
	public AsientoDet persistir(AsientoDet entity) {
		return asientoDetDao.persistir(entity);
	}

	@Override
	public AsientoDet actualizar(AsientoDet entity) {
		return asientoDetDao.persistir(entity);
	}

	@Override
	public void eliminar(AsientoDet entity) {
		asientoDetDao.eliminar(entity);

	}

	@Override
	public AsientoDetDto listarPorId(Long id) {

		AsientoDet asientoDet;

		asientoDet = asientoDetDao.getById(id);
		if (asientoDet == null) {

			throw new EntityNotFoundException("AsientoDet no encontrado con id " + id);

		}
		AsientoDetDto asientoDetDto = new AsientoDetDto();
		asientoDetDto.setId(asientoDet.getId());
		asientoDetDto.setMontoDebe(asientoDet.getMontoDebe());
		asientoDetDto.setMontoHaber(asientoDet.getMontoHaber());
		asientoDetDto.setAsientoCabId(asientoDet.getAsientoCab().getId());
		asientoDetDto.setAsientoCabDescripcion(asientoDet.getAsientoCab().getDescripcion());
		asientoDetDto.setCuentaContableId(asientoDet.getCuentaContable().getId());
		asientoDetDto.setCuentaContableDescripcion(asientoDet.getCuentaContable().getDescripcion());
		return asientoDetDto;

	}

	@Override
	public AsientoDet getByCabeceraYCuentaContable(Long idAsiento, Long idCuenta) {
		 try {
		        return asientoDetDao.getByCabeceraYCuentaContable(idAsiento, idCuenta);
		    } catch (CustomNotFoundException e) {
		        // Log or rethrow the exception, or handle it with specific business logic
		        throw e;
		    } catch (CustomMultipleResultsException e) {
		        // Log or rethrow the exception, or handle it with specific business logic
		        throw e;
		    }
	}

}
