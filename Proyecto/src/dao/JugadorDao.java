package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Jugador;
import modelo.Preguntas;

public class JugadorDao {

	public Jugador consultarJugadorDao(Connection conn, Jugador jugador) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "select * from jugadores where nombre = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, jugador.getNombre());
			rs = stmt.executeQuery();


//ssa
			if(rs.next()) {

				jugador.setNombre(rs.getString("nombre"));
				jugador.setId(rs.getInt("id"));
				jugador.setPuntuacion(rs.getInt("puntuacion"));
				jugador.setTiempo(rs.getInt("tiempo"));
				

			}

			if (jugador.getNombre() == null) {
				return null;
			} else {
				return jugador;
			}

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}
	}

	public Boolean registrarJugadorDao(Connection conn, Jugador jugador) throws SQLException {
		PreparedStatement stmt = null;

		try {

			boolean f = true;
			Jugador j = consultarJugadorDao(conn, jugador);
			if (j.getNombre() != null) {
				String sql = "insert into jugadores (nombre,puntuacion) values (?,?)";
				stmt = conn.prepareStatement(sql);

				stmt.setString(1, jugador.getNombre().toLowerCase());
				jugador.setPuntuacion(0);
				stmt.setInt(2, jugador.getPuntuacion());
				stmt.execute();
			}else {
				f = false;
			}

			return f;

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}

	}

	public void insertarPuntuacionDao(Connection conn, String respuesta, Integer idPregunta, Jugador jugador) throws SQLException {
		PreparedStatement stmt = null;

		try {

			PreguntasDao pd = new PreguntasDao();
			Preguntas p = new Preguntas();
			p = pd.consultarPreguntaDao(conn, idPregunta);

			//respuesta --> Opcion que elige el usuario

			//Consulta de puntuacion

			Integer puntos = consultarPuntuacionDao(conn, jugador.getId());

			if (p.getRespuesta().equals(respuesta)) {
				String sql = "insert into puntuacion values ?"; // Consultar puntuacuion que lleva el usuario para sumarla
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, 100+puntos); // TODO !!!!!!
				stmt.execute();
			}
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}
	}

	//!!!!! TODO hacer
	public Integer consultarPuntuacionDao(Connection conn, Integer id) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//List<Jugador> listaPuntos = new ArrayList<Jugador>();
			String sql = "select puntuacion from jugadores where ? = id";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			Integer puntos = 0;
			while (rs.next()) {
				puntos = rs.getInt("puntuacion");
				//listaPuntos.add(jugador);
			}

			return puntos;

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}
	}

//	public Boolean jugadorActivo(Connection conn) throws SQLException {
//
//		Integer contador = 0; // Incrementar para recorrer BD por id
//		Jugador jugador = new Jugador();
//
//
//
//
//	}



}
