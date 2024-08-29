package py.edu.ucsa.contabilidad.microservice.core.dao.impl;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.contabilidad.microservice.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.microservice.core.dao.CobroDetConceptoDao;
import py.edu.ucsa.contabilidad.microservice.core.entities.CobroDetConcepto;

@Repository("cobroDetConceptoDao")
public class CobroDetConceptoDaoImpl extends AbstractDao<Long, CobroDetConcepto> implements CobroDetConceptoDao {

	
}
