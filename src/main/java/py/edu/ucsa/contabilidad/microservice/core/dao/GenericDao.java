package py.edu.ucsa.contabilidad.microservice.core.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<PK extends Serializable, T> {
	List<T> listar();
	T getById(PK id);
	public boolean existsById(PK id);
	T persistir(T entity);
	public T actualizar(T entity);
	void eliminar(T entity);
	void eliminar(PK id);

}
