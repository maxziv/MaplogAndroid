package com.example.googlemaptest1;


import java.util.Date;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

@TargetApi(9)
public class Viewpage1 extends Activity {
	static{
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	    StrictMode.setThreadPolicy(policy);
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_viewpage1, menu);
        return true;
    }
	public void viewlist(View view){
	//Intent intent = new Intent(this,NextActivity.class);
		
		EditText editTitle   = (EditText)findViewById(R.id.editText1);
		EditText editText   = (EditText)findViewById(R.id.editText2);
		String title = editTitle.getText().toString();
		String content = editText.getText().toString();
		
		MaplogNode currentNode = new MaplogNode(new Date()); //new node id = current size
		
		currentNode.setTitle(title);
		currentNode.setContent(content);
		
		
		Login_UI.user.maplogSet
			.add(currentNode);
		
		
		
		Toast.makeText(getApplicationContext(), "button5", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this,ListLog.class);
		startActivity(intent);
		
		
	}
	
	
}
