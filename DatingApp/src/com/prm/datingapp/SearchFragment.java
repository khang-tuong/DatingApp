//package com.prm.datingapp;
//import com.prm.datingapp.R;
//
//import android.app.Fragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//public class Tab3Activity  extends Fragment {
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
//	               R.layout.tab3, container, false);
//
//	    }
//
////	public Tab1Activity() {
////		// TODO Auto-generated constructor stub
////	}
//
//}


package com.prm.datingapp;

import android.app.Fragment;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchFragment extends Fragment  {

 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_search, container, false);

        return V;
    }
}