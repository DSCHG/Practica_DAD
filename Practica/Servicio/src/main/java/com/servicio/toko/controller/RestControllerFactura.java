package com.servicio.toko.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.servicio.toko.model.*;
import com.servicio.toko.repositorios.*;
import com.servicio.toko.servicio.EnviarMail;



@RestController
@RequestMapping("/enviarFactura")
public class RestControllerFactura {
	
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired 
	private UserRepository userRepo;
	@Autowired
	private EnviarMail envio;
	
	@GetMapping("/{id_p}/{id_u}")
	public void generarFactura(@PathVariable int id_u,@PathVariable int id_p) {
		System.out.println(id_p+" hola "+id_u);
		Optional<Usuario> u = userRepo.findById(id_u);
		Usuario user = u.get();
		Optional<Pedido> p = pedidoRepo.findById(id_p);
		Pedido pedido = p.get();
		getPDF(pedido.getProducto());
		System.out.println("ENVIANDO MAIL A "+user.getNombre()+" ...");
		envio.sendMail("darwinstalinchumapi@gmail.com", "Prueba de envio de correo", "Esto es una prueba de envio de mensajes");
		
	}
	
	private void getPDF(List<Producto> listadeproductosdelpedido) {
		PDDocument documentPDF = new PDDocument(); // creamos el documento pdf vacio
		PDPage pagina = new PDPage(PDRectangle.A4); // creamos una pagina del tamaño de un A4
		documentPDF.addPage(pagina);
		
		try {
			PDPageContentStream contenidoStream = new PDPageContentStream(documentPDF,pagina);
			
			// generacion de un titulo para el pdf
			
			contenidoStream.beginText(); // nos posicionamos al comienzo del documento
			contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 32); 
			contenidoStream.newLineAtOffset(25, 800);
			contenidoStream.showText("Factura ");
			contenidoStream.endText();
			
			// una linea divisoria
			contenidoStream.moveTo(1, 790);
			contenidoStream.lineTo(360, 790);
			
			// insercion de los titulos de la tabla
			contenidoStream.beginText(); // nos posicionamos al comienzo del documento
			contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 12); 
			contenidoStream.newLineAtOffset(25, 770);
			contenidoStream.showText("Producto ");
			contenidoStream.endText();
			contenidoStream.beginText(); // nos posicionamos al comienzo del documento
			contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 12); 
			contenidoStream.newLineAtOffset(150, 770);
			contenidoStream.showText("Cantidad ");
			contenidoStream.endText();
			contenidoStream.beginText(); // nos posicionamos al comienzo del documento
			contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 12); 
			contenidoStream.newLineAtOffset(275, 770);
			contenidoStream.showText("Precio ");
			contenidoStream.endText();
			
			// relleno de filas con los datos de la DB
			
			
			// insercion de una linea vertical
			contenidoStream.moveTo(1, 790);
			contenidoStream.lineTo(1, 0);
			contenidoStream.moveTo(120, 790);
			contenidoStream.lineTo(120, 0);
			contenidoStream.moveTo(240, 790);
			contenidoStream.lineTo(240, 0);
			contenidoStream.moveTo(360, 790);
			contenidoStream.lineTo(360, 0);
			
			// insercion de una linea horizontal
			contenidoStream.moveTo(1, 760);
			contenidoStream.lineTo(360, 760);
			int ultimalineescrita = 770;
			double preciototal = 0;
			for(Producto p : listadeproductosdelpedido) {				
				ultimalineescrita-=20;
				contenidoStream.beginText(); 
				contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 12); 
				contenidoStream.newLineAtOffset(25, ultimalineescrita);
				contenidoStream.showText(p.getNombre());
				contenidoStream.endText();
				contenidoStream.beginText(); 
				contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 12); 
				contenidoStream.newLineAtOffset(150, ultimalineescrita);
				contenidoStream.showText("1");
				contenidoStream.endText();
				contenidoStream.beginText(); 
				contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 12); 
				contenidoStream.newLineAtOffset(275, ultimalineescrita);
				contenidoStream.showText(String.valueOf(p.getPrecio()));
				contenidoStream.endText();
				contenidoStream.moveTo(1, ultimalineescrita-10);
				contenidoStream.lineTo(360, ultimalineescrita-10);
				preciototal+=p.getPrecio();
			}
			
			// colocar texto final del total del pedido
			contenidoStream.beginText(); 
			contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 12); 
			contenidoStream.newLineAtOffset(150, ultimalineescrita-20);
			contenidoStream.showText(String.valueOf(preciototal));
			contenidoStream.endText();
			
			contenidoStream.beginText(); 
			contenidoStream.setFont(PDType1Font.TIMES_ROMAN, 12); 
			contenidoStream.newLineAtOffset(275, ultimalineescrita-20);
			contenidoStream.showText(String.valueOf(preciototal));
			contenidoStream.endText();
			
			
			contenidoStream.stroke();			
			contenidoStream.close();
			documentPDF.save(new File("doc.pdf"));
			documentPDF.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
