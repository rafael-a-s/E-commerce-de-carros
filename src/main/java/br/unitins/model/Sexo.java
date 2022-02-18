package br.unitins.model;

public enum Sexo {
	FEMININO(1, "Feminino"), 
	MASCULINO(2, "Masculino");
	
	private Integer id;
	private String desc;
	
	Sexo(Integer id, String desc){
		this.id = id;
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static Sexo valuesOf( Integer id) {
		for (Sexo sexo :  Sexo.values()) {
			if(sexo.getId() == id)
				return sexo;
		}
		return null;
	}
}
