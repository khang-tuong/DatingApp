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

//	 public void selectFrag(View view) {
//
//		          Fragment fr;
//		          if(view == findViewById(R.id.home)) {
//		              fr = new Tab1Activity();
//		          }
////		          else if(view == findViewById(R.id.SEARCH)) {
////		              fr = new Tab3Activity();
////		          }
////		          else if(view == findViewById(R.id.Matchs)) {
////		              fr = new Tab4Activity();
////		          }
//		          else {
//		              fr = new Tab1Activity();
//		          }
//		          FragmentManager fm = getFragmentManager();
//		          FragmentTransaction fragmentTransaction = fm.beginTransaction();
//		          fragmentTransaction.replace(R.id.tableLayout1, fr);
//		          fragmentTransaction.commit();
//	     }
////add end
	
	
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
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		
		//add Hungdm
		
	}

	public void onRegister(View v) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}

}
