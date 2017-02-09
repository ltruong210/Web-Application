package com.fabflix.datatype;
import java.util.ArrayList;

public class Star 
{
	private int id;
	private String firstName;
	private String lastName;
	private String dob;
	private String photoUrl;
	private ArrayList<MiniMovie> movies;
	
	public Star(int id, String firstName, String lastName, String dob, String photo_url, ArrayList<MiniMovie> movies) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.photoUrl = photo_url;
		this.movies = movies;
	}
	
	public int getId() {
		return id;
	}
	
	public String  getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getDob() {
		return dob;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public ArrayList<MiniMovie> getMovies() {
		return movies;
	}
	
}
