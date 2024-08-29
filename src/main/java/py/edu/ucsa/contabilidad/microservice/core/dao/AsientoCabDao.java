package py.edu.ucsa.contabilidad.microservice.core.dao;

import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoCab;

public interface AsientoCabDao extends GenericDao<Long, AsientoCab> {

	public AsientoCab getAsientoCabByNroAsiento(String nroAsiento);
	
}
