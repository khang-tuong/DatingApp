package com.prm.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.prm.datingapp.LoginActivity;

import android.os.AsyncTask;


public class LoginTask extends AsyncTask<String, String, String>{

	private LoginActivity activity;
	
	public LoginTask(LoginActivity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	
	public String login(String username, String password) {
		// TODO Auto-generated method stub
		int id = -1;
		try {
			String url = "http://10.0.3.2:58996/Account/Login";
			
			String charset = "UTF_8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
			
			String query = String.format("username=%s&password=%s", 
				     URLEncoder.encode(username, charset), 
				     URLEncoder.encode(password, charset));
			
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
		    	String tmp = br.readLine();
		    	return tmp;
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	


	@Override
	protected String doInBackground(String... params) {
		String username = params[0];
		String password = params[1];
		
		String result = login(username, password);
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		this.activity.onLoginSuccess(result);
	}
	

}
