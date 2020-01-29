package gestionturnos.controller;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Personal;
import gestionturnos.model.manager.ManagerPersonal;

import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class BeanPersonal implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPersonal managepersonal;
	private int codArea;
	private int idUsuario;
	private int horas;

	private Personal personal;
	private Personal personalselecionado;
	private List<Personal> listaPersonal;

	@PostConstruct
	public void inicializar() {
		listaPersonal = managepersonal.findallPersonal();
			personal=new Personal();
	} 
	
	public String actionCrearPersonal() {
		try {
			
		managepersonal.crearPersonal(codArea,idUsuario, horas);
		listaPersonal = managepersonal.findallPersonal();
		JSFUtil.createMensajeInfo("Datos Ingresados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return"personalprueba.xhtml";
	}
	public void actionListenerEliminarPersonal(int idPersonal) {
		managepersonal.eliminarPersonal(idPersonal);
		listaPersonal = managepersonal.findallPersonal();
		JSFUtil.createMensajeInfo("Personal Eliminado");
	}
	public void actionListenerSelecionarPersonal(Personal personal) {
		personalselecionado= personal;
	}
	
	public void actionListenerActualizarPersonal() {
		try {
			managepersonal.actualizarPersonal(personalselecionado);
			listaPersonal= managepersonal.findallPersonal();
			JSFUtil.createMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public int getCodArea() {
		return codArea;
	}
	public void setCodArea(int codArea) {
		this.codArea = codArea;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Personal getPersonalselecionado() {
		return personalselecionado;
	}

	public void setPersonalselecionado(Personal personalselecionado) {
		this.personalselecionado = personalselecionado;
	}

	public List<Personal> getListaPersonal() {
		return listaPersonal;
	}

	public void setListaPersonal(List<Personal> listaPersonal) {
		this.listaPersonal = listaPersonal;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	
	
}

