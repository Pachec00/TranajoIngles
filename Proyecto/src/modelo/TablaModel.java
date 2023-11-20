package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;



public class TablaModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9091737343105705648L;
	private List<Jugador> lista;

	public TablaModel() {

		lista = new ArrayList<>();
	}

	public int getRowCount() {
		return lista.size();
	}

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int column) {
		if (column == 0) {
			return "Name";
		} else{
			return "Points";
		} 
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Jugador jugador = lista.get(rowIndex);
		if (columnIndex == 0) {
			String nombre = jugador.getNombre();
			return nombre;

		} else   {
			return jugador.getPuntuacion();
		} 

	}

	public List<Jugador> getLista() {
		return lista;
	}

	public void setLista(List<Jugador> lista) {
		this.lista = lista;
	}

}
