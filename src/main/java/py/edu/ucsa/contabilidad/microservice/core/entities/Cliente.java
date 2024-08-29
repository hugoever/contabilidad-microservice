package py.edu.ucsa.contabilidad.microservice.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Cliente.getByNroDocumento", query = "SELECT c FROM Cliente c WHERE c.nroDocumento = :nroDocumento")
@NamedQuery(name = "Cliente.getByNombreYApellido", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre AND c.apellido = :apellido")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "apellido", length = 255)
	private String apellido;

	@Column(name = "nombre", length = 255)
	private String nombre;

	@Column(name = "nro_documento", length = 255)
	private String nroDocumento;

}
