package com.android.bft.equipment;

import com.android.bft.R;
import com.android.bft.listsession.SessionListActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class EquipmentTabLayoutActivity extends TabActivity {
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.equipment_tab);
	 	    TabHost tabHost = getTabHost();
	     
	        TabSpec board = tabHost.newTabSpec("Board");
	        board.setIndicator("Board");
	        Intent boardIntent = new Intent(this, EquipmentActivity.class);
	        boardIntent.putExtra("EQUIPMENT" , 0);
	        board.setContent(boardIntent);
	 
	        TabSpec sail = tabHost.newTabSpec("Sail/Kite");
	        sail.setIndicator("Sail/Kite");
	        Intent sailIntent = new Intent(this, EquipmentActivity.class);
	        sailIntent.putExtra("EQUIPMENT" , 1);
	        sail.setContent(sailIntent);
	 
	        // Adding all TabSpec to TabHost
	        tabHost.addTab(board);
	        tabHost.addTab(sail);
	        
	        final int height = 45;
	        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = height;
	        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = height;
	        
	        Bundle b = getIntent().getExtras();
	        
	        if (b != null){
	        	tabHost.setCurrentTab(b.getInt("EQUIPMENT"));
	        }
	        
	        
	    }
	  
}
      
