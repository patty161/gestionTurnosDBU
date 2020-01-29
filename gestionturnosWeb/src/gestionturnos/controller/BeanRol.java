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
	private String cedula;
	private String clave;

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

	/// USUARIO NORMAL
	public String actionListenerUsuarioNor() throws Exception {
		if (managerUsuario.ValidaUsuario(cedula, " n")) {
			System.out.println("si vale aqui hay que poner el codigo que creer nuevo turno");
			return "/usuario/inicio.xhtml";

		} else {

			System.out.println("ud no es un usuario registrado");
		}
		return "va el crud de escojer tunro.xhtml";

	}

	/// USUARIO OTRO
	public String actionListenerUsuario() throws Exception {
		if (managerUsuario.ValidaUsuario(cedula, clave)) {
			System.out.println("sivale");
			System.out.println("aaaaaaaaaaaaaaaaaaaaa" + managerUsuario.getIdusuariobuscado());

			System.out.println("si vale aqui hay que poner la vista de "
					+ managerUsuario.TipoUsuario(managerUsuario.getIdusuariobuscado()));
			//////// editar por 2 para admin
			if (managerUsuario.TipoUsuario(managerUsuario.getIdusuariobuscado()).equals("Administrativo")) {
				System.out.println("entra 22222");
				return "/administrativo/indexPrincipal.xhtml";
			}
			if (managerUsuario.TipoUsuario(managerUsuario.getIdusuariobuscado()).equals("Especilista")) {
				System.out.println("entra 22222");
				return "/personal/inicio.xhtml";
			}
		
		} else {

			System.out.println("ud no es un usuario registrado");
		}
		return "indexPrincipal.xhtml";

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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
