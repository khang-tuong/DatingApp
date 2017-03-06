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

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.provider.Contacts.SettingsColumns;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingFragment extends Activity  {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_setting);
	}
	
}