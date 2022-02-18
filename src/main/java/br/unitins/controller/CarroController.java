package br.unitins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.CarroDAO;
import br.unitins.carro.DAO.MarcaDAO;
import br.unitins.carro.DAO.UsuarioDAO;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Carro;
import br.unitins.model.Cor;
import br.unitins.model.Fabricante;

@Named
@ViewScoped
public class CarroController implements Serializable {

	
	private static final long serialVersionUID = 2713148775178360713L;
	private Carro carro;
	private List<Fabricante> listaMarca;
	
	public Cor[] getListaCor() {
		return Cor.values();
	}
	public CarroController(){
		Flash flash= FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("carroFlash");
		carro=(Carro) flash.get("carroFlash");

	}
	public void registrar() {
		CarroDAO dao = new CarroDAO();
		
		if(dao.incluir(getCarro())) {
			Util.addInfoMessage("Sucesso !");
			limpar();
		}else {
			Util.addErrorMessage("Error");
		}
	}
	
	public void alterar() {
		CarroDAO dao = new CarroDAO();
		if(dao.alterar(getCarro())) {
			Util.addInfoMessage("Sucesso !");
			limpar();
		}else {
			Util.addErrorMessage("Error");
		}
	}
	public void excluir(Integer id) {
		CarroDAO dao = new CarroDAO();
		if(dao.excluir(id)) {
			Util.addInfoMessage("Sucesso !");
			limpar();
		}else {
			Util.addErrorMessage("Error");
		}
	}
	public void limpar() {
		setCarro(null);
		getCarro().setMarca(null);
	}
	
	public Carro getCarro() {
		if(carro == null)
			carro = new Carro();
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public List<Fabricante> getListaMarca() {
		if(listaMarca == null) {
			MarcaDAO dao = new MarcaDAO();
			listaMarca = dao.obterTodos();
			if(listaMarca == null)
				listaMarca = new ArrayList<Fabricante>();
		}
		return listaMarca;
	}
	public void setListaMarca(List<Fabricante> listaMarca) {
		this.listaMarca = listaMarca;
	}
	




	
	
}
