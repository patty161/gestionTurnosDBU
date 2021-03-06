package gestionturnos.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import gestionturnos.model.dto.LoginDTO;
import gestionturnos.model.manager.ManagerAuditoria;
import gestionturnos.model.manager.ManagerSeguridad;
import gestionturnos.model.manager.ManagerUsuario;
import gestionturnos.model.util.ModelUtil;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigoUsuario;
	private String cedula;
	private String clave = "n";
	@EJB
	private ManagerSeguridad managerSeguridad;
	@EJB
	private ManagerAuditoria managerAuditoria;
	@EJB
	private ManagerUsuario managerUsuario;

	// objeto DTO para control de sesion:
	private LoginDTO loginDTO;

	@PostConstruct
	public void inicializar() {
		loginDTO = new LoginDTO();
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

	/**
	 * Action que permite el acceso al sistema.
	 * 
	 * @return
	 */
	public String accederSistema() {
		try {
			
			if (!validadorDeCedula()) {
				JSFUtil.createMensajeError("No es la cedula correcta");
			};
		System.out.println("laaaaaaaaaaaaaa cedulavale :"+validadorDeCedula());
		BeanEnvio envio= new BeanEnvio();
//		envio.enviarCorreo("ajvallejosm@utn.edu.ec");
			loginDTO = managerSeguridad.ValidaUsuario(cedula, clave);
			
				
//			System.out.println(""+loginDTO.getRutaAcceso()+"?faces-redirect=true");
			// redireccion dependiendo del tipo de usuario:
			return loginDTO.getRutaAcceso() + "?faces-redirect=true";
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.createMensajeError(e.getMessage());
		}
		return "";
	}
	public String accederSistemaA() {
		try {
			
			if (!validadorDeCedula()) {
				JSFUtil.createMensajeError("No es la cedula correcta");
			};
		System.out.println("laaaaaaaaaaaaaa cedulavale :"+validadorDeCedula());
		BeanEnvio envio= new BeanEnvio();
//		envio.enviarCorreo("ajvallejosm@utn.edu.ec");
			loginDTO = managerSeguridad.ValidaUsuarioA(cedula, clave);
			
				
//			System.out.println(""+loginDTO.getRutaAcceso()+"?faces-redirect=true");
			// redireccion dependiendo del tipo de usuario:
			return loginDTO.getRutaAcceso() + "?faces-redirect=true";
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.createMensajeError(e.getMessage());
		}
		return "";
	}

	/// USUARIO NORMAL
//	public String actionListenerUsuarioNor() throws Exception {
//		if (managerUsuario.ValidaUsuario(cedula, " n")) {
//			System.out.println("si vale aqui hay que poner el codigo que creer nuevo turno");
//			return "/usuario/inicio.xhtml";
//
//		} else {
//
//			System.out.println("ud no es un usuario registrado");
//		}
//		return "va el crud de escojer tunro.xhtml";
//
//	}

	/// USUARIO OTRO
//	public void actionListenerUsuario() throws Exception {
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//		String requestPath=ec.getRequestPathInfo();
//		
//		try {
//			//si no paso por login:
//			if(loginDTO==null || ModelUtil.isEmpty(loginDTO.getRutaAcceso())){
//				ec.redirect(ec.getRequestContextPath() + "/index.html");
//			}else{
//				//validar las rutas de acceso:
//				if(requestPath.contains("/administrativo") && loginDTO.getRutaAcceso().startsWith("/administrativo"))
//					return;
//				if(requestPath.contains("/vendedor") && loginDTO.getRutaAcceso().startsWith("/vendedor"))
//					return;
//				//caso contrario significa que hizo login pero intenta acceder a ruta no permitida:
//				ec.redirect(ec.getRequestContextPath() + "/index.html");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		if (managerUsuario.ValidaUsuario(cedula, clave)) {
//			System.out.println("sivale");
//			System.out.println("aaaaaaaaaaaaaaaaaaaaa" + managerUsuario.getIdusuariobuscado());
//
//			System.out.println("si vale aqui hay que poner la vista de "
//					+ managerUsuario.TipoUsuario(managerUsuario.getIdusuariobuscado()));
//			//////// editar por 2 para admin
//			if (managerUsuario.TipoUsuario(managerUsuario.getIdusuariobuscado()).equals("Administrativo")) {
//				System.out.println("entra 22222");
//				ec.redirect("faces/administrativo/indexPrincipal.xhtml");
//				
//			}
//			if (managerUsuario.TipoUsuario(managerUsuario.getIdusuariobuscado()).equals("Especilista")) {
//				
//				ec.redirect("faces/personal/inicio.xhtml");
//				
//			}
//		
//		} else {
//
//			System.out.println("ud no es un usuario registrado");
//		}
//		
//
//	}

	/**
	 * Finaliza la sesion web del usuario.
	 * 
	 * @return
	 */
	public String salirSistema() {
		System.out.println("salirSistema");
		try {
			managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(), this.getClass(), "salisSistema", "Cerrar sesion");
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	public void salirSistemaN() {

		clave = "n";
	}

	public void actionVerificarLogin() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath = ec.getRequestPathInfo();
		try {
			// si no paso por login:
			if (loginDTO == null || ModelUtil.isEmpty(loginDTO.getRutaAcceso())) {
				ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
			} else {
				// validar las rutas de acceso:
				if (requestPath.contains("/administrativo") && loginDTO.getRutaAcceso().startsWith("/administrativo"))
					return;
				if (requestPath.contains("/usuario") && loginDTO.getRutaAcceso().startsWith("/usuario"))
					return;
				// caso contrario significa que hizo login pero intenta acceder a ruta no
				// permitida:
				ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
			}
		} catch (IOException e) {
			ec.redirect(ec.getRequestContextPath() + "/colorlib-error-404-10/colorlib-error-404-10/index.html");
			e.printStackTrace();
		}
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

}
