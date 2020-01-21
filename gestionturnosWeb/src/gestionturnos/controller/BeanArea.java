package gestionturnos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Area;
import gestionturnos.model.manager.ManagerArea;

@Named
@SessionScoped
public class BeanArea implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerArea managerArea;
	private List<Area> listaAreas;
	private Area area;
	private Area areaSeleccionada;

	@PostConstruct
	public void inicializar() {
		listaAreas = managerArea.findAllAreas();
		area = new Area();
	}

	public String actionListenerInsertarArea() {
		try {
			managerArea.insertarArea(area);
			listaAreas = managerArea.findAllAreas();
			area = new Area();
			JSFUtil.createMensajeInfo("Datos Ingresados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "areas_dbu.xhtml";
	}

	public void actionListenerEliminarArea(int IdArea) {
		managerArea.eliminarArea(IdArea);
		listaAreas = managerArea.findAllAreas();
		JSFUtil.createMensajeInfo("Area Eliminada");
	}

	public void actionListenerSelecionarArea(Area area) {
		areaSeleccionada = area;
	}

	public void actionListenerActualizarArea() {
		try {
			managerArea.actualizarArea(areaSeleccionada);
			listaAreas = managerArea.findAllAreas();
			JSFUtil.createMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public Area getAreaSeleccionada() {
		return areaSeleccionada;
	}

	public void setAreaSeleccionada(Area areaSeleccionada) {
		this.areaSeleccionada = areaSeleccionada;
	}

	public List<Area> getListaAreas() {
		return listaAreas;
	}

	public void setListaAreas(List<Area> listaAreas) {
		this.listaAreas = listaAreas;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
}
