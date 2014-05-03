package purdue.maplog.controllers;

import com.example.googlemaptest1.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordpage extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passwordpage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_change_passwordpage, menu);
        return true;
    }
	public void gomappage(View view){
	//Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "mappage", Toast.LENGTH_SHORT).show();
	Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	public void gomappage1(View view){
	//Intent intent = new Intent(this,NextActivity.class);
	
		//Login_UI.user.setPassword();
		EditText editOldPassword   = (EditText)findViewById(R.id.editText11);
		EditText editPassword   = (EditText)findViewById(R.id.editText12);
		EditText editPassword2   = (EditText)findViewById(R.id.editText13);
	
		String oldPassword = editOldPassword.getText().toString();
	
		String password = editPassword.getText().toString();
		String password2 = editPassword2.getText().toString();
		String s=Login_UI.user.getPassWord();
	//	Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
		
		if (Login_UI.user.getPassWord().equals(oldPassword)){
			if(!password.equals("")&&password.equals(password2)){
				Client.changePassword(Login_UI.user.getUserName(), oldPassword, password);
				Login_UI.user.setPassword(password);
				Toast.makeText(getApplicationContext(), password, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(this,MainActivity.class);
				startActivity(intent);
				
				
			}else {
				Toast.makeText(getApplicationContext(), "Failed to change password, make sure everything is correct.", Toast.LENGTH_SHORT).show();
				
			}
			
			
		}else{
			
			Toast.makeText(getApplicationContext(), "Oldpassword Wrong!", Toast.LENGTH_SHORT).show();
		}
		

	}
}
