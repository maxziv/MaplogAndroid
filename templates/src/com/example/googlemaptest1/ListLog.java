package com.example.googlemaptest1;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
Button button;
TableLayout table;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_log);
        table =(TableLayout) findViewById(R.id.table_id); 
        int log_size=Login_UI.user.maplogSet.size();
        /*toast*/
        Toast.makeText(getApplicationContext(), "list size: "+Integer.toString(log_size) , Toast.LENGTH_SHORT).show();
        
        //ArrayList <TableRow> rowList = new ArrayList<TableRow>();
        for(int i=0;i<log_size;i++){
          TableRow row = new TableRow(this);
          row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,40));
          //rowList.add(new TableRow(this));
          ImageView iv = new ImageView(this);
          iv.setImageBitmap(Login_UI.user.maplogSet.get(i).getPreview());;
          TextView t = new TextView(this);
          t.setText(Login_UI.user.maplogSet.get(i).getTitle()+"\n"+Login_UI.user.maplogSet.get(i).getDate().toString());
          t.setHeight(50);
          CheckBox c = new CheckBox(this);
          

          TextView ht = new TextView(this);
          ht.setText("hidden");
          row.addView(iv);
          row.addView(t);
          row.addView(c);
          row.addView(ht);
//          rowList.get(i).addView(iv);
//          rowList.get(i).addView(t);
//          rowList.get(i).addView(c);
//          rowList.get(i).addView(ht);
          addListenerOnRow(row, i);
          
          table.addView(row);//, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_log, menu);
        return true;
    }
	
    
	public void gomappage(View view){
	//Intent intent = new Intent(this,NextActivity.class);
		Toast.makeText(getApplicationContext(), "goto mappage", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	public void addListenerOnRow(TableRow v, final int i) {

		v.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Login_UI.currentViewNumber =  i;				
				Toast.makeText(getApplicationContext(), "Goto Viewpage2 item "+i, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(ListLog.this,Viewpage2.class);
				startActivity(intent);
			}

		});
	}
	
	public void newPage(View view){
		Login_UI.currentViewNumber = -1;
		Toast.makeText(getApplicationContext(), "new content", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ListLog.this,Viewpage1.class);
		startActivity(intent);
	}
	
	public void changeOrder(View view){
		button = (Button)findViewById(R.id.button2);
		if(button.getText().equals("Time Order")){

			button.setText("Title Order");
			
			Collections.sort(Login_UI.user.maplogSet, new maplogTimeComparable());
			Login_UI.currentViewNumber = -1;
			
			
			/*refresh*/
			//setContentView(R.layout.activity_list_log);
	        table =(TableLayout) findViewById(R.id.table_id); 
	        
	        table.removeAllViews();
	        int log_size=Login_UI.user.maplogSet.size();
	        
	        Toast.makeText(getApplicationContext(), "change to time order" , Toast.LENGTH_SHORT).show();
	        
	        ArrayList <TableRow> rowList = new ArrayList<TableRow>();
	        for(int i=0;i<log_size;i++){
	        	
	          rowList.add(new TableRow(this));
	          ImageView iv = new ImageView(this);
	          iv.setImageBitmap(Login_UI.user.maplogSet.get(i).getPreview());;
	          TextView t = new TextView(this);
	          t.setText(Login_UI.user.maplogSet.get(i).getTitle()+"\n"+Login_UI.user.maplogSet.get(i).getDate());
	          CheckBox c = new CheckBox(this);
	          

	          TextView ht = new TextView(this);
	          ht.setText("hidden");
	          rowList.get(i).addView(iv);
	          rowList.get(i).addView(t);
	          rowList.get(i).addView(c);
	          rowList.get(i).addView(ht);
	          addListenerOnRow(rowList.get(i), i);
	          
	          table.addView(rowList.get(i), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 100));
	        }
			
		}	
		else{
			button.setText("Time Order");

			Collections.sort(Login_UI.user.maplogSet, new maplogTitleComparable());
			Login_UI.currentViewNumber = -1;

			/*refresh*/
			//setContentView(R.layout.activity_list_log);
	        TableLayout table =(TableLayout) findViewById(R.id.table_id); 
	        
	        table.removeAllViews();
	        int log_size=Login_UI.user.maplogSet.size();
	        
	        Toast.makeText(getApplicationContext(), "Change to title order" , Toast.LENGTH_SHORT).show();
	        
	        ArrayList <TableRow> rowList = new ArrayList<TableRow>();
	        for(int i=0;i<log_size;i++){
	        	
	          rowList.add(new TableRow(this));
	          ImageView iv = new ImageView(this);
	          iv.setImageBitmap(Login_UI.user.maplogSet.get(i).getPreview());;
	          TextView t = new TextView(this);
	          t.setText(Login_UI.user.maplogSet.get(i).getTitle()+"\n"+Login_UI.user.maplogSet.get(i).getDate());
	          CheckBox c = new CheckBox(this);
	          

	          TextView ht = new TextView(this);
	          ht.setText("hidden");
	          rowList.get(i).addView(iv);
	          rowList.get(i).addView(t);
	          rowList.get(i).addView(c);
	          rowList.get(i).addView(ht);
	          addListenerOnRow(rowList.get(i), i);
	          
	          table.addView(rowList.get(i), new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 100));
	        }
		}
		
	}
	
}
