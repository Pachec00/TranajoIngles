package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import modelo.Jugador;
import modelo.TextFieldCustom;
import service.JugadorService;
import service.JugadorsServiceException;

public class PanelInicio extends JFrame {
	public PanelInicio() {
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -2571490972066091586L;

	private Interfaz interfaz;
	private finalPanel fPanel;
	private JugadorService js;
	private Jugador jugador;

	public void inicializar() throws JugadorsServiceException, SQLException {
		js = new JugadorService();
		interfaz = new Interfaz(this);
		fPanel = new finalPanel(this);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("You want to be a robot?");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1980, 1060);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblBienvenida = new JLabel("WELCOME");
		lblBienvenida.setFont(new Font("Elephant", Font.PLAIN, 30));
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(10, 127, 1904, 111);
		getContentPane().add(lblBienvenida);

		JLabel lblNewLabel = new JLabel("TO");
		lblNewLabel.setFont(new Font("Elephant", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 249, 1904, 85);
		getContentPane().add(lblNewLabel);

		JLabel lblQuien = new JLabel("WHO WANTS TO BE A ROBOT!");
		lblQuien.setFont(new Font("Elephant", Font.PLAIN, 30));
		lblQuien.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuien.setBounds(10, 345, 1904, 203);
		getContentPane().add(lblQuien);

		JLabel lblUsuario = new JLabel("User");
		lblUsuario.setFont(new Font("Elephant", Font.PLAIN, 20));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(744, 663, 81, 32);
		getContentPane().add(lblUsuario);

		TextFieldCustom textFieldUsuario = new TextFieldCustom();
		textFieldUsuario.setBounds(859, 663, 215, 32);
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JButton btnRegistrar = new JButton("Register");
		btnRegistrar.setBounds(1122, 668, 89, 23);
		getContentPane().add(btnRegistrar);

		ActionListener acRegistrarUsuario = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					 jugador = new Jugador();
					jugador.setNombre(textFieldUsuario.getText());
					jugador.setPuntuacion(0);
					if (js.registrarJugador(jugador)) {
						textFieldUsuario.setEnabled(false);
						btnRegistrar.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "EL NOMBRE YA EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);

					}
					interfaz.empezarTimer();

				} catch (JugadorsServiceException e1) {
					e1.printStackTrace();
				}

			}
		};

		btnRegistrar.addActionListener(acRegistrarUsuario);

		setVisible(true);

	}

	public void ponerPantalla() {
		setContentPane(interfaz);
		revalidate();
	}

	public void cambiarPantallaFinal() {
		setContentPane(fPanel);
		revalidate();
	}

	public List<Jugador> crearTabla() {
		List<Jugador> lista = new ArrayList<Jugador>();
		try {
			lista = js.consultarListaJugadoresService();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void añadirPuntuacion(Integer tiempo) {
		
		try {
			js.insertarPuntuacionService(jugador, tiempo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
