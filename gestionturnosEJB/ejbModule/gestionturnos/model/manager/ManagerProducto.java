package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import gestionturnos.model.entities.Producto;

/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerProducto {

	@PersistenceContext
	private EntityManager em;
	
    public ManagerProducto() {
        // TODO Auto-generated constructor stub
    }
    public List<Producto> findAllProductos(){
    	String consulta = "SELECT p FROM Producto p";
    	Query q= em.createQuery(consulta, Producto.class);
    	return q.getResultList();
    }
    public Producto findProductoById(int idProducto) {
		return em.find(Producto.class, idProducto);
	}
    public void eliminarProducto(int IdProducto) {
    	Producto producto = findProductoById(IdProducto);
		if (producto!= null)
			em.remove(producto);
	}
    public void insertarProducto (Producto producto) {
    	em.persist(producto);
    }
}
