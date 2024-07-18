package br.com.table.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PessoaDAO {

	private Connection con = null;
	
	public List<String[]> listarPessoas() throws SQLException {
		
		 Conexao conexao = new Conexao();
		 con = conexao.getConexao();
		 
		  Statement st = con.createStatement();
		  st.execute("CREATE TABLE IF NOT EXISTS pessoas (id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(255), idade INT, cargo VARCHAR(255))");
		  
		  st.executeUpdate("INSERT INTO pessoas (nome, idade, cargo) VALUES ('Carlos', 30, 'Assistente')");
		  st.executeUpdate("INSERT INTO pessoas (nome, idade, cargo) VALUES ('Atomario', 29, 'Assistente')");
		  st.executeUpdate("INSERT INTO pessoas (nome, idade, cargo) VALUES ('Jonas', 55, 'Assistente')");
		 
		  List<String[]> dados = new ArrayList<String[]>();		 
		  String sql = "SELECT * FROM Pessoas";
		
		  PreparedStatement pstmt = con.prepareStatement(sql);
		  ResultSet rs = pstmt.executeQuery();
		 
		 while (rs.next()) {
              dados.add(new String[]{
                     String.valueOf(rs.getInt("id")),
                     rs.getString("nome"),
                    String.valueOf(rs.getInt("idade")),
                    rs.getString("cargo")             
			
             });
         }
		 rs.close();
		 pstmt.close();
		 con.close();
		 
		 return dados;
		 
	}
	
}
