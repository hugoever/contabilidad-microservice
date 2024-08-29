package py.edu.ucsa.contabilidad.microservice.web.dto;

import lombok.Data;

@Data
public class AsientoDetDto {
	private Long id;
    private Double montoDebe;
    private Double montoHaber;
    private Long asientoCabId; // only the ID of the AsientoCab, not the entire object
    private String asientoCabDescripcion;
    private Long cuentaContableId; // only the ID of the CuentaContable, not the entire object
    private String cuentaContableDescripcion;
}
