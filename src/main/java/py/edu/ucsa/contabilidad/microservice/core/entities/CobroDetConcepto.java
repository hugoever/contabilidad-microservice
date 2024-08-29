package py.edu.ucsa.contabilidad.microservice.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cobros_det_concepto")
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CobroDetConcepto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "monto")
	private Double monto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_concepto", nullable = false)
	private ConceptoCobro conceptoCobro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cobro", nullable = false)
	private Cobro cobro;
}
