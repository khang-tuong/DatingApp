//package com.prm.datingapp;
//import com.prm.datingapp.R;
//
//import android.app.Fragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//public class Tab2Activity  extends Fragment {
//	 @Override
//
//	    public View onCreateView(LayoutInflater inflater,
//
//	       ViewGroup container, Bundle savedInstanceState) {
//
//	        
//
//	        //Inflate the layout for this fragment
//
//	       return inflater.inflate(
//
//	               R.layout.tab2, container, false);
//
//	    }
//
////	public Tab1Activity() {
////		// TODO Auto-generated constructor stub
////	}
//
//}



package com.prm.datingapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.UpdateInfoTask;
import com.prm.tasks.UpdateSettingTask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.provider.Contacts.SettingsColumns;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingFragment extends Activity  {

	public static HomeActivity host;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_setting);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setup();
	}
	
	public void setup(){
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.fragment_setting_gender);
		SeekBar sb = (SeekBar) findViewById(R.id.fragment_setting_sbAge);
		
		try {
			if (HomeActivity.json.get("interest") != null)
				if (HomeActivity.json.get("Interest").toString().equalsIgnoreCase("True"))
					radioGroup.check(R.id.fragment_setting_male);
				else 
					radioGroup.check(R.id.fragment_setting_female);
			
			if(HomeActivity.json.get("AgeRange") != null)
				sb.setProgress(Integer.parseInt(HomeActivity.json.getString("AgeRange")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void apply(View v) {
		boolean interest = false;
		int ageRange = 0;
		
		RadioGroup rdGroup = (RadioGroup) findViewById(R.id.fragment_setting_gender);
		RadioButton rd = (RadioButton) findViewById(rdGroup.getCheckedRadioButtonId());
		
		interest = rd.getText().equals("Male");
		
		SeekBar sb = (SeekBar) findViewById(R.id.fragment_setting_sbAge);
		ageRange = sb.getProgress();
		
		try {
			new UpdateSettingTask(this).execute(HomeActivity.json.getString("Id"), interest + "", ageRange + "");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onDoneUpdateInfo(String result) {
		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
		host.updateData();

	}
}