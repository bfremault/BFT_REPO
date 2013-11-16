package com.bft.mws;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import com.bft.utils.*;

public class MainActivity extends Activity {

	private static String url = "http://api.geonames.org/postalCodeLookupJSON?postalcode=6600&country=AT&username=demo";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// faire IHM de login
		//https://www.learn2crack.com/2013/10/android-asynctask-json-parsing-example.html
		// org.json.JSONException: Unterminated string at character 84241 of
		//http://json.parser.online.fr/
		//parser avec Jackson : http://www.tutos-android.com/parsing-json-jackson-android
		// buffer + important
		// mnt/scdcard/tuto...
				
		new JSONParse().execute();
		
	}

	 private class JSONParse extends AsyncTask<String, String, JSONObject> {
         private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // uid = (TextView)findViewById(R.id.uid);
            // name1 = (TextView)findViewById(R.id.name);
            // email1 = (TextView)findViewById(R.id.email);
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
 
        }
 
        @Override
        protected JSONObject doInBackground(String... args) {
             // Getting JSON from URL
        	JSONutils jSon = new JSONutils();
    		jSon.init();
    		
        	JSONParser jParser = new JSONParser();
           	JSONObject json = jParser.getJSONFromUrl(url);
           	return json;
            
            
        }
         @Override
         protected void onPostExecute(JSONObject json) {/*
             pDialog.dismiss();
             try {
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
