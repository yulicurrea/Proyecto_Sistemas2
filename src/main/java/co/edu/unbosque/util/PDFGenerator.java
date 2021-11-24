package co.edu.unbosque.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import co.edu.unbosque.model.PresupuestoVis;

public class PDFGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	
	public static ByteArrayInputStream customerPDFReport(List<PresupuestoVis> presupuesto) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Customer Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(3);
        	// Add PDF Table Header ->
			Stream.of("ID", "First Name", "Last Name")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (PresupuestoVis pre : presupuesto) {
            	PdfPCell concepto = new PdfPCell(new Phrase(pre.getConcepto()));
            	concepto.setPaddingLeft(4);
            	concepto.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	concepto.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(concepto);

                PdfPCell anio = new PdfPCell(new Phrase(pre.getAnio()));
                anio.setPaddingLeft(4);
                anio.setVerticalAlignment(Element.ALIGN_MIDDLE);
                anio.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(anio);

                PdfPCell ppto_asignado = new PdfPCell(new Phrase(String.valueOf(pre.getPpto_asignado())));
                ppto_asignado.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ppto_asignado.setHorizontalAlignment(Element.ALIGN_RIGHT);
                ppto_asignado.setPaddingRight(4);
                table.addCell(ppto_asignado);
                
                PdfPCell porce_ppto_alcanzado = new PdfPCell(new Phrase(String.valueOf(pre.getPorce_ppto_alcanzado())));
                porce_ppto_alcanzado.setVerticalAlignment(Element.ALIGN_MIDDLE);
                porce_ppto_alcanzado.setHorizontalAlignment(Element.ALIGN_RIGHT);
                porce_ppto_alcanzado.setPaddingRight(4);
                table.addCell(porce_ppto_alcanzado);
                
                PdfPCell ppto_alcanzado = new PdfPCell(new Phrase(String.valueOf(pre.getPpto_alcanzado())));
                ppto_alcanzado.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ppto_alcanzado.setHorizontalAlignment(Element.ALIGN_RIGHT);
                ppto_alcanzado.setPaddingRight(4);
                table.addCell(ppto_alcanzado);
                
                PdfPCell ppto_restante = new PdfPCell(new Phrase(String.valueOf(pre.getPpto_restante())));
                ppto_restante.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ppto_restante.setHorizontalAlignment(Element.ALIGN_RIGHT);
                ppto_restante.setPaddingRight(4);
                table.addCell(ppto_restante);
                
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}