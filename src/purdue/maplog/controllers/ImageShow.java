package purdue.maplog.controllers;

import com.example.googlemaptest1.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageShow extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        
        Bitmap pic = Login_UI.user.maplogSet.get(Login_UI.currentViewNumber).getPicture();
        ImageView im = (ImageView) findViewById(R.id.imageView01);
        im.setImageBitmap(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_image_show, menu);
        return true;
    }
    
}
