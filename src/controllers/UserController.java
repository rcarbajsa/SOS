package controllers;
import database.*;
import resources.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController   {

	public String createUser(UserResource user)throws SQLException{
		UserDB db=new UserDB();
		String location=db.createUser(user);
		return location;
	}
}
