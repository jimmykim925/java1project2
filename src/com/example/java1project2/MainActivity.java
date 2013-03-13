package com.example.java1project2;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.jimmykim.ecom.Product;
import com.jimmykim.ecom.Item;
import com.jimmykim.ecom.Value;
import com.jimmykim.lib.packageClass;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	// class variables
	RadioGroup productOptions;
	
	// Array for products
	ArrayList<Product> products;
	
	// Text view
	TextView result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Linear Layout
		LinearLayout ll = new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout entryBox = packageClass.singleEntryWithButton(this, "Insert an Integer Value", "Submit");
		
		// Create button
		Button valueButton = (Button) entryBox.findViewById(2);
		
		// Event for button
		valueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int selectedRadioId = productOptions.getCheckedRadioButtonId();
				RadioButton selectedRadio = (RadioButton) productOptions.findViewById(selectedRadioId);
				String radioText = (String)selectedRadio.getText();
				
				double price = 0;
				
				// conditional
				for (int i = 0; i < products.size(); i++){
					if(radioText.compareTo(products.get(i).getName()) == 0){
						price = products.get(i).getPrice();
					}
				}
				
				// get reference to the tag for the text field
				EditText value = (EditText)v.getTag();
				double amountDouble = Double.parseDouble(value.getText().toString());
				
				double refund = amountDouble - price;
				
				HashMap<Value, Integer> change = Value.getChange(refund);
				
				// add text and values to the view
				result.setText("Change Due: " + "\r\n" + "Dollar: " + change.get(Value.DOLLAR) + "\r\n" +
						"QUARTER: " + change.get(Value.QUARTER) + "\r\n" +
						"DIME: " + change.get(Value.DIME) + "\r\n" +
						"NICKEL: " + change.get(Value.NICKEL) + "\r\n" +
						"PENNY: " + change.get(Value.PENNY));
			}
		});
		
		// Use Resource to get strings for items, from items.xml
		String resourceStringOne = getString(R.string.item_one);
		String resourceStringTwo = getString(R.string.item_two);
		String resourceStringThree = getString(R.string.item_three);
		String resourceStringFour = getString(R.string.item_four);
		
		// Creates a new JSON Object
		JSONObject obj=new JSONObject();
		
		try {
		//  Add value to JSON Objects using string from Resource
			obj.put("productOne",resourceStringOne);
			obj.put("productTwo",resourceStringTwo);
			obj.put("productThree",resourceStringThree);
			obj.put("productFour",resourceStringFour);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// create strings to hold retrieved JSON strings 
		String oneTitle = null;
		String twoTitle = null;
		String threeTitle = null;
		String fourTitle = null;
		
		try {
			// get object
			oneTitle = (String) obj.get("productOne");
			twoTitle = (String) obj.get("productTwo");
			threeTitle = (String) obj.get("productThree");
			fourTitle = (String) obj.get("productFour");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// create list using strings from JSON retrieval, set prices for products
		products = new ArrayList<Product>();
		products.add(new Item(oneTitle, 69.73));
		products.add(new Item(twoTitle, 49.52));
		products.add(new Item(threeTitle, 29.86));
		products.add(new Item(fourTitle, 9.31));
		
		// string array
		String[] productNames = new String[products.size()];
		for (int i =0; i < products.size(); i++){
			productNames[i] = products.get(i).getName();
		}
		
		// package class
		productOptions = packageClass.getOptions(this, productNames);
		
		// add views
		ll.addView(productOptions);
		ll.addView(entryBox);
		result = new TextView(this);
		ll.addView(result);
		
		setContentView(ll);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
