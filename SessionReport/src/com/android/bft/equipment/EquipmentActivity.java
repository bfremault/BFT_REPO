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
        setContentView(R.layout.equipment_layout);
        
        Bundle b = getIntent().getExtras();

        final int equipmentType = b.getInt("EQUIPMENT");
              
        list = (ListView)findViewById(R.id.listView);
            
		sessionbdd.open();
        
		Cursor c = sessionbdd.getAllEquipment(equipmentType);
	
        final ArrayList<HashMap<String, String>> EquipmentItem = new ArrayList<HashMap<String, String>>();
	    
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
      
        EquipmentAdapter mAdapter = new EquipmentAdapter(this.getBaseContext(), EquipmentItem,
    				R.layout.equipment_item, new String[] { "equipment"}, new int[] {
    						R.id.equipment_item});  
//        
        // set this adapter as your ListActivity's adapter
        this.setListAdapter(mAdapter);
		
        mAdapter.notifyDataSetChanged();
                   
        add = (Button)findViewById(R.id.button1);
     	
        switch (equipmentType){
        case 0:
        add.setText(R.string.add_board);
        break;
        case 1:
        add.setText(R.string.add_sail);
     
        };
		add.setOnClickListener(new View.OnClickListener() 
        {
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			public void onClick(View v) 
    		{
				DialogFragment newFragment = new EquipmentDialog();
			    newFragment.show(getFragmentManager() , String.valueOf(equipmentType));
			}
        });
		
	}
		public void BtHandler(View v) {
			//on récupère la position à l'aide du tag défini dans la classe MyListAdapter
			int position = Integer.parseInt(v.getTag().toString());
			int j = hmEquipment.get(position);
			sessionbdd.removeEquipmentWithId(j);
       		       		
    		Intent intent = new Intent(EquipmentActivity.this,EquipmentActivity.class);
       		
			startActivity(intent);	
			}
	
}
