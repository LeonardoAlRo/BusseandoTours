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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "venta")
public class Venta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdVentas;
	
	
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate FechaVenta;
	
	@Column
	private Integer total;
	
	@Column
	private String TipoComprobante;
	
	@Column
	private String correo;
	
	@Column
	private Boolean documentoGenerado;
	
	@OneToOne(mappedBy = "venta")
    private Comprobante comprobante;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_venta",
               joinColumns = @JoinColumn(name = "id_ventas"),
               inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Usuario> usuario = new ArrayList<>();
	
	
	@OneToOne
    @JoinColumn(name = "idReserva")
    private Reserva reserva;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ventas")
    private List<Usuario> usuarios = new ArrayList<>();

	public Venta() {
		super();
	}


	public Venta(Integer idVentas, LocalDate fechaVenta, Integer total, String tipoComprobante,
			List<Usuario> usuario, Reserva reserva, List<Usuario> usuarios) {
		super();
		IdVentas = idVentas;
		FechaVenta = fechaVenta;
		this.total = total;
		TipoComprobante = tipoComprobante;
		this.usuario = usuario;
		this.reserva = reserva;
		this.usuarios = usuarios;
	}


	public Venta(LocalDate fechaVenta, Integer total, String tipoComprobante, List<Usuario> usuario,
			Reserva reserva, List<Usuario> usuarios) {
		super();
		FechaVenta = fechaVenta;
		this.total = total;
		TipoComprobante = tipoComprobante;
		this.usuario = usuario;
		this.reserva = reserva;
		this.usuarios = usuarios;
	}
	
	

	public Venta(LocalDate fechaVenta, Integer total, String tipoComprobante, Boolean documentoGenerado,
			Comprobante comprobante, List<Usuario> usuario, Reserva reserva, List<Usuario> usuarios) {
		super();
		FechaVenta = fechaVenta;
		this.total = total;
		TipoComprobante = tipoComprobante;
		this.documentoGenerado = documentoGenerado;
		this.comprobante = comprobante;
		this.usuario = usuario;
		this.reserva = reserva;
		this.usuarios = usuarios;
	}

	

	public Venta(LocalDate fechaVenta, Integer total, String tipoComprobante, String correo, Boolean documentoGenerado,
			Comprobante comprobante, List<Usuario> usuario, Reserva reserva, List<Usuario> usuarios) {
		super();
		FechaVenta = fechaVenta;
		this.total = total;
		TipoComprobante = tipoComprobante;
		this.correo = correo;
		this.documentoGenerado = documentoGenerado;
		this.comprobante = comprobante;
		this.usuario = usuario;
		this.reserva = reserva;
		this.usuarios = usuarios;
	}


	public Integer getIdVentas() {
		return IdVentas;
	}

	public void setIdVentas(Integer idVentas) {
		IdVentas = idVentas;
	}
	
	public LocalDate getFechaVenta() {
		return FechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		FechaVenta = fechaVenta;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getTipoComprobante() {
		return TipoComprobante;
	}


	public void setTipoComprobante(String tipoComprobante) {
		TipoComprobante = tipoComprobante;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}


	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}




	public Boolean getDocumentoGenerado() {
		return documentoGenerado;
	}


	public void setDocumentoGenerado(Boolean documentoGenerado) {
		this.documentoGenerado = documentoGenerado;
	}


	public Comprobante getComprobante() {
		return comprobante;
	}


	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
}
