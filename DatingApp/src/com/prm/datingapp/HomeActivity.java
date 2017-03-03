package com.prm.datingapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.GetAccountAsyncTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class HomeActivity extends Activity {
	private TabHost homeTabhost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		//setup();
		
		//add
		//add
		homeTabhost = (TabHost)findViewById(R.id.tabHost);
		
		homeTabhost.setup();

		TabSpec tab = homeTabhost.newTabSpec("tab1").setIndicator("Tab1");
		tab.setContent(R.layout.tab1);
		homeTabhost.addTab(tab);
//		homeTabhost.addTab(homeTabhost.newTabSpec("tab2").setIndicator("Tab2"),
//				Tab2Activity.class, null);
//		homeTabhost.addTab(homeTabhost.newTabSpec("tab3").setIndicator("Tab3"),
//				Tab3Activity.class, null);
		//add end
	}

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
	
	public void onDoneGetInfo(String info) {
		try {
			JSONObject obj = new JSONObject(info);
			Log.d("Username", obj.getString("username")); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
