package py.edu.ucsa.contabilidad.microservice.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.contabilidad.microservice.core.entities.Cobro;
import py.edu.ucsa.contabilidad.microservice.core.services.CobroService;

@RestController
@RequestMapping("/cobros")
public class CobroController {

    @Autowired
    @Qualifier("cobroService")
    private CobroService cobroService;

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
    	return ResponseEntity.ok(cobroService.getById(id));
    }
    
    @GetMapping
    public ResponseEntity<?> listar(){
    	return ResponseEntity.ok(cobroService.listar());
    }
    @PostMapping
    public ResponseEntity<Cobro> createCobro(@RequestBody Cobro cobro) {
        try {
            Cobro createdCobro = cobroService.persistir(cobro);
            return ResponseEntity.ok(createdCobro);
        } catch (Exception e) {
            // Handle exception and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }
}
