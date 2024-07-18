package br.com.table.main;
import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableDesign {
	
	public void aplicarFuncs(Table tabela) {
		
		for (TableColumn coluna : tabela.getColumns()) {
			coluna.addListener(SWT.Selection, event -> {
				TableColumn colunaSelecionada = (TableColumn) event.widget;
				int indiceColuna = getIndiceColuna(tabela, coluna);

				int ordenacao = tabela.getSortDirection() == SWT.UP ? SWT.DOWN : SWT.UP;
				tabela.setSortDirection(ordenacao);
				tabela.setSortColumn(colunaSelecionada);

				TableItem[] items = tabela.getItems();
				Arrays.sort(items, new Comparator<TableItem>() {
					@Override
					public int compare(TableItem item1, TableItem item2) {
						String text1 = item1.getText(indiceColuna);
						String text2 = item2.getText(indiceColuna);
						int compare = text1.compareTo(text2);
						if (ordenacao == SWT.DOWN) {
							compare = -compare;
						}
						return compare;
					}
				});
				
				for (int i = 0; i < items.length; i++) {
                    String[] valores = new String[tabela.getColumnCount()];
                    for (int j = 0; j < tabela.getColumnCount(); j++) {
                        valores[j] = items[i].getText(j);
                    }
                    items[i].dispose();
                    TableItem novoItem = new TableItem(tabela, SWT.NONE);
                    novoItem.setText(valores);
                }

			});
		}
	}

	public int getIndiceColuna(Table table, TableColumn column) {
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (table.getColumn(i) == column) {
				return i;
			}
		}
		return -1;
	}

}
