package br.unitins.model;

public enum Perfil {
	ADMIN(1,"Admin"),
	CLIENTE(2, "Cliente");
	
	
	private Integer id;
	private String desc;
	Perfil(Integer id, String desc){
		this.id = id;
		this.desc = desc;
	}
	public Integer getId() {
		return id;
	}
	public String getDesc() {
		return desc;
	}
	
	public static Perfil valuesOf( Integer id) {
		for (Perfil perfil :  Perfil.values()) {
			if(perfil.getId() == id)
				return perfil;
		}
		return null;
	}
}
