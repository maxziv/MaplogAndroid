package purdue.maplog.controllers;
import java.text.SimpleDateFormat;
import java.util.Collections;

import com.example.googlemaptest1.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class ListLog extends Activity {
	int counter=1;
	Button button;
	TableLayout table;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_log);
		showList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.activity_list_log, menu);
		return true;

	}


	public void gomappage(View view){
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

	public void addListenerOnBox(CheckBox x, final int i) {



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

			Toast.makeText(getApplicationContext(), "Change to Time order" , Toast.LENGTH_SHORT).show();
			Login_UI.currentViewNumber = -1;
			showList();                        
		}        
		else{
			button.setText("Time Order");
			Collections.sort(Login_UI.user.maplogSet, new maplogTitleComparable());
			Login_UI.currentViewNumber = -1;
			Toast.makeText(getApplicationContext(), "Change to Title order" , Toast.LENGTH_SHORT).show();

			showList();
		}
	}


	private void showList() {
		int log_size=Login_UI.user.maplogSet.size();
		table =(TableLayout) findViewById(R.id.table_id);
		table.removeAllViews();
		for(int i=0;i<log_size;i++){
			TableRow row = new TableRow(this);
			row.setMinimumHeight(80);
			ImageView iv = new ImageView(this);

			iv.setAdjustViewBounds(true);
			iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);   
			Bitmap pic = Login_UI.user.maplogSet.get(i).getPicture();
			if (pic != null)
				iv.setImageBitmap(pic);
			else {
				iv.setMinimumHeight(100);
				iv.setMaxHeight(100);			
				iv.setImageResource(R.drawable.image_02);
			}
			TextView t = new TextView(this);
			SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd HH:mm:ss");
			String formattedDate = formatter.format(Login_UI.user.maplogSet.get(i).getDate());
			t.setText(Login_UI.user.maplogSet.get(i).getTitle()+"\n"+formattedDate);
			t.setHeight(50);
			
			CheckBox c = new CheckBox(this);
			c.setChecked(Login_UI.user.maplogSet.get(i).isHidden());
			
			final int index = i;
			c.setOnCheckedChangeListener(new OnCheckedChangeListener()
			{
			    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			    {
			        Login_UI.user.maplogSet.get(index).setIsHidden(isChecked);
			        Client.saveToServer(Login_UI.user.getUserName(), Login_UI.user.getPassWord(), Login_UI.user);
			    }
			});
			
			TextView ht = new TextView(this);
			ht.setText("hide");

			LinearLayout layout = new LinearLayout(this);
			ht.setText("hide");
			t.setMinHeight(90);
			t.setMinimumWidth(470);
			t.setMaxWidth(470);
			layout.addView(iv);
			layout.addView(t);
			layout.addView(c);
			layout.addView(ht);

			row.addView(layout);
			addListenerOnRow(row, i);

			table.addView(row);//, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
}
