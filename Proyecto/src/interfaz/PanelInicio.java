package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modelo.Jugador;
import modelo.TextFieldCustom;
import service.JugadorService;
import service.JugadorsServiceException;

public class PanelInicio extends JFrame {
	public PanelInicio() {
		setResizable(false);
		getContentPane().setBackground(new Color(113, 191, 208));
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

		setTitle("You want to be a robot?");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1980, 1060);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setLayout(null);

		JLabel lblQuien = new JLabel();
		lblQuien.setFont(new Font("Segoe Print", Font.PLAIN, 30));
		lblQuien.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuien.setBounds(100, 100, 1750, 500);
		ImageIcon imagenIcon = new ImageIcon(getClass().getResource("/src/Logo.PNG"));
		lblQuien.setIcon(new ImageIcon(imagenIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH)));
		getContentPane().add(lblQuien);

		JLabel lblUsuario = new JLabel("User");
		lblUsuario.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(744, 663, 81, 32);
		getContentPane().add(lblUsuario);

		TextFieldCustom textFieldUsuario = new TextFieldCustom();
		textFieldUsuario.setBounds(859, 663, 215, 32);
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setHorizontalAlignment(JTextField.CENTER);

		JButton btnRegistrar = new JButton("Register");
		btnRegistrar.setBounds(1122, 668, 89, 23);
		getContentPane().add(btnRegistrar);


		ActionListener acRegistrarUsuario = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					jugador = new Jugador();
					if (!textFieldUsuario.getText().isEmpty()) {
						jugador.setNombre(textFieldUsuario.getText());
						jugador.setPuntuacion(0);
						jugador = js.registrarJugador(jugador);
						if (jugador.getId() != null) {
							textFieldUsuario.setEnabled(false);
							btnRegistrar.setEnabled(false);
							interfaz.empezarTimer();
						} else {
							JOptionPane.showMessageDialog(null, "That name already exists", "ERROR",
									JOptionPane.ERROR_MESSAGE);

						}
					} else {
						JOptionPane.showMessageDialog(null, "Please write something", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

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

	public void crearTabla() {
		List<Jugador> lista = new ArrayList<Jugador>();
		try {
			lista = js.consultarListaJugadoresService();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fPanel.crearTabla(lista);
	}

	public void añadirPuntuacion(Integer tiempo) {

		try {
			js.insertarPuntuacionService(jugador, tiempo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Jugador getJugador() {
		return this.jugador;
	}

	public Integer consultarPuntuacion() {

		return js.consultarPuntuacion(jugador);
	}

}
