package br.unitins.controller;

import java.io.Serializable;


import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.UsuarioDAO;
import br.unitins.carro.aplication.Session;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class LoginController implements Serializable{

	private static final long serialVersionUID = -1767247913604157834L;
	private Usuario usu;
	
	
	
	public String logar() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario u = dao.validaUsuario(usu.getEmail(), Util.hash(getUsu()));
		
		if(u != null) {
			Session.getInstance().set("usuLogado", u);
			Util.redirect("tamplate.xhtml");
		}else {
			Util.addErrorMessage("Email ou senha, incorretos.");
		}
		
	return null;
	}
	
	
	public Usuario getUsu() {
		if(usu ==  null)
			usu = new Usuario();
		return usu;
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
	
	
	
	

}
