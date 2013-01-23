package com.android.bft.equipment;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.bft.R;
import com.android.bft.data.Equipment;
import com.android.bft.data.SessionBDD;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class EquipmentActivity extends ListActivity {
	
	final SessionBDD sessionbdd = new SessionBDD(this);

	ListView list;
	Button add;
	HashMap<Integer, Integer> hmEquipment = new HashMap<Integer, Integer>();

	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        setContentView(R.layout.equipment_layout);
        
        Bundle b = getIntent().getExtras();

        final int equipmentType = b.getInt("EQUIPMENT");
              
        list = (ListView)findViewById(R.id.listView);
            
		sessionbdd.open();
        
		Cursor c = sessionbdd.getAllActiveEquipment(equipmentType);
	
        final ArrayList<HashMap<String, String>> EquipmentItem = new ArrayList<HashMap<String, String>>();
	    
  		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++){
			hmEquipment.put(i, c.getInt(0));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("equipment_label", c.getString(1));
			map.put("equipment_volume", c.getString(2));
			EquipmentItem.add(map);
            c.moveToNext();
        }
        c.close();
      
        EquipmentAdapter mAdapter = new EquipmentAdapter(this.getBaseContext(), EquipmentItem,
    				R.layout.equipment_item, new String[] { "equipment_label","equipment_volume"}, new int[] {
    						R.id.equipment_item,R.id.equipment_volume});  

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
			public void onClick(View v) 
    		{
	    	
				LayoutInflater factory = LayoutInflater.from(EquipmentActivity.this);
			    final View alertDialogView = factory.inflate(R.layout.equipment_dialog, null);
			 
				
				AlertDialog.Builder alert = new AlertDialog.Builder(EquipmentActivity.this);
				
		        alert.setView(alertDialogView);
				final EditText equipment_label = (EditText) alertDialogView.findViewById(R.id.equipment_label);
	            	   	
				final EditText equipment_volume = (EditText) alertDialogView.findViewById(R.id.equipment_volume);
		        
				switch (equipmentType){
		        case 0:
		        	alert.setTitle(R.string.add_board);
		        	equipment_volume.setHint(R.string.equipment_volume);
		        	equipment_label.setHint(R.string.board);
		        break;
		        case 1:
		        	alert.setTitle(R.string.add_sail);
		        	equipment_volume.setHint(R.string.equipment_size);
		        	equipment_label.setHint(R.string.sail);
		        };
			
				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					  
		          	   	String equipment_label_str = equipment_label.getEditableText().toString();

	            	   	float equipment_volume_int = Float.parseFloat(equipment_volume.getText().toString());
	            	   	
	            	   	Equipment equipment = new Equipment(0,equipment_label_str,equipment_volume_int,equipmentType,false);
	               		
	            	   	if (equipment_label_str != null){
	            	   		sessionbdd.open();
	            	   	
	            	   		sessionbdd.insertEquipment(equipment);
	               			               		
	            	   		Intent intent = new Intent(EquipmentActivity.this,EquipmentTabLayoutActivity.class);
		    	        
	            	   		intent.putExtra("EQUIPMENT" , equipmentType);

	            	   		startActivity(intent);		     
	            	   	}
						
					  }
					});

					alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					  public void onClick(DialogInterface dialog, int whichButton) {
					    // Canceled.
					  }
					});

					alert.show();
				
			}
        });
		
	}
		public void BtHandler(View v) {
			//on récupère la position à l'aide du tag défini dans la classe MyListAdapter
			int position = Integer.parseInt(v.getTag().toString());
			int j = hmEquipment.get(position);
			sessionbdd.removeEquipmentWithId(j);       		
						
    		Intent intent = new Intent(EquipmentActivity.this,EquipmentTabLayoutActivity.class);
       		    		
	   		intent.putExtra("EQUIPMENT" , this.getIntent().getExtras().getInt("EQUIPMENT"));
    		
			startActivity(intent);	
			}
	
}
