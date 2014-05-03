package com.example.googlemaptest1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class UserNode implements Serializable{
	private String username;
	private String password;
	public ArrayList<MaplogNode> maplogSet = new ArrayList<MaplogNode>();
	
	public UserNode(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	
	public UserNode() {
	}


	public String getUserName()
	{
		return username;
	}
	
	public String getPassWord()
	{
		return password;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
		
	}

	
};