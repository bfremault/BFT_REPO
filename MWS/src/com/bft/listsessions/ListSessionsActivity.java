
package com.bft.listsessions;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.bft.sessions.SessionActivity;
import com.bft.utils.DateUtils;
import com.bft.bdd.DatabaseHelper;
import com.bft.bo.Board;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spot;
import com.bft.listspots.ListSpotsActivity;
import com.bft.mws.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class ListSessionsActivity extends OrmLiteBaseActivity<DatabaseHelper> implements OnNavigationListener {

	ListView list;
	ListSessionsAdapter adapter;
	ArrayList<HashMap<String, String>> SessionsList = new ArrayList<HashMap<String, String>>();
	List<Session> list_session = new ArrayList<Session>();
	List<String> year = new ArrayList<String>();
	Integer Year = 0;
	Integer nextYearSession = 0;
    ActionBar actionBar;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_session);
	
		   	list = (ListView)findViewById(R.id.listView);

		    yearToSessionList(Year);
						
			adapter = new ListSessionsAdapter(this.getBaseContext(), SessionsList,
	        		R.layout.session_detail, new String[] { "date", "spot","note", "planche","voile","orientation","orientation","vent"}, new int[] {
	        		R.id.date, R.id.spot,R.id.ratingBar1,R.id.board,R.id.sail,R.id.direction,R.id.orientation,R.id.vent}
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


	private void yearToSessionList(Integer yearSession) {
		
		SessionsList.clear();
	
		DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

		nextYearSession = yearSession+1;
		
		RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();

		QueryBuilder<Session, Integer> queryBuilder =sessionDao.queryBuilder();	
		if(yearSession>0){
			try {
				queryBuilder.where().between("date", new DateUtils().dateToTimestamp("01/01/"+yearSession.toString()), new DateUtils().dateToTimestamp("01/01/"+(nextYearSession.toString())));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
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
	}
	
	
	@Override
	public void onRestart() { 
	    super.onRestart();
        Intent i = new Intent(getBaseContext(), ListSessionsActivity.class); 
        startActivity(i);
        finish();
	}
	
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
				RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();
			    Long numberSessions = sessionDao.countOf();
			    
			    GenericRawResults<String[]> rawResults = sessionDao.queryRaw("SELECT strftime('%Y',date, 'unixepoch'),count(*) FROM session group by strftime('%Y',date, 'unixepoch')");
			    			    
			    for (String[] resultArray : rawResults) {
			    	year.add(resultArray[0]);
			    	}
			    Collections.sort(year,Collections.reverseOrder());
			    
				MenuInflater inflater = getMenuInflater();
				inflater.inflate(R.menu.mainmenu, menu);
				actionBar = getActionBar();
				actionBar.setTitle(numberSessions + " Sessions");
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			
		        ArrayAdapter<String> aAdpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, year);
		        actionBar.setListNavigationCallbacks(aAdpt, this);
		
		        populateMenu(menu);
				return(super.onCreateOptionsMenu(menu));
			}
		 
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
				return(applyMenuChoice(item) ||
				super.onOptionsItemSelected(item));
			}
		
		private void populateMenu(Menu menu) {
				String spots = getResources().getString(R.string.spots);
				//menu.add(Menu.NONE, 1, Menu.NONE, spots);
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


		@Override
		public boolean onNavigationItemSelected(int itemPosition, long itemId) {

			Year = Integer.parseInt(year.get(itemPosition));
						
		    yearToSessionList(Year);
			
			adapter.notifyDataSetChanged();

			return false;
		} 
}
