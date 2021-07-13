package com.Bikash.studentinfosys.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bikash.studentinfosys.dao.UserDao;
import com.Bikash.studentinfosys.dao.UserDaoImpl;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String User_LIST = "userList.jsp";
	UserDao userDao = new UserDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("actions");
		if (action.equals("student_list")) {
			request.setAttribute("students", userDao.getAllUserInfo());
			RequestDispatcher rd = request.getRequestDispatcher(User_LIST);
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user = request.getParameter("username");
		String pass = request.getParameter("password");

		int isuser = userDao.checkUser(user, pass);

		if (isuser == 1) {

			RequestDispatcher rd = request.getRequestDispatcher(User_LIST);
			rd.forward(request, response);
		} else {

		}

	}

}
