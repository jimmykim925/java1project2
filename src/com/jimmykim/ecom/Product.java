package com.jimmykim.ecom;

public interface Product {
	// set name
	public boolean setName(String name);
	
	// price
	public boolean setPrice(double price);
	
	// get name
	public String getName();
	
	// get price
	public double getPrice();
	
}
