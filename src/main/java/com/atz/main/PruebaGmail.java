package com.atz.main;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class PruebaGmail 
{
	private static final String USERNAME	= "extindet.facturas@gmail.com";
//  private static final String PWD1 		= "3xt1nd3t";
	private static final String PWD2 		= "lydp pvzx gwzd zics";
	
	public static void main(String[] args) 
	throws MessagingException 
	{
		File f1 	= new File("C:\\Users\\jsilva\\Desktop\\f1.pdf");
		File f2 	= new File("C:\\Users\\jsilva\\Desktop\\f2.pdf");
		
		String to1 	= "smallsalta@gmail.com";
		String to2 	= "jose.antonio.silva.gonzalez@gmail.com";
		
		new PruebaGmail().sendMailTipoA
		( 
			new String[] { to1, to2 }, 
			new File[] { f1, f2 }
		);
		
		System.out.println("Done");
	}
	
	private void sendMailTipoA(String[] to, File[] f) 
	throws MessagingException
	{
		String header 	= "Testing Gmail SSL";
		String body		= "Carliqueño Mawakeño come mucho y no es pequeño";
		
		this.sendMail(to, header, body, f);
	}
	
	private void sendMail(String[] to, String header, String body, File[] f) 
	throws MessagingException
	{
			
		Properties prop = new Properties();
		
		prop.put( "mail.smtp.auth", true );
		prop.put( "mail.smtp.starttls.enable", true );
		prop.put( "mail.smtp.host", "smtp.gmail.com" );
		prop.put( "mail.smtp.port", "587" );
		prop.put( "mail.smtp.ssl.trust", "*" );
		
		Session session = Session.getInstance
							(
								prop,
								new javax.mail.Authenticator() 
								{
									protected PasswordAuthentication getPasswordAuthentication() 
									{
										return new PasswordAuthentication( PruebaGmail.USERNAME, PruebaGmail.PWD2 );
									}
								}
							);

		Message message 			= new MimeMessage(session);
		BodyPart messageBodyPart 	= new MimeBodyPart();
		Multipart multipart 		= new MimeMultipart();
		String comato				= String.join( ",", to );
		
		messageBodyPart.setText(body);
		
		multipart.addBodyPart(messageBodyPart);
		
		message.setFrom( new InternetAddress( PruebaGmail.USERNAME ) );
		message.setRecipients( Message.RecipientType.TO, InternetAddress.parse(comato) );
		message.setSubject(header);
		message.setContent(multipart);
		
		if( f != null )
		{
			for(File fp : f)
			{
				this.addAttachment(multipart, fp);
			}
		}
		
		Transport.send(message);
	}
	
	private void addAttachment(Multipart multipart, File filename) 
	throws MessagingException
	{
	    DataSource source			= new FileDataSource(filename);
	    BodyPart messageBodyPart 	= new MimeBodyPart();        
	    
	    messageBodyPart.setDataHandler( new DataHandler(source) );
	    messageBodyPart.setFileName( filename.getName() );
	    multipart.addBodyPart(messageBodyPart);
	}
}
