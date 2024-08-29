package py.edu.ucsa.contabilidad.microservice.core.dao.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import py.edu.ucsa.contabilidad.common.exceptions.CustomMultipleResultsException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomNotFoundException;
import py.edu.ucsa.contabilidad.microservice.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.AsientoDetDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoDet;

@Repository("asientoDetDao")
public class AsientoDetDaoImpl extends AbstractDao<Long, AsientoDet> implements AsientoDetDao {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class CustomNoResultException extends RuntimeException {
	 
		private static final long serialVersionUID = 1L;

		public CustomNoResultException(String message) {
	        super(message);
	    }
	}
	@Override
	public AsientoDet getByCabeceraYCuentaContable(Long idAsiento, Long idCuenta) {
		try {
			Query q = this.getEntityManager().createNamedQuery("AsientoDet.getByCabeceraYCuentaContable");
			q.setParameter("idAsiento",idAsiento);
			q.setParameter("idCuenta", idCuenta);
			return (AsientoDet) q.getSingleResult();			
		 } 
		catch (NoResultException e) {
	            //logger.info("No se encontró un detalle con el id de asiento: {} y el id de cuenta: {}", idAsiento,idCuenta);
	            //return null; // O lanzar una excepción personalizada
	            throw new CustomNotFoundException("No se encontró un detalle con el id de asiento: "+ idAsiento+" y el id de cuenta: "+idCuenta);
	        } 
		catch (NonUniqueResultException e) {
	            //logger.error("Se encontraron múltiples detalles con la cabecera: {} y la cuenta {}", idAsiento,idCuenta);
	            throw new CustomMultipleResultsException("Más de un detalle con el id de asiento: "+ idAsiento+" y el id de cuenta: "+idCuenta);
	        	 
	        }
	}

}
