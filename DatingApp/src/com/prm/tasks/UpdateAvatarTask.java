package com.prm.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.prm.datingapp.SettingFragment;

import android.os.AsyncTask;

public class UpdateAvatarTask extends AsyncTask<String, String, String> {

	public UpdateAvatarTask() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String updateAvatar(int id, String link) {
		String result = "";
		// TODO Auto-generated method stub
		try {
			String url = "http://10.0.3.2:58996/Account/UpdateAvatar";
			
			String charset = "UTF_8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
			
			String query = String.format("id=%s&link=%s", 
				     URLEncoder.encode(id + "", charset),
				     URLEncoder.encode(link + "", charset));
			
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
			connection.setRequestProperty("Host", "localhost:58996");
			
			OutputStream output = connection.getOutputStream(); 
		    output.write(query.getBytes(charset));
		    output.flush();
		    output.close();
		    
		    connection.connect();
		    int code = connection.getResponseCode();
		    if (code == 200){
		    	BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    	result = br.readLine();
		    }
		    //publishProgress(code + "", message, result);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	


	@Override
	protected String doInBackground(String... params) {
		int id = Integer.parseInt(params[0]);
		String link = params[1];
		
		String info = updateAvatar(id, link);
		return info;
	}
	
	
}
