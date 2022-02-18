package br.unitins.model;

public enum Cor {
	AZULMETALICO(1,"Azul Metalico"),
	BRANCO(2,"Branco"),
	PRATA(3,"Prata"),
	PRETO(4,"Preto"),
	PRETOFOSCO(5,"Preto Fosco"),
	VERMELHO(6,"Vermelho");
	
	
	
	private Integer id;
	private String desc;

	Cor(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getId() {
		return id;
	}
	public static Cor valuesOf(Integer id) {
		for (Cor cor : Cor.values()) {
			if(cor.getId() == id)
				return cor;
		}
		return null;
	}
	
	
	
}
