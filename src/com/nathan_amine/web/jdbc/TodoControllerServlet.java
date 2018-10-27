package com.nathan_amine.web.jdbc;

import java.io.IOException;
import java.sql.Connection;
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


@WebServlet("/TodoControllerServlet")
public class TodoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/web-todo-list")
	private AccountDBUtil dBUtil;
	private DataSource dataSource;
	
	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
    public TodoControllerServlet() {
        // TODO Auto-generated constructor stub
    	dBUtil = new AccountDBUtil(dataSource);
		}*/
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		dBUtil = new AccountDBUtil(dataSource);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login_success_test.jsp"); // Juste pour test d'ouvrir une page
		dispatcher.forward(request, response);
		/*try {
			listStudents(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	private void listStudents(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
			List<Todo> todos = dBUtil.getTodo();
			request.setAttribute("TODO_LIST", todos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/todo.jsp");
			dispatcher.forward(request, response);
			}

	
}
