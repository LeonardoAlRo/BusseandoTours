package idat.edu.pe.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import idat.edu.pe.modelo.Venta;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;

public class VentaExporterPDF {

	private List<Venta> listaVentas;

	public VentaExporterPDF(List<Venta> listaVentas) {
		super();
		this.listaVentas = listaVentas;
	}

	// Crea la Cabecera de la tabla
	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.gray);
		celda.setPadding(4);

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Fecha de Emisión", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Tipo de Comprobante", fuente));
		tabla.addCell(celda);


		celda.setPhrase(new Phrase("Destino Paquete", fuente));
		tabla.addCell(celda);
		

	}

	// Trae los datos de la base de datos
	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Venta venta : listaVentas) {
			tabla.addCell(String.valueOf(venta.getIdVentas()));
			tabla.addCell(String.valueOf(venta.getFechaVenta().toString()));
			tabla.addCell(venta.getTipoComprobante());
			tabla.addCell(String.valueOf(venta.getReserva().getPaquete().getLugarDestino()));
			
			
		}
	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.gray);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("REGISTRO DE VENTAS", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(4);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 2f, 3f, 3f, 3f});
		tabla.setWidthPercentage(110);

		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);
		documento.close();
	}

}
