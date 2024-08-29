package py.edu.ucsa.contabilidad.microservice.web.controllers;

import java.util.Objects;

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
import py.edu.ucsa.contabilidad.microservice.core.services.AsientoDetService;
import py.edu.ucsa.contabilidad.microservice.web.dto.AsientoDetDto;
import py.edu.ucsa.contabilidad.microservice.web.dto.ErrorDto;

@RestController
@RequestMapping("asientos-detalles")
public class AsientoDetController {
	private static final Logger logger = LoggerFactory.getLogger(AsientoDet.class);

	@Autowired
	@Qualifier("asientoDetService")
	private AsientoDetService asientoDetService;

	private AsientoDetDto asientoDetDto;

	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(asientoDetService.listar());
	}

	@GetMapping("{id}")
	public ResponseEntity<?> listarPorId(@PathVariable("id") Long id) {
		// return ResponseEntity.ok(cuentaContableService.getById(id),
		// FetchMode.SELECT);
		AsientoDetDto asientoDetDto;

		asientoDetDto = asientoDetService.listarPorId(id);
		if (asientoDetDto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(asientoDetDto);
	}

	@GetMapping("/porcabeceraycuenta/{idAsiento}/{idCuenta}")
	public ResponseEntity<?> getByCabeceraYCuentaContable(@PathVariable("idAsiento") Long idAsiento,
			@PathVariable("idCuenta") Long idCuenta) {
//		try {
//			return ResponseEntity.ok(asientoDetService.getByCabeceraYCuentaContable(idAsiento, idCuenta));
//		} catch (NoResultException e) {
//			return new ResponseEntity<>(new ErrorDto("No se encontró el detalle"), HttpStatus.NOT_FOUND);
//		}
//		 try {
//		        AsientoDet result = asientoDetService.getByCabeceraYCuentaContable(idAsiento, idCuenta);
//		        return ResponseEntity.ok(result);
//		    } catch (EmptyResultDataAccessException e) {
//		        logger.info("No hay resultados para la cabecera {} y la cuenta {}", idAsiento, idCuenta);
//		        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 with no content
//		        return new ResponseEntity<ErrorDto>(
//						new ErrorDto("No hay resultados para la cabecera "+ idAsiento+" y la cuenta "+ idCuenta),HttpStatus.NOT_FOUND);
//		    } 
//		catch (NonUniqueResultException e) {
//		        logger.error("Error: {}", e.getMessage());
//		        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//		        return new ResponseEntity<ErrorDto>(
//						new ErrorDto("Varios resultados para la cabecera "+ idAsiento+" y la cuenta "+ idCuenta),HttpStatus.CONFLICT);
//		    }
		AsientoDet result = asientoDetService.getByCabeceraYCuentaContable(idAsiento, idCuenta);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<?> crearAsientoDet(@RequestBody AsientoDet asientoDet, UriComponentsBuilder ucBuilder) {
		logger.info("Creando el detalle  : {}", asientoDet.getId());
		AsientoDet insertado = asientoDetService.persistir(asientoDet);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/asientos-detalles/{id}").buildAndExpand(insertado.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> actualizarAsientoDet(@PathVariable("id") Long id, @RequestBody AsientoDet asientoDet) {
		logger.info("Actualizando el detalle con el id: {}", id);
		AsientoDet asientoBD = asientoDetService.getById(id);
		if (Objects.isNull(asientoBD)) {
			logger.info("Actualización fallida, detalle con el id {} no existe", id);
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("Actualización fallida, no existe detalle con el id: {}" + id), HttpStatus.NOT_FOUND);
		}
		asientoDetService.persistir(asientoDet);
		asientoDetDto = asientoDetService.listarPorId(id);
		return new ResponseEntity<AsientoDetDto>(asientoDetDto, HttpStatus.CREATED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarAsientoDet(@PathVariable("id") Long id) {
		logger.info("Eliminando detalle del asiento con el id: {}", id);
		AsientoDet detalleBD = asientoDetService.getById(id);
		if (Objects.isNull(detalleBD)) {
			logger.error("Eliminación fallida, no existe el detalle del asiento: {}", id);
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("Eliminación fallida, no existe el detalle del asiento " + id), HttpStatus.NOT_FOUND);
		}
		Long detalleEliminado = detalleBD.getId();
		asientoDetService.eliminar(detalleBD);
		return new ResponseEntity<ErrorDto>(
				new ErrorDto("Cabecera del Asiento con id: " + detalleEliminado + " eliminada satisfactoriamente."),
				HttpStatus.OK);
	}
	
//HABILITAR PARA EMITIR MENSAJES EN FORMATO CONSOLA, NO JSON
//
//	@ExceptionHandler(CustomNotFoundException.class)
//	public ResponseEntity<String> handleNotFound(CustomNotFoundException ex) {
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//	}
//
//	@ExceptionHandler(CustomMultipleResultsException.class)
//	public ResponseEntity<String> handleMultipleResults(CustomMultipleResultsException ex) {
//		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
//	}

}
