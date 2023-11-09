package controlador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControlDao {

	public Integer consultarControlDao(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "select * from control";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			Integer control = 0;
			if(rs.next()) {
				control = rs.getInt("Numero");
			}
			
			return control;
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				
			}
		}
	}
	
	public void aumentarControl(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "update control set Numero = ?";
			stmt = conn.prepareStatement(sql);
			Integer control = consultarControlDao(conn);
			control++;
			stmt.setInt(1, control);
			stmt.execute();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
				
			}
		}
	}
}
