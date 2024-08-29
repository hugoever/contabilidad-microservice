package py.edu.ucsa.contabilidad.microservice.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import py.edu.ucsa.contabilidad.common.exceptions.CustomMultipleResultsException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomNotFoundException;
import py.edu.ucsa.contabilidad.microservice.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.ClienteDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.Cliente;

@Repository("clienteDao")
public class ClienteDaoImpl extends AbstractDao<Long, Cliente> implements ClienteDao {

	@Override
	public Cliente getByNroDocumento(String nroDocumento) {
		
		try {
			Query q = this.getEntityManager().createNamedQuery("Cliente.getByNroDocumento");
		
		q.setParameter("nroDocumento",nroDocumento);
		return (Cliente) q.getSingleResult();	
		} catch (NoResultException e) {
			throw new CustomNotFoundException("No se encontró el cliente con el nro. de documento: "+nroDocumento);
		} catch (NonUniqueResultException e) {
			throw new CustomMultipleResultsException("Se encontraron varios clientes con el documento: "+nroDocumento);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> getByNombreYApellido(String nombre, String apellido) {
		try {
			Query q = this.getEntityManager().createNamedQuery("Cliente.getByNombreYApellido");
			q.setParameter("nombre",nombre);
			q.setParameter("apellido",apellido);
			return (List<Cliente>) q.getResultList();			
		} catch (NoResultException e) {
			throw new CustomNotFoundException("No se encontró el cliente con el nombre: "+nombre+" y el apellido: "+apellido);
		}
	}



	

}
