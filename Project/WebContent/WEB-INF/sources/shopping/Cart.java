package com.fabflix.shopping;
import com.fabflix.datatype.*;
import java.io.IOException;
import java.util.ArrayList;
import com.fabflix.query.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Cart extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Query query = new Query();	
		ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items");
		ArrayList<String> genres = new ArrayList<String>();
		String title = request.getParameter("title");
		String movieId = request.getParameter("id");
		String remove = request.getParameter("remove");
		String removeAll = request.getParameter("removeAll");
		String update = request.getParameter("update");
		
		int id = 0;
		if(movieId != null)
			id = Integer.parseInt(movieId);
		if(items == null)
			items = new ArrayList<Item>();
		
		if(update != null) {
			for(Item i : items) {
				String q = request.getParameter(Integer.toString(i.getMovie().getId()));
				int newQuantity = 0;
				if(q != null)
					newQuantity = Integer.parseInt(q);
				i.setQuantity(newQuantity);
			}
		}
		if(title != null & id != 0) {
			MiniMovie movie = new MiniMovie(id, title);
			if(!items.isEmpty()) {
				int index = query.findIndex(items, id);
				if (index == -1)
				{
					Item item = new Item(movie, 1);
					items.add(item);
				}
				else {
					items.get(index).addQuantity();
				}
			}
			else {
				Item item = new Item(movie, 1);
				items.add(item);
			}
			
		}
		if (remove != null) {
			int removeId = Integer.parseInt(remove);
			int index = query.findIndex(items, removeId);
			items.remove(index);
		}
		if (removeAll != null) {
			items.clear();
		}
		
		String empty = "";
		if(items.isEmpty())
			empty = "Empty Cart";
		
		int total = 0;
		for(Item i : items){
			total += i.getQuantity()*5;
		}
		try {
			genres = query.getGenres();	
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		session.setAttribute("emptyCart", empty);
		session.setAttribute("items", items);
		session.setAttribute("genres", genres);
		session.setAttribute("total", total);
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
}
