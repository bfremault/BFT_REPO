package com.android.bft.equipment;

import com.android.bft.R;
import com.android.bft.SessionActivity;
import com.android.bft.SessionListActivity;
import com.android.bft.R.id;
import com.android.bft.R.layout;
import com.android.bft.data.Equipment;
import com.android.bft.data.SessionBDD;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("NewApi")
public class EquipmentDialog extends DialogFragment {
	

	
	@SuppressLint("NewApi")
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		
		final int equipmentType = Integer.parseInt(this.getTag());
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
		final View textEntryView =inflater.inflate(R.layout.equipment_dialog, null);
	    
		final SessionBDD sessionbdd = new SessionBDD(textEntryView.getContext());
	
		builder.setView(textEntryView)
	           .setPositiveButton("Save", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	        
	            	   	EditText et = (EditText) textEntryView.findViewById(R.id.equipment);
	            	   	
	            	   	String str = et.getEditableText().toString();
	            	   	
	            	   	Equipment equipment = new Equipment(1,str,equipmentType);
	               		
	            	   	sessionbdd.open();
	            	   	
	               		sessionbdd.insertEquipment(equipment);
	               		
	               		Activity activity = getActivity();
	               		
		        		Intent intent = new Intent(activity,EquipmentTabLayoutActivity.class);
		    	        
		        		intent.putExtra("EQUIPMENT" , equipmentType);

		    			startActivity(intent);		               		
	              }
	           })
	           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	               }
	           });      
	    return builder.create();
		
	}
}
	
