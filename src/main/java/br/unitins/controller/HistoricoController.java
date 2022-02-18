package br.unitins.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.VendaDAO;
import br.unitins.carro.aplication.Session;
import br.unitins.carro.aplication.Util;
import br.unitins.model.ItemVenda;
import br.unitins.model.Usuario;
import br.unitins.model.Venda;
@Named
@ViewScoped
public class HistoricoController implements Serializable {

  
	private static final long serialVersionUID = 8204451125834003975L;
	private List<Venda>vendasCarros;
	
	
	
	public List<Venda> getVendasCarros() {
		if(vendasCarros == null) {
			VendaDAO dao = new VendaDAO();
			Usuario usu = (Usuario) Session.getInstance().get("usuLogado");
			vendasCarros = dao.obterTodos(usu);
			
		}
		return vendasCarros;
	}
	public void detalhes(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("vendaFlash", venda);
		Util.redirect("detalhes.xhtml");
		
	}
	public void setVendasCarros(List<Venda> vendasCarros) {
		this.vendasCarros = vendasCarros;
	}
	

    

}
