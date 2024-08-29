package py.edu.ucsa.contabilidad.microservice.core.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import py.edu.ucsa.contabilidad.microservice.web.dto.ErrorDto;
import py.edu.ucsa.contabilidad.microservice.web.validators.Validable;
import py.edu.ucsa.contabilidad.microservice.web.validators.Validador;

@Entity
@Table(name = "cuentas_contables")
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "CuentaContable.getCuentasByNivel", query = "SELECT cc FROM CuentaContable cc WHERE cc.nivel = :nivel")
@NamedQuery(name = "CuentaContable.getCuentasAsentables", query = "SELECT cc FROM CuentaContable cc WHERE cc.asentable = true")
@NamedQuery(name = "CuentaContable.getCuentaByNroCuenta", query = "SELECT cc FROM CuentaContable cc WHERE cc.nroCuenta = :nroCuenta")
@NamedQuery(name = "CuentaContable.getCuentaByCodigo", query = "SELECT cc FROM CuentaContable cc WHERE cc.codigo = :codigo")
@NamedQuery(name = "CuentaContable.getCuentasByTipo", query = "SELECT cc FROM CuentaContable cc WHERE cc.tipoCuenta = :tipoCuenta")
@NamedQuery(name = "CuentaContable.getCuentasHijas", query = "SELECT cc FROM CuentaContable cc WHERE cc.cuentaPadre.id = :id")
public class CuentaContable implements Serializable, Validable<CuentaContable> {

	private static final long serialVersionUID = -3169432760891001053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "asentable")
	private Boolean asentable;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "nivel")
	private Integer nivel;

	@Column(name = "numero_cuenta")
	private String nroCuenta;

	@Column(name = "tipo_cuenta", nullable = false)
	private String tipoCuenta; // A:ACTIVO, P:PASIVO, D:DEUDORA, H:ACREEDORA

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cuenta_padre")
	//@Transient //Se Usa Transient en caso de que no se considere cuentaPadre y sea null para evitar traer los datos anidados
	private CuentaContable cuentaPadre;

	
	@JsonIgnore
	@Transient
	private List<Validador<CuentaContable>> validadores;
	
	public List<Validador<CuentaContable>> getValidadores() {
		if(Objects.isNull(validadores)) {
			validadores = new ArrayList<>();
		}
		return validadores;
	}
	
//	@Override
//	public boolean equals(Object o) {
//	    if (this == o) return true;
//	    if (o == null || getClass() != o.getClass()) return false;
//	    CuentaContable that = (CuentaContable) o;
//	    return Objects.equals(id, that.id);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//		
	@Override
	public List<ErrorDto> validar() {
		List<ErrorDto> errores = new ArrayList<>();
		this.getValidadores().forEach(v -> errores.addAll(v.validar(this)));
		return errores;
	}

	@Override
	public void agregarValidador(Validador<CuentaContable> v) {
		this.getValidadores().add(v);
		
	}

}
