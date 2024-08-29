package py.edu.ucsa.contabilidad.microservice.core.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "cobros")
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Cobros.getByCliente", query = "SELECT c FROM Cobro c WHERE c.cliente.id = :idCliente")
public class Cobro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_cobro")
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fechaCobro;

	@Column(name = "monto_total")
	private Double montoTotal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
//PARA INSERTAR DETALLES	
//	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CobroDetConcepto> detallesConceptos = new ArrayList<>();
//
//    @OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CobroDetMedioPago> detallesMedioPago = new ArrayList<>();

}
