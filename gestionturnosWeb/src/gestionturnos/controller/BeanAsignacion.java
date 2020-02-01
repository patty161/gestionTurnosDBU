package gestionturnos.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import gestionturnos.model.entities.Asignacion;
import gestionturnos.model.manager.ManagerAsignacion;


import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class BeanAsignacion implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerAsignacion manageasignacion;
	private int idRol;
	private int idUsuario;
	
	private Asignacion asignacion;
	private Asignacion asignacionselecionado;
	private List<Asignacion> listaAsignacion;

	@PostConstruct
	public void inicializar() {
		listaAsignacion = manageasignacion.findallAsignacion();
			asignacion=new Asignacion();
	} 
	
	public String actionCrearAsignacion() {
		try {
			
		manageasignacion.crearAsignacion(idRol, idUsuario);
		listaAsignacion = manageasignacion.findallAsignacion();
		JSFUtil.createMensajeInfo("Datos Ingresados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return"asignacion.xhtml";
	}
	public void actionListenerEliminarAsignacion(int idAsignacion) {
		manageasignacion.eliminarAsignacion(idAsignacion);
		listaAsignacion = manageasignacion.findallAsignacion();
		JSFUtil.createMensajeInfo("Asignacion Eliminado");
	}
	public void actionListenerSelecionarAsignacion(Asignacion asignacion) {
		asignacionselecionado= asignacion;
	}
	
	public void actionListenerActualizarAsignacion() {
		try {
			manageasignacion.actualizarAsignacion(asignacionselecionado);
			listaAsignacion= manageasignacion.findallAsignacion();
			JSFUtil.createMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Asignacion getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = asignacion;
	}

	public Asignacion getAsignacionselecionado() {
		return asignacionselecionado;
	}

	public void setAsignacionselecionado(Asignacion asignacionselecionado) {
		this.asignacionselecionado = asignacionselecionado;
	}

	public List<Asignacion> getListaAsignacion() {
		return listaAsignacion;
	}

	public void setListaAsignacion(List<Asignacion> listaAsignacion) {
		this.listaAsignacion = listaAsignacion;
	}

	
	
}

