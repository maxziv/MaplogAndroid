package com.example.googlemaptest1;

import android.os.Bundle;
import android.os.StrictMode;

import com.google.android.maps.*;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		GeoPoint point = new GeoPoint(19240000,-99120000);
		OverlayItem overlayitem =new OverlayItem(point, "Hello, world","This is Mexico City");
		
		MyLocationOverlay myLoc = new MyLocationOverlay(this, mapView);
		GeoPoint myPoint = myLoc.getMyLocation();
		if(myPoint == null) {
			Toast.makeText(getApplicationContext(), "failed to get location",
					Toast.LENGTH_SHORT).show();
		} else mapView.getController().animateTo(myPoint);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_change_password:	
			//Toast.makeText(getApplicationContext(), "Pppp", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, ChangePasswordpage.class);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void goSatelliteView(View view) {
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setSatellite(true);
	}

	public void goMapView(View view) {
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setSatellite(false);
	}
	
	public void golistview(View view) {
		// Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "Page of list",
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, ListLog.class);
		startActivity(intent);
	}

	public void goedit(View view) {
		// Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "115", Toast.LENGTH_SHORT)
				.show();
		Intent intent = new Intent(this, Viewpage1.class);
		startActivity(intent);
	}

	public void gologinpage(View view) {
		// Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "115", Toast.LENGTH_SHORT)
				.show();
		Intent intent = new Intent(this, Login_UI.class);
		startActivity(intent);
	}
}
