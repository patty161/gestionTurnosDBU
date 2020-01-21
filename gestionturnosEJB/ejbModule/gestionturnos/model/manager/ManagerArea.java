package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Area;

/**
 * Session Bean implementation class ManagerArea
 */
@Stateless
@LocalBean
public class ManagerArea {

	@PersistenceContext
	private EntityManager em;
	
    public ManagerArea() {
    	
    }
    
    public List<Area> findAllAreas(){
    	String consulta = "SELECT a FROM Area a order by id_area";
    	Query q= em.createQuery(consulta, Area.class);
    	return q.getResultList();
    }
    public Area findAreaById(int idArea) {
		return em.find(Area.class, idArea);
	}
    public void eliminarArea(int IdArea) {
    	Area area = findAreaById(IdArea);
		if (area!= null)
			em.remove(area);
	}
    public void insertarArea (Area area) {
    	em.persist(area);
    }
    public void actualizarArea(Area area) throws Exception {
		Area ar = findAreaById(area.getIdArea());
		if (ar == null)
			throw new Exception("No existe el area con el Id especificada");
		ar.setCodArea(area.getCodArea());
		ar.setNombreArea(area.getNombreArea());
		ar.setCantTurnos(area.getCantTurnos());
		ar.setTiempoAprox(area.getTiempoAprox());
		ar.setEspPersonals(area.getEspPersonals());
		ar.setTurTurnos(area.getTurTurnos());
		em.merge(ar);
	}

}
