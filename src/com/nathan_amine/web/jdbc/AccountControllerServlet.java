package com.nathan_amine.web.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;





@WebServlet("/AccountControllerServlet")
public class AccountControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web-todo-list")
	private DataSource dataSource;
	private AccountDBUtil accountDBUtil;
	/**
	 * Default constructor. 
	 *//*
    public AccountControllerServlet() {
        // TODO Auto-generated constructor stub
    	accountDBUtil = new AccountDBUtil(dataSource);
		}*/
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		accountDBUtil = new AccountDBUtil(dataSource);
	}

	/**
"	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);


	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Account account = LoginCheck(username, password);
			if(account != null)
			{
				//resp.sendRedirect("TodoControllerServlet");
				//RequestDispatcher dispatcher = req.getRequestDispatcher("/login_success_test.jsp"); // Juste pour test d'ouvrir une page
				//dispatcher.forward(req, resp);

				ListTodos(req,resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
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
			closev2(myConn,myStmt,myRs);
		}
		return account;
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

	private void ListTodos(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		List<Todo> todos = getTodo();
		request.setAttribute("TODO_LIST", todos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/todolist.jsp");
		dispatcher.forward(request, response);
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
			closev2(myConn,myStmt,myRs);
		}
		return todos;
	}

}
