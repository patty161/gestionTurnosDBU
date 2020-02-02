package gestionturnos.model.manager;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import gestionturnos.model.entities.Bitacora;



/**
 * Clase que implementa la logica de manejo de
 * pistas de auditoria.
 * @author mrea
 *
 */
@Stateless
@LocalBean
public class ManagerAuditoria {
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerSeguridad managerSeguridad;
	
	public ManagerAuditoria() {
		
	}
	
	/**
	 * Almacena la informacion de un evento en la tabla de auditoria.
	 * @param codigoUsuario Codigo del usuario que genero el evento.
	 * @param clase Clase que genera el evento.
	 * @param metodo Nombre del metodo que genero el evento.
	 * @param descripcion Informacion detallada del evento.
	 * @throws Exception 
	 */
	public void crearEvento(String codigoUsuario,Class clase,String metodo,String descripcion) throws Exception{
		Bitacora evento=new Bitacora();
		//cambio para probar git
		
		if(codigoUsuario==null||codigoUsuario.length()==0)
			throw new Exception("Error auditoria: debe indicar el codigo del usuario.");
		if(metodo==null||metodo.length()==0)
			throw new Exception("Error auditoria: debe indicar el metodo que genera el evento.");

		evento.setCodigoUsuario(codigoUsuario);
		evento.setMetodo(clase.getSimpleName()+"/"+metodo);
		evento.setDescripcion(descripcion);
		evento.setFechaEvento(new Date());
		evento.setDireccionIp("localhost");
		
		managerDAO.insertar(evento);
	}

}
