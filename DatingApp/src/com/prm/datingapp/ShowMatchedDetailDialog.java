package com.prm.datingapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.prm.tasks.DownloadImageTask;
import com.prm.tasks.LikeTask;
import com.prm.tasks.UnMatchTask;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowMatchedDetailDialog extends DialogFragment {

private JSONObject data;
	
	public ShowMatchedDetailDialog(JSONObject data) {
		// TODO Auto-generated method stub
		this.data = data;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.show_matched_detail_dialog, container);
		Button btnClose = (Button) v.findViewById(R.id.show_matched_detail_dialog_btnClose);
		ImageView image = (ImageView) v.findViewById(R.id.show_matched_detail_dialog_image);
		TextView txtName = (TextView) v.findViewById(R.id.show_matched_detail_dialog_txtName);
		TextView txtDescription = (TextView) v.findViewById(R.id.show_matched_detail_dialog_txtDescription);
		Button btnLike = (Button) v.findViewById(R.id.show_detail_dialog_btnLike);
		
		btnClose.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowMatchedDetailDialog.this.dismiss();
			}
		});
		
		btnLike.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unmatch(v);
			}

			
		});
		
		try {
			new DownloadImageTask(image).execute(this.data.getString("ImageUrl"));
			txtName.setText(this.data.getString("Name"));
			txtDescription.setText(this.data.getString("Description"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
	}
	
	
	public void unmatch(View v) {
		// TODO Auto-generated method stub
		try {
			String accountSource = HomeActivity.json.getString("Id");
			String accountDes = data.getString("Id");
			
			new UnMatchTask(this).execute(accountSource, accountDes);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
