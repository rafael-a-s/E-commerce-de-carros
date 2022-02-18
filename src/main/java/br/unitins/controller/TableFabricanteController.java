package br.unitins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


import br.unitins.carro.DAO.MarcaDAO;
import br.unitins.carro.aplication.Util;

import br.unitins.model.Fabricante;

@Named
@ViewScoped
public class TableFabricanteController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4029940108306626238L;
	private List<Fabricante> fablista;
	
	TableFabricanteController(){
		Flash flash= FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("fabricanteFlash");
		fablista= (List<Fabricante>) flash.get("fabricanteFlash");
	}
	public void excluir(Fabricante fab) {
		MarcaDAO dao = new MarcaDAO();
		
		if(dao.excluir(fab.getIdmarca())) {
			Util.redirect("gerenciador.xhtml");
		}
		
	}
	public void editar(Integer id) {
		MarcaDAO dao = new MarcaDAO();
		Fabricante fabricante = dao.buscarPorId(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("fabEditarFlash", fabricante);
		Util.redirect("fabricante.xhtml");
	}
	public List<Fabricante> getFablista() {
		if(fablista == null)
			fablista = new ArrayList<Fabricante>();
		return fablista;
	}
	public void setFablista(List<Fabricante> fablista) {
		this.fablista = fablista;
	}
	
	
	
	
}
