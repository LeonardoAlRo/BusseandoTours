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
@Table(name = "comprobante")
public class Comprobante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdComprobante;
	
	
	@Column
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate FechaEmision;
	
	@Column
	private Integer total;
	
	@Column
	private String TipoComprobante;
	
	@Column
	private String TipoDocumento;
	
	@Column
	private Integer NroDocumento;
	
	
	@OneToOne
    @JoinColumn(name = "idVenta")
    private Venta venta;
	
	
	public Comprobante() {
		super();
	}

	

	public Comprobante(Integer idComprobante, LocalDate fechaEmision, Integer total, String tipoComprobante,
			Venta venta) {
		super();
		IdComprobante = idComprobante;
		FechaEmision = fechaEmision;
		this.total = total;
		TipoComprobante = tipoComprobante;
		this.venta = venta;
	}

	

	public Comprobante(LocalDate fechaEmision, Integer total, String tipoComprobante, Venta venta) {
		super();
		FechaEmision = fechaEmision;
		this.total = total;
		TipoComprobante = tipoComprobante;
		this.venta = venta;
	}

	

	public Comprobante(LocalDate fechaEmision, Integer total, String tipoComprobante, String tipoDocumento,
			Integer nroDocumento, Venta venta) {
		super();
		FechaEmision = fechaEmision;
		this.total = total;
		TipoComprobante = tipoComprobante;
		TipoDocumento = tipoDocumento;
		NroDocumento = nroDocumento;
		this.venta = venta;
	}



	public Integer getIdComprobante() {
		return IdComprobante;
	}


	public void setIdComprobante(Integer idComprobante) {
		IdComprobante = idComprobante;
	}


	public LocalDate getFechaEmision() {
		return FechaEmision;
	}


	public void setFechaEmision(LocalDate fechaEmision) {
		FechaEmision = fechaEmision;
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


	public Venta getVenta() {
		return venta;
	}


	public void setVenta(Venta venta) {
		this.venta = venta;
	}



	public String getTipoDocumento() {
		return TipoDocumento;
	}



	public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}



	public Integer getNroDocumento() {
		return NroDocumento;
	}



	public void setNroDocumento(Integer nroDocumento) {
		NroDocumento = nroDocumento;
	}


	


}
