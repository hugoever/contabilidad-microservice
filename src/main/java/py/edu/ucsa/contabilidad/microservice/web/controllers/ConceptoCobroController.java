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
import py.edu.ucsa.contabilidad.microservice.core.entities.ConceptoCobro;
import py.edu.ucsa.contabilidad.microservice.core.services.ConceptoCobroService;

@RestController
@RequestMapping("conceptos-cobros")
public class ConceptoCobroController {

	private static final Logger logger = LoggerFactory.getLogger(AsientoDet.class);

	@Autowired
	@Qualifier("conceptoCobroService")
	private ConceptoCobroService conceptoCobroService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(conceptoCobroService.listar());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(conceptoCobroService.getById(id));
	}
	
	@GetMapping("/porcodigoycuenta/{codigo}/{idCuenta}")
	public ResponseEntity<?> getByCodigoYCuentaContable(@PathVariable("codigo") String codigo,
			@PathVariable("idCuenta") Long idCuenta) {
		ConceptoCobro result = conceptoCobroService.getByCodigoYCuentaContable(codigo, idCuenta);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<?> crearConceptoCobro(@RequestBody ConceptoCobro conceptoCobro,
			UriComponentsBuilder uriComponentBuilder) {
		logger.info("Creando el concepto cobro  : {}", conceptoCobro.getId());
		ConceptoCobro insertado = conceptoCobroService.persistir(conceptoCobro);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentBuilder.path("/conceptos-cobros/{id}").buildAndExpand(insertado.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizarConceptoCobro(@PathVariable("id") Long id, @RequestBody ConceptoCobro conceptoCobro) {
		logger.info("Actualizando concepto cobro con el id: {}", id);
		ConceptoCobro conceptoCobroBD = conceptoCobroService.getById(id);	
		conceptoCobroService.actualizar(conceptoCobro);
		return new ResponseEntity<ConceptoCobro>(conceptoCobroBD, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarConceptoCobro(@PathVariable("id") Long id) {
		logger.info("Eliminando detalle del asiento con el id: {}", id);
		ConceptoCobro conceptoCobroBD = conceptoCobroService.getById(id);
		Long conceptoCobroEliminado = conceptoCobroBD.getId();
		conceptoCobroService.eliminar(conceptoCobroBD);
		return new ResponseEntity<String>(
				"Medio de Pago con id: " + conceptoCobroEliminado + " eliminado satisfactoriamente.",
				HttpStatus.OK);
	}

}
