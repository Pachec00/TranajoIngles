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

	public Jugador registrarJugador(Jugador jugador) throws JugadorsServiceException {
		Connection conn = null;
		Jugador jug = new Jugador();
		try {

			conn = openConnection.getConnection();
			JugadorDao jd = new JugadorDao();
			jug = jd.registrarJugadorDao(conn, jugador);
			return jug;

		}catch (Exception e) {
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
			Integer puntos = 0;
			if (tiempo <= 5 && tiempo <= 9) {
				puntos = 150;
			} else if (tiempo <= 10 && tiempo <= 14) {
				puntos = 100;
			} else if (tiempo <= 15 && tiempo <= 19) {
				puntos = 50;
			} else if (tiempo <= 20 && tiempo <= 24) {
				puntos = 25;
			} else if (tiempo == 25) {
				puntos = 0;
			}
			jugador.getPuntuacion();
			puntos = puntos + jugador.getPuntuacion();
			jugador.setPuntuacion(puntos);
			jd.insertarPuntuacionDao(conn, jugador);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}

	public Integer consultarPuntuacion(Jugador jug) {
		Connection conn = null;
		JugadorDao jugadorDao = new JugadorDao();
		Integer puntos = 0;

		try {
			conn = openConnection.getConnection();
			puntos = jugadorDao.consultarPuntuacionDao(conn, jug);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return puntos;

	}

}
