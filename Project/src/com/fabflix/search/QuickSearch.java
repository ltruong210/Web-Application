package com.fabflix.search;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fabflix.query.*;
import com.fabflix.datatype.Movie;

public class QuickSearch extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Query query = new Query();				
		//for sort
		String sortReq = request.getParameter("sort");
		String order =(String) session.getAttribute("order");
		if (order == null) {
			order = "ASC";
		}
		String sortSession = (String) session.getAttribute("sort");
	
		if (sortReq == null & sortSession == null)
			sortSession = "title";
		else if (sortSession != null & sortReq != null) {
			if (sortSession.equals(sortReq)) {
				if (order.equals("ASC"))
					order = "DESC";
				else if (order.equals("DESC"))
					order="ASC";
			}
			else 
				sortSession = sortReq;
				
		}
		else if(sortSession == null & sortReq != null)
			sortSession = sortReq;
		
		//for paging
		String pattern = request.getParameter("pattern");
		if(pattern == null)
			pattern = "";
		String currentPattern = (String) session.getAttribute("currentPattern");
		if(currentPattern == null)
			currentPattern = pattern;
		
		String command = (String) session.getAttribute("command");
		if (command == null) 
			command = "home";
		String req = request.getParameter("req");
		if (req == null) 
			req = "home";
		
		String p =  request.getParameter("page");
		Integer page = new Integer(1);
		if(p == null) 
			page = (Integer) session.getAttribute("page");
		else
			page = Integer.parseInt(p);

		if (page == null)
			page = 1;
		
		if(!command.equals(req) || page < 1 || !currentPattern.equals(pattern)){
			page = 1;
			currentPattern = pattern;
			command = req;
		}
		
		
		String paging = request.getParameter("paging");
		if(paging == null) {
			paging = "";
		}
		
		if (page == 1 & paging.equals("previous"))
			paging = "";
		
		if (paging.equals("previous")) {
			page = page - 1;
		}
		else if(paging.equals("next")) {
			page = page + 1;
		}
		//for paging
		
		int offset = (page-1)*10;
		ArrayList<Movie> movies = new ArrayList<Movie>();		
		ArrayList<String> genres = new ArrayList<String>();
		try {
			movies = query.quickSearch(pattern, order, offset, sortSession);
			genres = query.getGenres();	

		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ArrayList<String> alphabet = query.getAlphabet();
		
		request.setAttribute("alphabet", alphabet);
		session.setAttribute("page", page);
		session.setAttribute("movies", movies);
		session.setAttribute("command", command);
		session.setAttribute("sort", sortSession);
		session.setAttribute("order", order);
		session.setAttribute("genres", genres);
		session.setAttribute("currentPattern", currentPattern);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
