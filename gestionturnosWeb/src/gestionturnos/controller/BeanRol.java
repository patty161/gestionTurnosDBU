package gestionturnos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Rol;

import gestionturnos.model.manager.ManagerRoles;
import gestionturnos.model.manager.ManagerUsuario;

@Named
@SessionScoped
public class BeanRol implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerRoles managerRoles;
	@EJB
	private ManagerUsuario managerUsuario;
	private List<Rol> listaRoles;
	private Rol rol;
	private Rol RolSeleccionado;
	

	@PostConstruct
	public void inicializar() {
		listaRoles = managerRoles.findAllRoles();
		rol = new Rol();
	}

	public String actionListenerInsertarRol() {
		try {
			managerRoles.insertarRol(rol);
			listaRoles = managerRoles.findAllRoles();
			rol = new Rol();
			JSFUtil.createMensajeInfo("Datos Ingresados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "rol_dbu.xhtml";
	}

	public void actionListenerEliminarRol(int idRol) {
		managerRoles.eliminarrol(idRol);
		listaRoles = managerRoles.findAllRoles();
		JSFUtil.createMensajeInfo("Area Eliminada");
	}

	public void actionListenerSelecionarRol(Rol rol) {
		RolSeleccionado = rol;
	}



	public void actionListenerActualizarRol() {
		try {
			managerRoles.actualizarrol(RolSeleccionado);
			listaRoles = managerRoles.findAllRoles();
			JSFUtil.createMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Rol getRolSeleccionado() {
		return RolSeleccionado;
	}

	public void setRolSeleccionado(Rol rolSeleccionado) {
		RolSeleccionado = rolSeleccionado;
	}


}
