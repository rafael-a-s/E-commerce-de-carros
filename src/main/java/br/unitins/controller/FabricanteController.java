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
public class FabricanteController implements Serializable {

	
	private static final long serialVersionUID = 92629888251542168L;
	private Fabricante fab;
	private List<Fabricante> listaFabrica;
	
	
	public FabricanteController(){
		Flash flash= FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("fabEditarFlash");
		fab = (Fabricante) flash.get("fabEditarFlash");
		
	}
	
	
	public void registrar() {
		MarcaDAO dao = new MarcaDAO();
		
		if(dao.incluir(getFab())) {
			Util.addInfoMessage("Sucesso !");
			limpar();
		}else {
			Util.addErrorMessage("Error");
		}
	}

	public void alterar() {
		MarcaDAO dao = new MarcaDAO();
		
		if(dao.alterar(getFab())) {
			Util.addInfoMessage("Sucesso !");
			
			limpar();
			Util.redirect("gerenciador.xhtml");
		}else {
			Util.addErrorMessage("Error");
		}
	}
	public void excluir(Integer id) {
		MarcaDAO dao = new MarcaDAO();
		if(dao.excluir(id)) {
			Util.addInfoMessage("Sucesso !");
			limpar();
			Util.redirect("gerenciador.xhtml");
		}else {
			Util.addErrorMessage("Error");
		}
	}
	public void limpar() {
		setFab(null);
	}
	public Fabricante getFab() {
		if(fab == null)
		fab = new Fabricante();
		return fab;
	}
	public void setFab(Fabricante fab) {
		this.fab = fab;
	}
	public List<Fabricante> getListaFabrica() {
		if(listaFabrica == null)
			listaFabrica = new ArrayList<Fabricante>();
		return listaFabrica;
	}
	public void setListaFabrica(List<Fabricante> listaFabrica) {
		this.listaFabrica = listaFabrica;
	}
	
	
	
    
    















}
