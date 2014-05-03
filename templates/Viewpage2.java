package com.example.googlemaptest1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(9) public class Viewpage2 extends Activity {

	TextView stime;
	String t;
	TextView content;
	int mader=13;
    int message=R.drawable.image_02;
    String message1="mde";
	public final static String EXTRA_MESSAGE = "com.example.viewpage2.MESSAGE";
	static{
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	    StrictMode.setThreadPolicy(policy);
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date=new Date();
    	t=dateFormat.format(date);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage2);
        
        stime = (TextView) findViewById(R.id.s_time);
        stime.setText("Time: "+t);
        
        content = (TextView) findViewById(R.id.content);
        content.setText("Content: "+Login_UI.user.maplogSet.get(0).getContent());
    }

	public void sendMessage(View view){
	//Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "button5", Toast.LENGTH_SHORT).show();
	Intent intent = new Intent(this,Viewpage1.class);
		startActivity(intent);
	}
	public void gomappage(View view){
	//Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "mappage", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}

	public void showpicture(View i){
	
		
	Intent intent = new Intent(this,ImageShow.class);
//		intent.putExtra(EXTRA_MESSAGE, message1);
//		
//		Bundle bundle=new Bundle();
//		bundle.putInt("madee",mader);
//		bundle.putString("as", "mm");
//		intent.putExtras(bundle);
		
	startActivity(intent);
	      
		
		
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_viewpage2, menu);
        
        return true;
       
    }
}
