package com.prm.datingapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchedFragment extends ListActivity {
	ListView list;
	String[] web = { "Google Plus", "Twitter", "Windows", "Bing", "Itunes", "Wordpress", "Drupal" };
	Integer[] imageId = { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher

	};
	private List<String> listValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_matched);

		CustomList adapter = new CustomList(MatchedFragment.this, web, imageId);
		list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Toast.makeText(MatchedFragment.this, "You Clicked at " +web[+
				// position], Toast.LENGTH_SHORT).show();
				final int InternalPosition = position;
				// Toast.makeText(MainActivity.this, "You Clicked at " +web[+
				// position], Toast.LENGTH_SHORT).show();
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case DialogInterface.BUTTON_POSITIVE:
							// This doesn't work, can't access position from
							// here
							Object o = MatchedFragment.this.getListView().getItemAtPosition(InternalPosition);

							break;
						}
					}
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(MatchedFragment.this);
				builder.setMessage("Are you sure you want to do this?").setPositiveButton("Yes", dialogClickListener)
						.setNegativeButton("No", dialogClickListener).show();
				// check dialog here
			}
		});

	}

	public class Holder {
		TextView tv;
		ImageView img;
	}

//	public View getView(final int position, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		Holder holder = new Holder();
//		View rowView;
//		rowView = inflater. inflater.inflate(R.layout.fragment_matched, null);
//		holder.tv = (TextView) rowView.findViewById(R.id.activity_register_lbAppName);
//		holder.img = (ImageView) rowView.findViewById(R.id.activity_home_userImage);
//		holder.tv.setText(result[position]);
//		holder.img.setImageResource(imageId[position]);
//		rowView.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
//			}
//		});
//		return rowView;
//	}

}
