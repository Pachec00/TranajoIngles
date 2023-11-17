package interfaz;

import java.awt.Font;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import modelo.Jugador;
import modelo.TablaModel;

public class finalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private TablaModel model;
	private PanelInicio pI;

	public finalPanel(PanelInicio pI) {
		this.pI = pI;
		setBounds(100, 100, 1980, 1060);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Congratulations!!!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(676, 185, 431, 54);
		add(lblNewLabel);

		JLabel lblRanking = new JLabel("Final Ranking");
		lblRanking.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setBounds(763, 313, 265, 90);
		add(lblRanking);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(485, 414, 886, 533);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.getTableHeader().setReorderingAllowed(false);
		model = new TablaModel();
		table.setModel(model);


	
		Comparator<Jugador> a = new Comparator<Jugador>() {

			@Override
			public int compare(Jugador o1, Jugador o2) {
	
				return o2.getPuntuacion().compareTo(o1.getPuntuacion());
			}

		};
		model.getLista().sort(a);

	}

	public void crearTabla() {
		model.setLista(pI.crearTabla());
		model.fireTableDataChanged();

	}
}
