package main.java.com.asolutworld.Authorization.dao;

import main.java.com.asolutworld.Constants.Strings;
import main.java.com.asolutworld.Utils.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDAO {
	private static int u_id;
	public static String validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
            con = DataConnection.getConnecion();
            if (con != null){
                ps = con.prepareStatement("SELECT access, login, pass, u_id from volunteers where login = ? and pass = ?");
                ps.setString(1, user);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
					u_id=rs.getInt(4);
                    return rs.getString(1);
                }
            }

		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return Strings.PUBLIC;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Strings.PUBLIC;
	}

	public static int getU_ID(){
		return u_id;
	}

}