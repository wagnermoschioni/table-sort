import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;

public class MainApplication {
	
	
	//Metodo Main
	public static void main(String[] args) throws SQLException {
		
		Connection con = null;
		Display display = new Display();
		Shell shell = new Shell(display);
		TableDesign design = new TableDesign();
		
		shell.setLayout(new FillLayout());
		shell.setText("Funcionários");
		
		Table tabela = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tabela.setHeaderVisible(true);
		tabela.setLinesVisible(true);		
	   
		
		 String[] titulos = {"Nome", "Idade", "Cargo"};
		 
		 for (String titulo : titulos) {
	            TableColumn coluna = new TableColumn(tabela, SWT.NONE);
	            coluna.setText(titulo);
	            coluna.pack();
	          
	        } 
		
		 
		  //Dados mockados por enquanto, após ok do cliente integrar com Banco de dados
		  String[][] dados = {
		            {"Alessandro", "30", "Engenheiro"},
		            {"Paulo", "25", "Web Designer"},
		            {"Carol", "35", "Diretora Comercial"},
		            {"David", "28", "Desenvolvedor"},
		        };
		 
		  Conexao conn = new Conexao();
		  con = conn.getConexao();
		  
		  Statement st = con.createStatement();
		  st.execute("CREATE TABLE IF NOT EXISTS pessoas (id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(255), idade INT, cargo VARCHAR(255))");
		  
		  st.executeUpdate("INSERT INTO pessoas (nome, idade, cargo) VALUES ('Carlos', 30, 'Assistente')");
		  st.executeUpdate("INSERT INTO pessoas (nome, idade, cargo) VALUES ('Atomario', 29, 'Assistente')");
		  st.executeUpdate("INSERT INTO pessoas (nome, idade, cargo) VALUES ('Jonas', 55, 'Assistente')");
		  
		  ResultSet resultSet = st.executeQuery("SELECT * FROM pessoas");
		  
		  while (resultSet.next()) {
              TableItem item = new TableItem(tabela, SWT.NONE);
              item.setText(new String[]{
                      String.valueOf(resultSet.getInt("id")),
                      resultSet.getString("nome"),
                     String.valueOf(resultSet.getInt("idade")),
                     resultSet.getString("cargo")
			
              });
          }
		  
//		  for (String[] linha : dados) {
//	            TableItem item = new TableItem(tabela, SWT.NONE);
//	            item.setText(linha);
//	        }
		  
		  resultSet.close();
          st.close();
          con.close();
		  
		  
		  //chamando metodo aplicarFuncs 	
		  design.aplicarFuncs(tabela);		  
				  
		  shell.setSize(500, 250);
		  shell.open();
		  
		  while(!shell.isDisposed()) {
			  if (!display.readAndDispatch()) {
				  display.sleep();
			  }
		  }
		  display.dispose();
		  		 
	}
	
	
	
}
