package com.jimmykim.ecom;

import java.util.HashMap;

public enum Value {
	// set values for entities
	PENNY(1),
	NICKEL(5),
	DIME(10),
	QUARTER(25),
	DOLLAR(100);
	
	private final int amount;
	
	Value(int amount){
		this.amount = amount;
		
	}
	
	// use hash map to get values
	public static HashMap<Value, Integer> getChange(double getAmt){
		HashMap<Value, Integer> change = new HashMap<Value, Integer>();
		
		Value[] coins = {DOLLAR, QUARTER, DIME, NICKEL, PENNY};
		
		double left = getAmt*100;
		
		// conditional to get the remainder change
		for(int i = 0; i < coins.length; i++){
			Value coin = coins[i];
			int num = (int) Math.floor(left/coin.amount);
			left = left%coin.amount;
			change.put(coin, num);
		}
		
		return change;
		
	}
}

