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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gestionturnos.model.entities.Area;
import gestionturnos.model.entities.Asignacion;
import gestionturnos.model.entities.Turno;
import gestionturnos.model.entities.Usuario;
import gestionturnos.model.manager.ManagerTurnos;

@Named
@SessionScoped
public class BeanTurnos implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerTurnos managerTurnos;
	private List<Turno> listaTurnos;
	private List<Turno> listaTurnosXdoc;
	private List<Turno> UltimoTurno;
	private Turno turno;
	private Turno turnoTmp;
	private boolean turnoTmpGuardado;
	
	@Inject
	private BeanLogin beanLogin;

	@PersistenceContext
	private EntityManager em;
	
	private Integer idUsuario;
	private Integer idArea;
	private Integer idAsignacion;
	private Integer idEstado;

	private Date fecha1 = new Date();
	Timestamp fecha = new Timestamp(fecha1.getTime());

	
	public List<Turno> getListaTurnosXdoc() {
		return listaTurnosXdoc;
	}

	public void setListaTurnosXdoc(List<Turno> listaTurnosXdoc) {
		this.listaTurnosXdoc = listaTurnosXdoc;
	}

	public BeanTurnos() {

	}

	@PostConstruct
	public void inicializar() {
		listaTurnos = managerTurnos.findAllDoctor();
		listaTurnosXdoc = managerTurnos.findfinDoctor();
		turnoTmp = new Turno();
		turno = new Turno();
	}

	public String actionListenerInsertarTurno() {
		Turno t= new Turno();
//		Area a=em.find(Area.class, idArea);
//		t.setEspArea(a);
//		
//		Asignacion asig= em.find(Asignacion.class, idAsignacion);
//		t.setSegAsignacion(asig);
		
		//Usuario us= em.find(Usuario.class, idUsuario);
		//t.setUsuario(us);
		
		t.setFecha(fecha);
		
			try {
			managerTurnos.insertarTurno(t);	
			listaTurnos = managerTurnos.findAllTurnos();
			turno = new Turno();
			JSFUtil.createMensajeInfo("Turno Generado");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "detalle.xhtml";
	}

	public void crearNuevoTurno() {
		turnoTmp = managerTurnos.crearTurnoTmp();
		idUsuario = null;
		idAsignacion = null;
		idArea = null;
		idEstado = null;
		setTurnoTmpGuardado(false);
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

	public Turno getTurnoTmp() {
		return turnoTmp;
	}

	public void setTurnoTmp(Turno turnoTmp) {
		this.turnoTmp = turnoTmp;
	}

	public boolean isTurnoTmpGuardado() {
		return turnoTmpGuardado;
	}

	public void setTurnoTmpGuardado(boolean turnoTmpGuardado) {
		this.turnoTmpGuardado = turnoTmpGuardado;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public Integer getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(Integer idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public List<Turno> getUltimoTurno() {
		return UltimoTurno;
	}

	public void setUltimoTurno(List<Turno> ultimoTurno) {
		UltimoTurno = ultimoTurno;
	}
}