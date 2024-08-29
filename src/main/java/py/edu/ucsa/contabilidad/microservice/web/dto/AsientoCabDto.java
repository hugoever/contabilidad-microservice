package py.edu.ucsa.contabilidad.microservice.web.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsientoCabDto {
    private Long id;
    private String descripcion;
    private String estado;
    private Timestamp fechaAsiento;
    private Timestamp fechaRegistro;
    private String nroAsiento;
}
