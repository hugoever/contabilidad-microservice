package py.edu.ucsa.contabilidad.microservice.core.services;

import java.util.List;

import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.microservice.web.dto.AsientoCabDto;

public interface AsientoCabService extends GenericService<Long, AsientoCab> {

	public List<AsientoCabDto> getAll();

	public boolean isExisteAsientoCab(String nroAsiento);
	
}
