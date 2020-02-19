package gestionturnos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Query;

import gestionturnos.model.entities.Parametro;
import gestionturnos.model.entities.Parametro;
import gestionturnos.model.manager.ManagerParametro;

@Named
@SessionScoped
public class BeanParametro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanParametro() {
		// TODO Auto-generated constructor stub
	}


	@EJB
	private ManagerParametro managerParametro;
	private List<Parametro> listaParametros;

	private Parametro parametro;
    @PostConstruct
	public void inicializar() {
		listaParametros = managerParametro.findAllParametros();
		parametro = new Parametro();
	}
	public List<Parametro> getListaParametros() {
		return listaParametros;
	}
	public void setListaParametros(List<Parametro> listaParametros) {
		this.listaParametros = listaParametros;
	}
	public Parametro getParametro() {
		return parametro;
	}
	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}
    
}
