package com.prm.datingapp;


import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.LoginTask;

//import com.prm.tasks.LoginTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
//import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	public void onLogin(View v) {
		String username = ((EditText) findViewById(R.id.activity_login_txtUsername)).getText().toString();
		String password = ((EditText) findViewById(R.id.activity_login_txtPassword)).getText().toString();
		
		new LoginTask(this).execute(username, password);
		
	}
	
	public void onLoginSuccess(String result) {
		try {
			JSONObject json = new JSONObject(result);
			if (!json.has("Error")) {
				Intent intent = new Intent(this, HomeActivity.class);
				HomeActivity.json = json;
				startActivity(intent);
				this.finish();
			} else {
				Toast.makeText(this, json.getString("Error"), Toast.LENGTH_SHORT).show();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
