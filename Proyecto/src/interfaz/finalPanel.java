package interfaz;

import java.awt.Font;
import java.util.Comparator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import modelo.Jugador;
import modelo.TablaModel;
import java.awt.Color;

public class finalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private TablaModel model;

	private Comparator<Jugador> a;

	public finalPanel(PanelInicio pI) {
		setBackground(new Color(113, 191, 208));

		setBounds(100, 100, 1980, 1060);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Congratulations!!!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 40));
		lblNewLabel.setBounds(715, 184, 431, 54);
		add(lblNewLabel);

		JLabel lblRanking = new JLabel("Final Ranking");
		lblRanking.setFont(new Font("Segoe Print", Font.PLAIN, 40));
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setBounds(734, 313, 380, 90);
		add(lblRanking);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(485, 414, 886, 533);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.getTableHeader().setReorderingAllowed(false);
		model = new TablaModel();
		table.setModel(model);

		JLabel lblAutor = new JLabel(
				"Made by: Pablo Gonzalez Parrado, Fabio Rodriguez Yesares, Patricia Miranda Oliver, Miranda Remasal Serrano, Blas Lopez Calero");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblAutor.setBounds(10, 980, 1904, 69);
		add(lblAutor);

		a = new Comparator<Jugador>() {

			@Override
			public int compare(Jugador o1, Jugador o2) {

				return o2.getPuntuacion().compareTo(o1.getPuntuacion());
			}

		};

	}

	public void crearTabla(List<Jugador> lista) {
		model.setLista(lista);
		model.getLista().sort(a);
		model.fireTableDataChanged();

	}
}
