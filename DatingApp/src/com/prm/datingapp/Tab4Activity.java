//package com.prm.datingapp;
//import com.prm.datingapp.R;
//
//import android.app.Fragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//public class Tab4Activity  extends Fragment {
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
//	               R.layout.tab4, container, false);
//
//	    }
////
////	public Tab1Activity() {
////		// TODO Auto-generated constructor stub
////	}
//
//}

package com.prm.datingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Tab4Activity extends  ListActivity {
    ListView list;
    String[] web = {
        "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId = {
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher
 
    };
    private List<String> listValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab4);
 
        CustomList adapter = new
                CustomList(Tab4Activity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Toast.makeText(Tab4Activity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
 
                    }
                });
 
    }
 
}