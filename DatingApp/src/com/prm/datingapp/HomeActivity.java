package com.prm.datingapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.DownloadImageTask;
import com.prm.tasks.GetAccountAsyncTask;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.prm.datingapp.HomeActivity;

public class HomeActivity extends ActivityGroup {
	private TabHost homeTabhost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setup();
		
	}
		//add end
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void setup() {
		Bundle bundle = getIntent().getBundleExtra("user_info");
		String id = bundle.getLong("user_id") + "";
		new GetAccountAsyncTask(this).execute(id);
		
		
	}
	
	public void setupTabs(final JSONObject json) {
		this.homeTabhost = (TabHost)findViewById(android.R.id.tabhost);
		homeTabhost.setup(getLocalActivityManager());

		TabSpec info  = homeTabhost.newTabSpec("fragment_info").setIndicator("Info").setContent(new Intent(this, InfoFragment.class));
		TabSpec setting  = homeTabhost.newTabSpec("fragment_setting").setIndicator("Setting").setContent(new Intent(this, SettingFragment.class));
		TabSpec search = homeTabhost.newTabSpec("fragment_search").setIndicator("Search").setContent(new Intent(this, SearchFragment.class));
		TabSpec matched = homeTabhost.newTabSpec("fragment_matched").setIndicator("Matched").setContent(new Intent(this, MatchedFragment.class));
		
		homeTabhost.addTab(info);
		homeTabhost.addTab(setting);
		homeTabhost.addTab(search);
		homeTabhost.addTab(matched);
		
		this.homeTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				if (tabId.equals("fragment_info")) {
					//setupInfo(json);
				}
			}
		});
	}
	
	public void onDoneGetInfo(String info) {
		try {
			JSONObject obj = new JSONObject(info);
			InfoFragment.json = obj;
			TextView txtUsername = (TextView) findViewById(R.id.activity_home_txtUsername);
			ImageView userImage = (ImageView) findViewById(R.id.activity_home_userImage);
			
			txtUsername.setText(obj.getString("username"));
			String imageURL = obj.getString("imageURL");
			if (imageURL != null) {
				new DownloadImageTask(userImage).execute(obj.getString("imageURL"));
				
			}
			setupTabs(obj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setupInfo(JSONObject json) {
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
