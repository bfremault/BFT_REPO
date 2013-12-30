package com.bft.sessions;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.bft.sessions.SessionActivity;
import com.bft.bdd.DbManager;
import com.bft.mws.R;
import com.bft.spots.ListSpotsActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListSessionsActivity extends Activity {

	ListView list;
	Button button;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_session);
		   	list = (ListView)findViewById(R.id.listView);
			button = (Button) findViewById(R.id.button);
		   	
		    DbManager sessionbdd = new DbManager(this);
		    sessionbdd.open();
			
	        ArrayList<HashMap<String, String>> SessionsList = new ArrayList<HashMap<String, String>>();
		    
		    Cursor c = sessionbdd.GetAllSessions_date_spot();
			c.moveToFirst();

		    DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
	        //DateFormat dateformat = DateFormat.getDateInstance();
	     //   String test = dateformat.format(Long.parseLong("1386445334") * 1000L);

		    
			for (int i = 0; i < c.getCount(); i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("date", dateFormat.format(Long.parseLong(c.getString(0)) * 1000L));
				map.put("spot", c.getString(1));
				SessionsList.add(map);
				c.moveToNext();
			}
			c.close();
			sessionbdd.close();
			
	        ListSessionsAdapter adapter = new ListSessionsAdapter(this.getBaseContext(), SessionsList,
	        		R.layout.session_detail, new String[] { "date", "spot" }, new int[] {
	        		R.id.date, R.id.spot}
	        		);
	        
		    list.setAdapter(adapter);
			 
		    list.setClickable(true);
		    
		    button.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v) 
	    		{
	        		Intent intent = new Intent(ListSessionsActivity.this,SessionActivity.class);
	    	
	    			startActivity(intent);	
	    		}
	        										
	        }
	     );
					
	}
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
				populateMenu(menu);
				return(super.onCreateOptionsMenu(menu));
			}
		 
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
				return(applyMenuChoice(item) ||
				super.onOptionsItemSelected(item));
			}
		
		private void populateMenu(Menu menu) {
				String spots = "SPOTS"; //getResources().getString(R.string.equipment);
				//String dlt = getResources().getString(R.string.button3);
				menu.add(Menu.NONE, 1, Menu.NONE, spots);
				//menu.add(Menu.NONE, 2, Menu.NONE, dlt);
			}
		
		private boolean applyMenuChoice(MenuItem item) {
			switch (item.getItemId()) {
			case 1:
//				startActivity(new Intent(this, EditPreferencesActivity.class));
				startActivity(new Intent(this, ListSpotsActivity.class));
				return(true);
//			case 2:
//				return(true);
			}
			return(false);
			} 

}
