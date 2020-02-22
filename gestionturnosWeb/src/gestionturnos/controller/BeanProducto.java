package gestionturnos.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Producto;
import gestionturnos.model.manager.ManagerProducto;
@Named
@SessionScoped
public class BeanProducto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanProducto() {
		// TODO Auto-generated constructor stub
	}
	@EJB
	private ManagerProducto managerProducto;
	private List<Producto> listaProductos;
	private Producto producto;
	private int suma1=0;
	private double suma2=0;
	private Producto productoSeleccionada;

	@PostConstruct
	public void inicializar() {
		listaProductos = managerProducto.findAllProductos();
		producto = new Producto();
	}

	public String actionListenerInsertarProducto() {
		try {
			managerProducto.insertarProducto(producto);
			listaProductos = managerProducto.findAllProductos();
			producto = new Producto();
			JSFUtil.createMensajeInfo("Datos Ingresados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "productos.xhtml";
	}

	public void actionListenerEliminarProducto(int IdProducto) {
		managerProducto.eliminarProducto(IdProducto);
		listaProductos = managerProducto.findAllProductos();
		JSFUtil.createMensajeInfo("Producto Eliminada");
	}

	public double getSuma2() {
		suma2=0;
		listaProductos=managerProducto.findAllProductos();
		for	(Producto producto: listaProductos) {
		suma2=suma2+producto.getValor();
		}
		return suma2;
	}
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getProductoSeleccionada() {
		return productoSeleccionada;
	}

	public void setProductoSeleccionada(Producto productoSeleccionada) {
		this.productoSeleccionada = productoSeleccionada;
	}

	public void actionListenerSelecionarProducto(Producto producto) {
		productoSeleccionada = producto;
	}

	public int getSuma1() {
		suma1=0;
		listaProductos=managerProducto.findAllProductos();
		for	(Producto producto: listaProductos) {
		suma1=suma1+producto.getCantidad();
		}
		return suma1;
	}

	public void setSuma1(int suma1) {
		this.suma1 = suma1;
	}

	

	public void setSuma2(double suma2) {
		this.suma2 = suma2;
	}

}
