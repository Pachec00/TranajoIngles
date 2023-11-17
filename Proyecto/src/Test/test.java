package Test;

import java.sql.SQLException;

import interfaz.PanelInicio;
import service.JugadorsServiceException;

public class test {

	public static void main(String[] args) throws JugadorsServiceException, SQLException {



//		Jugador j = new Jugador();
//		JugadorService js = new JugadorService();
//		j.setId(1);
//		j.setNombre("America");
//		js.registrarJugador(j);

		PanelInicio pI = new PanelInicio();
		
		pI.inicializar();

//color en borde y fondo textfield cambiar colo al foco




		/*
		 * TODO Hacer pantalla despues del registro para ir viendo quien
		 * va accediendo. Para que asi todos los jugadores empiecen a la vez
		 * Todos los jugadores tienen que estar en la misma pregunta a la vez
		 *
		 *
		 *
		 *
		 */






	}



}
