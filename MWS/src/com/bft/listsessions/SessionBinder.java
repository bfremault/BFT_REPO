package com.bft.listsessions;

import com.bft.mws.R;
import com.bft.utils.DownloadImageTask;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter.ViewBinder;

public class SessionBinder implements ViewBinder {

	@Override
    public boolean setViewValue(View view, Object data, String textRepresentation) {
        if(view.getId() == R.id.ratingBar1){
            String note = (String) data;
            float ratingValue = Float.parseFloat(note);
            RatingBar ratingBar = (RatingBar) view;
            ratingBar.setRating(ratingValue);
            return true;
        }
        if(view.getId() == R.id.imageView1){
            ImageView imageView = (ImageView) view;
            new DownloadImageTask(imageView).execute(textRepresentation);
            return true;
        }
        return false;
    }
	
}