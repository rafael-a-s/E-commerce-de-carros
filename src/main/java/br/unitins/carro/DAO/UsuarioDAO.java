package br.unitins.carro.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unitins.carro.connection.Conn;
import br.unitins.model.Fabricante;

import br.unitins.model.Perfil;
import br.unitins.model.Sexo;
import br.unitins.model.Telefone;
import br.unitins.model.Usuario;


public class UsuarioDAO implements DAO<Usuario> {
	
	public List<Usuario> buscarPorNome(String nome){
		List<Usuario> listaUsu = new ArrayList<Usuario>();
		Connection conn = Conn.getConnection();
		if(conn == null)
			return null;
		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append(" u.nome, ");
			sql.append(" u.cpf, ");
			sql.append(" u.email, ");
			sql.append(" u.sexo, ");
			sql.append(" u.senha, ");
			sql.append(" u.perfil, ");
			sql.append(" u.idusuario, ");
			sql.append(" t.idtelefone , ");
			sql.append(" t.ddd, ");
			sql.append(" t.numero ");
			sql.append("FROM ");
			sql.append(" usuario u LEFT JOIN telefone t ON u.idusuario = t.idtelefone ");
			sql.append("WHERE ");
			sql.append(" u.nome iLIKE ? ");
			
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%"+nome+"%");
			
			
			rs = stat.executeQuery();
			while(rs.next()) {
				Usuario usu = new Usuario();
				
				usu.setNome(rs.getString("nome"));
				usu.setCpf(rs.getString("cpf"));
				usu.setEmail(rs.getString("email"));
				usu.setSexo(Sexo.valuesOf(rs.getInt("sexo")));
				usu.setSenha(rs.getString("senha"));
				usu.setPerfil(Perfil.valuesOf(rs.getInt("perfil")));
				usu.setId(rs.getInt("idusuario"));
				usu.setTelefone(new Telefone());
				usu.getTelefone().setId(rs.getInt("idtelefone"));
				usu.getTelefone().setDdd(rs.getString("ddd"));
				usu.getTelefone().setNumero(rs.getString("numero"));
				
				listaUsu.add(usu);
			}
		} catch (SQLException e) {
			listaUsu = null;
			e.printStackTrace();
		}finally {
			try {
				stat.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			Conn.stopConn(conn);
		}
		return listaUsu;
	}
	
	
	
	public Usuario validaUsuario(String email, String senha) {
		Connection conn = Conn.getConnection();
		if(conn == null)
			return null;
		
		ResultSet rs = null;
		PreparedStatement stat = null;
		Usuario usu = null;
		
		
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  u.idusuario, ");
			sql.append("  u.nome, ");
			sql.append("  u.cpf, ");
			sql.append("  u.email, ");
			sql.append("  u.sexo, ");
			sql.append("  u.perfil, ");
			sql.append("  u.senha, ");
			sql.append("  t.idtelefone, ");
			sql.append("  t.ddd, ");
			sql.append("  t.numero ");
			sql.append("FROM ");
			sql.append("  usuario u LEFT JOIN telefone t ON u.idusuario = t.idtelefone ");
			sql.append("  WHERE ");
			sql.append("  u.email = ? and ");
			sql.append("  u.senha = ? ");
			sql.append("ORDER BY ");
			sql.append("  u.nome ");
			
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, email);
			stat.setString(2, senha);
			
			rs = stat.executeQuery();
			while (rs.next()) {
				usu = new Usuario();
				usu.setNome(rs.getString("nome"));
				usu.setCpf(rs.getString("cpf"));
				usu.setEmail(rs.getString("email"));
				usu.setSexo(Sexo.valuesOf(rs.getInt("sexo")));
				usu.setSenha(rs.getString("senha"));
				usu.setPerfil(Perfil.valuesOf(rs.getInt("perfil")));
				usu.setId(rs.getInt("idusuario"));
			}
		} catch (SQLException e) {
			usu = null;
			e.printStackTrace();
		}finally {
			try {
				stat.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			Conn.stopConn(conn);
		}
		
		
		
		
		
		return usu;
	}
	
	public Usuario bucarPorId(Integer id) {
		Connection conn = Conn.getConnection();
		if(conn == null)
			return null;
		
		ResultSet rs = null;
		PreparedStatement stat = null;
		Usuario usu = null;
		
		
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  u.idusuario, ");
			sql.append("  u.nome, ");
			sql.append("  u.cpf, ");
			sql.append("  u.email, ");
			sql.append("  u.sexo, ");
			sql.append("  u.perfil, ");
			sql.append("  u.senha, ");
			sql.append("  t.idtelefone, ");
			sql.append("  t.ddd, ");
			sql.append("  t.numero ");
			sql.append("FROM ");
			sql.append("  usuario u LEFT JOIN telefone t ON u.idusuario = t.idtelefone ");
			sql.append("  WHERE ");
			sql.append("  u.idusuario = ?");
			
			
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			while (rs.next()) {
				usu = new Usuario();
				usu.setNome(rs.getString("nome"));
				usu.setCpf(rs.getString("cpf"));
				usu.setEmail(rs.getString("email"));
				usu.setSexo(Sexo.valuesOf(rs.getInt("sexo")));
				usu.setSenha(rs.getString("senha"));
				usu.setPerfil(Perfil.valuesOf(rs.getInt("perfil")));
				usu.setId(rs.getInt("idusuario"));
				usu.setTelefone(new Telefone());
				usu.getTelefone().setId(rs.getInt("idtelefone"));
				usu.getTelefone().setDdd(rs.getString("ddd"));
				usu.getTelefone().setNumero(rs.getString("numero"));
			}
		} catch (SQLException e) {
			usu = null;
			e.printStackTrace();
		}finally {
			try {
				stat.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			Conn.stopConn(conn);
		}
		return usu;
	}
	@Override
	public boolean incluir(Usuario obj) {
		Connection conn = Conn.getConnection();
		if (conn == null) 
			return false;
		boolean resultado = true;
		
		PreparedStatement stat = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO usuario ( ");
			sql.append(" nome, ");
			sql.append(" cpf, ");
			sql.append(" email, ");
			sql.append(" sexo, ");
			sql.append(" perfil, ");
			sql.append(" senha  ");
			sql.append(") VALUES ( ");
			sql.append(" ?, ");
			sql.append(" ?, ");
			sql.append(" ?, ");
			sql.append(" ?, ");
			sql.append(" ?, ");
			sql.append(" ? ");
			sql.append(" ) ");
			
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS); // pega a chave primaria
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCpf());
			stat.setString(3, obj.getEmail());
			if (obj.getSexo() == null)
				stat.setObject(4, null);
			else
				stat.setInt(4, obj.getSexo().getId());
			if (obj.getPerfil() == null)
				stat.setObject(5, null);
			else
				stat.setInt(5, obj.getPerfil().getId());
			stat.setString(6, obj.getSenha());
			stat.execute();
			
			ResultSet rs = stat.getGeneratedKeys();
			if(rs.next()) {
				Integer id = rs.getInt("idusuario"); // Busca a chave primaria usando o resultset
				
				sql = new StringBuffer();
				sql.append("INSERT INTO telefone ( ");
				sql.append("  idtelefone, ");
				sql.append("  ddd, ");
				sql.append("  numero ");
				sql.append(") VALUES (");
				sql.append("  ?,  ");
				sql.append("  ?,  ");
				sql.append("  ?  ");
				sql.append(") ");
				
				stat.close(); //fecha o PreparedStatement para setar o telefone / evita de criar outro stat
				stat = conn.prepareStatement(sql.toString());
				stat.setInt(1, id);
				stat.setString(2, obj.getTelefone().getDdd());
				stat.setString(3, obj.getTelefone().getNumero());
				
				stat.execute();
			}
			
		} catch (SQLException e) {
			resultado = false;
			e.printStackTrace();
		}finally {
			try {
				stat.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			Conn.stopConn(conn);
		}
		
		
		
		return resultado;
	}

	@Override
	public boolean alterar(Usuario obj) {
		Connection conn = Conn.getConnection();
		if (conn == null) 
			return false;
		boolean resultado = true;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario SET ");
		sql.append("  nome = ?, ");
		sql.append("  cpf = ?, ");
		sql.append("  email = ?, ");
		sql.append("  sexo = ?, ");
		sql.append("  perfil = ?, ");
		sql.append("  senha = ? ");
		sql.append("WHERE ");
		sql.append("  idusuario = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCpf());
			stat.setString(3, obj.getEmail());
			if (obj.getSexo() == null)
				stat.setObject(4, null);
			else
				stat.setInt(4, obj.getSexo().getId());
			if (obj.getPerfil() == null)
				stat.setObject(5, null);
			else
				stat.setInt(5, obj.getPerfil().getId());
			stat.setString(6, obj.getSenha());
			stat.setInt(7, obj.getId());
			
			stat.execute();
			
			sql = new StringBuffer();
			if (obj.getTelefone().getId() == null) {
				sql.append("INSERT INTO telefone ( ");
				sql.append("  ddd, ");
				sql.append("  numero, ");
				sql.append("  idtelefone ");
				sql.append(") VALUES (");
				sql.append("  ?,  ");
				sql.append("  ?,  ");
				sql.append("  ?  ");
				sql.append(") ");
			} else {
				sql.append("UPDATE telefone SET ");
				sql.append("  ddd = ?, ");
				sql.append("  numero = ? ");
				sql.append("WHERE ");
				sql.append("  idtelefone = ? ");
			}
			
			stat.close();
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getTelefone().getDdd());
			stat.setString(2, obj.getTelefone().getNumero());
			stat.setInt(3, obj.getId());
			
			stat.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
			}
			Conn.stopConn(conn);
		}
		return resultado;
	}

	
	public boolean excluir(Usuario usu) {
		Connection conn = Conn.getConnection();
		if (conn == null) 
			return false;
		
		boolean resultado = true;
		PreparedStatement stat = null;
		
		try {
			StringBuffer sql = new StringBuffer();
			if(usu.getTelefone().getId() != null) {
				sql.append("DELETE FROM telefone WHERE idtelefone = ? ");
			}
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, usu.getId());
			stat.execute();
			
			sql = new StringBuffer();
			sql.append("DELETE FROM usuario WHERE idusuario = ? ");
			
			stat.close();
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, usu.getId());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
			}
			Conn.stopConn(conn);
		}
		return resultado;
	}

	@Override
	public List<Usuario> obterTodos() {
		Connection conn = Conn.getConnection();
		
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		if (conn == null) 
			return null;
			
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  u.idusuario, ");
			sql.append("  u.nome, ");
			sql.append("  u.cpf, ");
			sql.append("  u.email, ");
			sql.append("  u.sexo, ");
			sql.append("  u.perfil, ");
			sql.append("  u.senha, ");
			sql.append("  t.idtelefone, ");
			sql.append("  t.ddd, ");
			sql.append("  t.numero ");
			sql.append("FROM ");
			sql.append("  usuario u LEFT JOIN telefone t ON u.idusuario = t.idtelefone ");
			sql.append("ORDER BY ");
			sql.append("  u.nome ");
			
			rs = conn.createStatement().executeQuery(sql.toString());
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSexo(Sexo.valuesOf(rs.getInt("sexo")));
				usuario.setPerfil(Perfil.valuesOf(rs.getInt("perfil")));
				usuario.setSenha(rs.getString("senha"));
				
				usuario.setTelefone(new Telefone());
				
				// esse codigo eh necessario por conta de o id estar nulo
				Object idTelefone = rs.getObject("id_telefone");
						
				usuario.getTelefone().setId(idTelefone == null ? null : (Integer) idTelefone);
				usuario.getTelefone().setDdd(rs.getString("ddd"));
				usuario.getTelefone().setNumero(rs.getString("numero"));
				
				listaUsuario.add(usuario);
			}
			
		} catch (SQLException e) {
			listaUsuario = null;
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
			}
			Conn.stopConn(conn);
		}
		
		return listaUsuario;
		
	}



	@Override
	public boolean excluir(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	



}
