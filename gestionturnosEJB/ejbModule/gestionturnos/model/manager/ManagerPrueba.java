package gestionturnos.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import gestionturnos.model.dto.LoginDTO;
import gestionturnos.model.entities.Asignacion;
import gestionturnos.model.entities.Personal;
import gestionturnos.model.entities.Prueba;
import gestionturnos.model.entities.Usuario;



/**
 * 
 */
@Stateless
@LocalBean
public class ManagerPrueba {

	@PersistenceContext
	private EntityManager em;
	

	public ManagerPrueba() {

	}

	public List<Prueba> findAllPrueba() {
		String consulta = "SELECT u FROM Prueba u";
		Query q = em.createQuery(consulta, Prueba.class);
		return q.getResultList();
	}

	

	public Prueba findPruebaById(int id) {
		return em.find(Prueba.class, id);
	}
	

	public void eliminarPrueba(int Id) {
		Prueba prueba = findPruebaById(Id);
		if (prueba != null)
			em.remove(prueba);
	}

	public void insertarPrueba (Prueba prueba) throws Exception {
		em.persist(prueba);
		
		System.out.println("Usuario insertado.........");
	}
	
	public int total(List<Prueba> prueba) {
		int suma=0;
		for(Prueba p:prueba)
			suma+=p.getNumero();
		return suma;
		
	}
	
	public double totalPago(List<Prueba> prueba) {
		double suma=0;
		for(Prueba p:prueba)
			suma+=p.getPago();
		return suma;
		
	}



}
