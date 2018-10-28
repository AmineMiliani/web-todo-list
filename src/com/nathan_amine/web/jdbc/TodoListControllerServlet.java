package com.nathan_amine.web.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/TodoListControllerServlet")
public class TodoListControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web-todo-list")
	private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoListControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ListTodosInstructor(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
