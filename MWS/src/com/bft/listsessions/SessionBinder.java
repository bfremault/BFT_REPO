package com.bft.listsessions;

import com.bft.mws.R;
import com.bft.utils.DownloadImageTask;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class SessionBinder implements ViewBinder {

	protected ImageLoader imageLoader = ImageLoader.getInstance();
	
	@Override
    public boolean setViewValue(View view, Object data, String textRepresentation) {
        if(view.getId() == R.id.ratingBar1){
            String note = (String) data;
            float ratingValue = Float.parseFloat(note);
            RatingBar ratingBar = (RatingBar) view;
            ratingBar.setRating(ratingValue/2);
            return true;
        }
        if(view.getId() == R.id.imageView1){
            ImageView imageView = (ImageView) view;
            imageLoader.displayImage(textRepresentation, imageView);
            //new DownloadImageTask(imageView).execute(textRepresentation);
            return true;
        }
        if(view.getId() == R.id.imageView3){
            ImageView imageView = (ImageView) view;
            imageLoader.displayImage(textRepresentation, imageView);

  //          new DownloadImageTask(imageView).execute(textRepresentation);
            return true;
        }
        if(view.getId() == R.id.imageView2){
            ImageView imageView = (ImageView) view;
            int id_orientation = Integer.decode(textRepresentation);
            switch (id_orientation)	{
            case 1:  id_orientation = 1;
	        imageView.setImageResource(R.drawable.n);
	        break;
            case 2:  id_orientation = 2;
            imageView.setImageResource(R.drawable.nne);            
            break;            
            case 3:  id_orientation = 3;
            imageView.setImageResource(R.drawable.ne);            
            break;
            case 4:  id_orientation = 4;
            imageView.setImageResource(R.drawable.ene);            
            break;
            case 5:  id_orientation = 5;
            imageView.setImageResource(R.drawable.e);            
            break;
            case 6:  id_orientation = 6;
            imageView.setImageResource(R.drawable.ese);            
            break;
            case 7:  id_orientation = 7;
            imageView.setImageResource(R.drawable.se);            
            break;
            case 8:  id_orientation = 8;
            imageView.setImageResource(R.drawable.sse);            
            break;
            case 9:  id_orientation = 9;
            imageView.setImageResource(R.drawable.s);            
            break;
            case 10:  id_orientation = 10;
            imageView.setImageResource(R.drawable.ssw);            
            break;
            case 11:  id_orientation = 11;
            imageView.setImageResource(R.drawable.sw);            
            break;
            case 12:  id_orientation = 12;
            imageView.setImageResource(R.drawable.wsw);            
            break;
            case 13:  id_orientation = 13;
            imageView.setImageResource(R.drawable.w);            
            break;
            case 14:  id_orientation = 14;
            imageView.setImageResource(R.drawable.wnw);            
            break;
            case 15:  id_orientation = 15;
            imageView.setImageResource(R.drawable.nw);            
            break;
            case 16:  id_orientation = 16;
            imageView.setImageResource(R.drawable.nnw);            
            break;
         //   default :
            }
  
            return true;
        }
        if(view.getId() == R.id.orientation){
        	TextView textView = (TextView) view;
            int id_orientation = Integer.decode(textRepresentation);
            switch (id_orientation)	{
            case 1:  id_orientation = 1;
            textView.setText(R.string.N);        
	        break;
            case 2:  id_orientation = 2;
            textView.setText(R.string.NNE);        
            break;            
            case 3:  id_orientation = 3;
            textView.setText(R.string.NE);        
            break;
            case 4:  id_orientation = 4;
            textView.setText(R.string.ENE);        
            break;
            case 5:  id_orientation = 5;
            textView.setText(R.string.E);        
            break;
            case 6:  id_orientation = 6;
            textView.setText(R.string.ESE);        
            break;
            case 7:  id_orientation = 7;
            textView.setText(R.string.SE);        
            break;
            case 8:  id_orientation = 8;
            textView.setText(R.string.SSE);        
            break;
            case 9:  id_orientation = 9;
            textView.setText(R.string.S);        
            break;
            case 10:  id_orientation = 10;
            textView.setText(R.string.SSW);        
            break;
            case 11:  id_orientation = 11;
            textView.setText(R.string.SW);        
            break;
            case 12:  id_orientation = 12;
            textView.setText(R.string.WSW);        
            break;
            case 13:  id_orientation = 13;
            textView.setText(R.string.W);        
            break;
            case 14:  id_orientation = 14;
            textView.setText(R.string.WNW);        
            break;
            case 15:  id_orientation = 15;
            textView.setText(R.string.NW);        
            break;
            case 16:  id_orientation = 16;
            textView.setText(R.string.NNW);        
            break;
         //   default :
            }
  
            return true;
        }
        
        return false;
    }
	
}