package gestionturnos.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import gestionturnos.model.entities.Usuario;
import gestionturnos.model.manager.ManagerUsuario;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped

public class BeanUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerUsuario managerUsuario;
	private List<Usuario> listausuario;
	private Usuario usuario;
	private Usuario usuarioSeleccionado;
	private String cedula;
	
	@PostConstruct

	public void inicializar() {
		listausuario= managerUsuario.findAllUsuario();
		usuario=new Usuario();
			
	}

	public String actionListenerInsertarUsuario() {
		
		try {
			
			managerUsuario.insertarUsuario(usuario);
			
			listausuario = managerUsuario.findAllUsuario();

			usuario = new Usuario();			
			
			JSFUtil.createMensajeInfo("Datos Ingresados");
			
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
			JSFUtil.createMensajeInfo("Usuario incorrecto");
		}
		return "usuario.xhtml";
		
	}
	public void actionListenerEliminarUsuario(int IdUsuario) {
		managerUsuario.eliminarUsuario(IdUsuario);
		listausuario = managerUsuario.findAllUsuario();
		JSFUtil.createMensajeInfo("Usuario Eliminada");
	}

	public void actionListenerSelecionarUsuario(Usuario usuario) {
		usuarioSeleccionado = usuario;
	}
	public void actionListenerActualizarUsuario() {
		try {
			managerUsuario.actualizarUsuario(usuarioSeleccionado);
			listausuario = managerUsuario.findAllUsuario();
			JSFUtil.createMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.createMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public boolean validadorDeCedula() {
		cedula=this.cedula;
		boolean cedulaCorrecta = false;

		try {

			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					// Coeficientes de validación cédula
					// El decimo digito se lo considera dígito verificador
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out.println("Una excepcion ocurrio en el proceso de validadcion");
			cedulaCorrecta = false;
		}

		if (!cedulaCorrecta) {
			System.out.println("La Cédula ingresada es Incorrecta");
		}
		return cedulaCorrecta;
	}

	
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<Usuario> getListausuario() {
		return listausuario;
	}

	public void setListausuario(List<Usuario> listausuario) {
		this.listausuario = listausuario;
	}
	

}
