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

public class UserDB extends Conexion {
	public UserDB() {
		super();
	}
	
	public ResultSet getUser(UserResource user) throws SQLException {
		if(this.conn != null) {
			String query = "SELECT * FROM `faceSOS`.`users` WHERE user_id = ?;";
			PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			return rs;
		}
		return null;
	}
	
	public ResultSet createUser(UserResource user) throws SQLException {
		// TODO: unique username value. If already exists, then error
		if(this.conn != null) {
			String query = "INSERT INTO `faceSOS`.`users`(name,username,email,biography) VALUE (?,?,?,?);";
			PreparedStatement ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getBiography());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			return rs;
		}
		return null;
	}
	
	public int editUser(UserResource user) throws SQLException {
		if(this.conn != null) {
			String query = "UPDATE `faceSOS`.`users` SET name = ? WHERE user_id = ?;";
			PreparedStatement ps = this.conn.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getId());
			return ps.executeUpdate();
		}
		return -1;
	}
	
	public int removeUser(UserResource user) throws SQLException {
		if(this.conn != null) {
			String query = "DELETE FROM `faceSOS`.`users` WHERE user_id = ?;";
			PreparedStatement ps = this.conn.prepareStatement(query);
			ps.setInt(1, user.getId());
			return ps.executeUpdate();
		}
		return -1;
	}
	
	public ResultSet getUsers(String name, int limitTo, int page) throws SQLException {
		if(this.conn != null) {
			String query = "SELECT * FROM `faceSOS`.`users`";
			if(name.equals("")) 
				query += " WHERE name LIKE ?";
			if(limitTo!=0)
				query+="LIMIT ?,?";
			PreparedStatement ps = this.conn.prepareStatement(query);
			int i=1;
			if(name.equals("")) {
				ps.setString(1, "%" + name + "%");
				i++;
			}
			if(limitTo != 0) {
				int inic = page * limitTo;
				int fin = inic + limitTo;
				ps.setInt(i, inic);
				ps.setInt(i + 1, fin);
			}
			
			ResultSet rs = ps.executeQuery();
			return rs;
		}
		return null;
	}
	
	public ResultSet getFriends(String name, int count, String userId, int page) throws SQLException {
		if(this.conn != null) {
			
			String query = "SELECT faceSOS.friends.UserID2,faceSOS.users.name, faceSOS.users.username FROM faceSOS.users "
					+ "JOIN faceSOS.friends ON (faceSOS.users.user_id=faceSOS.friends.UserID2) where faceSOS.friends.UserID1= ? ";
			if (!name.equals(""))
				query += " and (faceSOS.users.name like ? or faceSOS.users.username like ?)";
			if (count != 0)	
				query += "limit ?,?;";
			PreparedStatement ps = this.conn.prepareStatement(query);
			ps.setString(1, userId);
			int i=2;
			if (!name.equals("")) {
				name="%"+name+"%";
				ps.setString(i, name);
				i++;
				ps.setString(i, name);
				i++;
			}
			if (count != 0) {
				int inic=page*count;
				int fin= inic+count;
				ps.setInt(i, inic);
				ps.setInt(i + 1, fin);
			}
				
			ResultSet rs =ps.executeQuery();
			return rs;
		}
		return null;
	}
	
	
}
