package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Usuario;
import gestionturnos.model.entities.Asignacion;
import gestionturnos.model.entities.Rol;

/**
 * Session Bean implementation class ManagerPersonal
 */
@Stateless
@LocalBean
public class ManagerAsignacion {

	@PersistenceContext
	private EntityManager em;

	public ManagerAsignacion() {
		// TODO Auto-generated constructor stub
	}

	public List<Asignacion> findallAsignacion() {
		String consulta = "SELECT e FROM Asignacion e";
		Query q = em.createQuery(consulta, Asignacion.class);
		return q.getResultList();

	}

	public Asignacion findAsignacionById(int idAsignacion) {
		return em.find(Asignacion.class, idAsignacion);
	}
	public Asignacion findAsignacionByUsuario(int idUsuario) {
		return em.find(Asignacion.class, idUsuario);
	}

	public void eliminarAsignacion(int IdAsignacion) {
		Asignacion asignacion = findAsignacionById(IdAsignacion);
		if (asignacion != null)
			em.remove(asignacion);
	}

	 public void crearAsignacion(int idRol,int idUsuario) {
	    	Asignacion asig=new Asignacion();
	    	//ClavesForaneas
	    	Usuario u=em.find(Usuario.class,idUsuario);
	    	asig.setUsuario(u);
	    
	    	Rol r=em.find(Rol.class,idRol);
	    	asig.setSegRol(r);
	    	em.persist(asig);
	    }
	public void actualizarAsignacion(Asignacion asignacion) throws Exception {
		Asignacion p = findAsignacionById(asignacion.getIdAsignacion());
		if (p == null)
			throw new Exception("No existe Asignacion indicado");
		p.setSegRol(asignacion.getSegRol());
		p.setUsuario(asignacion.getUsuario());
		em.merge(p);
	}
    
}
