package br.unitins.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.VendaDAO;
import br.unitins.carro.aplication.Session;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Carro;
import br.unitins.model.ItemVenda;
import br.unitins.model.Usuario;
import br.unitins.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {
	private static final long serialVersionUID = 3215323285756110606L;
	private Double valorTotal;
	private List<ItemVenda> listaItemVenda = null;
	
	
	@SuppressWarnings("unchecked")
	public List<ItemVenda> getListaItemVenda() {
		listaItemVenda = (List<ItemVenda>) Session.getInstance().get("itemSession");
		return listaItemVenda;
	}
	public double valorTotal(List<ItemVenda> lista) {
		double aux = 0.0;
		for (ItemVenda item : lista) {
			
			
			aux = aux + item.getValor();
		}
		return aux;
	}
	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	public void remover(ItemVenda item) {
		
		int aux = getListaItemVenda().indexOf(item);
		Integer i = (Integer) Session.getInstance().get("contCarrinho");
		i = i-getListaItemVenda().get(aux).getQuantidade();
		Session.getInstance().set("contCarrinho", i);
		getListaItemVenda().remove(item);
		Util.addInfoMessage("Item removido com sucesso !!");
	}
	public void finalizar() {

		Usuario usuarioLogado = (Usuario) Session.getInstance().get("usuLogado");
		if (usuarioLogado == null) {
			Util.addErrorMessage("Faça o Login para concluir a venda");
			Util.redirect("login.xhtml");
			return;
		}

		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("itemSession");
		
		
		if (carrinho == null || carrinho.isEmpty()) {
			Util.addErrorMessage("Carrinho vazio !!!");
			return;
		}

		Venda venda = new Venda();
		venda.setData(LocalDate.now());
		venda.setUsuario(usuarioLogado);
		venda.setListaItemVenda(carrinho);
		for (ItemVenda item : carrinho) {
			venda.setTotalVenda(venda.getTotalVenda()+item.getValor());
		}
		VendaDAO dao = new VendaDAO();
		if (dao.incluir(venda)) {
			Util.addInfoMessage("Venda realizada com sucesso.");
			
		}else 
			Util.addErrorMessage("Problemas ao realizar a venda.");

	}

	public Double getValorTotal() {
		if(valorTotal == null)
			valorTotal = valorTotal(listaItemVenda);
			if(valorTotal == null)
				valorTotal = 0.0;
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		
		
		
		this.valorTotal = valorTotal;
	}

}
