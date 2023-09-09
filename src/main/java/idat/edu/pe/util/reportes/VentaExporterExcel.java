package idat.edu.pe.util.reportes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import idat.edu.pe.modelo.Venta;
import idat.edu.pe.modelo.Paquete;
import idat.edu.pe.modelo.Pasajero;

public class VentaExporterExcel {

	private XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Venta> listaVentas;


	public VentaExporterExcel(List<Venta> listaVentas) {
		this.listaVentas = listaVentas;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Ventas");
	}

	
		private void escribirCabeceraDeTabla() {
			Row fila = hoja.createRow(0);
			
			CellStyle estilo = libro.createCellStyle();
			XSSFFont fuente = libro.createFont();
			fuente.setBold(true);
			fuente.setFontHeight(16);
			estilo.setFont(fuente);
			
			Cell celda = fila.createCell(0);
			celda.setCellValue("ID");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(1);
			celda.setCellValue("Fecha de Emisión");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(2);
			celda.setCellValue("Tipo de Comprobante");
			celda.setCellStyle(estilo);
		
			celda = fila.createCell(3);
			celda.setCellValue("Paquete Turistico");
			celda.setCellStyle(estilo);
			
			celda = fila.createCell(4);
			celda.setCellValue("Total");
			celda.setCellStyle(estilo);
			
			
		}
		
		private void escribirDatosDeLaTabla() {
			int numeroFilas = 1;
			
			CellStyle estilo = libro.createCellStyle();
			XSSFFont fuente = libro.createFont();
			fuente.setFontHeight(14);
			estilo.setFont(fuente);
			
			for(Venta venta : listaVentas){
				Row fila = hoja.createRow(numeroFilas ++);
				
				Cell celda = fila.createCell(0);
				celda.setCellValue(venta.getIdVentas());
				hoja.autoSizeColumn(0);
				celda.setCellStyle(estilo);
				
				celda = fila.createCell(1);
				celda.setCellValue(venta.getFechaVenta().toString());
				hoja.autoSizeColumn(1);
				celda.setCellStyle(estilo);
				
				
				celda = fila.createCell(2);
				celda.setCellValue(venta.getTipoComprobante());
				hoja.autoSizeColumn(2);
				celda.setCellStyle(estilo);
				
				
				
				celda = fila.createCell(3);
				celda.setCellValue(venta.getReserva().getPaquete().getLugarDestino());;
				hoja.autoSizeColumn(3);
				celda.setCellStyle(estilo);
				
				celda = fila.createCell(4);
				celda.setCellValue(venta.getTotal());;
				hoja.autoSizeColumn(4);
				celda.setCellStyle(estilo);
		        
		        
				
				
				
			}
		}
		public void exportar(HttpServletResponse response) throws IOException {
		    escribirCabeceraDeTabla();
		    escribirDatosDeLaTabla();

		    ServletOutputStream outPutStream = response.getOutputStream();
		    libro.write(outPutStream);

		    outPutStream.close();
		}
}
