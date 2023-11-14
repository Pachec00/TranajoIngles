package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

		getContentPane().setLayout(null);
	
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -2571490972066091586L;

	private Interfaz interfaz;

	public void inicializar() throws JugadorsServiceException, SQLException {

		interfaz = new Interfaz(this);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("You want to be a robot?");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1980, 1060);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JLabel lblBienvenida = new JLabel("WELCOME");
		lblBienvenida.setFont(new Font("Elephant", Font.PLAIN, 30));
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(10, 127, 1904, 111);
		add(lblBienvenida);

		JLabel lblNewLabel = new JLabel("TO");
		lblNewLabel.setFont(new Font("Elephant", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 249, 1904, 85);
		add(lblNewLabel);

		JLabel lblQuien = new JLabel("WHO WANTS TO BE A ROBOT!");
		lblQuien.setFont(new Font("Elephant", Font.PLAIN, 30));
		lblQuien.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuien.setBounds(10, 345, 1904, 203);
		add(lblQuien);

		JLabel lblUsuario = new JLabel("User");
		lblUsuario.setFont(new Font("Elephant", Font.PLAIN, 20));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(744, 663, 81, 32);
		add(lblUsuario);

		TextFieldCustom textFieldUsuario = new TextFieldCustom();
		textFieldUsuario.setBounds(859, 663, 215, 32);
		add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JButton btnRegistrar = new JButton("Register");
		btnRegistrar.setBounds(1122, 668, 89, 23);
		add(btnRegistrar);

		JugadorService js = new JugadorService();
		ActionListener acRegistrarUsuario = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Jugador jugador = new Jugador();
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

}
