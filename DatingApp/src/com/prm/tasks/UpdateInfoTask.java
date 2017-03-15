package com.prm.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.prm.datingapp.InfoFragment;

import android.os.AsyncTask;


public class UpdateInfoTask extends AsyncTask<String, String, String>{

	private InfoFragment activity;
	
	public UpdateInfoTask(InfoFragment activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	
	public String updateInfo(int id, String name, int age, String district, String description) {
		String result = "";
		// TODO Auto-generated method stub
		try {
			String url = "http://10.0.3.2:58996/Account/UpdateInfo";
			
			String charset = "UTF_8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
			
			String query = String.format("id=%s&name=%s&age=%s&district=%s&description=%s", 
				     URLEncoder.encode(id + "", charset),
				     URLEncoder.encode(name, charset),
				     URLEncoder.encode(age + "", charset),
				     URLEncoder.encode(district, charset),
				     URLEncoder.encode(description, charset));
			
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
		String name = params[1];
		int age = Integer.parseInt(params[2]);
		String district = params[3];
		String description = params[4];
		
		String info = updateInfo(id, name, age, district, description);
		return info;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		this.activity.onDoneUpdateInfo(result);
	}
	

}
