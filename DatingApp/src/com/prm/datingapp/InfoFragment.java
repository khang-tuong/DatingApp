package com.prm.datingapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.UpdateInfoTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InfoFragment extends Activity  {

	public static HomeActivity host;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_info);
		setup();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
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
			txtName.setText(HomeActivity.json.getString("name"));
			txtDescription.setText(HomeActivity.json.getString("selfDescription"));
			spAge.setSelection(HomeActivity.json.getInt("age") - 17);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void apply(View v) {
		EditText txtName = (EditText) this.findViewById(R.id.fragment_info_txtName);
		EditText txtDescription = (EditText) this.findViewById(R.id.fragment_info_txtDescription);
		Spinner spAge = (Spinner) this.findViewById(R.id.fragment_info_spAge);
		Spinner spDistrict = (Spinner) this.findViewById(R.id.fragment_info_spDistrict);
		
		String name = txtName.getText().toString();
		String description = txtDescription.getText().toString();
		Integer age = (Integer) spAge.getSelectedItem();
		String district = (String) spDistrict.getSelectedItem();
		
		try {
			new UpdateInfoTask(this).execute(HomeActivity.json.getString("id"), name, age.toString(), district, description);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onDoneUpdateInfo(String result){
		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
		host.updateData();
	}
 
 	
 
}