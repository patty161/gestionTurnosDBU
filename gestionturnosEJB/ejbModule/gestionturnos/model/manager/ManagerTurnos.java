package gestionturnos.model.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Area;
import gestionturnos.model.entities.Asignacion;
import gestionturnos.model.entities.Estado;
import gestionturnos.model.entities.Turno;
import gestionturnos.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerArea
 */
@Stateless
@LocalBean
public class ManagerTurnos {
	private Date fecha1 = new Date();
	Timestamp fecha = new Timestamp(fecha1.getTime());
	private int idArea, idAsignacion, idUsuario;

	@PersistenceContext
	private EntityManager em;
	private String idasi;

	public String getIdasi() {
		return idasi;
	}

	public void setIdasi(String idasi) {
		this.idasi = idasi;
	}

	public ManagerTurnos() {

	}

	public Turno crearTurnoTmp() {
		Turno turnoTmp = new Turno();
		return turnoTmp;
	}

	public List<Area> findAllAreas() {
		String consulta = "SELECT a FROM Area a";
		Query q = em.createQuery(consulta, Area.class);
		return q.getResultList();
	}

	public List<Turno> findAllDoctor() {
		String consulta = "SELECT a FROM Turno a order by id_turnos";
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}

	public List<Turno> findfinDoctor() {
		System.out.println("ssssssssssssssssssssssssssssssssssssssssssss"+idasi);
		String consulta = "SELECT t FROM Turno t where id_asignacion=" + idasi;
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}

	public List<Turno> findAllTurnos() {
		String consulta = "SELECT t FROM Turno t";
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}

	public Area findAreaById(int idArea) {
		Area area = em.find(Area.class, idArea);
		return area;
	}

	public Estado findEstadoById(int idEstado) {
		Estado estado = em.find(Estado.class, idEstado);
		return estado;
	}

	public Turno findTurnoById(int idTurno) {
		return em.find(Turno.class, idTurno);
	}

	public Usuario findUsuarioById(int idUsuario) {
		Usuario usuario = em.find(Usuario.class, idUsuario);
		return usuario;
	}

	public Asignacion findAsignacionById(int idAsignacion) {
		Asignacion usuario = em.find(Asignacion.class, idAsignacion);
		return usuario;
	}

	public void eliminarTurno(int IdTurno) {
		Turno turno = findTurnoById(IdTurno);
		if (turno != null)
			em.remove(turno);
	}

	public void insertarTurno(Turno turno) throws Exception {
		em.persist(turno);
	}

}