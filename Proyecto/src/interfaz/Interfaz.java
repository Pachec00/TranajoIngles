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
    private ActionListener acTimer;
    private ActionListener acTimerInfinito;
    private Boolean correcto;
    private JLabel flechaB;
    private JLabel flechaC;
    private JLabel flechaD;
    private JLabel flechaA;

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

        btnD = new JButton(pregunta.getListaRespuesta().get(3));
        btnD.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnD.setActionCommand("D");
        btnD.setBackground(new Color(255, 255, 128));
        btnA.setOpaque(true);
        btnD.setBounds(1135, 824, 767, 134);
        add(btnD);

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

                    if (!marcado) {
                        correcto = false;
                    } 
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

                segundosInfinito = segundosInfinito - 1000;
                ControlService cS = new ControlService();
                try {
                    numeroin = cS.consultarControlService();
                    System.out.println(numeroin);
                    if (numeroin == id && id == 1) {
                        pI.ponerPantalla();
                        id++;
                        cambiarPregunta(1);

                    } else if (id == numeroin) {
                        cambiarPregunta(id);
                        id++;

                    }else if(id==10) {
                        
                    }
                } catch (SQLException e1) {
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
                if(e.getActionCommand().equals("A")) {
                    flechaA.setVisible(true);
                }else if(e.getActionCommand().equals("B")) {
                    flechaB.setVisible(true);
                }else if(e.getActionCommand().equals("C")) {
                    flechaC.setVisible(true);
                }else if(e.getActionCommand().equals("D")) {
                    flechaD.setVisible(true);
                }

                btnA.setEnabled(false);
                btnB.setEnabled(false);
                btnC.setEnabled(false);
                btnD.setEnabled(false);

                Integer segundosPuntuacion=25-(segundos/1000);
            }
        };

        timer = new Timer(1000, acTimer);

        lblTimer = new JLabel("");
        lblTimer.setHorizontalTextPosition(SwingConstants.LEFT);
        lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimer.setBounds(904, 715, 150, 65);
        add(lblTimer);

        flechaB = new JLabel("<---");
        flechaB.setFont(new Font("Tahoma", Font.PLAIN, 40));
        flechaB.setBounds(838, 855, 119, 81);
        add(flechaB);

        flechaC = new JLabel("--->");
        flechaC.setHorizontalAlignment(SwingConstants.RIGHT);
        flechaC.setBounds(1031, 720, 94, 60);
        flechaC.setFont(new Font("Tahoma", Font.PLAIN, 40));
        add(flechaC);

        flechaD = new JLabel("--->");
        flechaD.setHorizontalAlignment(SwingConstants.RIGHT);
        flechaD.setBounds(997, 855, 128, 81);
        flechaD.setFont(new Font("Tahoma", Font.PLAIN, 40));
        add(flechaD);
        flechaA = new JLabel("<---");
        flechaA.setFont(new Font("Tahoma", Font.PLAIN, 40));
        flechaA.setBounds(842, 715, 115, 65);
        add(flechaA);
        flechaA.setVisible(false);
        flechaB.setVisible(false);
        flechaC.setVisible(false);
        flechaD.setVisible(false);
        
    
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
        flechaA.setVisible(false);
        flechaB.setVisible(false);
        flechaC.setVisible(false);
        flechaD.setVisible(false);
        timer = new Timer(1000, acTimer);

        segundos = 10000;
        timerInfinito.stop();
        timer.start();
        primeraVez = true;
        lblNumeroPregunta.setText(id + "/10");

        revalidate();

    }

    public void empezarTimer() {
        timer.stop();
        timerInfinito = new Timer(1000, acTimerInfinito);
        segundosInfinito = 1000000000000000000L;
        timerInfinito.start();
    }

}


