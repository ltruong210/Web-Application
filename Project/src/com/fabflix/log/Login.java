package com.fabflix.log;

import java.io.IOException;
import com.fabflix.query.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Query query = new Query();
		int customerId = 0;
		try {
			customerId = query.verifyUser(email, password);

			if (customerId != -1) {
				session.setAttribute("email", email);
				session.setAttribute("customerId", customerId);
				response.sendRedirect("home");
			} else {
				request.setAttribute("errMessage", "Invalid email or password!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
