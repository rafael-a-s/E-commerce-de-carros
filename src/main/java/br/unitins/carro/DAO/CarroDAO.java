package br.unitins.carro.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.carro.connection.Conn;
import br.unitins.model.Carro;
import br.unitins.model.Cor;
import br.unitins.model.Fabricante;

public class CarroDAO implements DAO<Carro> {

	@Override
	public boolean incluir(Carro carro) {

		Connection conn = Conn.getConnection();
		if (conn == null)
			return false;

		boolean resultado = true;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO carro (");
		sql.append(" nome, ");
		sql.append(" anolancamento, ");
		sql.append(" descricao, ");
		sql.append(" id_marca, ");
		sql.append(" cor, ");
		sql.append(" preco ");
		sql.append(") VALUES ( ");
		sql.append(" ?, ");
		sql.append(" ?, ");
		sql.append(" ?, ");
		sql.append(" ?, ");
		sql.append(" ?, ");
		sql.append(" ? ");
		sql.append(")");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, carro.getNome());
			stat.setInt(2, carro.getAnolancamento());
			stat.setString(3, carro.getDescricao());
			stat.setInt(4, carro.getMarca().getIdmarca());
			stat.setInt(5, carro.getCor().getId());
			stat.setDouble(6, carro.getPreco());

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
	public boolean alterar(Carro carro) {
		Connection conn = Conn.getConnection();
		if (conn == null)
			return false;
		boolean resultado = true;
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE carro SET ");
		sql.append(" nome = ?, ");
		sql.append(" anolancamento = ?, ");
		sql.append(" descricao = ?, ");
		sql.append(" id_marca = ?, ");
		sql.append(" preco = ?, ");
		sql.append(" cor = ? ");
		sql.append(" WHERE ");
		sql.append(" idcarro = ?");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, carro.getNome());
			stat.setInt(2, carro.getAnolancamento());
			stat.setString(3, carro.getDescricao());
			stat.setInt(4, carro.getMarca().getIdmarca());
			stat.setDouble(5, carro.getPreco());
			stat.setInt(6, carro.getCor().getId());
			stat.setInt(7, carro.getId());
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
	public boolean excluir(Integer id) {
		Connection conn = Conn.getConnection();
		if (conn == null)
			return false;

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM carro WHERE idcarro = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

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
	public List<Carro> obterTodos() {
		Connection conn = Conn.getConnection();

		List<Carro> listaCarros = new ArrayList<Carro>();

		if (conn == null)
			return null;

		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  c.idcarro, ");
			sql.append("  c.nome, ");
			sql.append("  c.anolancamento, ");
			sql.append("  c.descricao, ");
			sql.append("  c.preco, ");
			sql.append("  c.cor, ");
			sql.append("  m.idmarca, ");
			sql.append("  m.nome AS nome_marca ");
			sql.append("FROM ");
			sql.append("  carro c INNER JOIN marca m ON c.id_marca = m.idmarca ");
			sql.append("ORDER BY ");
			sql.append("  c.nome ");

			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Carro carro = new Carro();
				carro.setId(rs.getInt("idcarro"));
				carro.setNome(rs.getString("nome"));
				carro.setAnolancamento(rs.getInt("anolancamento"));
				carro.setDescricao(rs.getString("descricao"));
				carro.setPreco(rs.getDouble("preco"));
				carro.setCor(Cor.valuesOf(rs.getInt("cor")));
				

				carro.setMarca(new Fabricante());
				carro.getMarca().setIdmarca(rs.getInt("idmarca"));
				carro.getMarca().setNome(rs.getString("nome_marca"));

				listaCarros.add(carro);
			}

		} catch (SQLException e) {
			listaCarros = null;
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

		return listaCarros;

	}

	public Carro buscarPorId(Integer id) {
		Connection conn = Conn.getConnection();

		if (conn == null)
			return null;

		PreparedStatement stat = null;
		ResultSet rs = null;
		Carro carro = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  c.idcarro, ");
			sql.append("  c.nome, ");
			sql.append("  c.anolancamento, ");
			sql.append("  c.descricao, ");
			sql.append("  c.cor, ");
			sql.append("  c.preco, ");
			sql.append("  m.idmarca, ");
			sql.append("  m.nome AS nome_marca ");
			sql.append("FROM ");
			sql.append("  carro c INNER JOIN marca m ON c.id_marca = m.idmarca ");
			sql.append("WHERE ");
			sql.append(" c.idcarro = ? ");

			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

			rs = stat.executeQuery();

			if (rs.next()) {
				carro = new Carro();
				carro.setId(rs.getInt("idcarro"));
				carro.setNome(rs.getString("nome"));
				carro.setAnolancamento(rs.getInt("anolancamento"));
				carro.setDescricao(rs.getString("descricao"));
				carro.setCor(Cor.valuesOf(rs.getInt("cor")));
				carro.setPreco(rs.getDouble("preco"));
				carro.setMarca(new Fabricante());
				carro.getMarca().setIdmarca(rs.getInt("idmarca"));
				carro.getMarca().setNome(rs.getString("nome_marca"));
			}

		} catch (SQLException e) {
			carro = null;
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
			}
			try {
				rs.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

		return carro;

	}

	public List<Carro> buscarPorNome(String nome) {

		Connection conn = Conn.getConnection();

		List<Carro> listaCarros = new ArrayList<Carro>();

		if (conn == null)
			return null;

		PreparedStatement stat = null;
		ResultSet rs = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  c.idcarro, ");
			sql.append("  c.nome, ");
			sql.append("  c.anolancamento, ");
			sql.append("  c.descricao, ");
			sql.append("  c.cor, ");
			sql.append("  c.preco, ");
			sql.append("  m.idmarca, ");
			sql.append("  m.nome AS nome_marca ");
			sql.append("FROM ");
			sql.append("  carro c INNER JOIN marca m ON c.id_marca = m.idmarca ");
			sql.append(" WHERE ");
			sql.append("  c.nome iLIKE ? ");

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + nome + "%");

			rs = stat.executeQuery();

			while (rs.next()) {
				Carro carro = new Carro();

				carro.setId(rs.getInt("idcarro"));
				carro.setNome(rs.getString("nome"));
				carro.setAnolancamento(rs.getInt("anolancamento"));
				carro.setDescricao(rs.getString("descricao"));
				carro.setCor(Cor.valuesOf(rs.getInt("cor")));
				carro.setPreco(rs.getDouble("preco"));
				carro.setMarca(new Fabricante());
				carro.getMarca().setIdmarca(rs.getInt("idmarca"));
				carro.getMarca().setNome(rs.getString("nome_marca"));

				listaCarros.add(carro);

			}

		} catch (SQLException e) {
			listaCarros = null;
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {

			}
			try {
				rs.close();
			} catch (SQLException e) {

			}
			try {
				conn.close();

			} catch (SQLException e) {
			}
		}

		return listaCarros;

	}

}