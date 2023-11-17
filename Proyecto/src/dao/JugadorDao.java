package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Jugador;

public class JugadorDao {

	public Jugador consultarJugadorDao(Connection conn, Jugador jugador) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "select * from jugadores where nombre = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, jugador.getNombre());
			rs = stmt.executeQuery();

			if (rs.next()) {

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
	//TODO devuelva un jugador creado
	public Jugador registrarJugadorDao(Connection conn, Jugador jugador) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs  =null;
		
		try {

		
			Jugador j = consultarJugadorDao(conn, jugador);
			if (j.getNombre() == null) {
				String sql = "insert into jugadores (nombre,puntuacion) values (?,?)";
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, jugador.getNombre().toLowerCase());
				stmt.setInt(2, jugador.getPuntuacion());
				stmt.execute();
				rs = stmt.getGeneratedKeys();
				rs.next();
				jugador.setId(rs.getInt(1));
				return jugador;
			} 
			j=new Jugador();
			return j;

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}

	}

	public List<Jugador> consultarListaJugadoresDao(Connection conn) throws SQLException { //
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Jugador> jugadores = new ArrayList<Jugador>();

		try {
			String sql = "select * from jugadores";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Jugador jugador = new Jugador();
				jugador.setId(rs.getInt("id"));
				jugador.setNombre(rs.getString("nombre"));
				jugador.setPuntuacion(rs.getInt("puntuacion"));
				jugadores.add(jugador);
			}

			return jugadores;

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}
	}

	public void insertarPuntuacionDao(Connection conn, Jugador jugador) throws SQLException { //
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE JUGADORES SET PUNTUACION=? WHERE ID=?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, jugador.getPuntuacion());
			stmt.setInt(2, jugador.getId());
			stmt.execute();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}
	}

	public Integer consultarPuntuacionDao(Connection conn, Jugador jugador) throws SQLException { //
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "select puntuacion from jugadores where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, jugador.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				jugador.setPuntuacion(rs.getInt("puntuacion"));
			}

			return jugador.getPuntuacion();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}
	}

}
