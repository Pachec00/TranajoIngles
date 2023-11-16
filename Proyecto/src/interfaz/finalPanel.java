package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class finalPanel extends JPanel {

	private static final long serialVersionUID = 1L;


	public finalPanel() {
		
		
		setBounds(100, 100, 1980, 1060);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Congratulations!!!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(837, 113, 431, 54);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Your points:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(871, 308, 226, 82);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PUNTOS");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(1107, 329, 173, 40);
		add(lblNewLabel_2);
		
		
		
		
		
		
	}
}
