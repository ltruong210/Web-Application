package com.fabflix.browse;

import com.fabflix.datatype.Movie;
import com.fabflix.home.*;

import java.io.IOException;
import java.util.ArrayList;
import com.fabflix.query.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BrowseTitle extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Query query = new Query();	
		String limit = request.getParameter("limit");
		if (limit == null) 
			limit = (String) session.getAttribute("limit");
		
		if (limit == null) 
			limit = "10";
		String command = (String) session.getAttribute("command");
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
		
		if (command == null) 
			command = "title";
		String req = request.getParameter("req");
		if (req == null) 
			req = "title";
		
		String title =  request.getParameter("character");
		if(title == null) 
			title = "";
		else {
			session.setAttribute("character", title);
		}
		String currentTitle = (String) session.getAttribute("currentTitle");
		if(currentTitle == null)
			currentTitle = title;
			
		String p =  request.getParameter("page");
		Integer page = new Integer(1);
		if(p == null) 
			page = (Integer) session.getAttribute("page");
		else
			page = Integer.parseInt(p);

		if (page == null)
			page = 1;
		
		if(!command.equals(req) || page < 1 || !currentTitle.equals(title)) {
			page = 1;
			command = req;
			currentTitle = title;
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
		
		int offset = (page-1)*10;
		ArrayList<Movie> movies = new ArrayList<Movie>();
		ArrayList<String> genres = new ArrayList<String>();
		try {
			movies = query.browseByTitle(title, order, offset, sortSession, limit);
			genres = query.getGenres();	
			if (movies.isEmpty()){
				page = page - 1;
				offset = (page-1)*10;
				movies = query.browseByTitle(title, order, offset, sortSession, limit);
			}
				
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
		session.setAttribute("currentTitle", currentTitle);
		session.setAttribute("limit", limit);
		request.getRequestDispatcher("browseTitle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
