package com.servicio.toko.servicio;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EnviarMail {

	@Autowired
	private JavaMailSender mailsender;
	
	public void sendMail(String to,String subject,String content) {
		MimeMessage msg = mailsender.createMimeMessage();
		
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content);
			
			FileSystemResource file = new FileSystemResource("doc.pdf");
			helper.addAttachment(file.getFilename(), file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("Estas en la clase de envio de envio del correo");
		mailsender.send(msg);
		
		

	}
}
