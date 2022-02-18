package br.unitins.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.UsuarioDAO;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Carro;
import br.unitins.model.Fabricante;
import br.unitins.model.Perfil;
import br.unitins.model.Sexo;
import br.unitins.model.Telefone;
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	
	private static final long serialVersionUID = -1252416557438065646L;
	private Usuario usu;
	
	public UsuarioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("usuFlash");
		
		usu =(Usuario) flash.get("usuFlash");
	}
	public void registrar() {
		UsuarioDAO dao = new UsuarioDAO();
		//Geranso o hash
		getUsu().setSenha(Util.hash(getUsu()));
		
		if(dao.incluir(getUsu())) {
			Util.addWarnMessage("Sucesso");
			limpar();
		}else {
			Util.addErrorMessage("Error");
		}
	}
	
	public void alterar() {
		UsuarioDAO dao = new UsuarioDAO();
		//Geranso o hash
		getUsu().setSenha(Util.hash(getUsu()));
		if(dao.alterar(getUsu())) {
			Util.addWarnMessage("Sucesso");
			limpar();
			Util.redirect("gerenciador.xhtml");
		}else {
			Util.addErrorMessage("Error");
		}
	}
	public void excluir(Integer id) {
		UsuarioDAO dao = new UsuarioDAO();
		if(dao.excluir(id)) {
			Util.addWarnMessage("Sucesso");
			limpar();
		}else {
			Util.addErrorMessage("Error");
		}
	}
	public void limpar() {
		setUsu(null);
		getUsu().setTelefone(null);
	}
	
	
	public Sexo[] getListaSexo() {
		return Sexo.values();
	}
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}
	
	public Usuario getUsu() {
		if(usu == null) {
			usu = new Usuario();
			usu.setTelefone(new Telefone());
		}
		return usu;
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
	
	
	
	
	
}
