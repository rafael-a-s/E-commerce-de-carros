package br.unitins.carro.DAO;

import java.util.List;

public interface DAO<T> {
	
	public boolean incluir(T obj);
	
	public boolean alterar(T obj);
	
	public boolean excluir(Integer id);
	
	public List<T> obterTodos();


	


	










		
	
}
