package br.com.table.dao;
import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {

	public Connection getConexao() {
		
		Connection result = null;
		try {			
			
			String url = "jdbc:h2:mem:testdb";
			String user = "sa";
			String password = "";
			
			
			// Carregando o driver JDBC do H2
            Class.forName("org.h2.Driver");
            
            result = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
		
	}
}
