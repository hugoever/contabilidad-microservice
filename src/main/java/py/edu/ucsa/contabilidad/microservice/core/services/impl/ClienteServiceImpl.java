package py.edu.ucsa.contabilidad.microservice.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Priority;
import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.microservice.core.dao.ClienteDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.Cliente;
import py.edu.ucsa.contabilidad.microservice.core.services.ClienteService;

@Service("clienteService")
@Transactional
@Priority(0)
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	public List<Cliente> listar() {
		return clienteDao.listar();
	}

	@Override
	public Cliente getById(Long id) {
		return clienteDao.getById(id);
	}

	@Override
	public Cliente persistir(Cliente entity) {
		return clienteDao.persistir(entity);
	}

	@Override
	public Cliente actualizar(Cliente entity) {
		return clienteDao.actualizar(entity);
	}

	@Override
	public void eliminar(Cliente entity) {
		clienteDao.eliminar(entity);

	}

	@Override
	public Cliente getByNroDocumento(String nroDocumento) {
		return clienteDao.getByNroDocumento(nroDocumento);
	}

	@Override
	public List<Cliente> getByNombreYApellido(String nombre, String apellido) {
		return clienteDao.getByNombreYApellido(nombre, apellido);
	}

}
