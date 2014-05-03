package com.example.googlemaptest1;

import java.util.List;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.maps.*;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
/////
import android.content.Context;
import android.graphics.drawable.Drawable;
//////

public class MainActivity extends MapActivity implements LocationListener {


	///
	public  MapController mapController;
	public MapView mapView;
	public LocationManager locationManager;
	public Location currentLocation;
	public GeoPoint currentPoint;
	////
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mapView = (MapView)findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		mapView.setStreetView(true);
		mapController = mapView.getController();
		mapController.setZoom(4);

		getLastLocation();
		animateToCurrentLocation();
		// mapController.setCenter(currentPoint);

		int log_size=Login_UI.user.maplogSet.size();
		for(int i=0;i<log_size;i++){
			GeoPoint point = getGeoPointWithLocation(Login_UI.user.maplogSet.get(i).getLoc());
			OverlayItem overlayitem =new OverlayItem(point, Login_UI.user.maplogSet.get(i).getTitle(), Integer.toString(i));

			Drawable drawable = this.getResources().getDrawable(R.drawable.pin);
			List<Overlay> mapOverlays = mapView.getOverlays();
			MyLocationOverlay myLoc = new MyLocationOverlay(this, mapView);
			HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this); 
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay); 
		}
	}

	public void getLastLocation(){
		String provider = getBestProvider();
		currentLocation = locationManager.getLastKnownLocation(provider);
		if(currentLocation != null){
			setCurrentLocation(currentLocation);
		}
		else
			Toast.makeText(this, "Location not yet acquired", Toast.LENGTH_LONG).show();
	}

	public void animateToCurrentLocation(){
		if(currentPoint != null)
			mapController.animateTo(currentPoint);
	}

	public void setCurrentLocation(Location location){
		int currLatitude = (int)(location.getLatitude()*1e6);
		int currLongitude = (int)(location.getLongitude()*1e6);
		currentPoint = new GeoPoint(currLatitude, currLongitude);

		currentLocation = new Location("");
		currentLocation.setLatitude(currentPoint.getLatitudeE6() / 1e6);
		currentLocation.setLongitude(currentPoint.getLongitudeE6() / 1e6);
	}

	public GeoPoint getGeoPointWithLocation(Location location){
		int currLatitude = (int)(location.getLatitude()*1e6);
		int currLongitude = (int)(location.getLongitude()*1e6);
		return new GeoPoint(currLatitude, currLongitude);
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
		Toast.makeText(getApplicationContext(), "goto edit page", Toast.LENGTH_SHORT)
		.show();
		Intent intent = new Intent(this, Viewpage1.class);
		startActivity(intent);
	}

	public void gologinpage(View view) {
		// Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "goto login page", Toast.LENGTH_SHORT)
		.show();
		Intent intent = new Intent(this, Login_UI.class);
		startActivity(intent);
	}

	public void onLocationChanged(Location newLocation) {
		// TODO Auto-generated method stub
		setCurrentLocation(newLocation);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
	@Override
	protected void onResume(){
		super.onResume();
		locationManager.requestLocationUpdates(getBestProvider(), 1000, 1, this);
	}

	@Override
	protected void onPause(){
		super.onPause();
		locationManager.removeUpdates(this);
	}


}
