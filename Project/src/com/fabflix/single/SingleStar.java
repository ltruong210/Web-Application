package com.fabflix.single;
import com.fabflix.datatype.*;
import java.io.IOException;
import java.util.ArrayList;
import com.fabflix.query.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SingleStar extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Query query = new Query();		
		
		ArrayList<String> genres = new ArrayList<String>();
		String id = request.getParameter("id");
		try {
			Star star = query.getStarFull(id);
			session.setAttribute("star",star);
			genres = query.getGenres();	
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		session.setAttribute("genres", genres);
		request.getRequestDispatcher("singleStar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
