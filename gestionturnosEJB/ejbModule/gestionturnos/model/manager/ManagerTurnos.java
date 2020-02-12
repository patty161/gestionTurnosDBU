package gestionturnos.model.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Area;
import gestionturnos.model.entities.Asignacion;
import gestionturnos.model.entities.Estado;
import gestionturnos.model.entities.Personal;
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

	@PersistenceContext
	private EntityManager em;
	private String idasi;

	@EJB
	private ManagerPersonal managerPersonal;
	@EJB
	private ManagerEstado managerEstado;
	@EJB
	private ManagerAsignacion managerAsignacion;
	@EJB
	private ManagerArea managerArea;
	@EJB
	private ManagerUsuario managerUsuario;

	private Personal personal;
	private Estado estado;
	private Asignacion asignacion;

	private Area area;
	private Usuario usuario;

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Asignacion getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = asignacion;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getIdasi() {
		return idasi;
	}

	public void setIdasi(String idasi) {
		this.idasi = idasi;
	}

	public ManagerTurnos() {

	}

	public List<Turno> findAllTurnos() {
		String consulta = "SELECT a FROM Turno a order by id_turnos";
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}

	public List<Turno> findTurnosProximos() {
		String consulta = "SELECT t FROM Turno t where id_estado='1' order by id_turnos";
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}

	public List<Turno> findfinDoctor() {
		// String consulta = "SELECT t FROM Turno t where id_asignacion=" + idasi+" and id_estado='1' order by id_turnos";
		String consulta = "SELECT t FROM Turno t where id_asignacion='"+idasi+"' and id_estado='1' order by id_turnos";
		Query q = em.createQuery(consulta, Turno.class);
		return q.getResultList();
	}

	public Turno findTurnoById(int idTurno) {
		return em.find(Turno.class, idTurno);
	}

	public void eliminarTurno(int IdTurno) {
		Turno turno = findTurnoById(IdTurno);
		if (turno != null)
			em.remove(turno);
	}

	int n_turno = 0;

	public void insertarTurno(Turno turno) throws Exception {
		turno.setSegAsignacion(asignacion);
		em.persist(turno);
	}

	public void actualizarTurno(Turno turno) throws Exception {
		Turno tur = findTurnoById(turno.getIdTurnos());
		System.out.println("ID TURNOOOO " + tur.getIdTurnos());
		estado = managerEstado.findEstadoById(2);
		if (tur == null)
			throw new Exception("No existe el Turno con el Id especificada");
		tur.setTurEstado(estado);
		System.out.println("estado act " + estado);
		em.merge(tur);
	}

	public void cancelarTurno(Turno turno) throws Exception {
		Turno tur = findTurnoById(turno.getIdTurnos());
		System.out.println("ID TURNOOOO " + tur.getIdTurnos());
		Estado estado = managerEstado.findEstadoById(3);
		if (tur == null)
			throw new Exception("No existe el Turno con el Id especificada");
		tur.setTurEstado(turno.getTurEstado());
		tur.setTurEstado(estado);
		em.merge(tur);
	}

}