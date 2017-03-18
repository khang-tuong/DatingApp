package com.prm.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.prm.datingapp.SearchFragment;

import android.os.AsyncTask;
import android.util.Log;

public class SearchTask extends AsyncTask<String, String, String> {

	private SearchFragment activity;
	
	public SearchTask(SearchFragment activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	
	public String updateInfo(String id, String keyword) {
		String result = "";
		// TODO Auto-generated method stub
		try {
			String url = "http://10.0.3.2:58996/Matched/Search";
			
			String charset = "UTF_8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
			
			String query = String.format("accountId=%s&keyword=%s", 
				     URLEncoder.encode(id, charset),
				     URLEncoder.encode(keyword, charset));
			
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
		    	Log.d("Result", result);
		    } else {
		    	Log.d("Error: ", connection.getResponseMessage());
		    }
		    //publishProgress(code + "", message, result);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	


	@Override
	protected String doInBackground(String... params) {
		String id = params[0];
		String keyword = params[1];
		
		String result = updateInfo(id, keyword);
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		this.activity.onDoneSearch(result);
	}
	
	
}
