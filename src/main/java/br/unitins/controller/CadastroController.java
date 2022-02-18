package br.unitins.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.UsuarioDAO;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Perfil;
import br.unitins.model.Sexo;
import br.unitins.model.Telefone;
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class CadastroController implements Serializable {

	private static final long serialVersionUID = 6088078623907293104L;
	private Usuario usu;
	
	public void registrar() {
		UsuarioDAO dao = new UsuarioDAO();
		usu.setPerfil(Perfil.CLIENTE);
		//Geranso o hash
		getUsu().setSenha(Util.hash(getUsu()));
		if(dao.incluir(getUsu())) {
			Util.addInfoMessage("Cadastro realziado com sucesso !");
			Util.redirect("login.xhtml");
		}else {
			Util.addErrorMessage("Error");
		}
	}
	
	public Sexo[] getListaSexo() {
		return Sexo.values();
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
