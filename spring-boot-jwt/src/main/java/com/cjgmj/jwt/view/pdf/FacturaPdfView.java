package com.cjgmj.jwt.view.pdf;

import java.awt.Color;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.cjgmj.jwt.entity.Factura;
import com.cjgmj.jwt.entity.ItemFactura;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private LocaleResolver localeResolver;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		response.setHeader("Content-Disposition", "attachment; filename=\"factura_view.pdf\"");
		Factura factura = (Factura) model.get("factura");

		Locale locale = localeResolver.resolveLocale(request);

		// Forma más sencilla que a través de MessageSource
		MessageSourceAccessor mensajes = getMessageSourceAccessor();

		PdfPTable tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);

		Font font = new Font();
		font.setColor(0, 64, 133);
		PdfPCell cell = new PdfPCell(
				new Phrase(messageSource.getMessage("text.factura.ver.datos.cliente", null, locale), font));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);

		tabla.addCell(cell);
		tabla.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		tabla.addCell(factura.getCliente().getEmail());

		PdfPTable tabla2 = new PdfPTable(1);
		tabla2.setSpacingAfter(20);

		font = new Font();
		font.setColor(21, 87, 36);
		cell = new PdfPCell(new Phrase(messageSource.getMessage("text.factura.ver.datos.factura", null, locale), font));
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);

		tabla2.addCell(cell);
		tabla2.addCell(mensajes.getMessage("text.cliente.factura.factura") + ": " + factura.getId());
		tabla2.addCell(mensajes.getMessage("text.cliente.factura.descripcion") + ": " + factura.getDescripcion());
		tabla2.addCell(mensajes.getMessage("text.cliente.factura.fecha") + ": " + factura.getCreateAt());

		PdfPTable tabla3 = new PdfPTable(4);
		tabla3.setWidths(new float[] { 3.5f, 1f, 1f, 1f });

		font = new Font();
		font.setStyle(Font.BOLD);
		font.setSize(14);
		font.setFamily(FontFactory.HELVETICA);

		cell = new PdfPCell(new Phrase("Producto", font));
		tabla3.addCell(cell);

		cell = new PdfPCell(new Phrase("Precio", font));
		tabla3.addCell(cell);

		cell = new PdfPCell(new Phrase("Cantidad", font));
		tabla3.addCell(cell);

		cell = new PdfPCell(new Phrase("Total", font));
		tabla3.addCell(cell);

		for (ItemFactura item : factura.getItems()) {
			tabla3.addCell(item.getProducto().getNombre());
			tabla3.addCell(item.getProducto().getPrecio().toString());

			cell = new PdfPCell(new Phrase(item.getCantidad().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			tabla3.addCell(cell);
			tabla3.addCell(item.calcularImporte().toString());
		}

		cell = new PdfPCell(new Phrase("Total: "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tabla3.addCell(cell);
		tabla3.addCell(factura.getTotal().toString());

		document.add(tabla);
		document.add(tabla2);
		document.add(tabla3);
	}

}
