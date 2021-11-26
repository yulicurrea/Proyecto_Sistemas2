package co.edu.unbosque.util.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import co.edu.unbosque.util.Utils;

public class CustomFooter extends PdfPageEventHelper{
	
	private String user;
		
	public CustomFooter(String user){
		this.user = user;		
	}

	 public void onEndPage(PdfWriter writer,Document document) {			
		 PdfContentByte cb = writer.getDirectContent();
		 Phrase footer = new Phrase( "Documento impreso el día y hora: " +  Utils.dateToPattern() + " por " + this.user );
		 
		 ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
				 footer,
				 (document.right() - document.left()) / 2 + document.leftMargin(),
				 document.bottom() -10,
				 0);
	    }
}
