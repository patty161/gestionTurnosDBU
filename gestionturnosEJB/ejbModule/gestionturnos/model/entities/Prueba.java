package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the prueba database table.
 * 
 */
@Entity
@Table(name="prueba")
@NamedQuery(name="Prueba.findAll", query="SELECT p FROM Prueba p")
public class Prueba implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=50)
	private String descripcion;

	private Timestamp fecha;

	private Integer numero;

	private double pago;

	public Prueba() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public double getPago() {
		return this.pago;
	}

	public void setPago(double pago) {
		this.pago = pago;
	}

}