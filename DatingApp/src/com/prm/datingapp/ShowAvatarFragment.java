package com.prm.datingapp;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

import org.json.JSONException;

import com.prm.tasks.DownloadImageTask;
import com.prm.tasks.UpdateAvatarTask;
import com.prm.tasks.UploadImageTask;
import com.prm.uploadimage.UploadImage;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient.FileChooserParams;
import android.widget.Button;
import android.widget.ImageView;

public class ShowAvatarFragment extends DialogFragment{

	private String imageUrl;
	public HomeActivity activity;
	private View layoutView;
	
	public ShowAvatarFragment(String imageUrl) {
		// TODO Auto-generated constructor stub
		this.imageUrl = imageUrl;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_show_avatar, container, false);
		this.layoutView = v;
		ImageView image = (ImageView) v.findViewById(R.id.fragment_show_avatar_image);
		Button btnClose = (Button) v.findViewById(R.id.fragment_show_avatar_btnClose);
		Button btnChange = (Button) v.findViewById(R.id.fragment_show_avatar_btnChange);
		
		btnClose.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowAvatarFragment.this.dismiss();
			}
		});
		new DownloadImageTask(image).execute(this.imageUrl);
		
		btnChange.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				
				getActivity().startActivityForResult(Intent.createChooser(intent, "Choose an image"), 1);
			}
		});
		
		return v;
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) this.layoutView.findViewById(R.id.fragment_show_avatar_image);

            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            imageView.setImageBitmap(bmp);
            
            ImageView avatar = (ImageView) getActivity().findViewById(R.id.activity_home_userImage);
            avatar.setImageBitmap(bmp);

        }

	}

	private Bitmap getBitmapFromUri(Uri uri) throws IOException, JSONException {
        ParcelFileDescriptor parcelFileDescriptor =
        		getActivity().getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        
        
        
        new UploadImageTask(this).execute(image);
        return image;
    }
	
	
	
	public void close(View v){
		this.dismiss();
	}
	
	public void onDoneUploadAvatar(String url) throws JSONException{
		new UpdateAvatarTask().execute(HomeActivity.json.getInt("id") + "", url);
	}
	
}
