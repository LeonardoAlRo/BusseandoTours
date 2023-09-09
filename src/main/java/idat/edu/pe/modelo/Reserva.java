package idat.edu.pe.modelo;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "Reserva")
public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdReserva;

	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate FechaReserva;
	

	@Column
	private Integer nroPasajeros;
	
	
	@Column
	private String TipoDoc;
	
	@Column
	private String estado;
	
	
	@Column
	private Integer nroDoc;
	
	@Column
	private Integer nroRegistrados;

	
	
	@ManyToOne
	@JoinColumn(name = "id_paquete", nullable = true, unique = false, 
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_paquete) references Paquete(id_paquete)"))
	@JsonBackReference
    private Paquete paquete;
	
	
	
	@OneToOne(mappedBy = "reserva")
    private Venta venta;
	
	@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "usuario_reserva",
	               joinColumns = @JoinColumn(name = "id_reserva"),
	               inverseJoinColumns = @JoinColumn(name = "id"))
	    private List<Usuario> usuario = new ArrayList<>();
		
		
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "reservas")
	private List<Usuario> usuarios = new ArrayList<>();
		

	@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
	private List<Pasajero> pasajero = new ArrayList<>();
	
	
	public Reserva(LocalDate fechaReserva, Integer nroPasajeros) {
		super();
		FechaReserva = fechaReserva;
		this.nroPasajeros = nroPasajeros;
	}
	


	public Reserva(LocalDate fechaReserva, Integer nroPasajeros, String tipoDoc, Integer nroDoc, Integer nroRegistrados,
			Paquete paquete, Venta venta, List<Usuario> usuario, List<Usuario> usuarios,
			List<Pasajero> pasajero) {
		super();
		FechaReserva = fechaReserva;
		this.nroPasajeros = nroPasajeros;
		TipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.nroRegistrados = nroRegistrados;
		this.paquete = paquete;
		this.venta = venta;
		this.usuario = usuario;
		this.usuarios = usuarios;
		this.pasajero = pasajero;
	}


	public Reserva(Integer idReserva, LocalDate fechaReserva, Integer nroPasajeros, String tipoDoc, Integer nroDoc,
			Paquete paquete, Venta venta, List<Usuario> usuario) {
		super();
		IdReserva = idReserva;
		FechaReserva = fechaReserva;
		this.nroPasajeros = nroPasajeros;
		TipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.paquete = paquete;
		this.venta = venta;
		this.usuario = usuario;
	}

	
	public Reserva(LocalDate fechaReserva, Integer nroPasajeros, String tipoDoc, String estado, Integer nroDoc,
			Integer nroRegistrados, Paquete paquete, Venta venta, List<Usuario> usuario, List<Usuario> usuarios,
			List<Pasajero> pasajero) {
		super();
		FechaReserva = fechaReserva;
		this.nroPasajeros = nroPasajeros;
		TipoDoc = tipoDoc;
		this.estado = estado;
		this.nroDoc = nroDoc;
		this.nroRegistrados = nroRegistrados;
		this.paquete = paquete;
		this.venta = venta;
		this.usuario = usuario;
		this.usuarios = usuarios;
		this.pasajero = pasajero;
	}


	public Reserva() {
			super();
		}


	public Reserva(LocalDate fechaReserva, Integer nroPasajeros, Paquete paquete, Venta venta) {
		super();
		FechaReserva = fechaReserva;
		this.nroPasajeros = nroPasajeros;
		this.paquete = paquete;
		this.venta = venta;
	}

	

	public Reserva(LocalDate fechaReserva, Integer nroPasajeros, Paquete paquete) {
		super();
		FechaReserva = fechaReserva;
		this.nroPasajeros = nroPasajeros;
		this.paquete = paquete;
	}
	


	public Reserva(LocalDate fechaReserva, @NotEmpty Integer nroPasajeros, @NotEmpty String tipoDoc,
			@NotEmpty Integer nroDoc, Paquete paquete, Venta venta, List<Usuario> usuario,
			List<Usuario> usuarios) {
		super();
		FechaReserva = fechaReserva;
		this.nroPasajeros = nroPasajeros;
		TipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.paquete = paquete;
		this.venta = venta;
		this.usuario = usuario;
		this.usuarios = usuarios;
	}


	public Reserva(LocalDate fechaReserva, Integer nroPasajeros, String tipoDoc, Integer nroDoc, Integer nroRegistrados,
			Paquete paquete, Venta venta, List<Usuario> usuario) {
		super();
		FechaReserva = fechaReserva;
		this.nroPasajeros = nroPasajeros;
		TipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.nroRegistrados = nroRegistrados;
		this.paquete = paquete;
		this.venta = venta;
		this.usuario = usuario;
	}

	
	public List<Pasajero> getPasajero() {
		return pasajero;
	}


	public void setPasajero(List<Pasajero> pasajero) {
		this.pasajero = pasajero;
	}

	public Integer getIdReserva() {
		return IdReserva;
	}

	public void setIdReserva(Integer idReserva) {
		IdReserva = idReserva;
	}
	
	

	public LocalDate getFechaReserva() {
		return FechaReserva;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		FechaReserva = fechaReserva;
	}

	public Integer getNroPasajeros() {
		return nroPasajeros;
	}

	public void setNroPasajeros(Integer nroPasajeros) {
		this.nroPasajeros = nroPasajeros;
	}



	public Paquete getPaquete() {
		return paquete;
	}



	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}



	public Venta getVenta() {
		return venta;
	}



	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	
	
	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Integer getNroRegistrados() {
		return nroRegistrados;
	}


	public void setNroRegistrados(Integer nroRegistrados) {
		this.nroRegistrados = nroRegistrados;
	}


	public String getTipoDoc() {
		return TipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		TipoDoc = tipoDoc;
	}


	public Integer getNroDoc() {
		return nroDoc;
	}


	public void setNroDoc(Integer nroDoc) {
		this.nroDoc = nroDoc;
	}
	
	
	

}
