import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;

public class MainApplication {
	
	
	//Metodo Main
	public static void main(String[] args) {
		
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
		  
		  for (String[] linha : dados) {
	            TableItem item = new TableItem(tabela, SWT.NONE);
	            item.setText(linha);
	        }
		  
		  
		  //chamando metodo aplicarFuncs 	
		  design.aplicarFuncs(tabela);		  
				  
		  shell.setSize(400, 200);
		  shell.open();
		  
		  while(!shell.isDisposed()) {
			  if (!display.readAndDispatch()) {
				  display.sleep();
			  }
		  }
		  display.dispose();
		  		 
	}
	
	
	
}
