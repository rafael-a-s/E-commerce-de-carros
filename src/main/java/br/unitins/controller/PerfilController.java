package br.unitins.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.UsuarioDAO;
import br.unitins.carro.aplication.Session;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class PerfilController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7356387250949294875L;
	private Usuario usuario;



	public void alterar(Usuario usu) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuPerfil", usu);
		Util.redirect("alterarperfil.xhtml");
	}
   

	public Usuario getUsuario() {
		UsuarioDAO dao = new UsuarioDAO();
        if(usuario== null) {
        	usuario = (Usuario) Session.getInstance().get("usuLogado");
        	usuario=dao.bucarPorId(usuario.getId());
        	if(usuario == null)
        		usuario=new Usuario();
        }
        	

		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    




}
