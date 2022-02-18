package br.unitins.model;

import java.util.Objects;

public class Carro {
    private Integer id;
    private String nome;
    private Double preco;
	private Integer anolancamento;
	private Fabricante marca;
	private String descricao;
    private Cor cor;





	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getAnolancamento() {
		return anolancamento;
	}
	public void setAnolancamento(Integer anolancamento) {
		this.anolancamento = anolancamento;
	}
	
	public Fabricante getMarca() {
		if(marca == null)
			marca = new Fabricante();
		return marca;
	}
	public void setMarca(Fabricante marca) {
		this.marca = marca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		return Objects.equals(id, other.id);
	}













    


}
