package interfaz;

import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Jfondo extends JPanel {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7859082401349094092L;
	private Image imagen;
	
	@Override
	public void paint(Graphics g) {
		URL url;
		try {
			url = new URL("https://img.freepik.com/foto-gratis/fondo-mano-robot-3d-vista-lateral-tecnologia-ai_53876-129789.jpg?size=626&ext=jpg&ga=GA1.1.1826414947.1699833600&semt=ais");
			ImageIcon img = new ImageIcon(url);
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		

		
		
		
		setOpaque(false);
		super.paint(g);
	}
	
}
