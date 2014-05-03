package purdue.maplog.controllers;


import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.example.googlemaptest1.R;

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
	public static int currentViewNumber = -1;
	public static boolean isSuccess;
	String con_server="time-A.timefreq.bldrdoc.gov";
	int port=13;
	Client_test st=new Client_test("time-A.timefreq.bldrdoc.gov", 13);



	@Override
	public void onCreate(Bundle savedInstanceState) {
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



		EditText editUsername   = (EditText)findViewById(R.id.editText1);
		EditText editPassword   = (EditText)findViewById(R.id.editText2);

		String username = editUsername.getText().toString();
		String password = editPassword.getText().toString();


		if(username == null| password == null| username.equals("")|password.equals(""))
		{
			Toast toast = Toast.makeText(getApplicationContext(), "null input", Toast.LENGTH_LONG);
			toast.show();

		}
		else{
			user.setPassword(password);
			user.setUsername(username);
			try {
				new SaveAndLoad().load();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(user == null)
			{
				Toast toast = Toast.makeText(getApplicationContext(), "user/password incorrect", Toast.LENGTH_LONG);
				toast.show();
				user = new UserNode();
				Intent intent = new Intent(this, Login_UI.class);
				startActivity(intent);
			}
			else{
				Toast toast = Toast.makeText(getApplicationContext(), "login successful!", Toast.LENGTH_LONG);
				toast.show();
				user.setPassword(password);
				user.setUsername(username);
				Client.saveToServer(Login_UI.user.getUserName(),Login_UI.user.getPassWord(), Login_UI.user);
				Intent intent = new Intent(this,MainActivity.class);
				startActivity(intent);
			}
		}
	}	
}
