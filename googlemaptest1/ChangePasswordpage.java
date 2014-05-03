package com.example.googlemaptest1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
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
}
