package br.unitins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.CarroDAO;
import br.unitins.carro.DAO.UsuarioDAO;
import br.unitins.carro.aplication.Session;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Carro;
import br.unitins.model.Fabricante;
import br.unitins.model.Usuario;



@Named
@ViewScoped
public class TableUsuarioController implements Serializable{

	
	private static final long serialVersionUID = 760748047498338058L;
	private List<Usuario> listaUsu;
	
	public TableUsuarioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("usuFlash");
		
		
		listaUsu = (List<Usuario>) flash.get("usuFlash");
	}
	public void excluir(Usuario usu) {
		UsuarioDAO dao = new UsuarioDAO();
		
		if(dao.excluir(usu)) {
			Util.redirect("gerenciador.xhtml");
		}
		
	}
	public void editar(Integer id) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usu = dao.bucarPorId(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuFlash", usu);
		Util.redirect("usuario.xhtml");
	}
	
	public List<Usuario> getListaUsu() {
		if(listaUsu == null)
			listaUsu = new ArrayList<Usuario>();
		return listaUsu;
	}
	public void setListaUsu(List<Usuario> listaUsu) {
		this.listaUsu = listaUsu;
	}
}