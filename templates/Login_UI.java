package com.example.googlemaptest1;


import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Login_UI extends Activity {

	public static Hashtable<String, String> userList;
	public static UserNode user = new UserNode();
	
	String con_server="time-A.timefreq.bldrdoc.gov";
	int port=13;
	Client_test st=new Client_test("time-A.timefreq.bldrdoc.gov", 13);
	
	private void getUserList() {//this should be on server-side, pretend to get user list. have 3 examples instead
		
		userList = new Hashtable<String, String>();
		userList.put("ABC","ABC");
		userList.put("123","123");
		userList.put("a","a");
		
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	getUserList();
//    	SaveAndLoad load = new SaveAndLoad();
//		try {
//			load.load();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__ui);
        
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login__ui, menu);
       
		
        return true;
    }
    public void jumpToRegisterUI(View view){
    	Intent intent = new Intent(this,Register_UI.class);
    	startActivity(intent);
    }
	public void gomappage(View view){
	//Intent intent = new Intent(this,NextActivity.class);
	//Toast.makeText(getApplicationContext(), st.getServerString(), Toast.LENGTH_LONG).show();
	//TODO: get info from textfield and save to user class
		
		
		/* fake getUserList */
		
		EditText editUsername   = (EditText)findViewById(R.id.editText1);
		EditText editPassword   = (EditText)findViewById(R.id.editText2);
		
		String username = editUsername.getText().toString();
		String password = editPassword.getText().toString();

		
		if(username.equals("") || password.equals("") )
		{
			Toast toast = Toast.makeText(getApplicationContext(), "null input", Toast.LENGTH_LONG);
			toast.show();
		}
		else if(user!=null && username.equals(user.getUserName()) && password.equals(user.getPassWord()))
		{
			Toast toast = Toast.makeText(getApplicationContext(), "login successful!", Toast.LENGTH_LONG);
			toast.show();

			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
		}
		else if(!userList.contains(username))
		{
			Toast toast = Toast.makeText(getApplicationContext(), "username not found", Toast.LENGTH_LONG);
			toast.show();
		}
		else if(!userList.get(username).equals(password))
		{
			Toast toast = Toast.makeText(getApplicationContext(), "password not match", Toast.LENGTH_LONG);
			toast.show();
		}
		else
		{
			Toast toast = Toast.makeText(getApplicationContext(), "login successful!", Toast.LENGTH_LONG);
			toast.show();

			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
		}
	}



	
}
