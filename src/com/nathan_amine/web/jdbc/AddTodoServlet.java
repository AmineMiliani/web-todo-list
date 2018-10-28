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
 * Servlet implementation class AddTodoServlet
 */
@WebServlet("/AddTodoServlet")
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web-todo-list")
	private DataSource dataSource;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTodoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/add-todo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
	String description= req.getParameter("description");
	Todo todo = new Todo(description);
	addTodo(todo);
	resp.sendRedirect("TodoListControllerServlet");
	}
	public void addTodo(Todo todo){
		Connection myConn=null;
		PreparedStatement myStmt = null;
		ResultSet myRs= null;
		try {
		myConn = dataSource.getConnection();
		String sql = "INSERT INTO todos (description) VALUES (?)";
		myStmt = myConn.prepareStatement(sql);
		String description = todo.getDescription();
		myStmt.setString(1, description);
		myStmt.execute();
		}
		catch(Exception e){
		System.out.println(e.getMessage());
		}
		finally{
		closev2(myConn,myStmt,myRs);
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
