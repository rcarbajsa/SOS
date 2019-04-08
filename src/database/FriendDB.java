package database;
import resources.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendDB extends Conexion{
	
	public FriendDB() {
		super();
	}
	
	public boolean checkFriends(String idUser, String idFriend) throws SQLException {
		
		String query = "Select * from faceSOS.friends where (user_id= ? and friend_id=?) or (user_id= ? and friend_id=?);";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setString(1, idUser);
		ps.setString(2, idFriend);
		ps.setString(4, idUser);
		ps.setString(3, idFriend);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			return true;
		else
			return false;
		
	}
	
	public void addFriend(String idUser, String idFriend) throws SQLException {
		String query = "Insert into faceSOS.friends(user_id,friend_id) values(?,?);";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setString(1, idUser);
		ps.setString(2, idFriend);
		ps.executeUpdate();
		
	}
	public void removeFriend(String idUser, String idFriend) throws SQLException {
		String query = "Delete from faceSOS.friends where (user_id= ? and friend_id=?) or (user_id= ? and friend_id=?);";
		PreparedStatement ps = this.conn.prepareStatement(query);
		ps.setString(1, idUser);
		ps.setString(2, idFriend);
		ps.setString(4, idUser);
		ps.setString(3, idFriend);
		ps.executeUpdate();
		
	}
}
