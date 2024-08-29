package py.edu.ucsa.contabilidad.microservice.core.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import py.edu.ucsa.contabilidad.microservice.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.CuentaContableDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.CuentaContable;

@Repository("cuentaContableDao")
public class CuentaContableDaoImpl extends AbstractDao<Long, CuentaContable> implements CuentaContableDao {
	private static final Logger logger = LoggerFactory.getLogger(CuentaContable.class);
	@Override
	public List<CuentaContable> getCuentasByNivel(Integer nivel) {
		TypedQuery<CuentaContable> q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentasByNivel", CuentaContable.class);
		q.setParameter("nivel", nivel);
		List<CuentaContable> resultado = q.getResultList();
		return resultado;
	}

	@Override
	public List<CuentaContable> getCuentasAsentables() {
		TypedQuery<CuentaContable> q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentasAsentables", CuentaContable.class);
		List<CuentaContable> resultado = q.getResultList();
		return resultado;
	}

	@Override
	public CuentaContable getCuentaByNroCuenta(String nroCuenta) {
		

		try {
			Query q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentaByNroCuenta");
			q.setParameter("nroCuenta", nroCuenta);
			//CuentaContable resultado = (CuentaContable) q.getSingleResult();
            return (CuentaContable) q.getSingleResult();
        } catch (NoResultException e) {
            logger.info("No se encontró una cuenta contable con el número: {}", nroCuenta);
            return null; // O lanzar una excepción personalizada
        } catch (NonUniqueResultException e) {
            logger.error("Se encontraron múltiples cuentas contables con el número: {}", nroCuenta);
            throw new NonUniqueResultException("Múltiples resultados para el número de cuenta");
        }
	}

	@Override
	public Optional<CuentaContable> getCuentaByCodigo(String codigo) {
		Query q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentaByCodigo");
		q.setParameter("codigo", codigo);
		 try {
		CuentaContable resultado = (CuentaContable) q.getSingleResult();
		 return Optional.of(resultado);
		 } catch (NoResultException e) {
		        return Optional.empty();
		    }
	}

	@Override
	public List<CuentaContable> getCuentasByTipo(String tipoCuenta) {
		TypedQuery<CuentaContable> q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentasByTipo", CuentaContable.class);
		//Query q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentasByTipo");
		q.setParameter("tipoCuenta", tipoCuenta);
		List<CuentaContable> resultado = q.getResultList();
		return resultado;
	}

	@Override
	public List<CuentaContable> getCuentasHijas(Long id) {
		TypedQuery<CuentaContable> q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentasHijas", CuentaContable.class);
		//Query q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentasHijas");
		q.setParameter("id", id);
		List<CuentaContable> resultado = q.getResultList();
		return resultado;
	}

}
