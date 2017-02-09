package com.fabflix.datatype;
import java.util.ArrayList;

public class Movie 
{
	private int id;
	private String title;
	private int year;
	private String director;
	private String banner_url;
	private String trailer_url;
	private ArrayList<String> genres;
	private ArrayList<MiniStar> stars;
	
	public Movie(int id, String title, int year, String director, String banner_url, String trailer_url, ArrayList<String> genres, ArrayList<MiniStar> stars) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
		this.genres = genres;
		this.stars= stars;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getBanner_url() {
		return banner_url;
	}
	
	public String getTrailer_url() {
		return trailer_url;
	}
	
	public ArrayList<String> getGenres() {
		return genres;
	}

	public ArrayList<MiniStar> getStars() {
		return stars;
	}

}
