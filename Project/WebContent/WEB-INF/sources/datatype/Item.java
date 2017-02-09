package com.fabflix.datatype;


public class Item {

	private MiniMovie movie;
	private int quantity;
	
	public Item(MiniMovie movie, int quantity) 
	{	
		this.movie = movie;
		this.quantity = quantity;
	}

	public MiniMovie getMovie() 
	{
		return movie;
	}
	
	
	public int getQuantity() 
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity() {
		this.quantity += 1;
	}
	
}
	