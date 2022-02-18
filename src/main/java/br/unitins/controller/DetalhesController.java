package br.unitins.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.model.Venda;

@Named
@ViewScoped
public class DetalhesController implements Serializable{

	private static final long serialVersionUID = 791469825434041908L;
	private Venda venda;
	public DetalhesController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("vendaFlash");
		venda =(Venda) flash.get("vendaFlash");
	}
	
	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
	
	
	
	
	
}
