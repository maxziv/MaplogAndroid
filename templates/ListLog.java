package com.example.googlemaptest1;



import java.util.ArrayList;
import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

public class ListLog extends Activity {
int counter=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_log);
        TableLayout table =(TableLayout) findViewById(R.id.table_id); 
        int log_size=Login_UI.user.maplogSet.size();
        
        Toast.makeText(getApplicationContext(), Integer.toString(log_size) , Toast.LENGTH_SHORT).show();
        
        ArrayList <TableRow> rowList = new ArrayList<TableRow>();
        for(int i=0;i<log_size;i++){
        	
          rowList.add(new TableRow(this));
          ImageView iv = new ImageView(this);
          iv.setImageBitmap(Login_UI.user.maplogSet.get(i).getPreview());;
          TextView t = new TextView(this);
          t.setText(Login_UI.user.maplogSet.get(i).getTitle());
          CheckBox c = new CheckBox(this);
          rowList.get(i).addView(iv);
          rowList.get(i).addView(t);
          rowList.get(i).addView(c);
          addListenerOnRow(rowList.get(i));
          
          table.addView(rowList.get(i), new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_log, menu);
        return true;
    }
	public void viewpage(View view){
	//Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "115", Toast.LENGTH_SHORT).show();
	Intent intent = new Intent(this,Viewpage2.class);
		startActivity(intent);
	}
	public void gomappage(View view){
	//Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "mappage", Toast.LENGTH_SHORT).show();
	Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	public void addListenerOnRow(TableRow v) {

		v.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "1111", Toast.LENGTH_SHORT).show();
				//v1.setImageResource(R.drawable.nonfull22);
			//	Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();
			
			}

		});
	
	
	}
	
}
