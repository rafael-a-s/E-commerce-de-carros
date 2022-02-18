package br.unitins.carro.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.carro.connection.Conn;
import br.unitins.model.Carro;
import br.unitins.model.Cor;
import br.unitins.model.Fabricante;
import br.unitins.model.ItemVenda;
import br.unitins.model.Usuario;
import br.unitins.model.Venda;



public class VendaDAO implements DAO<Venda>{

	@Override
	public boolean incluir(Venda obj) {
		Connection conn = Conn.getConnection();
		if (conn == null) 
			return false;
		
		// controlando a transacao de forma manual
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		boolean resultado = true;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO venda ( ");
		sql.append("  data, ");
		sql.append("  id_usuario, ");
		sql.append("  totalvenda ");
		sql.append(") VALUES (");
		sql.append("  ?,  ");
		sql.append("  ?,  ");
		sql.append("  ?  ");
		sql.append(") ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, Date.valueOf(obj.getData()));
			stat.setInt(2, obj.getUsuario().getId());
			stat.setDouble(3, obj.getTotalVenda());
			
			stat.execute();
			
			
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt("idvenda"));
			}
			
			salvarItensVenda(obj, conn);
			
			// salvando por definitivo os dados no banco
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			resultado = false;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return resultado;
	}
private void salvarItensVenda(Venda venda, Connection conn) throws SQLException{
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO item_venda ( ");
		sql.append("  valor, ");
		sql.append("  quantidade, ");
		sql.append("  id_venda, ");
		sql.append("  id_carro ");
		sql.append(") VALUES (");
		sql.append("  ?,  ");
		sql.append("  ?,  ");
		sql.append("  ?,  ");
		sql.append("  ?  ");
		sql.append(") ");
		
		PreparedStatement stat = null;
		for (ItemVenda item : venda.getListaItemVenda()) {
			stat = conn.prepareStatement(sql.toString());
			stat.setDouble(1, item.getValor());
			stat.setInt(2, item.getQuantidade());
			stat.setInt(3, venda.getId());
			stat.setInt(4, item.getCarro().getId());
			
			stat.execute();
		}
		
	}

	@Override
	public boolean alterar(Venda obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Venda> obterTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Venda> obterTodos(Usuario usuario) {
		Connection conn = Conn.getConnection();
		
		List<Venda> listaVenda = new ArrayList<Venda>();
		
		if (conn == null) 
			return null;
			
		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  v.idvenda, ");
			sql.append("  v.data, ");
			sql.append("  v.totalvenda ");
			sql.append("FROM ");
			sql.append("  venda v ");
			sql.append("WHERE  ");
			sql.append("  v.id_usuario = ? ");
			sql.append("ORDER BY ");
			sql.append("  v.data DESC ");
			
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, usuario.getId());
			
			rs = stat.executeQuery();
			while(rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("idvenda"));
				Date datavenda = rs.getDate("data");
				if(datavenda == null)
					venda.setData(null);
				else
					venda.setData(datavenda.toLocalDate());
				venda.setUsuario(usuario);
				venda.setTotalVenda(rs.getDouble("totalvenda"));
				venda.setListaItemVenda(obterItensVenda(venda.getId()));
				
				
				listaVenda.add(venda);
			}
			
		} catch (SQLException e) {
			listaVenda = null;
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		
		return listaVenda;
		
	}
	private List<ItemVenda> obterItensVenda(Integer id) {
		
		Connection conn = Conn.getConnection();
		
		List<ItemVenda> lista = new ArrayList<ItemVenda>();
		
		if (conn == null) 
			return null;
			
		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT  ");
			sql.append("  i.iditemvenda, ");
			sql.append("  i.valor, ");
			sql.append("  i.quantidade, ");
			sql.append("  c.idcarro, ");
			sql.append("  c.nome AS nome_carro, ");
			sql.append("  c.anolancamento, ");
			sql.append("  c.descricao, ");
			sql.append("  c.preco, ");
			sql.append("  c.cor, ");
			sql.append("  m.idmarca, ");
			sql.append("  m.nome AS nome_marca ");
			sql.append("FROM  ");
			sql.append("	item_venda i, ");
			sql.append("	carro c, ");
			sql.append("	marca m ");
			sql.append("WHERE  ");
			sql.append("	i.id_carro = c.idcarro AND ");
			sql.append("	c.id_marca = m.idmarca AND ");
			sql.append("	i.id_venda = ? ");
			
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			while(rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setId(rs.getInt("iditemvenda"));
				item.setValor(rs.getDouble("valor"));
				item.setQuantidade(rs.getInt("quantidade"));
				
				item.setCarro(new Carro());
				item.getCarro().setId(rs.getInt("idcarro"));
				item.getCarro().setNome(rs.getString("nome_carro"));
				item.getCarro().setAnolancamento(rs.getInt("anolancamento"));
				item.getCarro().setDescricao(rs.getString("descricao"));
				item.getCarro().setPreco(rs.getDouble("preco"));
				item.getCarro().setCor(Cor.valuesOf(rs.getInt("cor")));
				
				
				item.getCarro().setMarca(new Fabricante());
				item.getCarro().getMarca().setIdmarca(rs.getInt("idmarca"));
				item.getCarro().getMarca().setNome(rs.getString("nome_marca"));
				
				
				lista.add(item);
			}
			
		} catch (SQLException e) {
			lista = null;
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		
		return lista;	
		
	}
}
