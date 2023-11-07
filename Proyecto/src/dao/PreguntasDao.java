package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Preguntas;

public class PreguntasDao {



	public Preguntas consultarPreguntaDao(Connection conn, Integer id) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> respuestas = new ArrayList<>();

		try {

			Preguntas p = new Preguntas();
			String sql = "select * from preguntas where ? = id_pregunta";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();



			if (rs.next()) {
				p.setIdPregunta(rs.getInt("id_pregunta"));
				p.setPregunta(rs.getString("pregunta"));
				p.setRespuesta(rs.getString("respuesta_correcta"));
				respuestas.add(rs.getString("Respuesta A"));
				respuestas.add(rs.getString("Respuesta B"));
				respuestas.add(rs.getString("Respuesta C"));
				respuestas.add(rs.getString("Respuesta D"));
				p.setListaRespuesta(respuestas);


			}

			return p;
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}
	}
}
