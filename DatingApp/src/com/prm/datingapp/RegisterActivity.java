package com.prm.datingapp;


import com.prm.tasks.RegisterAsyncTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	
	public void onRegisterSuccess(long id) {
		Intent intent = new Intent(this, HomeActivity.class);
		Bundle bundle = new Bundle();
		bundle.putLong("user_id", id);
		intent.putExtra("user_info", bundle);
		startActivity(intent);
	}
	
	public void onRegister(View v) {
		String username = ((EditText) findViewById(R.id.activity_register_txtUsername)).getText().toString();
		String password = ((EditText) findViewById(R.id.activity_register_txtPassword)).getText().toString();
		String confirmPassword = ((EditText) findViewById(R.id.activity_register_txtConfirmPassword)).getText().toString();
		
		if (!password.equals(confirmPassword)) {
			Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();
		} else {
			new RegisterAsyncTask(this).execute(username, password, confirmPassword);
		}
	}
}
