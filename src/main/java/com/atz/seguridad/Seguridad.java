package com.atz.seguridad;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.atz.persistencia.TUsuario;

public class Seguridad
implements Filter
{
	private Logger log 			= LogManager.getLogger( Seguridad.class );
	private String[] exepciones	= {"login.do", "j_spring_security_check.do"};
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException 
	{
		HttpServletRequest req	= (HttpServletRequest)request;
		HttpServletResponse res	= (HttpServletResponse)response;
		
		String url 		= req.getRequestURL().toString();
		TUsuario u		= (TUsuario) req.getSession().getAttribute("usuario");
		boolean excep	= false;
		
		if(u == null)
		{
			this.log.info("Se solicita: " + url);
		
			for(String ex : this.exepciones)
			{
				if( url.contains(ex) )
				{
					excep = true;
					break;
				}
			}
		
			if(!excep)
			{
				res.sendRedirect("login.do");
			}
			else
			{
				chain.doFilter(request, response);
			}
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
