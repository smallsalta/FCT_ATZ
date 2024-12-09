package com.atz.service;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atz.persistencia.TCliente;

@Service
public class SendMailService 
{
	@Autowired
	@Qualifier("mailFrom")
	private String from;
	
	@Autowired
	@Qualifier("mailUsr")
	private String username;
	
	@Autowired
	@Qualifier("mailPwd")
	private String password;
	
	@Autowired
	@Qualifier("mailSub")
	private String subject;
	
	@Autowired
	@Qualifier("mailCopia1")
	private String copia1;
	
	@Autowired
	@Qualifier("mailCopia2")
	private String copia2;
	
	@Autowired
	@Qualifier("mailCopia3")
	private String copia3;
	
	@Autowired
	@Qualifier("pdfFolder")
	private File pdfFolder;
		
	/**
	 * Se manda el PDF al email del cliente
	 * @param c		Cliente
	 * @param f		Factura
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void enviarSinCC(TCliente c, File f) 
	throws AddressException, MessagingException 
	{
		List<String> tmp = Arrays.asList( c.getEmail().split(";") );
		
		tmp.add( this.copia1 );
		
		this.enviarComun
		( 
			c, 
			this.calculaTo( tmp.toArray( new String[0] ) ), 
			this.getCuerpo(), 
			f 
		);
	}
	
	/**
	 * Igual que enviarConCC pero sin mandar al mail2
	 * @param c			Cliente ... Para obtener su correo
	 * @param ccExtra	Campo CC del formularios
	 * @param fs		Ficheros a adjuntar al correo
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void enviarSinCC(TCliente c, String ccExtra, File ... fs) 
	throws AddressException, MessagingException 
	{
//		List<String> tmp1 	= Arrays.asList( ccExtra.split(";") );
//		List<String> tmp2	= Arrays.asList( c.getEmail().split(";") );
//		List<String> tmp3	= new LinkedList<>();
//		
//		tmp3.addAll( tmp1 );
//		tmp3.addAll( tmp2 );
//				
//		this.enviarComun
//		( 
//			c, 
//			this.calculaTo( tmp3.toArray( new String[0] ) ),
//			this.getCuerpo(), 
//			fs 
//		);
		
		this.enviarConCC(c, ccExtra, fs);
	}
	
	/**
	 * Se manda el PDF al email del cliente y a los CC
	 * @param c			Cliente
	 * @param f			Documento
	 * @param ccExtra	Cc extra
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void enviarConCC(TCliente c, String ccExtra, File ... fs) 
	throws AddressException, MessagingException 
	{
		List<String> tmp1 = Arrays.asList( ccExtra.split(";") );
		List<String> tmp2 = Arrays.asList( c.getEmail().split(";") );
		List<String> tmp3 = new LinkedList<>();
		
		tmp3.addAll( tmp1 );
		tmp3.addAll( tmp2 );
		
		this.enviarComun
		( 
			c, 
			this.calculaTo( tmp3.toArray( new String[0] ) ),
			this.getCuerpo(), 
			fs 
		);
	}
	
	/**
	 * Se manda el PDF al email del cliente y a los CC
	 * @param c		Cliente
	 * @param f		Documento
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void enviarConCCyCuadrante(TCliente c, String cc, File ... fs) 
	throws AddressException, MessagingException 
	{
		File cuadrante 	= new File( this.pdfFolder.getAbsolutePath() + "/cuadrante.pdf" );
		int tam			= fs.length;
		File[] tmp 		= Arrays.copyOf( fs, tam+1 );
		tmp[tam]		= cuadrante;
		
		this.enviarConCC( c, cc, tmp );
	}
	
	private String calculaTo(String ... dest)
	{
		String res = "";
		
		for(int i = 0; i < dest.length; i++) 
		{
			if(i == dest.length) 
			{
				res += this.toValido(dest[i], "");
			} 
			else 
			{
				res += this.toValido(dest[i], ", ");
			}
		}
		
		return res;
	}
	
	private String toValido(String to, String append)
	{
		return ( to == null || to.isEmpty() ) ? "" : append + to.trim();
	}
	
	private void enviarComun(TCliente c, String mailto, String texto, File ... fs) 
	throws AddressException, MessagingException 
	{
		Properties props 			= new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Autenticacion auth			= new Autenticacion( this.username, this.password );
		Session session 			= Session.getInstance(props,auth);

		Message message				= new MimeMessage(session);
		BodyPart messageBodyPart1	= new MimeBodyPart();
//		BodyPart messageBodyPart2 	= new MimeBodyPart();
		Multipart multipart			= new MimeMultipart();
//		DataSource source 			= new FileDataSource(f);
		
		message.setFrom( new InternetAddress( this.from ) );
		message.setRecipients( Message.RecipientType.TO, InternetAddress.parse(mailto) );
		message.setSubject( this.subject + " - " + c.getNombre() + " " + c.getApellidos() );
		
		messageBodyPart1.setText(texto);	
		multipart.addBodyPart(messageBodyPart1);
		
//		messageBodyPart2.setDataHandler( new DataHandler(source) );
//		messageBodyPart2.setFileName( f.getName() );
		
//		multipart.addBodyPart(messageBodyPart2);

		for(File f : fs)
		{
			this.addAttachment(multipart, f);
		}
		
		message.setContent(multipart);
		
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
	
	private String getCuerpo()
	{
		String txt1	=	"Este es un mensaje automático. Por favor no responda a este correo electrónico."; 
		txt1		+= 	"\n\nTras la nueva normativa del RD 513/2017, se ruega que la documentación adjunta en este correo, nos las devuelvan firmada a " + this.copia3 + ".";		
		txt1		+= 	"\n\nPara cualquier duda, pueden contactar con nosotros en 651 735 069 / 649 258 267 / 954 023 295";	
		
		return txt1;
	}
}

class Autenticacion 
extends Authenticator
{
	private String username;
	private String password;
	
	public Autenticacion(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() 
	{
		return new PasswordAuthentication( this.username, this.password );
	}
}
