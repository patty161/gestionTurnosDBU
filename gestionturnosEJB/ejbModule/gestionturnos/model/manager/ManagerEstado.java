package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Area;
import gestionturnos.model.entities.Estado;
import gestionturnos.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerEstado
 */
@Stateless
@LocalBean
public class ManagerEstado {

	@PersistenceContext
	private EntityManager em;
	
    public ManagerEstado() {
        // TODO Auto-generated constructor stub
    }
    public List<Estado> findallEstado(){
    	String consulta="SELECT a FROM Estado a order by id_estado";
    	Query q=em.createQuery(consulta, Estado.class);
    	return q.getResultList();
    				
    }
    public Estado findEstadoById(int idEstado) {
    	return em.find(Estado.class, idEstado);
    }
    public void eliminarEstado(int IdEstado) {
    	Estado estado = findEstadoById(IdEstado);
		if (estado!= null)
			em.remove(estado);
	}
    public void insertarEstado (Estado estado) {
    	em.persist(estado);
    }
    public void actualizarEstado(Estado estado) throws Exception {
		Estado es = findEstadoById(estado.getIdEstado());
		if (es == null)
			throw new Exception("No existe el estado");
		es.setDescripcionEstado(estado.getDescripcionEstado());
		em.merge(es);
	}
    

    
}
