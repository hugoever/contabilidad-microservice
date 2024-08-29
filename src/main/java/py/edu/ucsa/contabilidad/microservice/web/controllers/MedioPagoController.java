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
import py.edu.ucsa.contabilidad.microservice.core.entities.MedioPago;
import py.edu.ucsa.contabilidad.microservice.core.services.MedioPagoService;

@RestController
@RequestMapping("medios-pagos")
public class MedioPagoController {
	private static final Logger logger = LoggerFactory.getLogger(AsientoDet.class);

	@Autowired
	@Qualifier("medioPagoService")
	private MedioPagoService medioPagoService;

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		logger.info("Buscando Medio de Pago con el id: {}", id);
//		MedioPago cuentaBD = medioPagoService.getById(id);
//		if (Objects.isNull(cuentaBD)) {
//			logger.error("No existe la cabecera del asiento: {}", id);
//			return new ResponseEntity<ErrorDto>(new ErrorDto("Búsqueda fallida. No existe el método de pago con el id " + id),
//					HttpStatus.NOT_FOUND);
//		}
		MedioPago medioPago;
		medioPago = medioPagoService.getById(id);
		return ResponseEntity.ok(medioPago);
	}

	@GetMapping("/porcodigoycuenta/{codigo}/{idCuenta}")
	public ResponseEntity<?> getByCodigoYCuentaContable(@PathVariable("codigo") String codigo,
			@PathVariable("idCuenta") Long idCuenta) {
		MedioPago result = medioPagoService.getByCodigoYCuentaContable(codigo, idCuenta);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(medioPagoService.listar());
	}

	@PostMapping
	public ResponseEntity<?> crearMedioPago(@RequestBody MedioPago medioPago,
			UriComponentsBuilder uriComponentBuilder) {
		logger.info("Creando el medio de pago  : {}", medioPago.getId());
		MedioPago insertado = medioPagoService.persistir(medioPago);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentBuilder.path("/medios-pagos/{id}").buildAndExpand(insertado.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizarMedioPago(@PathVariable("id") Long id, @RequestBody MedioPago medioPago) {
		logger.info("Actualizando medio pago con el id: {}", id);
		MedioPago medioPagoBD = medioPagoService.getById(id);	
		medioPagoService.actualizar(medioPago);
		return new ResponseEntity<MedioPago>(medioPagoBD, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarMedioPago(@PathVariable("id") Long id) {
		logger.info("Eliminando medio de pago con el id: {}", id);
		MedioPago medioPagoBD = medioPagoService.getById(id);
		Long detalleEliminado = medioPagoBD.getId();
		medioPagoService.eliminar(medioPagoBD);
		return new ResponseEntity<String>(
				"Medio de Pago con id: " + detalleEliminado + " eliminado satisfactoriamente.",
				HttpStatus.OK);
	}
}
