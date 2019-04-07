package database;
import resources.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB extends Conexion{
	public UserDB(){
		super();
	}
	public int createUser(UserResource user) throws SQLException {
		String query = "Insert into faceSOS.users(name,username) value (?,?);";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setString(0, user.getName());
		ps.setString(1, user.getUsername());
		ps.executeQuery();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			int id = rs.getInt(1);
			user.setId(id);
			return id;
		}
		else {
			return -1;
		}	
	}
}
