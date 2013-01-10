	package com.android.bft.equipment;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.bft.R;
import com.android.bft.R.id;
import com.android.bft.R.layout;
import com.android.bft.data.Equipment;
import com.android.bft.data.SessionBDD;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class EquipmentActivity extends ListActivity {
	
	final SessionBDD sessionbdd = new SessionBDD(this);

	ListView list ;
	Button add;
	HashMap<Integer, Integer> hmEquipment = new HashMap<Integer, Integer>();

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_layout);
                		
        list = (ListView)findViewById(R.id.listView);
            
		sessionbdd.open();
        
		Cursor c = sessionbdd.getAllEquipment();
	
        // the desired columns to be bound
        // String[] columns = new String[] { "equipment" };
        // the XML defined views which the data will be bound to
        // int[] to = new int[] { R.id.equipment_item };

        // create the adapter using the cursor pointing to the desired data as well as the layout information
        //BaseAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.equipment_item, c, columns, to);

        final ArrayList<HashMap<String, String>> EquipmentItem = new ArrayList<HashMap<String, String>>();
	    
    	//HashMap<Integer, Integer> hmEquipment = new HashMap<Integer, Integer>();

        
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++){
			hmEquipment.put(i, c.getInt(0));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("equipment", c.getString(1));
			map.put("equipment_type", c.getString(2));
			EquipmentItem.add(map);
	        //listSession.add(c.getString(1));
            c.moveToNext();
        }
        c.close();
 
//        EquipmentArrAdapter mAdapter = new EquipmentArrAdapter(this.getBaseContext(), 
//				R.layout.equipment_item, EquipmentItem);  

        
        EquipmentAdapter mAdapter = new EquipmentAdapter(this.getBaseContext(), EquipmentItem,
    				R.layout.equipment_item, new String[] { "equipment", "equipment_type" }, new int[] {
    						R.id.equipment_item, R.id.equipment_type});  
//        
        // set this adapter as your ListActivity's adapter
        this.setListAdapter(mAdapter);
		
        mAdapter.notifyDataSetChanged();
        
		add = (Button)findViewById(R.id.button1);
		add.setOnClickListener(new View.OnClickListener() 
        {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			public void onClick(View v) 
    		{
				DialogFragment newFragment = new EquipmentDialog();
			    newFragment.show(getFragmentManager() , "instanceDialog");
			}
        });
		
	}
		public void BtHandler(View v) {
			//on récupère la position à l'aide du tag défini dans la classe MyListAdapter
			int position = Integer.parseInt(v.getTag().toString());
			int j = hmEquipment.get(position);
			sessionbdd.removeEquipmentWithId(j);
			}
	
}
