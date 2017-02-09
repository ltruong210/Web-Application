package com.fabflix.query;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.fabflix.datatype.*;

public class Query {
	public ArrayList<Movie> getMovieList(ResultSet result) throws Exception {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		while (result.next()) {
			int id = result.getInt(1);
			String title = result.getString(2);
			int year = result.getInt(3);
			String director = result.getString(4);
			String banner_url = result.getString(5);
			String trailer_url = result.getString(6);
			ArrayList<String> genres = new ArrayList<String>();
			genres = getGenres(id);
			ArrayList<MiniStar> stars = new ArrayList<MiniStar>();
			stars = getStars(id);
			Movie movie = new Movie(id, title, year, director, banner_url, trailer_url, genres, stars);
			movies.add(movie);
		}
		return movies;
	}

	public ArrayList<Movie> getMovieList(int offset, String order, String sort, String limit) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		ArrayList<Movie> movies = new ArrayList<Movie>();
		String str = "Select * from movies order by " + sort + " " + order + " limit " + limit + " offset ?;";
		PreparedStatement select = connection.prepareStatement(str);
		select.setInt(1, offset);

		ResultSet result = select.executeQuery();
		movies = getMovieList(result);

		connection.close();
		return movies;
	}

	public int verifyUser(String email, String password) throws Exception {		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");
		int customerId = 0;
		String query = "select * from customers where email = ? and password = ?";
		PreparedStatement select = connection.prepareStatement(query);
		select.setString(1, email);
		select.setString(2, password);
		ResultSet result = select.executeQuery();
		if(result.next())
			customerId = result.getInt(1);
		else
			customerId = -1;
		connection.close();
		return customerId;
	}

	public ArrayList<String> getGenres(int movieID) throws Exception {
		ResultSet result = null;
		ArrayList<String> genres = new ArrayList<String>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		String str = "SELECT name FROM genres JOIN genres_in_movies "
				+ "ON genres_in_movies.genre_id = genres.id  WHERE genres_in_movies.movie_id = ?";
		PreparedStatement query = connection.prepareStatement(str);
		query.setInt(1, movieID);
		result = query.executeQuery();
		while (result.next()) {
			genres.add(result.getString(1));
		}
		connection.close();

		return genres;
	}

	public ArrayList<MiniStar> getStars(int movieID) throws Exception {

		ArrayList<MiniStar> stars = new ArrayList<MiniStar>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		String str = "SELECT * FROM stars JOIN stars_in_movies "
				+ "ON stars_in_movies.star_id = stars.id  WHERE stars_in_movies.movie_id = " + movieID;
		Statement query = connection.createStatement();
		ResultSet result = query.executeQuery(str);
		while (result.next()) {
			int id = result.getInt(1);
			String first_name = result.getString(2);
			String last_name = result.getString(3);

			MiniStar star = new MiniStar(id, first_name, last_name);
			stars.add(star);
		}
		connection.close();
		return stars;
	}

	public ArrayList<String> getGenres() throws Exception {

		ArrayList<String> genres = new ArrayList<String>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery("Select name from genres order by name ASC");
		while (result.next()) {
			genres.add(result.getString(1));
		}
		connection.close();
		return genres;
	}

	public ArrayList<Movie> browseByGenre(String genre, String order, int offset, String sort, String limit)
			throws Exception {
		ArrayList<Movie> movies = new ArrayList<Movie>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		String str = "Select movies.id, title, year, director, banner_url, trailer_url "
				+ "from movies JOIN genres_in_movies on genres_in_movies.movie_id = movies.id "
				+ "JOIN genres ON genres.id = genres_in_movies.genre_id where genres.name = ? order by " + sort + " "
				+ order + " limit " + limit + " offset ?";

		PreparedStatement query = connection.prepareStatement(str);
		query.setString(1, genre);
		query.setInt(2, offset);

		ResultSet result = query.executeQuery();
		movies = getMovieList(result);
		connection.close();
		return movies;

	}

	public ArrayList<Movie> browseByTitle(String character, String order, int offset, String sort, String limit)
			throws Exception {
		ArrayList<Movie> movies = new ArrayList<Movie>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		String str = "Select * from movies where title LIKE ? order by " + sort + " " + order + " limit " + limit
				+ " offset ?";
		character = character + "%";

		PreparedStatement query = connection.prepareStatement(str);
		query.setString(1, character);
		query.setInt(2, offset);
		ResultSet result = query.executeQuery();
		movies = getMovieList(result);

		connection.close();
		return movies;

	}

	public ArrayList<String> getAlphabet() {
		ArrayList<String> alphabet = new ArrayList<String>();

		String str = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (char c : str.toCharArray()) {
			alphabet.add(Character.toString(c));
		}
		return alphabet;
	}

	public ArrayList<Movie> quickSearch(String pattern, String order, int offset, String sort) throws Exception {
		ArrayList<Movie> movies = new ArrayList<Movie>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		pattern = "%" + pattern + "%";
		String str = "Select * from movies where title LIKE ? order by " + sort + " " + order + " limit 10 offset ?";
		PreparedStatement query = connection.prepareStatement(str);
		query.setString(1, pattern);
		query.setInt(2, offset);
		ResultSet result = query.executeQuery();
		movies = getMovieList(result);
		connection.close();
		return movies;
	}

	public ArrayList<Movie> advanceSearch(String title, String year, String director, String firstName,
			String lastName, String order, int offset, String sort) throws Exception {
		ArrayList<Movie> movies = new ArrayList<Movie>();

		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");
	
		String condition = " 1=1 ";
		String fromCondition = "";
		
		if(title != null & !title.isEmpty()) 
			condition += "AND m.title LIKE '%"+title+"%' ";
		
		if(year != null & !year.isEmpty()) 
			condition += "AND m.year LIKE '%"+year+"%' ";
		
		if(director != null & !director.isEmpty())
			condition += "AND m.director LIKE '%"+ director +"%' ";
		
		if(firstName != null & !firstName.isEmpty() & (lastName == null || lastName.isEmpty())){ //only first name
			fromCondition = ",stars s, stars_in_movies sm ";
			condition += "AND s.first_name LIKE '%"+ firstName +"%' AND m.id = sm.movie_id AND s.id = sm.star_id ";
		}
		
		if(lastName != null & !lastName.isEmpty() & (firstName == null || firstName.isEmpty())){
			fromCondition= ",stars s, stars_in_movies sm ";
			condition += "AND s.last_name Like '%"+ lastName +"%' AND m.id = sm.movie_id AND s.id = sm.star_id ";
		}
		
		if(lastName != null & !lastName.isEmpty() & (firstName != null & !firstName.isEmpty())){
			fromCondition= ",stars s, stars_in_movies sm ";
			condition += "AND s.last_name Like '%"+ lastName +"%'" + "AND s.first_name LIKE '%"+ firstName +"%' AND m.id = sm.movie_id AND s.id = sm.star_id ";
		}
		
		String str = "Select * from movies m " + fromCondition +  "where" + condition + "order by " + sort + " " + order + " limit 10 offset ?";
				
		PreparedStatement query = connection.prepareStatement(str);
		query.setInt(1, offset);
		
		ResultSet result = query.executeQuery();
		while (result.next()) {
			int id = result.getInt(1);
			String title2 = result.getString(2);
			int year2 = result.getInt(3);
			String director2 = result.getString(4);
			String banner_url = result.getString(5);
			String trailer_url = result.getString(6);
			ArrayList<String> genres = new ArrayList<String>();
			genres = getGenres(id);
			ArrayList<MiniStar> stars = new ArrayList<MiniStar>();
			stars = getStars(id);
			Movie movie = new Movie(id, title2, year2, director2, banner_url, trailer_url, genres, stars);
			movies.add(movie);
		}
		return movies;
	}

	public Movie getMovie(String movieId) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		String str = "Select * from movies where id = " + movieId;
		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery(str);
		result.next();
		int id = result.getInt(1);
		String title = result.getString(2);
		int year = result.getInt(3);
		String director = result.getString(4);
		String banner_url = result.getString(5);
		String trailer_url = result.getString(6);
		ArrayList<String> genres = new ArrayList<String>();
		genres = getGenres(id);
		ArrayList<MiniStar> stars = new ArrayList<MiniStar>();
		stars = getStars(id);
		Movie movie = new Movie(id, title, year, director, banner_url, trailer_url, genres, stars);
		connection.close();
		return movie;
	}

	public Star getStarFull(String id) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		String str = "select * from stars where id = " + id;
		Statement select = connection.createStatement();

		ResultSet result = select.executeQuery(str);

		result.next();
		int starid = result.getInt(1);
		String first_name = result.getString(2);
		String last_name = result.getString(3);
		String dob = result.getString(4);
		String photo_url = result.getString(5);
		ArrayList<MiniMovie> movies = getMovieFromStar(id);
		Star star = new Star(starid, first_name, last_name, dob, photo_url, movies);
		connection.close();

		return star;
	}

	public ArrayList<MiniMovie> getMovieFromStar(String id) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");

		String str = "Select * from movies m, stars_in_movies s where m.id = s.movie_id and s.star_id = " + id;
		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery(str);
		ArrayList<MiniMovie> movies = new ArrayList<MiniMovie>();

		while (result.next()) {
			int movieid = result.getInt(1);
			String title = result.getString(2);

			MiniMovie movie = new MiniMovie(movieid, title);
			movies.add(movie);
		}
		
		connection.close();
		return movies;
	}

	public int findIndex(ArrayList<Item> items, int id) {
		int i;
		for (i = 0; i < items.size(); i++) {
			if (items.get(i).getMovie().getId() == id)
				return i;
		}
		return -1;

	}
	
	public boolean veryfyCC(String firstName, String lastName, String ccid, String exp) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");
		
		String str = "Select * from creditcards where first_name = ? and last_name=? and id = ? and expiration = ?";
		PreparedStatement select = connection.prepareStatement(str);
		select.setString(1, firstName);
		select.setString(2, lastName);
		select.setString(3, ccid);
		select.setString(4, exp);
		ResultSet result = select.executeQuery();
		boolean verified = false;
		if(result.next())
			verified = true;
		return verified;
	}
	
	public void addSale(int customerId, ArrayList<Integer> movieIds) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql:///moviedb?useSSL=false", "mytestuser",
				"mypassword");
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calobj = Calendar.getInstance();
		String date = df.format(calobj.getTime());
		
		for(Integer movieId : movieIds) {
			String str = "Insert into sales values(null, " + customerId + ", "+ movieId +", ?);";
			PreparedStatement insert = connection.prepareStatement(str);
			insert.setString(1, date);
			insert.executeUpdate();
		}
		
	}
}
