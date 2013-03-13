package com.jimmykim.ecom;

public class Item implements Product {

	String name;
	double price;
	
	public Item(String name, double price){
		setName(name);
		setPrice(price);
	}
	
	@Override
	public boolean setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
		return false;
	}

	@Override
	public boolean setPrice(double price) {
		// TODO Auto-generated method stub
		this.price = price;
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return this.price;
	}

}
