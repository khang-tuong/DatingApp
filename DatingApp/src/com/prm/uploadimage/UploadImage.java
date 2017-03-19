package com.prm.uploadimage;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.commons.io.*;
import org.json.*;

import android.graphics.Bitmap;
import biz.source_code.base64Coder.Base64Coder;


public class UploadImage {

	
	/**	
	 * <h2> Worked version </h2>
	 * <p>Upload image to imgur</p>
	 * @param filePath The link of file in local to upload
	 * @return <i>link</i> of the image
	 */
	public String uploadImage3(Bitmap bitmap) throws MalformedURLException, IOException, JSONException{
		//File file = new File(filePath);
		
		
		
		/**
		 * Encode file to base64 format to upload
		 */
		//char[] code = Base64Coder.encode(FileUtils.readFileToByteArray(file));
		
		
		
		/**
		 * URL of api 
		 */
		String url = "https://api.imgur.com:443/3/image";
		
		
		
		/**
		 * Setup params for the api
		 * 		charset
		 * 		image: the base64 format of the image
		 * 		key: CLIENT_ID on imgur
		 * 		type: define type of the image, in this case is base64
		 */
		String charset = "UTF_8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
		String image = String.valueOf(bitmap);
		String key = "afa812dad401a06";
		String type = "base64";
		
		String query = String.format("image=%s&key=%s&type=%s", 
			     URLEncoder.encode(image, charset), 
			     URLEncoder.encode(key, charset),
			     URLEncoder.encode(type, charset));
		
		
		/**
		 * Open an url connection
		 * Note: MUST set "Authorization" field to have access to upload
		 */
		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST.
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
		connection.setRequestProperty("Authorization", "Client-ID afa812dad401a06");
		
		try {
			OutputStream output = connection.getOutputStream();
			output.write(query.getBytes(charset));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		/**
		 * Get response from server
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		
		
		/**
		 * Parse it to JSON and return the link of the image
		 */
		JSONObject object = new JSONObject(br.readLine());
		String link = (String) object.get("link");
		
		return link;
		
		
	}
	
}