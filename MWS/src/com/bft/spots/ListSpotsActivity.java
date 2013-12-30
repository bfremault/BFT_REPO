package com.bft.spots;

import java.util.ArrayList;
import java.util.HashMap;

import com.bft.bdd.DbManager;
import com.bft.bo.Spot;
import com.bft.mws.R;
import com.bft.mws.R.layout;
import com.bft.mws.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.ListView;

public class ListSpotsActivity extends Activity {

	ListView list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_spots);
        list = (ListView)findViewById(R.id.listView);
		
	    DbManager sessionbdd = new DbManager(this);
	    sessionbdd.open();
		
        ArrayList<HashMap<String, String>> SpotsList = new ArrayList<HashMap<String, String>>();
	    
	    Cursor c = sessionbdd.GetAllSpots();
		c.moveToFirst();

		
		for (int i = 0; i < c.getCount(); i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("spot", c.getString(8));
			map.put("ville", c.getString(9));
			SpotsList.add(map);
			c.moveToNext();
		}
		c.close();
		sessionbdd.close();
		
        ListSpotsAdapter adapter = new ListSpotsAdapter(this.getBaseContext(), SpotsList,
        		R.layout.spot_detail, new String[] { "spot", "ville" }, new int[] {
        		R.id.spot, R.id.ville}
        		);
        
	    list.setAdapter(adapter);
		 
	    list.setClickable(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spots, menu);
		return true;
	}

}
