package com.bft.listspots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bft.bdd.DatabaseHelper;
import com.bft.bo.Spot;
import com.bft.listsessions.ListSessionsActivity;
import com.bft.mws.R;
import com.bft.sessions.SessionActivity;
import com.bft.spots.SpotActivity;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListSpotsActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	ListView list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_spots);
        list = (ListView)findViewById(R.id.listView);
		
//	    DbManager sessionbdd = new DbManager(this);
//	    sessionbdd.open();
		
        ArrayList<HashMap<String, String>> SpotsList = new ArrayList<HashMap<String, String>>();
	    
        RuntimeExceptionDao<Spot, Integer> spotDao = getHelper().getSpotRuntimeExceptionDao();

        List<Spot> list_spot = spotDao.queryForAll();
                    
		for (int i = 0; i < list_spot.size(); i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("spot", list_spot.get(i).getSpot());
			map.put("ville", list_spot.get(i).getVille());
			SpotsList.add(map);
		}
		
        ListSpotsAdapter adapter = new ListSpotsAdapter(this.getBaseContext(), SpotsList,
        		R.layout.spot_detail, new String[] { "spot", "ville" }, new int[] {
        		R.id.spot, R.id.ville}
        		);
        
	    list.setAdapter(adapter);
		 
	    list.setClickable(true);
	    
	    list.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {               
			        	
	        	Intent intent = new Intent(ListSpotsActivity.this,SpotActivity.class);
	  	  	  				
				startActivity(intent);	

	        }
	    });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spots, menu);
		return true;
	}

}
