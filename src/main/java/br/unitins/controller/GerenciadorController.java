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
import br.unitins.carro.aplication.Session;
import br.unitins.carro.aplication.Util;
import br.unitins.model.Carro;
import br.unitins.model.Fabricante;
import br.unitins.model.Usuario;

@Named
@ViewScoped
public class GerenciadorController implements Serializable {

	private static final long serialVersionUID = 2369501769009248699L;
	private String filtro;
	private Integer tipo;
	private Usuario usuLogado;
	private List<Usuario> listaUsu;
	private List<Carro> listaCarro;
	private List<Fabricante> listaFab;

	GerenciadorController(){
		usuLogado = (Usuario) Session.getInstance().get("usuLogado");
		System.out.println(usuLogado.getNome());
	}
	
	public void pesquisar() {
		if (getTipo().equals(1)) {

			UsuarioDAO dao = new UsuarioDAO();
			listaUsu = dao.buscarPorNome(getFiltro());
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			flash.put("usuFlash", listaUsu);
			Util.redirect("tableusuario.xhtml");
			// Session.getInstance().set("lista", getListaUsu());
		} else if (getTipo().equals(2)) {
			CarroDAO dao = new CarroDAO();
			listaCarro = dao.buscarPorNome(getFiltro());
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			flash.put("carroFlash", listaCarro);
			Util.redirect("tablecarro.xhtml");
		} else if (getTipo().equals(3)) {
			MarcaDAO dao = new MarcaDAO();
			listaFab = dao.buscarPorNome(getFiltro());
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
			flash.put("fabricanteFlash", listaFab);
			Util.redirect("tablefabricante.xhtml");
		} else {
			return;
		}

	}

	public String getFiltro() {
		return filtro;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public List<Usuario> getListaUsu() {
		if (listaUsu == null)
			listaUsu = new ArrayList<Usuario>();
		return listaUsu;
	}

	public void setListaUsu(List<Usuario> listaUsu) {
		this.listaUsu = listaUsu;
	}

	public List<Carro> getListaCarro() {
		if (listaCarro == null)
			listaCarro = new ArrayList<Carro>();
		return listaCarro;
	}

	public void setListaCarro(List<Carro> listaCarro) {
		this.listaCarro = listaCarro;
	}

	public List<Fabricante> getListaFab() {
		if (listaFab == null)
			listaFab = new ArrayList<Fabricante>();
		return listaFab;
	}

	public void setListaFab(List<Fabricante> listaFab) {
		this.listaFab = listaFab;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Usuario getUsuLogado() {
		if(usuLogado == null)
			usuLogado = new Usuario();
		return usuLogado;
	}

	public void setUsuLogado(Usuario usuLogado) {
		this.usuLogado = usuLogado;
	}

}
