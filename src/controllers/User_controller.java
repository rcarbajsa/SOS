package controllers;
import database.*;
import resources.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User_controller   {

	public String createUser(User_resource user)throws SQLException{
		User_db db=new User_db();
		String location=db.createUser(user);
		return location;
	}
}
