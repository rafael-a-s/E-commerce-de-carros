package br.unitins.carro.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("O Drive não foi encontrado.");
			e.printStackTrace();
			return null;
		}
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/carro","topicos1", "123456");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar no banco de dados.");
			e.printStackTrace();
			return null;
		}
		
		return conn;
	}
	
	public static void stopConn(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

