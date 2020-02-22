package gestionturnos.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Prueba;
import gestionturnos.model.entities.Usuario;
import gestionturnos.model.manager.ManagerPrueba;
import gestionturnos.model.manager.ManagerUsuario;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class BeanPrueba implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerPrueba managerPrueba;
	private List<Prueba> listaprueba;
	private Prueba prueba;
	private Prueba pruebaSeleccionado;
	private int total=0;
	private double totalpago;
	//private String cedula;
	
	@PostConstruct

	public void inicializar() {
		listaprueba= managerPrueba.findAllPrueba();
		total=managerPrueba.total(listaprueba);
		totalpago=managerPrueba.totalPago(listaprueba);
		prueba=new Prueba();
			
	}

	public String actionListenerInsertarPrueba() {
		
		try {
						
			managerPrueba.insertarPrueba(prueba);
			
			//total=managerPrueba.insertarPrueba(prueba);
			listaprueba = managerPrueba.findAllPrueba();
			prueba = new Prueba();	
			total=managerPrueba.total(listaprueba);
			totalpago=managerPrueba.totalPago(listaprueba); 
					
			
			JSFUtil.createMensajeInfo("Datos Ingresados");
			
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
			JSFUtil.createMensajeInfo("Usuario incorrecto");
		}
		return "usuario.xhtml";
		
	}
	public void actionListenerEliminarPrueba(int Id) {
		managerPrueba.eliminarPrueba(Id);
		listaprueba = managerPrueba.findAllPrueba();
		total=managerPrueba.total(listaprueba);
		totalpago=managerPrueba.totalPago(listaprueba);
		JSFUtil.createMensajeInfo("Usuario Eliminada");
	}

	public void actionListenerSelecionarPrueba(Prueba prueba) {
		pruebaSeleccionado = prueba;
	}
	
	
	public List<Prueba> getListaprueba() {
		return listaprueba;
	}

	public void setListaprueba(List<Prueba> listaprueba) {
		this.listaprueba = listaprueba;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public Prueba getPruebaSeleccionado() {
		return pruebaSeleccionado;
	}

	public void setPruebaSeleccionado(Prueba pruebaSeleccionado) {
		this.pruebaSeleccionado = pruebaSeleccionado;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getTotalpago() {
		return totalpago;
	}

	public void setTotalpago(double totalpago) {
		this.totalpago = totalpago;
	}



}
