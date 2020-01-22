package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import gestionturnos.model.entities.Rol;

/**
 * Session Bean implementation class Managerrol
 */
@Stateless
@LocalBean
public class ManagerRoles {

	@PersistenceContext
	private EntityManager em;
	
    public ManagerRoles() {
    	
    }
    
    public List<Rol> findAllRoles(){
    	String consulta = "SELECT r FROM Rol r order by id_rol";
    	Query q= em.createQuery(consulta, Rol.class);
    	return q.getResultList();
    }
    public Rol findRolById(int idRol) {
		return em.find(Rol.class, idRol);
	}
    public void eliminarrol(int idRol) {
    	Rol rol = findRolById(idRol);
		if (rol!= null)
			em.remove(rol);
	}
    public void insertarRol (Rol rol) {
    	em.persist(rol);
    }
    public void actualizarrol(Rol rol) throws Exception {
		Rol rl = findRolById(rol.getIdRol());
		if (rl == null)
			throw new Exception("No existe el rol con el Id especificada");
		rl.setNombreRol(rol.getNombreRol());
	
		em.merge(rl);
	}

}
