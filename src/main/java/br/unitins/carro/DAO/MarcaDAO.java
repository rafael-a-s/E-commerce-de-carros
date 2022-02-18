package br.unitins.carro.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.carro.connection.Conn;
import br.unitins.model.Fabricante;

public class MarcaDAO implements DAO<Fabricante>{
	@Override
	public boolean incluir(Fabricante marca) {
		
        Connection conn= Conn.getConnection();
        if(conn == null)
            return false;

            boolean resultado= true;
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO marca ( ");
            sql.append(" nome ");
             sql.append(" ) VALUES ( ");
            sql.append(" ? ");
            sql.append(" )");

             PreparedStatement stat = null;
            try{
                
                stat= conn.prepareStatement(sql.toString());
                stat.setString(1, marca.getNome());
                 

                    stat.execute();

            }catch(SQLException e){
                e.printStackTrace();
                resultado = false;

            }finally{
                try{
                    stat.close();
                }catch(SQLException e){


                }
               Conn.stopConn(conn);
            }
          

		return resultado;
	}

	@Override
	public boolean alterar(Fabricante marca) {
		  Connection conn = Conn.getConnection();
        if(conn == null)
            return false;
            boolean resultado = true;
            StringBuffer sql =new StringBuffer();
            sql.append(" UPDATE marca SET ");  
            sql.append(" nome = ? ");
             sql.append(" WHERE ");
             sql.append(" idmarca = ?");
             
             PreparedStatement stat = null;
             try{
                 stat= conn.prepareStatement(sql.toString());
                 stat.setString(1,marca.getNome());
                 stat.setInt(2, marca.getIdmarca());
                  
                    stat.execute();


                    



             }catch(SQLException e){
                 e.printStackTrace();
                 resultado=false;

             }finally{
                 try{
                     stat.close();
                 }catch(SQLException e){

                 }
                 try{
                     conn.close();

                 }catch(SQLException e){

                 }
                 
             }
             return resultado;
    









	}

	@Override
	public boolean excluir(Integer idmarca) {
        Connection conn= Conn.getConnection();
        if(conn == null)
            return false;

            boolean resultado = true;

            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM marca WHERE idmarca =? ");

            PreparedStatement stat = null;
            try{
                stat= conn.prepareStatement(sql.toString());
                stat.setInt(1,idmarca);

                stat.execute();

            }catch(SQLException e){
                e.printStackTrace();    
                resultado = false;

            }finally{
                try{
                    stat.close();

                }catch(SQLException e){


                }
                try{
                    conn.close();
                }catch(SQLException e){

                }
                
                
            
                
            }
            return resultado;
        
		
		

		
	}

	@Override
	public List<Fabricante> obterTodos() {
		List<Fabricante> listaMarca = new ArrayList<Fabricante>();
		Connection conn = Conn.getConnection();
		if(conn == null)
			return null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append(" m.idmarca, ");
			sql.append(" m.nome ");
			sql.append(" FROM ");
			sql.append(" marca m ");
			sql.append(" ORDER BY ");
			sql.append(" m.nome ");
			rs = conn.createStatement().executeQuery(sql.toString());
			while(rs.next()) {
				Fabricante marca = new Fabricante();
				marca.setIdmarca(rs.getInt("idmarca"));
				marca.setNome(rs.getString("nome"));
				
				listaMarca.add(marca);
			}
		} catch (SQLException e) {
			Conn.stopConn(conn);
			try {
				rs.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		
		
		
		return listaMarca;
	}
	public Fabricante buscarPorId(Integer id) {
		Connection conn = Conn.getConnection();
		Fabricante marca = null;
		if(conn == null)
			return null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" m.idmarca, ");
		sql.append(" m.nome ");
		sql.append(" FROM ");
		sql.append(" marca m ");
		sql.append("WHERE ");
		sql.append(" m.idmarca = ? ");
		
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			marca = new Fabricante();
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			stat.execute();
			rs = stat.executeQuery();
			while(rs.next()) {
				marca.setIdmarca(rs.getInt("idmarca"));
				marca.setNome(rs.getString("nome"));

			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				stat.close();
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			Conn.stopConn(conn);
		}
		
		
		return marca; 
	}

public List<Fabricante> buscarPorNome(String nome) {
	Connection conn = Conn.getConnection();
	List<Fabricante> listaFab = new ArrayList<Fabricante>();
	if(conn == null)
		return null;
	
	StringBuffer sql = new StringBuffer();
	sql.append("SELECT ");
	sql.append(" m.idmarca, ");
	sql.append(" m.nome ");
	sql.append(" FROM ");
	sql.append(" marca m ");
	sql.append("WHERE ");
	sql.append("  m.nome iLIKE ? ");
	
	PreparedStatement stat = null;
	ResultSet rs = null;
	try {
		
		
		stat = conn.prepareStatement(sql.toString());
		stat.setString(1, "%" + nome + "%");
		
		rs = stat.executeQuery();
		while(rs.next()) {
			Fabricante marca = new Fabricante();
			marca.setIdmarca(rs.getInt("idmarca"));
			marca.setNome(rs.getString("nome"));
			listaFab.add(marca);
		}
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		try {
			stat.close();
			rs.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		Conn.stopConn(conn);
	}
	
	
	return listaFab; 
}







}
















