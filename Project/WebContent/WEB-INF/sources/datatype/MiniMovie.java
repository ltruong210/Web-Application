package com.fabflix.datatype;

public class MiniMovie 
{
	private int id;
	private String title;

	
	public MiniMovie(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
}