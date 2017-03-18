package com.prm.datingapp;


import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.DownloadImageTask;

import android.app.ActivityGroup;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
	public static JSONObject json;
	private ShowAvatarFragment showAvatarFragment;
	
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
		TextView txtUsername = (TextView) findViewById(R.id.activity_home_txtUsername);
		ImageView userImage = (ImageView) findViewById(R.id.activity_home_userImage);
		
		try {
			txtUsername.setText(json.getString("Name"));
			String imageURL = json.getString("ImageUrl");
			if (imageURL != null) {
				new DownloadImageTask(userImage).execute(json.getString("ImageUrl"));
				
			}
			setupTabs(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void setupTabs(final JSONObject json) {
		this.homeTabhost = (TabHost)findViewById(android.R.id.tabhost);
		homeTabhost.setup(getLocalActivityManager());

		TabSpec info  = homeTabhost.newTabSpec("fragment_info").setIndicator("Info").setContent(new Intent(this, InfoFragment.class));
		TabSpec setting  = homeTabhost.newTabSpec("fragment_setting").setIndicator("Setting").setContent(new Intent(this, SettingFragment.class));
		TabSpec search = homeTabhost.newTabSpec("fragment_search").setIndicator("Search").setContent(new Intent(this, SearchFragment.class));
		TabSpec matched = homeTabhost.newTabSpec("fragment_matched").setIndicator("Matched").setContent(new Intent(this, MatchedFragment.class));
		
		homeTabhost.clearAllTabs();
		homeTabhost.addTab(info);
		homeTabhost.addTab(setting);
		homeTabhost.addTab(search);
		homeTabhost.addTab(matched);
		InfoFragment.host = HomeActivity.this;
		
		this.homeTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				if (tabId.equals("fragment_info")) {
					InfoFragment.host = HomeActivity.this;
				} else if (tabId.equals("fragment_setting")){
					SettingFragment.host = HomeActivity.this;
				}
			}
		});
		
	}
	
	public void updateData(){
		setup();
	}

	public void logout(View v) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}
	
	public void onAvatarClick(View v) throws JSONException {
		this.showAvatarFragment = new ShowAvatarFragment(json.getString("ImageUrl"));
		FragmentTransaction trans = getFragmentManager().beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			trans.remove(prev);
		}
		trans.addToBackStack(null);
		
		this.showAvatarFragment.show(trans, "dialog");
		
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		this.showAvatarFragment.onActivityResult(requestCode, resultCode, data);
        

    }
	
	
	
	
	
	
	
	
}
