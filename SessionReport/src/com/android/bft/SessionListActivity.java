package com.android.bft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.android.bft.R;
import com.android.bft.data.SessionBDD;
import com.android.bft.equipment.EquipmentActivity;
import com.android.bft.equipment.EquipmentTabLayoutActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class SessionListActivity extends Activity {
    
	Button btn;
    Button dlt;
	ArrayList<String> listSession = new ArrayList<String>();
	HashMap<Integer, Integer> hmSession = new HashMap<Integer, Integer>();
	ListView list;
	SparseBooleanArray checked;
    final SessionBDD sessionbdd = new SessionBDD(this);

    private final static Logger LOGGER = Logger.getLogger(SessionListActivity.class .getName());
    
	@Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
/*        FileHandler fh=null;
        String name ;
        
        if ( 0 == Environment.getExternalStorageState().compareTo(Environment.MEDIA_MOUNTED))
            name = Environment.getExternalStorageDirectory().getAbsolutePath();
        else
            name = Environment.getDataDirectory().getAbsolutePath();

        name += "/mylogfile.log";
        
        LOGGER.setLevel(Level.ALL);
              
        try {
            fh = new FileHandler(name, 256*1024, 1, true);
            fh.setFormatter(new SimpleFormatter());
            fh.publish(new LogRecord(Level.ALL, "Test"));
        } catch (Exception e)
        
        {
            Log.e("MyLog", "FileHandler exception \n", e);
            return;
        }finally{
            if (fh != null)
                fh.close();
        }*/
        
        setContentView(R.layout.main);
		btn=(Button)findViewById(R.id.button1);
        dlt=(Button)findViewById(R.id.button3);
        dlt.setEnabled(false);
        list = (ListView)findViewById(R.id.listView);
        
        list.setChoiceMode(2);
        
		sessionbdd.open();
			
		Cursor c = sessionbdd.GetAllSessionName();
		
		final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	    
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++){
			hmSession.put(i, c.getInt(0));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("session", c.getString(1));
			map.put("date", c.getString(3));
			listItem.add(map);
	        listSession.add(c.getString(1));
            c.moveToNext();
        }
        c.close();
        
		sessionbdd.close();
        
        SessionListAdapter adaptater = new SessionListAdapter(this.getBaseContext(), listItem,
				R.layout.list_detail, new String[] { "session", "date" }, new int[] {
						R.id.session, R.id.date});        
        
	    list.setAdapter(adaptater);
		 
	    list.setClickable(true);
	    	    
	    btn.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v) 
	    		{
	        		Intent intent = new Intent(SessionListActivity.this,SessionActivity.class);
	    	
	    			startActivity(intent);	
	    		}
	        										
	        }
	     );
	    
	    
	    dlt.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v) 
	    		{
	        		
	        		SparseBooleanArray checked = list.getCheckedItemPositions();
	        		
	        		sessionbdd.open();

	        		
	        		for (int i = 0 ; i<checked.size() ;i++){
	        			int j = checked.keyAt(i);
	        			int k =  hmSession.get(j);
	        			listSession.remove(j-i);
	        			sessionbdd.removeSessionWithID(k);
	        		};
	        		
	        		sessionbdd.close();
        		
	                dlt.setEnabled(false);

	        		Intent intent = new Intent(SessionListActivity.this,SessionListActivity.class);
	    	    	
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
		String pref = getResources().getString(R.string.equipment);
		//String dlt = getResources().getString(R.string.button3);
		menu.add(Menu.NONE, 1, Menu.NONE, pref);
		//menu.add(Menu.NONE, 2, Menu.NONE, dlt);
		}
	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
//			startActivity(new Intent(this, EditPreferencesActivity.class));
			startActivity(new Intent(this, EquipmentTabLayoutActivity.class));
			return(true);
//		case 2:
//			return(true);
		}
		return(false);
		} 
		
	public void MyHandler(View v) {
				//on récupère la position à l'aide du tag défini dans la classe MyListAdapter
		int position = Integer.parseInt(v.getTag().toString());
        dlt.setEnabled(true);
		SparseBooleanArray checked = list.getCheckedItemPositions();
		checked.put(position,true);
		
	}
	public void RowHandler(View v) {
					
		int position = Integer.parseInt(v.getTag().toString());
		
		Intent intent = new Intent(SessionListActivity.this,SessionActivity.class);
  	  
		intent.putExtra("IDSESSION" , hmSession.get(position));
		
		startActivity(intent);	
		}
	}