package br.unitins.carro.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import br.unitins.model.Perfil;
import br.unitins.model.Usuario;
@WebFilter(filterName = "SecurityFilter",urlPatterns = {"/admin/*"})
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			
			
			HttpSession session = servletRequest.getSession(false);
			Usuario usu = null;
			if(session != null)
				usu = (Usuario) session.getAttribute("usuLogado");
			
			
			
			if(usu == null) {
				((HttpServletResponse)response).sendRedirect("/Carro/login.xhtml");
			}else {
				if(usu.getPerfil().equals(Perfil.ADMIN)) {
					chain.doFilter(request, response);
				}else {
					((HttpServletResponse)response).sendRedirect("/Carro/semacesso.xhtml");
				}
			}
				
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		Filter.super.init(filterConfig);
		System.out.println("Teste filtro");
	}

}
