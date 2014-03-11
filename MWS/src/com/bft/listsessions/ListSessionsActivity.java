
package com.bft.listsessions;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bft.sessions.SessionActivity;
import com.bft.bdd.DatabaseHelper;
import com.bft.bo.Board;
import com.bft.bo.Session;
import com.bft.bo.Spot;
import com.bft.mws.R;
import com.bft.spots.ListSpotsActivity;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ListSessionsActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	ListView list;
	Button button;
	ArrayList<HashMap<String, String>> SessionsList = new ArrayList<HashMap<String, String>>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_session);
		   	list = (ListView)findViewById(R.id.listView);

		    DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
	
            RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();

		    List<Session> list_session = sessionDao.queryForAll();		

            RuntimeExceptionDao<Spot, Integer> spotDao = getHelper().getSpotRuntimeExceptionDao();
            RuntimeExceptionDao<Board, Integer> plancheDao = getHelper().getBoardRuntimeExceptionDao();

            for (Integer i = 0; i < list_session.size(); i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("date", dateFormat.format(list_session.get(i).getDate() * 1000L));
				Spot spot = spotDao.queryForId(list_session.get(i).getId_spot());
				map.put("spot",spot.getSpot());
				Integer note =list_session.get(i).getNote();
				if (note != null){
					map.put("note", note.toString());					
				}
				else {
					map.put("note", "");
				}
				
				Integer[] planche =list_session.get(i).getId_planche();
				if (planche[0] != null){
					map.put("planche", plancheDao.queryForId(planche[0]).getImage());					
				}
				else {
					map.put("planche", "");
				}
					
				
			//	map.put("orientation", list_session.get(i).getId_orientation().toString());
			//	map.put("planche", list_session.get(i).getId_planche()[0].toString());
			//	map.put("voile", list_session.get(i).getId_voile()[0].toString());		
				map.put("id_session", list_session.get(i).getId_session().toString());
				SessionsList.add(map);
			}
						
			ListSessionsAdapter adapter = new ListSessionsAdapter(this.getBaseContext(), SessionsList,
	        		R.layout.session_detail, new String[] { "date", "spot","note", "planche"}, new int[] {
	        		R.id.date, R.id.spot,R.id.ratingBar1,R.id.imageView1}
	        		);
	        
			adapter.setViewBinder(new SessionBinder());
	        
		    list.setAdapter(adapter);
			 
		    list.setClickable(true);
		    
		    list.setOnItemClickListener(new OnItemClickListener() {
		        @Override
		        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {               

		        	HashMap<String, String> hm = (HashMap<String, String>) list.getItemAtPosition(position);
 			        	
		        	Intent intent = new Intent(ListSessionsActivity.this,SessionActivity.class);
		  	  	  
					intent.putExtra("IDSESSION" , hm.get("id_session"));
					
					startActivity(intent);	

		        }
		    });
		  			
	}
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
				RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();
			    Long numberSessions = sessionDao.countOf();
			
				MenuInflater inflater = getMenuInflater();
				inflater.inflate(R.menu.mainmenu, menu);
				ActionBar actionBar = getActionBar();
				actionBar.setTitle(numberSessions.toString() + " Sessions"); 
				
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
			case R.id.action_add_session:
    			startActivity(new Intent(ListSessionsActivity.this,SessionActivity.class));	
				return(true);
			}
			return(false);
			} 
}
