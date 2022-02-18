package br.unitins.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Telefone {
	private Integer id;
	@Size(min = 2, max = 2, message = "Informe ao menos 2 digitos para codigo de área.")
	@NotNull(message = "o código de área é obrigatório.")
	private String ddd;
	@Size(min = 9, max = 10, message = "O número deve ter ao menos 9 digitos.")
	@Pattern(regexp = "([0-9]{5}[-]?[0-9]{4})")
	private String numero;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String dd) {
		this.ddd = dd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
