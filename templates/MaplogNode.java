package com.example.googlemaptest1;

import java.util.Date;

import android.graphics.Bitmap;
import android.location.Location;
import android.widget.ImageView;

public class MaplogNode {

	private int id;
	private String title;
	private String content;
	private ImageView picture;
	private Bitmap preview;
	private Location loc;
	private Date date;
	
	public MaplogNode(Date date)
	{
		this.date = date;
	}
	
	public MaplogNode(int id, String title, String content, ImageView picture, Bitmap preview, Location loc, Date date)
	{
		this.id = id;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.preview = preview;
		this.loc = loc;
		this.date = date;
	}
	public int getMaplogId(){
		return id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getContent(){
		return content;
	}
	
	public ImageView getPicture(){
		return picture;
	}
	
	public Bitmap getPreview(){
		return preview;
	}
	
	public Location getLoc(){
		return loc;
	}
	
	public Date getDate(){
		return date;
	}

	public void setTitle(String title2) {
		this.title = title2;
	}

	public void setContent(String content2) {
		this.content = content2;
	}
}
