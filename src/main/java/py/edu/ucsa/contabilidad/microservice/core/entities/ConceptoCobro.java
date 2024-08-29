package py.edu.ucsa.contabilidad.microservice.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conceptos_cobro")
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "ConceptoCobro.getByCodigoYCuentaContable", query = "SELECT c FROM ConceptoCobro c WHERE c.codigo = :codigo AND c.cuentaContable.id = :idCuenta")
public class ConceptoCobro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cuenta_contable", nullable = false)
	private CuentaContable cuentaContable;
}
