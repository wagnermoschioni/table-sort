package br.com.table.main;


import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import br.com.table.dao.PessoaDAO;

import org.eclipse.swt.widgets.TableColumn;

public class MainApplication {

	// Metodo Main
	public static void main(String[] args) throws SQLException {

		Display display = new Display();
		Shell shell = new Shell(display);
		TableDesign design = new TableDesign();

		shell.setLayout(new FillLayout());
		shell.setText("Funcionários");

		Table tabela = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tabela.setHeaderVisible(true);
		tabela.setLinesVisible(true);

		String[] titulos = { "Nome", "Idade", "Cargo" };

		for (String titulo : titulos) {
			TableColumn coluna = new TableColumn(tabela, SWT.NONE);
			coluna.setText(titulo);
			coluna.pack();

		}

		List<String[]> listaPessoas = new PessoaDAO().listarPessoas();

		for (String[] pessoa : listaPessoas) {
			TableItem item = new TableItem(tabela, SWT.NONE);
			item.setText(pessoa);
		}

		// chamando metodo aplicarFuncs
		design.aplicarFuncs(tabela);

		shell.setSize(500, 250);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

}
