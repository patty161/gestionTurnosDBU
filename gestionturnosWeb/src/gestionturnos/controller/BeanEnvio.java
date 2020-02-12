package gestionturnos.controller;

import java.util.Properties;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import gestionturnos.model.manager.ManagerSeguridad;
import gestionturnos.model.manager.ManagerUsuario;

import java.io.Serializable;

@Named
@SessionScoped
public class BeanEnvio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerUsuario managerUsuario;
	@EJB
	private ManagerSeguridad managerSeguridad;
	private String consenaNu;
	private String correo;
	private String codigo;
	

	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void beanEnvioguardarcontraseNue() {
		try {
			managerSeguridad.BusquedaActualizaContra(correo, consenaNu,codigo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean enviarCorreoDes() {
		String codigoenvia=(""+(int)Math.floor(Math.random()*(999999999-900000000+1)+(900000000)));
		managerSeguridad.setCodigo(codigoenvia);
//		System.out.println("correo" + correo);
		System.out.println("codigo    "+codigoenvia );
		String mensaje1 = "Ha solicitado recuperar su contraseña ingrese al siguiente link: http://localhost:8080/gestionturnosWeb/faces/administrativo/RecuperaCont2.xhtml";
				//				+ "de verificacion: " + (codigoenvia);
		try {
			Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.setProperty("mail.smtp.starttls.enable", "true");
			p.setProperty("mail.smtp.port", "587");
			p.setProperty("mail.smtp.user", "bienatsarutnturnos@gmail.com");
			p.setProperty("mail.smtp.auth", "true");

			Session s = Session.getDefaultInstance(p, null);
			BodyPart texto = new MimeBodyPart();
			texto.setText(mensaje1);

			MimeMultipart m = new MimeMultipart();

			m.addBodyPart(texto);
			MimeMessage mensaje = new MimeMessage(s);
			mensaje.setFrom(new InternetAddress("bienatsarutnturnos@gmail.com"));
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
			mensaje.setSubject("Recuperacion de contraseñas");
			mensaje.setContent(m);

			Transport t = s.getTransport("smtp");
			t.connect("bienatsarutnturnos@gmail.com", "12345678bU");
			t.sendMessage(mensaje, mensaje.getAllRecipients());
			t.close();
			System.out.println("correo enviado............................");
			JSFUtil.createMensajeInfo("Se envio un mensaje a su correo");
			return true;

		} catch (Exception e) {
			JSFUtil.createMensajeInfo("Correo no enviado");
			return false;
		}

	}

	public String getConsenaNu() {
		return consenaNu;
	}

	public void setConsenaNu(String consenaNu) {
		this.consenaNu = consenaNu;
	}

}
