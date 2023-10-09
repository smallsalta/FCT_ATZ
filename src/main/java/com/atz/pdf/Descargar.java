package com.atz.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class Descargar 
{
	public void flush(File pdfFile, HttpServletResponse resp) 
	throws IOException
	{
		FileInputStream fis	= new FileInputStream(pdfFile);
		OutputStream os		= resp.getOutputStream();
		byte[] buffer 		= new byte[4096];
		int length			= 0;
		
		resp.setContentType("application/pdf");
		resp.addHeader( "Content-Disposition", "attachment; filename=" + pdfFile.getName() );
		resp.setContentLength( (int) pdfFile.length() );

		while( (length = fis.read(buffer)) > -1 ) 
		{
			os.write( buffer, 0, length );
		}
		
		os.flush();
		fis.close();
		os.close();
	}
}
