package com.prm.datingapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class InfoFragment extends Activity  {

	public static JSONObject json;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_info);
		setup();
		
	}

 
	private void setup() {
		// TODO Auto-generated method stub
		EditText txtName = (EditText) this.findViewById(R.id.fragment_info_txtName);
		EditText txtDescription = (EditText) this.findViewById(R.id.fragment_info_txtDescription);
		Spinner spAge = (Spinner) this.findViewById(R.id.fragment_info_spAge);
		Spinner spDistrict = (Spinner) this.findViewById(R.id.fragment_info_spDistrict);
		
		List<Integer> ages = new ArrayList<Integer>();
		List<String> districts = new ArrayList<String>();
		
		for(int i = 18; i <= 60; ++i) {
			ages.add(i);
		}
		for(int i = 1; i <= 12; ++i) {
			districts.add(i + "");
		}
		
		ArrayAdapter<Integer> ageAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ages);
		ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districts);
		districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spAge.setAdapter(ageAdapter);
		spDistrict.setAdapter(districtAdapter);
		
		try {
			txtName.setText(json.getString("name"));
			txtDescription.setText(json.getString("selfDescription"));
			spAge.setSelection(json.getInt("age") - 17);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
 	
 
}