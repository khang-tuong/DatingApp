package com.prm.datingapp;


import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.*;
import com.prm.datingapp.HomeActivity;

/**
 * @author khang
 *
 */
public class MainActivity extends Activity {
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
//		Intent intent = new Intent(this, LoginActivity.class);
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		startActivity(intent);
	
	}

	public void onRegister(View v) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}

}
