package br.unitins.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.carro.DAO.CarroDAO;
import br.unitins.carro.aplication.Session;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Carro;
import br.unitins.model.ItemVenda;
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = 4876808364573796677L;
	private Integer cont;
	private String filtro;
	private Usuario usulogado;
	private List<Carro> listaCarro;

	public void pesquisar() {
		CarroDAO dao = new CarroDAO();

		listaCarro = dao.buscarPorNome(getFiltro());
		if (listaCarro == null)
			listaCarro = new ArrayList<Carro>();
	}

	public void addCarrinho(Carro carro) {

		setCont(getCont() + 1);

		@SuppressWarnings("unchecked")
		List<ItemVenda> listaCarrinho = (List<ItemVenda>) Session.getInstance().get("itemSession");
		if (listaCarrinho == null)
			listaCarrinho = new ArrayList<ItemVenda>();

		ItemVenda item = new ItemVenda();
		item.setCarro(carro);
		item.setValor(carro.getPreco());
		item.setQuantidade(1);

		if (listaCarrinho.contains(item)) {
			int aux = listaCarrinho.indexOf(item);
			int quant = listaCarrinho.get(aux).getQuantidade() + 1;
			double auxpreco = listaCarrinho.get(aux).getValor() + carro.getPreco();
			listaCarrinho.get(aux).setValor(auxpreco);
			listaCarrinho.get(aux).setQuantidade(quant);
		} else {
			listaCarrinho.add(item);
		}
		Session.getInstance().set("contCarrinho", getCont());
		Session.getInstance().set("itemSession", listaCarrinho);
	}

	public void invalida() {
		Session.getInstance().invalidationSession();
		Util.redirect("tamplate.xhtml");
	}

	public Usuario getUsulogado() {
		if (usulogado == null)
			usulogado = (Usuario) Session.getInstance().get("usuLogado");
		return usulogado;
	}

	public void setUsulogado(Usuario usulogado) {
		this.usulogado = usulogado;
	}

	public List<Carro> getListaCarro() {
		CarroDAO dao = new CarroDAO();
		if (listaCarro == null)
			listaCarro = dao.buscarPorNome(getFiltro());
		if (listaCarro == null)
			listaCarro = new ArrayList<Carro>();
		return listaCarro;
	}

	public void setListaCarro(List<Carro> listaCarro) {
		this.listaCarro = listaCarro;
	}

	public Integer getCont() {
		if (cont == null)
			cont = (Integer) Session.getInstance().get("contCarrinho");
		if (cont == null)
			cont = 0;
		return cont;
	}

	public void setCont(Integer cont) {
		this.cont = cont;
	}

	public String getFiltro() {
		if (filtro == null)
			filtro = "";
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
