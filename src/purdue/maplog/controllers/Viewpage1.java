package purdue.maplog.controllers;


import java.io.FileNotFoundException;
import java.util.Date;

import com.example.googlemaptest1.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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
	
	private Bitmap pic;  
      
    private Uri imageFilePath;  
	
	
	private static final int CAMERA_PIC_REQUEST = 1337;
	
	static{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {

		TextView content;
		TextView title;

		preLoadNode();
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
			
			ImageButton im;
			im = (ImageButton) findViewById(R.id.button1);
	        im.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	        im.setImageBitmap(Login_UI.user.maplogSet.get(Login_UI.currentViewNumber).getPicture());
			}
			catch(Exception e)
			{
				//Log.d(UI_MODE_SERVICE,e.getMessage());
			}
		}
	}
	



	private void preLoadNode() {
	//	preloadNode(new Date(), "title", "content", imageFilePath);
		
	}




	private void preloadNode(Date date, String string, String string2) {
		MaplogNode currentNode = new MaplogNode(new Date()); //new node id = current size
//
//		currentNode.setTitle(title);
//		currentNode.setContent(content);
//		
		currentNode.setPic(pic);

		int currLatitude = (int)(currentLocation.getLatitude()*1e6);
		int currLongitude = (int)(currentLocation.getLongitude()*1e6);
		
		currentNode.setLatitute(currLatitude);
		currentNode.setLongitute(currLongitude);
		
		Login_UI.user.maplogSet
		.add(currentNode);
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_viewpage1, menu);
		return true;
	}
	

	public void takePhoto(View view) {
		// Intent intent = new Intent(this,NextActivity.class);
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		ContentValues values = new ContentValues(3);  
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "name");  
        values.put(MediaStore.Images.Media.DESCRIPTION, "description");  
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");  
		imageFilePath = Viewpage1.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);  
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageFilePath); 
		startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_PIC_REQUEST) {
			//if (data!=null && data.getExtras()!=null) {
				//Bitmap newPhoto = (Bitmap) data.getExtras().get("data");
				//ImageButton image = (ImageButton) findViewById(R.id.button1);
				//image.setImageBitmap(newPhoto);
				//Bundle extra = data.getExtras();  
				BitmapFactory.Options op = new BitmapFactory.Options();   
				Display display = this.getWindowManager().getDefaultDisplay();  
                int dw = display.getWidth();  
                int dh = display.getHeight(); 
                op.inJustDecodeBounds = true;  
			
				try {
					pic = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(imageFilePath), null, op);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				int wRatio = (int) Math.ceil(op.outWidth / (float) dw); 
                int hRatio = (int) Math.ceil(op.outHeight / (float) dh); 
                Log.v("Width Ratio:", wRatio + "");  
                Log.v("Height Ratio:", hRatio + "");  
				if (wRatio > 1 && hRatio > 1) {  
                    if (wRatio > hRatio) {  
                        op.inSampleSize = wRatio;  
                    } else {  
                        op.inSampleSize = hRatio;  
                    }  
                }  
                op.inJustDecodeBounds = false; 
                try {
					pic = BitmapFactory.decodeStream(this.getContentResolver()  
					        .openInputStream(imageFilePath), null, op);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
        		ImageButton im;
        		im = (ImageButton) findViewById(R.id.button1);
                im.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                im.setImageBitmap(pic);
			}
	}
	
	
	public void viewMap(View view){ //new/edit page hit "done" go back to map page
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
			if(pic != null)
			currentNode.setPic(pic);

			int currLatitude = (int)(currentLocation.getLatitude()*1e6);
			int currLongitude = (int)(currentLocation.getLongitude()*1e6);
			
			currentNode.setLatitute(currLatitude);
			currentNode.setLongitute(currLongitude);
			
			Login_UI.user.maplogSet
			.add(currentNode);
		}
		else{//edit page

			MaplogNode currentNode = Login_UI.user.maplogSet.get(Login_UI.currentViewNumber);
			currentNode.setTitle(title);
			currentNode.setContent(content);
			
			int currLatitude = (int)(currentLocation.getLatitude()*1e6);
			int currLongitude = (int)(currentLocation.getLongitude()*1e6);
			
			currentNode.setLatitute(currLatitude);
			currentNode.setLongitute(currLongitude);
		}
		SaveAndLoad save = new SaveAndLoad();
		try {
			save.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Toast.makeText(getApplicationContext(), "map page", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this,MainActivity.class);
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
