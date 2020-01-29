package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Usuario;
import gestionturnos.model.entities.Area;
import gestionturnos.model.entities.Estado;
import gestionturnos.model.entities.Personal;
import gestionturnos.model.entities.Personal;

/**
 * Session Bean implementation class ManagerPersonal
 */
@Stateless
@LocalBean
public class ManagerPersonal {

	@PersistenceContext
	private EntityManager em;

	public ManagerPersonal() {
		// TODO Auto-generated constructor stub
	}

	public List<Personal> findallPersonal() {
		String consulta = "SELECT e FROM Personal e";
		Query q = em.createQuery(consulta, Personal.class);
		return q.getResultList();

	}

	public Personal findPersonalById(int idPersonal) {
		return em.find(Personal.class, idPersonal);
	}

	public void eliminarPersonal(int IdPersonal) {
		Personal personal = findPersonalById(IdPersonal);
		if (personal != null)
			em.remove(personal);
	}

	public void crearPersonal(int codArea, int idUsuario, int horas) {
		Personal p = new Personal();
		p.setHorasLaborables(new Integer(horas));

		// ClavesForaneas
		Usuario u = em.find(Usuario.class, idUsuario);
		p.setUsuario(u);

		Area a = em.find(Area.class, codArea);
		p.setEspArea(a);
		em.persist(p);
	}
	public void actualizarPersonal(Personal personal) throws Exception {
		Personal p = findPersonalById(personal.getIdPersonal());
		if (p == null)
			throw new Exception("No existe Personal indicado");
		p.setEspArea(personal.getEspArea());
		p.setUsuario(personal.getUsuario());
		p.setHorasLaborables(personal.getHorasLaborables());
		em.merge(p);
	}
    
}
