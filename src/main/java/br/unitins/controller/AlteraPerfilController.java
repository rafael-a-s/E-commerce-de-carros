package br.unitins.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.UsuarioDAO;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Sexo;
import br.unitins.model.Usuario;

@Named 
@ViewScoped
public class AlteraPerfilController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3306224267580380514L;
    private Usuario usu;
    public AlteraPerfilController() {
    	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    	flash.keep("usuPerfil");
    	usu =(Usuario) flash.get("usuPerfil");
    }
    public void alterar() {
    	UsuarioDAO dao = new UsuarioDAO();
    	//Geranso o hash
    	getUsu().setSenha(Util.hash(getUsu()));
    	if(dao.alterar(getUsu())) {
    		Util.addInfoMessage("Alteração realizada com sucesso !!");
    		Util.redirect("perfil.xhtml");
    	}
    }
    public Sexo[] getListaSexo() {
    	return Sexo.values();
    }
	public Usuario getUsu() {
		return usu;
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
    
    
    

}
