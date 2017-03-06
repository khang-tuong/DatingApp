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

import android.content.Context;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchedFragment extends BaseAdapter{   
    String [] result;
    Context context;
 int [] imageId;
      private static LayoutInflater inflater=null;
    public void CustomAdapter(MainActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
         inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;       
             rowView = inflater.inflate(R.layout.fragment_matched, null);
             holder.tv=(TextView) rowView.findViewById(R.id.activity_home_txtWelcome);
             holder.img=(ImageView) rowView.findViewById(R.id.activity_home_userImage);       
         holder.tv.setText(result[position]);
         holder.img.setImageResource(imageId[position]);         
         rowView.setOnClickListener(new OnClickListener() {            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });   
        return rowView;
    }

} 