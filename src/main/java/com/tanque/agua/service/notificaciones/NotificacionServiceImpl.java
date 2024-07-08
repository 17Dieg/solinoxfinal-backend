package com.tanque.agua.service.notificaciones;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.tanque.agua.util.GmailManager;

import lombok.RequiredArgsConstructor;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import static javax.mail.Message.RecipientType.TO;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService{


    private final GmailManager gmailManager;

    @Override
    public void enviarMensajes() {
        

        
        try {
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress("gonzaloartadi@gmail.com"));
            email.addRecipient(TO, new InternetAddress("gonzaloartadi@gmail.com"));
            email.setSubject("test");
            email.setText("test");

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);

            Message msg = new Message();
            msg.setRaw(encodedEmail);

            Gmail service = gmailManager.getInstance();
            try {
                service.users().messages().send("me", msg).execute();
            } catch (GoogleJsonResponseException e) {
                GoogleJsonError error = e.getDetails();
                if (error.getCode() == 403) {
                    System.err.println("Unable to send message: " + e.getDetails());
                } else {
                    throw e;
                }
            }
        } catch (MessagingException | IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        
        
    }

    private void envioCorreos(Message msg) {
        Gmail service;
        try {
            service = gmailManager.getInstance();
            service.users().messages().send("me", msg).execute();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enviarCita(LocalDate fecha, String hora, String nombre, String correo) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        try {
            email.setFrom(new InternetAddress(correo));
            email.addRecipient(TO, new InternetAddress(correo));
            email.setSubject("Programación de cita");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedString = fecha.format(formatter);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String htmlText="<p>Hola "+nombre+"</p>"
            +"<br/>"
            +"<p>Se ha programado una cita para el día " +formattedString+ " a las "+hora;

            messageBodyPart.setContent(htmlText, "text/html");
            MimeMultipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(messageBodyPart);

            email.setContent(multipart);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message msg = new Message();
            msg.setRaw(encodedEmail);
            envioCorreos(msg);

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        } 
    }

    public void enviarCotizacion(String correo){
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        try {
            email.setFrom(new InternetAddress(correo));
            email.addRecipient(TO, new InternetAddress(correo));
            email.setSubject("Envio de cotización");
            email.setText("La cotización a sido enviada a su correo.");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message msg = new Message();
            msg.setRaw(encodedEmail);
            envioCorreos(msg);

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        } 
    }
    
    

}
