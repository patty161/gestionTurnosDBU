package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Area;
import gestionturnos.model.entities.Turno;

/**
 * Session Bean implementation class ManagerArea
 */
@Stateless
@LocalBean
public class ManagerTurnos {

	@PersistenceContext
	private EntityManager em;
	
    public ManagerTurnos() {
    	
    }
    
    public List<Turno> findAllDoctor(){
    	String consulta = "SELECT a FROM Turno a order by id_turnos";
    	Query q= em.createQuery(consulta, Turno.class);
    	return q.getResultList();
    }

}