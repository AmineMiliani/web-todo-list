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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;




@WebServlet("/AccountControllerServlet")
public class AccountControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web-todo-list")
	private DataSource dataSource;
	private AccountDBUtil accountDBUtil;
	private HttpSession session;
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
		session = null;
	}

	/**
"	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cookie_username_value = getCookieValue(request,"LAST_USERNAME");
		session = request.getSession();
		if(cookie_username_value != null)
		{
			session.setAttribute("USERNAME_COOKIE", cookie_username_value);
			//request.setAttribute("USERNAME_COOKIE", cookie_username_value);
		}
		else
		{
			session.setAttribute("USERNAME_COOKIE", "Username");
			//request.setAttribute("USERNAME_COOKIE", "Username");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Account account = LoginCheck(username, password);
			session = req.getSession();
			session.setAttribute("USERNAME", username);
			session.setAttribute("ROLE", account.getRole());
			//String cookie_username_value = getCookieValue(req,"LAST_USERNAME");
			Cookie cookie_username = new Cookie("LAST_USERNAME",username);
			cookie_username.setMaxAge(100000);
			resp.addCookie(cookie_username);


			if(account != null)
			{
				//resp.sendRedirect("TodoControllerServlet");
				//RequestDispatcher dispatcher = req.getRequestDispatcher("/login_success_test.jsp"); // Juste pour test d'ouvrir une page
				//dispatcher.forward(req, resp);
				//resp.sendRedirect("TodoControllerServlet");
				//RequestDispatcher dispatcher = req.getRequestDispatcher("/login_success_test.jsp"); // Juste pour test d'ouvrir une page
				//dispatcher.forward(req, resp);


				if(account.getRole().equals("Student")||account.getRole().equals("student"))
				{
					ListTodos(req,resp);
				}
				else if(account.getRole().equals("Instructor")||account.getRole().equals("instructor"))
				{
					resp.sendRedirect("TodoListControllerServlet");
					//ListTodosInstructor(req,resp);
				}
				/*
				else if(account.getRole() == "Instructor" || account.getRole() == "instructor")
				{
				ListTodosInstructor(req,resp);
				}*/
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

	private void ListTodosInstructor(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		List<Todo> todos = getTodo();
		request.setAttribute("TODO_LIST", todos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/todolist_instructor.jsp");
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

	public void ClickTodo(int id)
	{
		Todo todo = fetchTodo(id);
		updateTodo(todo);
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
			String sql = "update student set description=? where id=?;";
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
	private static String getCookieValue( HttpServletRequest request, String nom ) {
		Cookie[] cookies = request.getCookies();
		if ( cookies != null ) {
			for ( Cookie cookie : cookies ) {
				if ( cookie != null && nom.equals( cookie.getName() ) ) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
