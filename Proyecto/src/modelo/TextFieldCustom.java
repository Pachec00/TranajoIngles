package modelo;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TextFieldCustom extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3616511314219006639L;

	private Integer longMax;

	public TextFieldCustom() {
		longMax = 0;
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (longMax < 20) {
					longMax++;
				} else {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				setBackground(Color.LIGHT_GRAY);
			}
		});
	}

}
