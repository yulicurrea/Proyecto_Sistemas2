package co.edu.unbosque.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;

import co.edu.unbosque.model.Presupuesto;
import co.edu.unbosque.model.PresupuestoVis;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.dto.PresupuestoDTO;
import co.edu.unbosque.util.itext.CustomFooter;

public class PDFGenerator {

	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	public static ByteArrayInputStream customerPDFReport(PresupuestoDTO ingresos, PresupuestoDTO egresos,
			Usuario usuario) {

		// Document document = new Document(new Rectangle(1024,200),0,0,0,0);
		Document document = new Document();

		// document.setMargins(0, 0, 0, 0);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new CustomFooter(usuario.getNombre() + " " + usuario.getApellido()));

			document.open();

			addHeader(document, writer);
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD);
			Paragraph para = new Paragraph("Plantilla Presupuesto", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);

			document.add(new Paragraph("Ingresos", font));
			document.add(new Paragraph("\n"));
			document.add(addTable(ingresos));

			document.add(Chunk.NEWLINE);

			document.add(new Paragraph("Egresos", font));
			document.add(new Paragraph("\n"));
			PdfPTable tableEgresos = addTable(egresos);

			
	
			PdfPCell total = footerCell("TOTAL INGRESOS - EGRESOS");
			total.setColspan(2);
			total.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableEgresos.addCell(total);

			double totalpresupuestoAsignado = ingresos.getTotalPresupuesto() - egresos.getTotalPresupuesto();
			double presupuestoAlcanzado = ingresos.getTotalEjecutado() - egresos.getTotalEjecutado();
			double porcentaje = presupuestoAlcanzado / totalpresupuestoAsignado * 100;

			tableEgresos.addCell(footerCell(formatValue(totalpresupuestoAsignado, "$%,.0f")));
			tableEgresos.addCell(footerCell(formatValue(porcentaje)));
			tableEgresos.addCell(footerCell(formatValue(presupuestoAlcanzado, "$%,.0f")));
			tableEgresos.addCell(
					footerCell(formatValue(ingresos.getTotalFaltante() - egresos.getTotalFaltante(), "$%,.0f")));
			document.add(tableEgresos);

			document.close();
		} catch (DocumentException e) {
			logger.error(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	private static PdfPTable addTable(PresupuestoDTO presupuestoDTO) {
		float[] columnsWidth = { 6, 2, 5, 5, 5, 5 };
		PdfPTable table = new PdfPTable(columnsWidth);
		table.setWidthPercentage(100);
		// Add PDF Table Header ->
		Stream.of("Concepto", "Año", "Presupuesto Asignado", "%Presupuesto Alcanzado", "Presupuesto Alcanzado",
				"Presupuesto restante").forEach(headerTitle -> {
					PdfPCell header = new PdfPCell();
					Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setHorizontalAlignment(Element.ALIGN_CENTER);
					header.setBorderWidth(2);
					header.setPhrase(new Phrase(headerTitle, headFont));
					table.addCell(header);
				});

		for (PresupuestoVis pre : presupuestoDTO.getDatos()) {
			PdfPCell concepto = new PdfPCell(new Phrase(pre.getConcepto()));
			concepto.setPaddingLeft(4);
			concepto.setVerticalAlignment(Element.ALIGN_MIDDLE);
			concepto.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(concepto);

			PdfPCell anio = new PdfPCell(new Phrase(String.valueOf(pre.getAnio())));
			anio.setPaddingLeft(4);
			anio.setVerticalAlignment(Element.ALIGN_MIDDLE);
			anio.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(anio);

			PdfPCell ppto_asignado = new PdfPCell(new Phrase(formatValue(pre.getPpto_asignado(), "$%,.0f")));
			ppto_asignado.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ppto_asignado.setHorizontalAlignment(Element.ALIGN_RIGHT);
			ppto_asignado.setPaddingRight(4);
			table.addCell(ppto_asignado);

			PdfPCell porce_ppto_alcanzado = new PdfPCell(new Phrase(formatValue(pre.getPorce_ppto_alcanzado())));
			porce_ppto_alcanzado.setVerticalAlignment(Element.ALIGN_MIDDLE);
			porce_ppto_alcanzado.setHorizontalAlignment(Element.ALIGN_RIGHT);
			porce_ppto_alcanzado.setPaddingRight(4);
			table.addCell(porce_ppto_alcanzado);

			PdfPCell ppto_alcanzado = new PdfPCell(new Phrase(formatValue(pre.getPpto_alcanzado(), "$%,.0f")));
			ppto_alcanzado.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ppto_alcanzado.setHorizontalAlignment(Element.ALIGN_RIGHT);
			ppto_alcanzado.setPaddingRight(4);
			table.addCell(ppto_alcanzado);

			PdfPCell ppto_restante = new PdfPCell(new Phrase(formatValue(pre.getPpto_restante(), "$%,.0f")));
			ppto_restante.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ppto_restante.setHorizontalAlignment(Element.ALIGN_RIGHT);
			ppto_restante.setPaddingRight(4);
			table.addCell(ppto_restante);

		}

		BaseColor fondo = WebColors.getRGBColor("#e6e6e6");

		PdfPCell concepto = new PdfPCell(new Phrase("TOTAL"));
		concepto.setColspan(2);
		concepto.setBackgroundColor(fondo);
		concepto.setPaddingLeft(4);
		concepto.setVerticalAlignment(Element.ALIGN_MIDDLE);
		concepto.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(concepto);

		PdfPCell ppto_asignado = new PdfPCell(new Phrase(formatValue(presupuestoDTO.getTotalPresupuesto(), "$%,.0f")));
		ppto_asignado.setBackgroundColor(fondo);
		ppto_asignado.setVerticalAlignment(Element.ALIGN_MIDDLE);
		ppto_asignado.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ppto_asignado.setPaddingRight(4);
		table.addCell(ppto_asignado);

		PdfPCell porce_ppto_alcanzado = new PdfPCell(
				new Phrase(formatValue(presupuestoDTO.getTotalPorcentajeEjecucion(), "%.2f")));
		porce_ppto_alcanzado.setBackgroundColor(fondo);
		porce_ppto_alcanzado.setVerticalAlignment(Element.ALIGN_MIDDLE);
		porce_ppto_alcanzado.setHorizontalAlignment(Element.ALIGN_RIGHT);
		porce_ppto_alcanzado.setPaddingRight(4);
		table.addCell(porce_ppto_alcanzado);

		PdfPCell ppto_alcanzado = new PdfPCell(new Phrase(formatValue(presupuestoDTO.getTotalEjecutado(), "$%,.0f")));
		ppto_alcanzado.setBackgroundColor(fondo);
		ppto_alcanzado.setVerticalAlignment(Element.ALIGN_MIDDLE);
		ppto_alcanzado.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ppto_alcanzado.setPaddingRight(4);
		table.addCell(ppto_alcanzado);

		PdfPCell ppto_restante = new PdfPCell(new Phrase(formatValue(presupuestoDTO.getTotalFaltante(), "$%,.0f")));
		ppto_restante.setBackgroundColor(fondo);
		ppto_restante.setVerticalAlignment(Element.ALIGN_MIDDLE);
		ppto_restante.setHorizontalAlignment(Element.ALIGN_RIGHT);
		ppto_restante.setPaddingRight(4);
		table.addCell(ppto_restante);

		return table;
	}

	private static String formatValue(double val) {
		return formatValue(val, "%.2f");
	}

	private static String formatValue(double val, String format) {

		return String.format(format, val);
	}

	private static void addHeader(Document document, PdfWriter writer) {
		// Agregar imagen
		try {
			Resource resource = new ClassPathResource("EPS.png");
			Image img = Image.getInstance(resource.getURL());
			PdfContentByte canvas = writer.getDirectContent();
			canvas.addImage(img, 250, 0, 0, 50, 0, 790);

			// document.add(img);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	private static PdfPCell footerCell(String value) {
		BaseColor fondo = WebColors.getRGBColor("#e6e6e6");
		PdfPCell cell = new PdfPCell(new Phrase(value));
		cell.setBackgroundColor(fondo);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPaddingRight(4);
		return cell;
	}

}