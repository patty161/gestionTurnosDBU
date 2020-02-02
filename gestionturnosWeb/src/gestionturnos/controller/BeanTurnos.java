package gestionturnos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Turno;
import gestionturnos.model.manager.ManagerTurnos;

@Named
@SessionScoped
public class BeanTurnos implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerTurnos managerdoctor;
	private List<Turno> listaTurnos;
	private List<Turno> listaTurnosXdoc;
	private Turno turno;
	
	

	public List<Turno> getListaTurnosXdoc() {
		return listaTurnosXdoc;
	}

	public void setListaTurnosXdoc(List<Turno> listaTurnosXdoc) {
		this.listaTurnosXdoc = listaTurnosXdoc;
	}

	@PostConstruct
	public void inicializar() {
		listaTurnos= managerdoctor.findAllDoctor();
		listaTurnosXdoc= managerdoctor.findfinDoctor();
		turno=new Turno();
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

	
}
