package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Parametro;

/**
 * Session Bean implementation class ManagerParametro
 */
@Stateless
@LocalBean
public class ManagerParametro {
	
	@PersistenceContext
	private EntityManager em;

	public ManagerParametro() {
		// TODO Auto-generated constructor stub
	}

    public List<Parametro> findAllParametros(){
    	String consulta = "SELECT p FROM Parametro p";
    	Query q= em.createQuery(consulta, Parametro.class);
    	return q.getResultList();
    }

}
