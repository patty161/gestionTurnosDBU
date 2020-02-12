package gestionturnos.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import gestionturnos.model.entities.Area;
import gestionturnos.model.entities.Asignacion;
import gestionturnos.model.entities.Estado;
import gestionturnos.model.entities.Personal;
import gestionturnos.model.entities.Turno;
import gestionturnos.model.entities.Usuario;
import gestionturnos.model.manager.ManagerArea;
import gestionturnos.model.manager.ManagerEstado;
import gestionturnos.model.manager.ManagerPersonal;
import gestionturnos.model.manager.ManagerTurnos;
import gestionturnos.model.manager.ManagerUsuario;
import gestionturnos.model.manager.ManagerAsignacion;

@Named
@SessionScoped
public class BeanTurnos implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerTurnos managerTurnos;
	@EJB
	private ManagerArea managerArea;
	@EJB
	private ManagerUsuario managerUsuario;
	@EJB
	private ManagerPersonal managerPersonal;
	@EJB
	private ManagerEstado managerEstado;
	@EJB
	private ManagerAsignacion managerAsignacion;

	private List<Turno> listaTurnos;
	private List<Turno> listaTurnosProximos;
	private List<Turno> listaTurnosXdoc;
	private Turno turno;
	@Inject
	private BeanLogin beanLogin;

	private Area area;
	private Usuario usuario;
	private Personal personal;
	private Estado estado;
	private Asignacion asignacion;
	private Asignacion asignacion1;

	private Integer n_turno = 0;
	private Integer n_turnosArea = 1;
	private String codArea;
	private Turno turnoSeleccionado;

	private Date fecha1 = new Date();
	Timestamp fecha = new Timestamp(fecha1.getTime());

	public BeanTurnos() {

	}

	@PostConstruct
	public void inicializar() {
		listaTurnos = managerTurnos.findAllTurnos();
		listaTurnosProximos = managerTurnos.findTurnosProximos();
		listaTurnosXdoc = managerTurnos.findfinDoctor();
		turno = new Turno();
	}

	public List<Turno> getListaTurnosProximos() {
		return listaTurnosProximos;
	}

	public void setListaTurnosProximos(List<Turno> listaTurnosProximos) {
		this.listaTurnosProximos = listaTurnosProximos;
	}


public Asignacion findAsignacion(int idPersonal) {
		List<Asignacion> asig = managerUsuario.findAllAsignacion();
		for (Asignacion asignacion : asig) {
			if (asignacion.getUsuario().getIdUsuario().equals(idPersonal)) {
				System.out.println("asiiiiiiig " + asignacion.getIdAsignacion() + "... perssso "
						+ asignacion.getUsuario().getIdUsuario());
				asignacion1 = asignacion;
				break;
			}
		}
		System.out.println("asig final metodo. "+asignacion1.getIdAsignacion());
		return asignacion1;
	}

	public String actionListenerInsertarTurno(Integer idArea) {
		area = managerArea.findAreaById(idArea);
		personal = managerPersonal.findPersonalByArea(idArea);
		int idPersonal = personal.getUsuario().getIdUsuario();
		codArea = area.getCodArea();
		n_turno++;
		estado = managerEstado.findEstadoById(1);
		usuario = managerUsuario.findUsuarioById(beanLogin.getLoginDTO().getIdUsuario());
		asignacion= findAsignacion(idPersonal);

		Turno t = new Turno();

		t.setEspArea(area);
		t.setFecha(fecha);
		t.setNroTurno(n_turno);
		t.setTurEstado(estado);
		t.setUsuario(usuario);
		t.setSegAsignacion(asignacion);
		try {
			managerTurnos.insertarTurno(t);
			listaTurnos = managerTurnos.findAllTurnos();
			turno = new Turno();
			JSFUtil.createMensajeInfo("Turno generado");
			return "detalle";
		} catch (Exception e) {
			JSFUtil.createMensajeError("Ha ocurrido un error. Intente nuevamente");
			return "detalle";
		}
	}
	public void actionListenerSelecionarTurno(Turno turno) {
		turnoSeleccionado = turno;
	}

	public String actionListenerActualizarTurno() {
		try {
			managerTurnos.actualizarTurno(turnoSeleccionado);
			listaTurnosXdoc = managerTurnos.findfinDoctor();
			JSFUtil.createMensajeInfo("Turno Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";

	}

	public String actionListenerCancelarTurno() {
		try {
			managerTurnos.cancelarTurno(turnoSeleccionado);
			listaTurnosXdoc = managerTurnos.findfinDoctor();
			JSFUtil.createMensajeInfo("Turno Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public List<Turno> getListaTurnosXdoc() {
		return listaTurnosXdoc;
	}

	public void setListaTurnosXdoc(List<Turno> listaTurnosXdoc) {
		this.listaTurnosXdoc = listaTurnosXdoc;
	}

	public List<Turno> getListaTurnos() {
		return listaTurnos;
	}

	public void setListaTurnos(List<Turno> listaTurnos) {
		this.listaTurnos = listaTurnos;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
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

	public Integer getN_turno() {
		return n_turno;
	}

	public void setN_turno(Integer n_turno) {
		this.n_turno = n_turno;
	}

	public Asignacion getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = asignacion;
	}

	public Turno getTurnoSeleccionado() {
		return turnoSeleccionado;
	}

	public void setTurnoSeleccionado(Turno turnoSeleccionado) {
		this.turnoSeleccionado = turnoSeleccionado;
	}

}