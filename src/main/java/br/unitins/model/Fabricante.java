package br.unitins.model;

import java.util.Objects;

public class Fabricante {

    private Integer idmarca;
    private String nome;
    
	public Integer getIdmarca() {
		return idmarca;
	}
	public void setIdmarca(Integer idmarca) {
		this.idmarca = idmarca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idmarca);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fabricante other = (Fabricante) obj;
		return Objects.equals(idmarca, other.idmarca);
	}




    






}
