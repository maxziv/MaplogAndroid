package com.example.googlemaptest1;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;


public class HelloItemizedOverlay extends ItemizedOverlay  {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	
	public HelloItemizedOverlay(Drawable defaultMarker) {
		// TODO Auto-generated constructor stub
		super(boundCenterBottom(defaultMarker));
	}
	
	public HelloItemizedOverlay(Drawable defaultMarker, Context context){
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}
	
	public void addOverlay(OverlayItem overlay){
		mOverlays.add(overlay);
		populate();
	}
	
	protected boolean onTap(int index){
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		
		int i=0;
		//= Integer.getInteger(item.getSnippet());
		Login_UI.currentViewNumber = i;
		dialog.setTitle(item.getTitle());
		dialog.setMessage(Login_UI.user.maplogSet.get(i).getContent());
		dialog.setIcon(new BitmapDrawable(mContext.getResources(),Login_UI.user.maplogSet.get(i).getPreview()));
		//dialog.setIcon(R.drawable.image_01);
		dialog.setNegativeButton("Back",new DialogInterface.OnClickListener(){
		       public void onClick(DialogInterface dia, int which)
		        {
					dia.dismiss();
		        }

		}
		);
		dialog.setPositiveButton("Edit",new DialogInterface.OnClickListener(){
		       public void onClick(DialogInterface dia, int which)
		        {
		   		Intent intent = new Intent(mContext, Viewpage1.class);
				mContext.startActivity(intent);
					
					dia.dismiss();
		        }

		}
		);
		
		
		
		
		dialog.show();
		return true;
	}
	


}
