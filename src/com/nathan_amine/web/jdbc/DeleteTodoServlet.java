package com.nathan_amine.web.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web-todo-list")
	private DataSource dataSource;
	int id;
	@Override
	public void init() throws ServletException {
		super.init();
	}
	public DeleteTodoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		id=Integer.parseInt(request.getParameter("todoId"));
		deleteTodo(id);
		response.sendRedirect("TodoListControllerServlet"); // A faire Redirect to an other servlet
	}
	
	public void deleteTodo(int id) {
		// TODO Auto-generated method stub
		Connection myConn=null;
		Statement myStmt = null;
		try {
		myConn = dataSource.getConnection();
		myStmt= myConn.createStatement();
		String sql= "delete from todos where id="+id;
		myStmt.execute(sql);
		}catch(Exception e){
		System.out.println(e.getMessage());
		} finally{ closev2(myConn,myStmt,null); }}

	public static void closev2(Connection myConn, Statement myStmt, ResultSet myRs) {
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
}
