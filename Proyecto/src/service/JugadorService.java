package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import bd.OpenConnection;
import dao.JugadorDao;
import modelo.Jugador;

public class JugadorService {

	private OpenConnection openConnection;

	public JugadorService() {
		openConnection = new OpenConnection();
	}

	public Boolean registrarJugador(Jugador jugador) throws JugadorsServiceException {
		Connection conn = null;

		try {

			conn = openConnection.getConnection();
			JugadorDao jd = new JugadorDao();

			Boolean r = jd.registrarJugadorDao(conn, jugador);

			return r;

			/*
			 * TODO si devuelve false lanzar un mensaje de usuario ya existe en la interfaz.
			 *
			 */

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("El nombre de este usuario ya existe");

			return false;
		} catch (Exception e) {
			System.err.println("Error en la base de datos");
			throw new JugadorsServiceException("Error al insertar el jugador", e);

		} finally {
			try {
				conn.close();
			} catch (Exception e2) {

			}
		}
	}

	public List<Jugador> consultarListaJugadoresService() throws SQLException {
		Connection conn = null;

		try {
			conn = openConnection.getConnection();
			JugadorDao jd = new JugadorDao();
			List<Jugador> jugadores = jd.consultarListaJugadoresDao(conn);

			return jugadores;
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}

	public void insertarPuntuacionService(Jugador jugador, Integer tiempo) throws SQLException {
		Connection conn = null;

		try {
			conn = openConnection.getConnection();
			JugadorDao jd = new JugadorDao();

			if (tiempo <= 5 && tiempo <= 9) {
				jugador.setPuntuacion(150);
			} else if (tiempo <= 10 && tiempo <= 14) {
				jugador.setPuntuacion(100);
			} else if (tiempo <= 15 && tiempo <= 19) {
				jugador.setPuntuacion(50);
			} else if (tiempo <= 20 && tiempo <= 24) {
				jugador.setPuntuacion(25);
			}else if (tiempo == 25) {
				jugador.setPuntuacion(0);
			}
			
			jd.insertarPuntuacionDao(conn, jugador);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}

}
