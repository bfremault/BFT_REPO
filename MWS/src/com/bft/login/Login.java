package com.bft.login;

import java.util.Iterator;
import java.util.List;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.bft.bdd.SessionBDD;
import com.bft.bo.Board;
import com.bft.bo.Data;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spot;
import com.bft.mws.R;
import com.bft.sessions.ListSessionsActivity;
import com.bft.utils.*;

public class Login extends Activity {

    final SessionBDD sessionbdd = new SessionBDD(this);
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	    final EditText uid = (EditText)findViewById(R.id.uid);
        final EditText password = (EditText)findViewById(R.id.password);
        Button button = (Button)findViewById(R.id.button1);
		        
        sessionbdd.open();    		
        final String srvtime = sessionbdd.getParameter("srvtime");

        button.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
    		{
				new JSONParse().execute(uid.getText().toString(),password.getText().toString(),srvtime);
    		}
        });
	}
	
	private class JSONParse extends AsyncTask<String, String, Boolean> { // C'était un JSONobject en 3ème paramètre
         private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           			pDialog = new ProgressDialog(Login.this);
	                pDialog.setMessage("Getting Data ...");
	                pDialog.setIndeterminate(false);
	                pDialog.setCancelable(true);
	                pDialog.show();	    		
	          }
 
        @Override
        protected Boolean doInBackground(String... args) {
             // Getting JSON from URL
        	JSONutils jSon = new JSONutils(args);
    		Data data = jSon.init();
    		sessionbdd.open();    		

    		List<Spot> spots = data.getListe_spots();
	    	if (spots != null) {	
    			Iterator<Spot> it = spots.iterator();
	    		while(it.hasNext()){
	    			Spot spot = (Spot)(it.next());
	    			Integer id_spot = spot.getId_spot();
	    			Cursor cursor = sessionbdd.getIdSpot(id_spot);
	    			cursor.moveToFirst();
	    			if (cursor.getCount() == 0){
		    			sessionbdd.insertSpot(spot);
	    			}
	    			else{
		    			sessionbdd.updateSpot(id_spot,spot);  	
	    			}
	    		}
	    	};
    		
    		List<Session> sessions = data.getSessions();
    		if (sessions != null) {
    			Iterator<Session> it = sessions.iterator();
	    		while(it.hasNext()){
	    			Session session = (Session)(it.next());
	    			Integer id_session = session.getId_session();
	    			Cursor cursor = sessionbdd.getIdSession(id_session);
	    			cursor.moveToFirst();
	    			if (cursor.getCount() == 0){
		    			sessionbdd.insertSession(session);
	    			}
	    			else{
		    			sessionbdd.updateSession(id_session,session);  	
	    			}
	    			
	    			sessionbdd.insertSession(session);
	    		}
    		};
    		
    		List<Board> boards = data.getPlanches();
    		if (boards != null) {
    			Iterator<Board> it = boards.iterator();
	    		while(it.hasNext()){
	    			Board board = (Board)(it.next());
	    			Integer id_board = board.getId_planche();
	    			Cursor cursor = sessionbdd.getIdBoard(id_board);
	    			cursor.moveToFirst();
	    			if (cursor.getCount() == 0){
		    			sessionbdd.insertBoard(board);
	    			}
	    			else{
		    			sessionbdd.updateBoard(id_board,board);  	
	    			}
	    		}
    		};
    		
    		List<Sail> sails = data.getVoiles();
    		if (sails != null) {
    			Iterator<Sail> it = sails.iterator();
	    		while(it.hasNext()){
	    			Sail sail = (Sail)(it.next());
	    			Integer id_sail = sail.getId_voile();
	    			Cursor cursor = sessionbdd.getIdSail(id_sail);
	    			cursor.moveToFirst();
	    			if (cursor.getCount() == 0){
		    			sessionbdd.insertSail(sail);
	    			}
	    			else{
		    			sessionbdd.updateSail(id_sail,sail);  	
	    			}
	    		}
    		};
    		
    		Integer srvtime = data.getSrvtime();
    		sessionbdd.updateParameter("srvtime", srvtime);
    		
    		Boolean done = true;
    		return done;
    				
    				
    		
        	/*JSONParser jParser = new JSONParser();
           	JSONObject json = jParser.getJSONFromUrl(url);
           	return json;*/
            
            
        }
         @Override
         protected void onPostExecute(Boolean done) {
             pDialog.dismiss();
             
          	Intent intent = new Intent(Login.this,ListSessionsActivity.class);
	    	startActivity(intent);
             
             /* try {
                    // Getting JSON Array
                    user = json.getJSONArray(TAG_USER);
                    JSONObject c = user.getJSONObject(0);
 
                    // Storing  JSON item in a Variable
                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String email = c.getString(TAG_EMAIL);
 
                    //Set JSON Data in TextView
                    uid.setText(id);
                    name1.setText(name);
                    email1.setText(email);
 
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
         */}
    }
}
