
package com.bft.listsessions;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bft.sessions.SessionActivity;
import com.bft.bdd.DatabaseHelper;
import com.bft.bo.Board;
import com.bft.bo.Orientations;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spot;
import com.bft.listspots.ListSpotsActivity;
import com.bft.mws.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import android.app.ActionBar;
import android.content.Context;
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
	List<Session> list_session = new ArrayList<Session>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_session);
		   	list = (ListView)findViewById(R.id.listView);

		    DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

		    RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();

		    QueryBuilder<Session, Integer> queryBuilder =sessionDao.queryBuilder();
		    
		    queryBuilder.orderBy("date", false);
		    
		    PreparedQuery<Session> preparedQuery = null;
		    
			try {
				preparedQuery = queryBuilder.prepare();
			    list_session = sessionDao.query(preparedQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    		   
            RuntimeExceptionDao<Spot, Integer> spotDao = getHelper().getSpotRuntimeExceptionDao();
            RuntimeExceptionDao<Board, Integer> plancheDao = getHelper().getBoardRuntimeExceptionDao();
            RuntimeExceptionDao<Sail, Integer> voileDao = getHelper().getSailRuntimeExceptionDao();
        //    RuntimeExceptionDao<Orientations, Integer> orientationDao = getHelper().getOrientationsRuntimeExceptionDao();
            
            
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
					map.put("note", "0");
				}
				
				Integer[] planche =list_session.get(i).getId_planche();
				if (planche[0] != null){
					map.put("planche", plancheDao.queryForId(planche[0]).getImage());					
				}
				else {
					map.put("planche", "");
				}
				
				Integer[] voile =list_session.get(i).getId_voile();
				if (voile[0] != null){
					map.put("voile", voileDao.queryForId(voile[0]).getImage());					
				}
				else {
					map.put("voile", "");
				}
				
				Integer id_orientation = list_session.get(i).getId_orientation();
				if (id_orientation != null){
					map.put("orientation", id_orientation.toString());					
		//			map.put("orientation", orientationDao.queryForId(id_orientation).getLibelle_court());
				}
				else{
					map.put("orientation", "0");
		//			map.put("orientation","");
				}
				
				Integer ventMin = list_session.get(i).getVentMin();
				Integer ventMax = list_session.get(i).getVentMax();

				if (ventMin != null && ventMax != null){
					map.put("vent", ventMin.toString()+" - "+ventMax.toString() + " " + getApplicationContext().getResources().getString(R.string.knots));
				}
				else{
					map.put("vent", "");

				}
								
				map.put("id_session", list_session.get(i).getId_session().toString());
				SessionsList.add(map);
			}
						
			ListSessionsAdapter adapter = new ListSessionsAdapter(this.getBaseContext(), SessionsList,
	        		R.layout.session_detail, new String[] { "date", "spot","note", "planche","voile","orientation","orientation","vent"}, new int[] {
	        		R.id.date, R.id.spot,R.id.ratingBar1,R.id.imageView1,R.id.imageView3,R.id.imageView2,R.id.orientation,R.id.vent}
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
