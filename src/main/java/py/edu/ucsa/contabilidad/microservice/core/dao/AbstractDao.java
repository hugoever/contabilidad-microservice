package py.edu.ucsa.contabilidad.microservice.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import py.edu.ucsa.contabilidad.common.exceptions.CustomDuplicateKeyException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomMultipleResultsException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomNotFoundException;

public abstract class AbstractDao<PK extends Serializable,T> implements GenericDao<PK, T>{
private final Class<T> persistentClass;
	
	protected Logger logger = null;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		Type genericSuperClass = getClass().getGenericSuperclass();
		ParameterizedType parametrizedType = null;
		while (parametrizedType == null && genericSuperClass != null) {
			if ((genericSuperClass instanceof ParameterizedType)) {
				parametrizedType = (ParameterizedType) genericSuperClass;
			} else {
				genericSuperClass = ((Class<?>)genericSuperClass).getGenericSuperclass();
			}
		}
		if (parametrizedType != null) {
			persistentClass = (Class<T>) parametrizedType.getActualTypeArguments()[1];
			this.logger = LoggerFactory.getLogger(persistentClass);
		} else {
			persistentClass = null;
		}
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
//	public T getById(PK id) {
//		
//		return (T)this.entityManager.find(persistentClass, id);
//		
//	}
	public T getById(PK id) {
	    try {
	        T entity = this.entityManager.find(persistentClass, id);
	        if (entity == null) {
	            throw new CustomNotFoundException("Registro no encontrado para el ID: " + id);
	        }
	        return entity;
	    } catch (NoResultException e) {
	        throw new CustomNotFoundException("Registro no encontrado para el ID: " + id, e);
	    } catch (NonUniqueResultException e) {
	        throw new CustomMultipleResultsException("Varios Registros encontrados para el ID: " + id, e);
	    }
	}
	
	// Método para verificar si existe un registro por ID
    public boolean existsById(PK id) {
        String queryStr = "SELECT COUNT(e) FROM " + persistentClass.getSimpleName() + " e WHERE e.id = :id";
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("id", id);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

//	public T persistir(T entity) {
//		return this.entityManager.merge(entity);
//	}
//	public T persistir(T entity) {
//	    try {
//	        return this.entityManager.merge(entity);
//	    } catch (jakarta.persistence.EntityExistsException e) {
//	        throw new CustomDuplicateKeyException("Registro con el mismo Id ya existe: " + entity, e);
//	    } catch (Exception e) {
//	        throw new RuntimeException("Error al guardar registro: " + entity, e);
//	    }
//	}
    public T persistir(T entity) {
        @SuppressWarnings("unchecked")
		PK id = (PK) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        if (existsById(id)) {
            throw new CustomDuplicateKeyException("Registro con el mismo ID:" + id + " ya existe: ");
        }
        try {
            return this.entityManager.merge(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar registro: " + entity, e);
        }
    }
	
//	public T actualizar(T entity) {
//		return this.entityManager.merge(entity);
//	}
    
    public T actualizar(T entity) {
    	@SuppressWarnings("unchecked")
		PK id = (PK) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    	if (existsById(id)) {
    		try {
                return this.entityManager.merge(entity);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar registro: " + entity, e);
            }
    		
        }else {
    	throw new RuntimeException("No existe el registro: " + entity);
        }
    }
    
    
	
	public void eliminar(T entity) {
		this.entityManager.remove(entity);
	}
	
//	public void eliminar(PK id) {
//		T entity = getById(id);
//		this.entityManager.remove(entity);
//	}
	
	public void eliminar(PK id) {
		//@SuppressWarnings("unchecked")
		//PK id = (PK) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
		T entity = getById(id);
    	if (existsById(id)) {
    		
    		try {    			
                this.entityManager.remove(entity);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar registro: " + entity, e);
            }
    		
        }else {
    	throw new RuntimeException("No existe el registro: " + entity);
        }
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		Query q = this.getEntityManager().createQuery("from " + persistentClass.getName());
		return q.getResultList();
	}
	
	
	
	
	
}
