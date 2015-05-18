package sample.ui.Controllers;

import org.apache.commons.validator.routines.EmailValidator;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmailSenderController {

    private static final Logger logger = Logger.getLogger(EmailSenderController.class.getName());

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    static MimeBodyPart mailMessagePart;
    static MimeMultipart mimeMultipart;


    public static void generateAndSendEmail(String email, File file) {

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        try {
            mimeMultipart = new MimeMultipart();
            mailMessagePart = new MimeBodyPart();
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            generateMailMessage.setSubject("Your SafeSafe delivery");
            String emailBodyHtml = "Attached is your file you've uploaded @ SafeSafe. <br><br> Regards, <br>SafeSafe team";

            MimeMultipart contentPart = new MimeMultipart("mixed");

            MimeMultipart bodyPart = new MimeMultipart("alternative");
            BodyPart bodyHtml = new MimeBodyPart();
            bodyHtml.setContent(emailBodyHtml, "text/html");
            bodyPart.addBodyPart(bodyHtml);

            MimeBodyPart bodyContent = new MimeBodyPart();
            bodyContent.setContent(bodyPart);

            contentPart.addBodyPart(bodyContent);

            try {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(file);
                contentPart.addBodyPart(attachmentPart);
                logger.log(Level.FINER, "added File named: " + file.getName());
            } catch (IOException e) {
                logger.severe("Could not attach file to email!" +
                        "; ExceptionMessage: " + e.getMessage());
            }

            generateMailMessage.setContent(contentPart);
            logger.log(Level.FINER, "Mail Session has been created successfully. Sending to: " + generateMailMessage.getRecipients(Message.RecipientType.TO).toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Transport transport = null;
        try {
            transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", "safesafe.app@gmail.com", "safesafe");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            logger.log(Level.FINER, "Message has been successfully sent.");
            transport.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public static boolean isEmailValid(String email){
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(emailValidator.isValid(email)){
            return true;
        }
        return false;
    }

}
