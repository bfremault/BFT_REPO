package com.android.bft.media;

import java.util.ArrayList;

import com.android.bft.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class SessionGalleryActivity extends Activity {
	
		ArrayList<String> alPict = new ArrayList<String>();
	    
	    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        Bundle b = getIntent().getExtras();
        if (b != null){
        	alPict = b.getStringArrayList("IMAGESURI");
        }
        
        setContentView(R.layout.sessiongallery);
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
		
		gallery.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView parent, View v, int position, long id) {
	            Toast.makeText(SessionGalleryActivity.this, "Test", Toast.LENGTH_SHORT).show();
	        }
	    });
	    
        
       
    
         /*
        //String mBaseFolderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/";
        //String filePath = mBaseFolderPath  + "test.jpg";
        String filePath = alPict.get(1);
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        
        setContentView(R.layout.sessionimageview);
        ImageView imageview = (ImageView) findViewById(R.id.imageView1);
        imageview.setImageBitmap(bitmap);
        
		

		}
	*/
}

	
private class ImageAdapter extends BaseAdapter {

int mGalleryItemBackground;
private Context mContext;

public ImageAdapter(Context c) {
       mContext = c;
       TypedArray attr = mContext.obtainStyledAttributes(R.styleable.SessionGallery);
       mGalleryItemBackground = attr.getResourceId(
                R.styleable.SessionGallery_android_galleryItemBackground, 0);
        attr.recycle();
    }

@Override
public int getCount() {
	// TODO Auto-generated method stub
   return alPict.size();   
}

@Override
public Object getItem(int position) {
	// TODO Auto-generated method stub
	return position;
}

@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return position;
}

@Override
public int getItemViewType(int position) {
	// TODO Auto-generated method stub
	return position;
}

@Override
public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView = new ImageView(mContext);
    try{
    	//Uri uri = Uri.parse("file:///"+alPict.get(position));
     	//int resId = R.drawable.icon;
    	//imageView.setImageResource(resId);
    	Uri uri = Uri.parse(alPict.get(position));
    	imageView.setImageURI(uri);
    	imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
    	imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    	imageView.setBackgroundResource(mGalleryItemBackground);
		}
    catch (ParseException e) 
    {
		Toast.makeText(SessionGalleryActivity.this, "Erreur Parsing" , Toast.LENGTH_LONG).show();	            
    }
    return imageView;
	}

@Override
public int getViewTypeCount() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public boolean hasStableIds() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void registerDataSetObserver(DataSetObserver observer) {
	// TODO Auto-generated method stub

}

@Override
public void unregisterDataSetObserver(DataSetObserver observer) {
	// TODO Auto-generated method stub

}

@Override
public View getDropDownView(int arg0, View arg1, ViewGroup arg2) {
	// TODO Auto-generated method stub
	return null;
}

}
}
