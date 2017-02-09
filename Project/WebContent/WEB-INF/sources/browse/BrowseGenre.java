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

public class BrowseGenre extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Query query = new Query();
		
		String limit = request.getParameter("limit");
		if (limit == null) 
			limit = (String) session.getAttribute("limit");
		
		if (limit == null) 
			limit = "10";
		
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

		String command = (String) session.getAttribute("command");
		if (command == null)
			command = "genre";
		String req = request.getParameter("req");
		if (req == null)
			req = "genre";
		
		String genre = request.getParameter("genre");
		if (genre == null) {
			genre = "";
		}
		String currentGenre = (String) session.getAttribute("currentGenre");
		if (currentGenre == null) 
			currentGenre = genre;
		
		String p = request.getParameter("page");
		Integer page = new Integer(1);
		if (p == null)
			page = (Integer) session.getAttribute("page");
		else
			page = Integer.parseInt(p);

		if (page == null)
			page = 1;
		
		
		if (!command.equals(req) || page < 1 || !currentGenre.equals(genre)) {
			page = 1;
			command = req;
			currentGenre = genre;
		}


		String paging = request.getParameter("paging");
		if (paging == null) {
			paging = "";
		}

		if (page == 1 & paging.equals("previous"))
			paging = "";

		if (paging.equals("previous")) {
			page = page - 1;
		} else if (paging.equals("next")) {
			page = page + 1;
		}
		
		int offset = (page - 1) * 10;
		ArrayList<String> genres = new ArrayList<String>();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		

		try {
			movies = query.browseByGenre(genre, order, offset, sortSession, limit);
			genres = query.getGenres();	
			if (movies.isEmpty()){
				page = page - 1;
				offset = (page - 1) * 10;
				movies = query.browseByGenre(genre, order, offset, sortSession, limit);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(movies.get(1).getTitle());
		
		ArrayList<String> alphabet = query.getAlphabet();
		request.setAttribute("alphabet", alphabet);
		session.setAttribute("page", page);
		session.setAttribute("movies", movies);
		session.setAttribute("command", command);
		session.setAttribute("genre", genre);
		session.setAttribute("sort", sortSession);
		session.setAttribute("order",order);
		session.setAttribute("genres", genres);
		session.setAttribute("currentGenre", currentGenre);
		session.setAttribute("limit", limit);
		request.getRequestDispatcher("browseGenre.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
