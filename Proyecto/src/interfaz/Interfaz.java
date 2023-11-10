package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import controlador.service.ControlService;
import modelo.Preguntas;
import service.PreguntasService;

public class Interfaz extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 2738291975208723435L;

	private Timer timer;
	private Timer timerInfinito;
	private Integer segundos;
	private Long segundosInfinito;
	private JLabel lblTimer;
	private PanelInicio pI;
	private PreguntasService ps;
	private Preguntas pregunta;
	private JTextArea txtPregunta;
	private JButton btnA;
	private JButton btnB;
	private JButton btnC;
	private JButton btnD;
	private JLabel lblPuntuacion;
	private JLabel lblNumeroPregunta;
	private Boolean primeraVez;
	private Integer id;
	public Boolean marcado;
	private JButton btnPasarPagina;
	private ActionListener acTimer;
	private ActionListener acTimerInfinito;
	private Boolean correcto;

	public Interfaz(PanelInicio pI) throws SQLException {

		this.pI = pI;
		setLayout(null);
		setBounds(100, 100, 1980, 1060);
		id = 1;
		correcto = false;
		primeraVez = true;
		marcado = false;
		ps = new PreguntasService();
		lblPuntuacion = new JLabel("Points: ");
		lblPuntuacion.setBounds(20, 27, 161, 111);
		lblPuntuacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPuntuacion);

		txtPregunta = new JTextArea();
		txtPregunta.setAutoscrolls(false);
		txtPregunta.setEditable(false);
		txtPregunta.setWrapStyleWord(true);
		txtPregunta.setBackground(new Color(128, 128, 128));
		txtPregunta.setFont(new Font("Monospaced", Font.PLAIN, 40));
		txtPregunta.setLineWrap(true);

		pregunta = new Preguntas();
		pregunta = ps.consultarPregunta(id);

		txtPregunta.setText(pregunta.getPregunta());
		txtPregunta.setBounds(20, 149, 1882, 465);
		add(txtPregunta);

		btnA = new JButton(pregunta.getListaRespuesta().get(0));
		btnA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnA.setOpaque(true);
		btnA.setActionCommand("A");
		btnA.setBackground(new Color(255, 128, 128));
		btnA.setForeground(new Color(0, 0, 0));
		btnA.setBounds(20, 677, 812, 134);
		add(btnA);

		btnB = new JButton(pregunta.getListaRespuesta().get(1));
		btnB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnB.setBackground(new Color(128, 255, 255));
		btnB.setActionCommand("B");
		btnA.setOpaque(true);
		btnB.setBounds(20, 822, 812, 134);
		add(btnB);

		btnC = new JButton(pregunta.getListaRespuesta().get(2));
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnC.setBackground(new Color(128, 255, 128));
		btnC.setActionCommand("C");
		btnA.setOpaque(true);
		btnC.setBounds(1135, 677, 767, 134);
		add(btnC);
		// TODO poner flecha en la repuesta seleccionada, poner bonito el login, poner
		// tamaño fijo a la ventana al frame, que sea fijo, quitar barra de arriba,
		// hacer una aplicasdasacion aparte que cambie un registro en la base datos para
		// pasar la pagina, tengo un boton que cada vez q le doy aumenta en uno ese
		// valor de la base de datos

		btnD = new JButton(pregunta.getListaRespuesta().get(3));
		btnD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnD.setActionCommand("D");
		btnD.setBackground(new Color(255, 255, 128));
		btnA.setOpaque(true);
		btnD.setBounds(1135, 824, 767, 134);
		add(btnD);

		btnPasarPagina = new JButton("");
		btnPasarPagina.setBorderPainted(false);
		btnPasarPagina.setBackground(new Color(255, 255, 128));
		btnPasarPagina.setOpaque(true);

		try {
			URL url = new URL("https://cdn-icons-png.flaticon.com/128/3248/3248150.png");
			ImageIcon img = new ImageIcon(url);
			btnPasarPagina.setIcon(img);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		btnPasarPagina.setBounds(1741, 48, 161, 90);
		btnPasarPagina.setEnabled(false);
		add(btnPasarPagina);

		lblNumeroPregunta = new JLabel(id + "/10");
		lblNumeroPregunta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumeroPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPregunta.setBounds(1602, 48, 129, 90);
		add(lblNumeroPregunta);

		JLabel titulo = new JLabel("DO YOU WANT TO BE A ROBOT?");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(387, 37, 1170, 101);
		add(titulo);
		segundos = 5000;
		acTimer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if ((segundos - 1000) / 1000 > -1) {
					lblTimer.setVisible(true);
					segundos = segundos - 1000;
					lblTimer.setText(String.valueOf(segundos / 1000));
				} else {
					lblTimer.setVisible(false);
					btnC.setEnabled(false);
					btnA.setEnabled(false);
					btnD.setEnabled(false);
					btnB.setEnabled(false);
					btnPasarPagina.setEnabled(true);
					if (!marcado) {
						correcto = false;
					} // asdsadasdas
					if (primeraVez) {
						if (correcto) {

							JOptionPane.showMessageDialog(null, "YOU ARE CORRECT, OLEEE", "CORRECT ANSWER",
									JOptionPane.INFORMATION_MESSAGE);
							primeraVez = false;

						} else {

							JOptionPane.showMessageDialog(null, "THATS INCORRECT, NOT OLEEE", "WRONG ANSWER",
									JOptionPane.ERROR_MESSAGE);
							primeraVez = false;

						}
						
					}
					empezarTimer();
				}

			}
		};
		acTimerInfinito = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer numeroin = 0;
				while ((segundosInfinito - 1000) / 1000 > -1) {
					segundosInfinito = segundosInfinito - 1000;
					ControlService cS = new ControlService();

					try {
						numeroin = cS.consultarControlService();
						System.out.println(numeroin);
						if (numeroin == 1) {
							pI.ponerPantalla();
							cambiarPregunta(1);
							break;
						}else if(id+1==numeroin) {
							id++;
							cambiarPregunta(id);
							break;
						}
					} catch (SQLException e1) {
					}

				}
			}
		};
		ActionListener acPasarPagina = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (id < 10) {
					id++;
					try {
						cambiarPregunta(id);
						// if id +1 == control --> id++ cambiar, si estoy en el 10 no hacerlo primer
						// caso cuando control sea igual 1
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				} else {
					// TODO poner posicion final con otra pantalla
				}

			}
		};

		ActionListener acComprobacionRespuesta = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				marcado = true;
				if (ps.validarRespuesta(pregunta, e.getActionCommand())) {
					correcto = true;
				} else {
					correcto = false;
				}
				btnA.setEnabled(false);
				btnB.setEnabled(false);
				btnC.setEnabled(false);
				btnD.setEnabled(false);

			}
		};

		timer = new Timer(1000, acTimer);
	
		lblTimer = new JLabel("");
		lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setBounds(904, 715, 150, 65);
		add(lblTimer);
		btnPasarPagina.addActionListener(acPasarPagina);
		btnA.addActionListener(acComprobacionRespuesta);
		btnB.addActionListener(acComprobacionRespuesta);
		btnC.addActionListener(acComprobacionRespuesta);
		btnD.addActionListener(acComprobacionRespuesta);

	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void cambiarPregunta(Integer id) throws SQLException {
		marcado = false;
		pregunta = ps.consultarPregunta(id);
		txtPregunta.setText(pregunta.getPregunta());
		btnA.setText(pregunta.getListaRespuesta().get(0));
		btnB.setText(pregunta.getListaRespuesta().get(1));
		btnC.setText(pregunta.getListaRespuesta().get(2));
		btnD.setText(pregunta.getListaRespuesta().get(3));
		btnA.setEnabled(true);
		btnB.setEnabled(true);
		btnD.setEnabled(true);
		btnC.setEnabled(true);
		timer = new Timer(1000, acTimer);
		btnPasarPagina.setEnabled(false);
		segundos = 5000;
		timer.start();
		primeraVez = true;
		lblNumeroPregunta.setText(id + "/10");
		timerInfinito.stop();		
		revalidate();

	}

	public void empezarTimer() {
		timerInfinito = new Timer(1000, acTimerInfinito);
		segundosInfinito = 1000000000000000000L;
		timerInfinito.start();
	}

}
