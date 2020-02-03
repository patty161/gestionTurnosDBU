package gestionturnos.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Usuario;
import gestionturnos.model.manager.ManagerUsuario;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class BeanUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerUsuario managerUsuario;
	private List<Usuario> listausuario;
	private Usuario usuario;
	private Usuario usuarioSeleccionado;
	private String cedula;
	
	@PostConstruct

	public void inicializar() {
		listausuario= managerUsuario.findAllUsuario();
		usuario=new Usuario();
			
	}

	public String actionListenerInsertarUsuario() {
		boolean a=false;
		try {
			managerUsuario.insertarUsuario(usuario);
			listausuario = managerUsuario.findAllUsuario();

			usuario = new Usuario();			
			
			JSFUtil.createMensajeInfo("Datos Ingresados");
			
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "usuario.xhtml";
		
	}
	public void actionListenerEliminarUsuario(int IdUsuario) {
		managerUsuario.eliminarUsuario(IdUsuario);
		listausuario = managerUsuario.findAllUsuario();
		JSFUtil.createMensajeInfo("Usuario Eliminada");
	}

	public void actionListenerSelecionarUsuario(Usuario usuario) {
		usuarioSeleccionado = usuario;
	}
	public void actionListenerActualizarUsuario() {
		try {
			managerUsuario.actualizarUsuario(usuarioSeleccionado);
			listausuario = managerUsuario.findAllUsuario();
			JSFUtil.createMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<Usuario> getListausuario() {
		return listausuario;
	}

	public void setListausuario(List<Usuario> listausuario) {
		this.listausuario = listausuario;
	}
	

}
