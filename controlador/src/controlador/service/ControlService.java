package controlador.service;

import java.sql.Connection;
import java.sql.SQLException;

import controlador.dao.ControlDao;




public class ControlService {

	private OpenConnection openConnection;

	public ControlService() {
		openConnection = new OpenConnection();
	}

	public void consultarControlService() throws SQLException {
		Connection conn = null;

		try {

			conn = openConnection.getConnection();
			ControlDao c = new ControlDao();
			c.consultarControlDao(conn);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}
	
	
	public void aumentarControlService() throws SQLException {
		Connection conn = null;

		try {

			conn = openConnection.getConnection();
			ControlDao c = new ControlDao();
			c.aumentarControlDao(conn);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}
	
}
