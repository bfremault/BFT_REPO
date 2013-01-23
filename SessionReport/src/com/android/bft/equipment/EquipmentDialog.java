package com.android.bft.equipment;

import com.android.bft.R;
import com.android.bft.data.Equipment;
import com.android.bft.data.SessionBDD;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.DialogFragment;


@SuppressLint("NewApi")
public class EquipmentDialog extends Activity {
	
	@SuppressLint("NewApi")
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		
			final int equipmentType = 1; //Integer.parseInt(this.getTag());
		
			LayoutInflater factory = LayoutInflater.from(this);
	        final View alertDialogView = factory.inflate(R.layout.equipment_dialog, null);
	 
	        //Création de l'AlertDialog
	        AlertDialog.Builder adb = new AlertDialog.Builder(this);
	 
		
		
		//AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		//LayoutInflater inflater = getActivity().getLayoutInflater();
		
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
		//final View textEntryView =inflater.inflate(R.layout.equipment_dialog, null);
	    
		final SessionBDD sessionbdd = new SessionBDD(alertDialogView.getContext());
			
		adb.setView(alertDialogView)
	           .setPositiveButton("Save", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	        
	            	   	EditText equipment_label = (EditText) alertDialogView.findViewById(R.id.equipment_label);
	            	   	
	            	   	EditText equipment_volume = (EditText) alertDialogView.findViewById(R.id.equipment_volume);

	            /*	    switch (equipmentType){
	                    case 0:
	                    equipment_volume.setHint(R.string.equipment_volume);
	                    break;
	                    case 1:
		                equipment_volume.setHint(R.string.equipment_size);
	                    };*/
	            	   	
	            	   	String equipment_label_str = equipment_label.getEditableText().toString();

	            	   	float equipment_volume_int = Float.parseFloat(equipment_volume.getText().toString());
	            	   	
	            	   	Equipment equipment = new Equipment(0,equipment_label_str,equipment_volume_int,equipmentType,false);
	               		
	            	   	if (equipment_label_str != null){
	            	   		sessionbdd.open();
	            	   	
	            	   		sessionbdd.insertEquipment(equipment);
	               		
	            	   		//Activity activity = getActivity();
	               		
	            	   		Intent intent = new Intent(EquipmentDialog.this,EquipmentTabLayoutActivity.class);
		    	        
	            	   		intent.putExtra("EQUIPMENT" , equipmentType);

	            	   		startActivity(intent);		     
	            	   	}
	            	   
	              }
	           })
	           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	               }
	           });      
	    return adb.create();
		
	}
}
	
