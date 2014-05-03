package com.example.googlemaptest1;


import java.util.Date;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(9)
public class Viewpage1 extends Activity {
	

	public  MapController mapController;
	public MapView mapView;
	public LocationManager locationManager;
	public Location currentLocation;
	public GeoPoint currentPoint;
	
	
	private static final int CAMERA_PIC_REQUEST = 1337;
	
	static{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {

		TextView content;
		TextView title;

		if(Login_UI.currentViewNumber == -1){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_viewpage1);
		}
		else{
			try{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_viewpage1);
			
			content = (TextView) findViewById(R.id.editText2);
			content.setText(Login_UI.user.maplogSet.get(Login_UI.currentViewNumber).getContent());

			title = (TextView) findViewById(R.id.editText1);
			title.setText(Login_UI.user.maplogSet.get(Login_UI.currentViewNumber).getTitle());
			}
			catch(Exception e)
			{
				//Log.d(UI_MODE_SERVICE,e.getMessage());
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_viewpage1, menu);
		return true;
	}
	

	public void takePhoto(View view) {
		// Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "button5", Toast.LENGTH_SHORT)
				.show();
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra("aspectX",1);
		cameraIntent.putExtra("aspectY",1);
		cameraIntent.putExtra("outputX",100);
		cameraIntent.putExtra("outputY",100);
		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_PIC_REQUEST) {
			if (data!=null && data.getExtras()!=null) {
				Bitmap newPhoto = (Bitmap) data.getExtras().get("data");
				ImageButton image = (ImageButton) findViewById(R.id.button1);
				image.setImageBitmap(newPhoto);
			}
		}
	}
	
	
	public void viewlist(View view){ //new/edit page hit "done"
		//Intent intent = new Intent(this,NextActivity.class);
		EditText editTitle   = (EditText)findViewById(R.id.editText1);
		EditText editText   = (EditText)findViewById(R.id.editText2);
		String title = editTitle.getText().toString();
		String content = editText.getText().toString();


		String provider = getBestProvider();
		currentLocation = locationManager.getLastKnownLocation(provider);
		
		if(Login_UI.currentViewNumber == -1){ //new page
			
			MaplogNode currentNode = new MaplogNode(new Date()); //new node id = current size

			currentNode.setTitle(title);
			currentNode.setContent(content);
			currentNode.setLocation(currentLocation);

			Login_UI.user.maplogSet
			.add(currentNode);
		}
		else{//edit page

			MaplogNode currentNode = Login_UI.user.maplogSet.get(Login_UI.currentViewNumber);
			currentNode.setTitle(title);
			currentNode.setContent(content);
			currentNode.setLocation(currentLocation);
		}
		SaveAndLoad save = new SaveAndLoad();
		try {
			save.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Toast.makeText(getApplicationContext(), "goto List page", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this,ListLog.class);
		startActivity(intent);
	}

	
	public String getBestProvider(){
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		///
		criteria.setAccuracy(Criteria.ACCURACY_FINE);  
		criteria.setAltitudeRequired(false);  
		criteria.setBearingRequired(false);  
		criteria.setCostAllowed(true);  
		criteria.setPowerRequirement(Criteria.POWER_LOW); 

		String bestProvider = locationManager.getBestProvider(criteria, true);
		return bestProvider;
	}


}
