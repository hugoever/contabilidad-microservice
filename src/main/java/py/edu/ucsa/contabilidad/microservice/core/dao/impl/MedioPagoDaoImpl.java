package py.edu.ucsa.contabilidad.microservice.core.dao.impl;

import org.springframework.stereotype.Repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import py.edu.ucsa.contabilidad.common.exceptions.CustomMultipleResultsException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomNotFoundException;
import py.edu.ucsa.contabilidad.microservice.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.MedioPagoDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.MedioPago;

@Repository("medioPagoDao")
public class MedioPagoDaoImpl extends AbstractDao<Long, MedioPago> implements MedioPagoDao {

	@Override
	public MedioPago getByCodigoYCuentaContable(String codigo, Long idCuenta) {
		try {
			Query q = this.getEntityManager().createNamedQuery("MedioPago.getByCodigoYCuentaContable");
			q.setParameter("codigo",codigo);
			q.setParameter("idCuenta",idCuenta);
			return (MedioPago) q.getSingleResult();			
		} catch (NoResultException e) {
			throw new CustomNotFoundException("No se encontró el medio de pago con el código: "+codigo+" y el id de cuenta contable: "+idCuenta);
		} catch (NonUniqueResultException e) {
			throw new CustomMultipleResultsException("Se encontraron varios medios de pago con el código: "+codigo+" y el id de cuenta contable: "+idCuenta);
		}
	}

}
