package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aud_bitacora database table.
 * 
 */
@Entity
@Table(name="aud_bitacora")
@NamedQuery(name="Bitacora.findAll", query="SELECT b FROM Bitacora b")
public class Bitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUD_BITACORA_CODEVENTO_GENERATOR", sequenceName="AUD_BITACORA_COD_EVENTO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUD_BITACORA_CODEVENTO_GENERATOR")
	@Column(name="cod_evento", unique=true, nullable=false)
	private Integer codEvento;

	@Column(length=100)
	private String descripcion;

	@Column(name="direccion_ip", length=20)
	private String direccionIp;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_evento")
	private Date fechaEvento;

	@Column(length=50)
	private String metodo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Bitacora() {
	}

	public Integer getCodEvento() {
		return this.codEvento;
	}

	public void setCodEvento(Integer codEvento) {
		this.codEvento = codEvento;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccionIp() {
		return this.direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public Date getFechaEvento() {
		return this.fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getMetodo() {
		return this.metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}