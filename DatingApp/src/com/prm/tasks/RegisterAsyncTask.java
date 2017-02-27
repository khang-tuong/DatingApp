package com.prm.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.prm.datingapp.RegisterActivity;

import android.os.AsyncTask;


public class RegisterAsyncTask extends AsyncTask<String, String, Long>{

	private RegisterActivity activity;
	
	public RegisterAsyncTask(RegisterActivity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	
	public long register(String username, String password, String confirmPassword) {
		// TODO Auto-generated method stub
		long id = -1;
		try {
			String url = "http://10.0.3.2:58996/Account/Register";
			
			String charset = "UTF_8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
			
			String query = String.format("username=%s&password=%s&passwordConfirm=%s", 
				     URLEncoder.encode(username, charset), 
				     URLEncoder.encode(password, charset),
				     URLEncoder.encode(confirmPassword, charset));
			
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
		    	id = Integer.parseInt(tmp);
		    }
		    //publishProgress(code + "", message, result);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	


	@Override
	protected Long doInBackground(String... params) {
		String username = params[0];
		String password = params[1];
		String passwordConfirm = params[2];
		
		long id = register(username, password, passwordConfirm);
		return id;
	}
	
	@Override
	protected void onPostExecute(Long result) {
		// TODO Auto-generated method stub
		this.activity.onRegisterSuccess(result);
	}
	

}
