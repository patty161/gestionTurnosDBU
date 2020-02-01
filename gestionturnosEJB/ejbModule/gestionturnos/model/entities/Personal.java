package gestionturnos.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the esp_personal database table.
 * 
 */
@Entity
@Table(name="esp_personal")
@NamedQuery(name="Personal.findAll", query="SELECT p FROM Personal p")
public class Personal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_personal", unique=true, nullable=false)
	private Integer idPersonal;

	@Column(name="horas_laborables")
	private Integer horasLaborables;

	//bi-directional many-to-one association to Area
	@ManyToOne
	@JoinColumn(name="id_area")
	private Area espArea;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Turno
	@OneToMany(mappedBy="espPersonal")
	private List<Turno> turTurnos;

	public Personal() {
	}

	public Integer getIdPersonal() {
		return this.idPersonal;
	}

	public void setIdPersonal(Integer idPersonal) {
		this.idPersonal = idPersonal;
	}

	public Integer getHorasLaborables() {
		return this.horasLaborables;
	}

	public void setHorasLaborables(Integer horasLaborables) {
		this.horasLaborables = horasLaborables;
	}

	public Area getEspArea() {
		return this.espArea;
	}

	public void setEspArea(Area espArea) {
		this.espArea = espArea;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Turno> getTurTurnos() {
		return this.turTurnos;
	}

	public void setTurTurnos(List<Turno> turTurnos) {
		this.turTurnos = turTurnos;
	}

	public Turno addTurTurno(Turno turTurno) {
		getTurTurnos().add(turTurno);
		turTurno.setEspPersonal(this);

		return turTurno;
	}

	public Turno removeTurTurno(Turno turTurno) {
		getTurTurnos().remove(turTurno);
		turTurno.setEspPersonal(null);

		return turTurno;
	}

}