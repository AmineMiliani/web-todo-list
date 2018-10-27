package com.nathan_amine.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;



public class AccountDBUtil {
public AccountDBUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
private DataSource dataSource;

public DataSource getDataSource() {
	return dataSource;
}

public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
}

		public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
		if(myStmt!=null)
		myStmt.close();
		if(myRs!=null)
		myRs.close();
		if(myConn!=null)
		myConn.close();
		}catch(Exception e){
		System.out.println(e.getMessage());
		}
		}

		
		
		public List<Todo> getTodo() throws Exception {
			List<Todo> todos= new ArrayList<Todo>();
			Connection myConn=null;
			Statement myStmt = null;
			ResultSet myRs= null;
			try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todos order by id;";
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()){
			int id = myRs.getInt("id");
			String description=myRs.getString("description");
			Todo tempTodo = new Todo(id, description);
			todos.add(tempTodo);
			}
			
			} finally{
			close(myConn,myStmt,myRs);
			}
			return todos;
			}
/*
 //Problème avec cette fonction dans cette classe aucune solution trouvée malgré des heures de recherche du coup je l'ai mise dans le servlet et ça passe
		public Account LoginCheck(String username, String password) {
			Connection myConn=null;
			PreparedStatement myStmt = null;
			ResultSet myRs= null;
			Account account = null;
			try {
				myConn = dataSource.getConnection();
				myStmt= myConn.prepareStatement(
						"SELECT * FROM account WHERE user = ? and password = ?;");
				myStmt.setString(1, username);
				myStmt.setString(2, password);
				myRs = myStmt.executeQuery();
				myRs.last();
				if(myRs.getRow() != 0) {
					String role = myRs.getString("role");
					account = new Account(username,password,role);
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally{
				close(myConn,myStmt,myRs);
			}
			return account;
		}*/


}
