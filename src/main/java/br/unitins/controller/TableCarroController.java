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
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class TableCarroController implements Serializable {

	
	private static final long serialVersionUID = -5309151840309193191L;
	private List<Carro> listaCarro;
	
	public TableCarroController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("carroFlash");
		listaCarro = (List<Carro>) flash.get("carroFlash");
	}
	public void excluir(Carro carro) {
		CarroDAO dao = new CarroDAO();
		
		if(dao.excluir(carro.getId())) {
			Util.redirect("gerenciador.xhtml");
		}
		
	}
	public void editar(Integer id) {
		CarroDAO dao = new CarroDAO();
		Carro carro =dao.buscarPorId(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("carroFlash", carro);
		Util.redirect("Carro.xhtml");
	}
	public List<Carro> getListaCarro() {
		if(listaCarro == null)
			listaCarro = new ArrayList<Carro>();
		return listaCarro;
	}
	public void setListaCarro(List<Carro> listaCarro) {
		this.listaCarro = listaCarro;
	}
	
	
}
