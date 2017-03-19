
package com.prm.datingapp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.DownloadImageTask;
import com.prm.tasks.SearchTask;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SearchFragment extends Activity  {

 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.fragment_search);
    }
    
    
    public void search(View v) {
    	EditText txtSearch = (EditText)findViewById(R.id.fragment_search_txtSearch);
    	String keyword = txtSearch.getText().toString();
    	
    	try {
			new SearchTask(this).execute(HomeActivity.json.getString("Id"), keyword);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void onDoneSearch(String result) {
    	try {
			final JSONArray obj = new JSONArray(result);
			int length = obj.length();
			List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
			for (int i = 0; i < length; i++) {
				jsonObjects.add((JSONObject) obj.get(i));
			}
			
			CustomListAdapter custom = new CustomListAdapter(this, R.id.fragment_search_listview, jsonObjects);
			
			ListView listview = (ListView) findViewById(R.id.fragment_search_listview);
			listview.setAdapter(custom);
			
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					ShowDetailDialog dialog;
					try {
						dialog = new ShowDetailDialog(((JSONObject) obj.get(position)));
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
			convertView = inflator.inflate(R.layout.search_list_item, null);
			
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