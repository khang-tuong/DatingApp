package com.prm.uploadimage;

import java.util.List;

public class ImgurUploadResponseModel {
	
	public class Data
	{
	    public String id;
	    public Object title;
	    public Object description;
	    public int datetime;
	    public String type;
	    public boolean animated;
	    public int width;
	    public int height;
	    public int size;
	    public int views;
	    public int bandwidth;
	    public Object vote;
	    public boolean favorite;
	    public Object nsfw;
	    public Object section;
	    public Object account_url;
	    public int account_id;
	    public boolean is_ad;
	    public List<Object> tags;
	    public boolean in_gallery;
	    public String deletehash;
	    public String name;
	    public String link;
	}

	public class RootObject
	{
	    public Data data;
	    public boolean success;
	    public int status;
	}
	
}
