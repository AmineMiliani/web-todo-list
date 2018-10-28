package com.nathan_amine.web.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class EditTodoServlet
 */
@WebServlet("/EditTodoServlet")
public class EditTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountDBUtil dbUtil;
	@Resource(name="jdbc/web-todo-list")
	private DataSource dataSource;
	int id;
	@Override
	public void init() throws ServletException {
		super.init();
		dbUtil = new AccountDBUtil(dataSource);
	}
	public EditTodoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		id=Integer.parseInt(request.getParameter("todoId"));
		Todo todo= fetchTodo(id);
		request.setAttribute("Todo", todo);
		request.getRequestDispatcher("edit-todo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
	String description= request.getParameter("description");
	Todo todo = new Todo(id,description);
	updateTodo(todo);
	response.sendRedirect("TodoListControllerServlet");
	}

	public Todo fetchTodo(int id)
	{
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		Todo todo=null;

		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todos where id="+id;
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()){
				String description=myRs.getString("description");
				todo = new Todo(id,description);
			}
			return todo;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		} finally{
			closev2(myConn,myStmt,myRs);
		}
	}

	public void updateTodo(Todo todo) {
		Connection myConn=null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "update todos set description=? where id=?;";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, todo.getDescription());
			myStmt.setInt(2, todo.getId());
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			closev2(myConn,myStmt,null);
		}
	}
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
