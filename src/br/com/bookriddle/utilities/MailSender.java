/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bookriddle.utilities;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Marcos Vinícius Brás de Oliveira
 */
public class MailSender {
    
    public MailSender() {}

    public static void sendMail(String destinatario, String assunto, String mensagem) throws AddressException, MessagingException {
        final String mailServer = "smtp.gmail.com";
        final String remetente = "bookriddleproject@gmail.com";
        final String username = "bookriddleproject";
        final String senha = "lukeeusouseupai";
     
        Properties props = System.getProperties();
        
        props.put("mail.smtp.host", mailServer);  // smtp.live.com  smtp.yahoo.com.br smtp.gmail.com
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.mime.charset", "ISO-8859-1");
        props.put("mail.smtp.port", "465");    //25
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getDefaultInstance(props);//recebe props   
        InternetAddress dest = new InternetAddress(destinatario);
        InternetAddress remete = new InternetAddress(remetente);
        Message msg = new MimeMessage(session);
        
        msg.setSentDate(new Date());//novo   
        msg.setFrom(remete);
        msg.setRecipient(Message.RecipientType.TO, dest);
        msg.setSubject(assunto);
        msg.setContent(mensagem, "text/HTML");
        
        Transport transport = session.getTransport("smtp");
        transport.connect(mailServer, username, senha);
        msg.saveChanges();
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}
