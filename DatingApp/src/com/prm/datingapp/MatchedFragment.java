package com.prm.datingapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.DownloadImageTask;
import com.prm.tasks.GetMatchedListTask;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
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
//	ListView list;
//	String[] web = { "Google Plus", "Twitter", "Windows", "Bing", "Itunes", "Wordpress", "Drupal" };
//	Integer[] imageId = { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher
//
//	};
//	private List<String> listValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_matched);

		
		
		getMatchedList();

	}


	
	private void getMatchedList() {
		// TODO Auto-generated method stub
		try {
			new GetMatchedListTask(this).execute(HomeActivity.json.getString("Id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void setup(String result){
		try {
			final JSONArray obj = new JSONArray(result);
			int length = obj.length();
			List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
			for (int i = 0; i < length; i++) {
				jsonObjects.add((JSONObject) obj.get(i));
			}
			
			CustomListAdapter custom = new CustomListAdapter(this, android.R.id.list, jsonObjects);
			
			ListView listview = (ListView) findViewById(android.R.id.list);
			listview.setAdapter(custom);
			
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					ShowMatchedDetailDialog dialog;
					try {
						dialog = new ShowMatchedDetailDialog(((JSONObject) obj.get(position)));
						FragmentTransaction trans = getFragmentManager().beginTransaction();
						Fragment fragment = getFragmentManager().findFragmentByTag("showDetail");
						if (fragment != null) {
							trans.remove(fragment);
						}
						trans.addToBackStack(null);
						dialog.show(trans, "showDetail");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	private class CustomListAdapter extends ArrayAdapter<JSONObject>{

    	private List<JSONObject> obj;
    	
		public CustomListAdapter(Context context, int textViewResourceId, List<JSONObject> objects) {
			super(context, textViewResourceId, objects);
			this.obj = objects;
			// TODO Auto-generatedconstructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			SearchListItem item = new SearchListItem();
			
			LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.list_single, null);
			
			item.image = (ImageView) convertView.findViewById(R.id.matched_list_item_image);
			item.txtName = (TextView) convertView.findViewById(R.id.matched_list_item_txtName);
			
			//item.image.setImageBitmap(this.obj.get(position).getString("imageUrl"));
			try {
				item.txtName.setText(this.obj.get(position).getString("Name"));
				new DownloadImageTask(item.image).execute(this.obj.get(position).getString("ImageUrl"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return convertView;
		}
		
		
		
    	
    }

}
