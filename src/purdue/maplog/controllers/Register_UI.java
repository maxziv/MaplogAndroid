package purdue.maplog.controllers;

import com.example.googlemaptest1.R;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(9)
public class Register_UI extends Activity {
	static{
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	    StrictMode.setThreadPolicy(policy);
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__ui);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_register__ui, menu);
        return true;
    }
	public void gologinpage(View view){
	//Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "115", Toast.LENGTH_SHORT).show();
	Intent intent = new Intent(this,Login_UI.class);
		startActivity(intent);
	}
	
	public void gonewregister(View view){
		//TODO:

		
		EditText editUsername   = (EditText)findViewById(R.id.editText1);
		EditText editPassword   = (EditText)findViewById(R.id.editText2);
		EditText editPassword2   = (EditText)findViewById(R.id.editText3);
		
		String username = editUsername.getText().toString();
		String password = editPassword.getText().toString();
		String password2 = editPassword2.getText().toString();

		
		if(username.equals("") || password.equals("") || password2.equals(""))
		{
			Toast toast = Toast.makeText(getApplicationContext(), "null input", Toast.LENGTH_LONG);
			toast.show();
		}
		else if(!password.equals(password2))
		{
			Toast toast = Toast.makeText(getApplicationContext(), "Two passwords not match", Toast.LENGTH_LONG);
			toast.show();
		}
		else{
			Login_UI.user.setUsername(username);
			Login_UI.user.setPassword(password);
			
			if(	!Client.userRegister(username, password))
			{ Toast toast = Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG);
				toast.show();
			}
			else{
			Toast.makeText(getApplicationContext(), "registered", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this,Login_UI.class);
				startActivity(intent);
			}
		}
	}
}
