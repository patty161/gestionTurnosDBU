package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the esp_area database table.
 * 
 */
@Entity
@Table(name="esp_area")
@NamedQuery(name="Area.findAll", query="SELECT a FROM Area a")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESP_AREA_IDAREA_GENERATOR", sequenceName="ESP_AREA_ID_AREA_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESP_AREA_IDAREA_GENERATOR")
	@Column(name="id_area", unique=true, nullable=false)
	private Integer idArea;

	@Column(name="cant_turnos")
	private Integer cantTurnos;

	@Column(name="cod_area", nullable=false, length=50)
	private String codArea;

	@Column(name="nombre_area", nullable=false, length=100)
	private String nombreArea;

	@Column(name="tiempo_aprox")
	private Time tiempoAprox;

	//bi-directional many-to-one association to Personal
	@OneToMany(mappedBy="espArea")
	private List<Personal> espPersonals;

	//bi-directional many-to-one association to Turno
	@OneToMany(mappedBy="espArea")
	private List<Turno> turTurnos;

	public Area() {
	}

	public Integer getIdArea() {
		return this.idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public Integer getCantTurnos() {
		return this.cantTurnos;
	}

	public void setCantTurnos(Integer cantTurnos) {
		this.cantTurnos = cantTurnos;
	}

	public String getCodArea() {
		return this.codArea;
	}

	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	public String getNombreArea() {
		return this.nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public Time getTiempoAprox() {
		return this.tiempoAprox;
	}

	public void setTiempoAprox(Time tiempoAprox) {
		this.tiempoAprox = tiempoAprox;
	}

	public List<Personal> getEspPersonals() {
		return this.espPersonals;
	}

	public void setEspPersonals(List<Personal> espPersonals) {
		this.espPersonals = espPersonals;
	}

	public Personal addEspPersonal(Personal espPersonal) {
		getEspPersonals().add(espPersonal);
		espPersonal.setEspArea(this);

		return espPersonal;
	}

	public Personal removeEspPersonal(Personal espPersonal) {
		getEspPersonals().remove(espPersonal);
		espPersonal.setEspArea(null);

		return espPersonal;
	}

	public List<Turno> getTurTurnos() {
		return this.turTurnos;
	}

	public void setTurTurnos(List<Turno> turTurnos) {
		this.turTurnos = turTurnos;
	}

	public Turno addTurTurno(Turno turTurno) {
		getTurTurnos().add(turTurno);
		turTurno.setEspArea(this);

		return turTurno;
	}

	public Turno removeTurTurno(Turno turTurno) {
		getTurTurnos().remove(turTurno);
		turTurno.setEspArea(null);

		return turTurno;
	}

}