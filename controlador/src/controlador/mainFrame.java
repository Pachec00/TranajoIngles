package controlador;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import controlador.service.ControlService;

public class mainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7369002336793870263L;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainFrame() {
		setResizable(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 671, 476);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnPasarPagina = new JButton("Pasar de Pagina");
		btnPasarPagina.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPasarPagina.setBounds(188, 126, 257, 162);
		getContentPane().add(btnPasarPagina);
		
		btnPasarPagina.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlService cService = new ControlService();
				try {
					cService.aumentarControlService();
				} catch (SQLException e1) {
					
				}
				}
		});
		
		
	}

}
