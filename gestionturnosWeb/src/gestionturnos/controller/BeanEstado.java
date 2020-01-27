package gestionturnos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Estado;
import gestionturnos.model.manager.ManagerEstado;

@Named
@SessionScoped
public class BeanEstado implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerEstado managerestado;
	private List<Estado> listaEstados;
	private Estado estado;
	private Estado estadoselecionado;

	@PostConstruct
	public void inicializar() {
		listaEstados = managerestado.findallEstado();
		estado = new Estado();
	}

	public String actionListenerInsertarEstado() {
		try {
			managerestado.insertarEstado(estado);
			listaEstados = managerestado.findallEstado();
			estado = new Estado();
			JSFUtil.createMensajeInfo("Datos Ingresados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "estado.xhtml";
	}

	public void actionListenerEliminarEstado(int idEstado) {
		managerestado.eliminarEstado(idEstado);
		listaEstados = managerestado.findallEstado();
		JSFUtil.createMensajeInfo("Estado Eliminado");
	}

	public void actionListenerSelecionarEstado(Estado estado) {
		estadoselecionado= estado;
	}

	public void actionListenerActualizarEstado() {
		try {
			managerestado.actualizarEstado(estadoselecionado);
			listaEstados= managerestado.findallEstado();
			JSFUtil.createMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	
	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Estado getEstadoselecionado() {
		return estadoselecionado;
	}

	public void setEstadoselecionado(Estado estadoselecionado) {
		this.estadoselecionado = estadoselecionado;
	}

}
