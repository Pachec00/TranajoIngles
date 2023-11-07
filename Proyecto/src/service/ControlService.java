package service;

import java.sql.Connection;
import java.sql.SQLException;

import bd.OpenConnection;
import dao.controlDao;

public class ControlService {

	private OpenConnection openConnection;
	
	public ControlService() {
		openConnection = new OpenConnection();
	}
	
	public void controlService() throws SQLException {
		Connection conn = null;
		
		try {
			
			conn = openConnection.getConnection();
			controlDao c = new controlDao();
			c.controlDao(conn);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				
			}
		}
	}
}
