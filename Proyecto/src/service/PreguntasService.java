package service;

import java.sql.Connection;
import java.sql.SQLException;

import bd.OpenConnection;
import dao.PreguntasDao;
import modelo.Preguntas;

public class PreguntasService {

	private OpenConnection openConnection;

	public PreguntasService() {
		openConnection = new OpenConnection();
	}

	/*
	 * TODO Solo un servicio para traer de una todos los atributos de las Preguntas
	 */

	public Preguntas consultarPregunta(Integer id) throws SQLException {
		Connection conn = null;

		try {

			conn = openConnection.getConnection();
			PreguntasDao pd = new PreguntasDao();

			Preguntas p = pd.consultarPreguntaDao(conn, id);

			return p;

		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}

	public Boolean validarRespuesta(Preguntas pregunta, String respuesta) {
		Connection conn = null;

		try {

			conn = openConnection.getConnection();
			pregunta = consultarPregunta(pregunta.getIdPregunta());

			if (pregunta.getRespuesta().equals(respuesta)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
		if (pregunta.getRespuesta().equals(respuesta)) {
			return true;
		} else {
			return false;
		}
	}

}
