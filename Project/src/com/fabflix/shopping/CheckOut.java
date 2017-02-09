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

public class CheckOut extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Query query = new Query();	
		
		ArrayList<String> genres = new ArrayList<String>();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String exp = request.getParameter("exp");
		String ccid = request.getParameter("ccid");
		int customerId = (Integer) session.getAttribute("customerId");
		ArrayList<Item> items = (ArrayList<Item>) session.getAttribute("items");
		ArrayList<Integer> movieId = new ArrayList<Integer>();
		if(items != null){
			for (Item item : items) {
				movieId.add(item.getMovie().getId());
			}
		}
		else
			items = new ArrayList<Item>();
		
		String status = "Invalid Credit Card Information!";
		if (!firstName.isEmpty() || !lastName.isEmpty() || !exp.isEmpty() || !ccid.isEmpty() ) {
			try {
				if(query.veryfyCC(firstName, lastName, ccid, exp)) {
					status = "Your order has been placed. Thank you for shopping at Fabflix";
					genres = query.getGenres();
					query.addSale(customerId, movieId);
					items.clear();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
				
		}
		
		
		session.setAttribute("status", status);
		session.setAttribute("genres", genres);
		request.getRequestDispatcher("confirmation.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
