package py.edu.ucsa.contabilidad.microservice.web.controllers;

import java.util.List;
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

import py.edu.ucsa.contabilidad.microservice.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.microservice.core.services.AsientoCabService;
import py.edu.ucsa.contabilidad.microservice.web.dto.AsientoCabDto;
import py.edu.ucsa.contabilidad.microservice.web.dto.ErrorDto;
import py.edu.ucsa.contabilidad.microservice.web.validators.impl.AsientoCabValidador;

@RestController
@RequestMapping("asientos-cabeceras")
public class AsientoCabController {

	private static final Logger logger = LoggerFactory.getLogger(AsientoCab.class);
	
	@Autowired
	@Qualifier("asientoCabService")
	private AsientoCabService asientoCabService;
	
	@GetMapping("/listar-con-detalles")
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(asientoCabService.listar());
	}
	
	@GetMapping()
	public List<AsientoCabDto> getAll(){
		return asientoCabService.getAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id){
		logger.info("Buscando Cuenta con el id: {}", id);
		AsientoCab cuentaBD = asientoCabService.getById(id);
		if (Objects.isNull(cuentaBD)) {
			logger.error("Actualización fallida, no existe la cabecera del asiento: {}", id);
			return new ResponseEntity<ErrorDto>(new ErrorDto("Búsqueda fallida. No existe la cabecera del asiento con el id " + id),
					HttpStatus.NOT_FOUND);
		}
		AsientoCab asientoCab;
		asientoCab = asientoCabService.getById(id);
		return ResponseEntity.ok(asientoCab);	
	}
	
	@PostMapping
	public ResponseEntity<?> crearAsientoCab(@RequestBody AsientoCab asientoCab, UriComponentsBuilder uriBuilder){
		logger.info("Creando la Cabecera de Asiento : {}", asientoCab);
		AsientoCabValidador v1 = new AsientoCabValidador();
		asientoCab.agregarValidador(v1);		
		List<ErrorDto> errores = asientoCab.validar();
		if(asientoCabService.isExisteAsientoCab(asientoCab.getNroAsiento())) {
			logger.error("Inserción fallida, ya existe un asiento con ese número: "+asientoCab.getNroAsiento());
			return new ResponseEntity<ErrorDto>(new ErrorDto ("Inserción fallida, ya existe un asiento con ese número: "+asientoCab.getNroAsiento()), HttpStatus.CONFLICT);
		}
	if(errores.isEmpty()) {
		AsientoCab insertado = asientoCabService.persistir(asientoCab);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/asientos-cabeceras/{id}").buildAndExpand(insertado.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}else {
		return new ResponseEntity<List<ErrorDto>>(errores, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizarAsientoCab(@PathVariable("id") Long id, @RequestBody AsientoCab asiento){
		logger.info("Actualizando el asiento con el id: {}", id);
		AsientoCab asientoBD = asientoCabService.getById(id);
		if(Objects.isNull(asientoBD)) {
			logger.error("Actualización fallida, no existe el asiento: {}", id);
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("Actualización fallida. No existe el asiento con el id " + id), HttpStatus.NOT_FOUND);
		}
		asientoCabService.actualizar(asiento);
		return new ResponseEntity<AsientoCab>(asiento,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarAsientoCab(@PathVariable("id") Long id){
		logger.info("Eliminando la cabecera del asiento con el id: {}", id);
		AsientoCab asientoBD = asientoCabService.getById(id);
		if(Objects.isNull(asientoBD)) {
			logger.error("Eliminación fallida, no existe la cabecera del asiento: {}", id);
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("Eliminación fallida, no existe la cabecera del asiento " + id), HttpStatus.NOT_FOUND);
		}
		String asientoEliminado = asientoBD.getDescripcion();
		asientoCabService.eliminar(asientoBD);
		return new ResponseEntity<ErrorDto>(new ErrorDto("Cabecera del Asiento "+ asientoEliminado + " eliminada satisfactoriamente."),HttpStatus.OK);
	}
	
	
}
