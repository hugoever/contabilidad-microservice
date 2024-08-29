package py.edu.ucsa.contabilidad.microservice.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.microservice.core.entities.Cliente;
import py.edu.ucsa.contabilidad.microservice.core.services.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {
	private static final Logger logger = LoggerFactory.getLogger(AsientoDet.class);

	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(clienteService.listar());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		return ResponseEntity.ok(clienteService.getById(id));
	}
	
	@GetMapping("/pordocumento/{nroDocumento}")
	public ResponseEntity<?> getByDocumento(@PathVariable("nroDocumento") String nroDocumento){
		return ResponseEntity.ok(clienteService.getByNroDocumento(nroDocumento));
	}
	
	@GetMapping("/pornombreyapellido/{nombre}/{apellido}")
	public ResponseEntity<?> getByNombreYApellido(@PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido){
		return ResponseEntity.ok(clienteService.getByNombreYApellido(nombre,apellido));
	}
	
	@PostMapping
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente,
			UriComponentsBuilder uriComponentBuilder) {
		logger.info("Creando el cliente cobro  : {}", cliente.getId());
		Cliente insertado = clienteService.persistir(cliente);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentBuilder.path("/clientes/{id}").buildAndExpand(insertado.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		logger.info("Actualizando cliente con el id: {}", id);
		Cliente clienteBD = clienteService.getById(id);	
		clienteService.actualizar(cliente);
		return new ResponseEntity<Cliente>(clienteBD, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable("id") Long id) {
		logger.info("Eliminando cliente con el id: {}", id);
		Cliente clienteBD = clienteService.getById(id);
		Long clienteEliminado = clienteBD.getId();
		clienteService.eliminar(clienteBD);
		return new ResponseEntity<String>(
				"Cliente con id: " + clienteEliminado + " eliminado satisfactoriamente.",
				HttpStatus.OK);
	}
	
}
