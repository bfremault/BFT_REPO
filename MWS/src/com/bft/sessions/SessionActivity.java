package com.bft.sessions;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.bft.bdd.DbManager;
import com.bft.bo.Session;
import com.bft.mws.R;

import android.app.Activity;
import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SessionActivity extends Activity {

	//DatePicker datepicker;
	Button date;
	TextView viewdate;
	AutoCompleteTextView listspot;
	EditText ventmin;
	EditText ventmax;
	Spinner listorientation;
	Spinner listplanche;
	Spinner listvoile;
	Spinner listmat;
	Button save;

	final DbManager sessionbdd = new DbManager(this);
	final Map<Integer,Integer> hmBoard = new HashMap <Integer,Integer>(); // Correspondance id board - position Spinner
	final Map<Integer,Integer> hmSail = new HashMap <Integer,Integer>(); // Correspondance id sail - position Spinner

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);        
		setContentView(R.layout.session);

		//datepicker = (DatePicker)findViewById(R.id.datePicker1);
		date = (Button)findViewById(R.id.button2);
		viewdate = (TextView)findViewById(R.id.textView9);
		listspot = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		ventmin = (EditText)findViewById(R.id.editText1);
		ventmax = (EditText)findViewById(R.id.editText2);
		listorientation = (Spinner)findViewById(R.id.spinner1);
		listplanche = (Spinner)findViewById(R.id.spinner2);
		listvoile = (Spinner)findViewById(R.id.spinner3);
		listmat = (Spinner)findViewById(R.id.spinner4);
		save = (Button)findViewById(R.id.button1);

		ArrayList<String> spotsArray = new ArrayList<String>();
		ArrayList<String> boardArray = new ArrayList<String>();
		ArrayList<String> sailArray = new ArrayList<String>();

		sessionbdd.open();    		
		Cursor cursor = sessionbdd.GetAllSpots();
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			spotsArray.add(cursor.getString(8));
			cursor.moveToNext();
		}


		ArrayAdapter<String> adapterspot = new ArrayAdapter<String>(this, R.layout.list_item, spotsArray);
		listspot.setAdapter(adapterspot);
		listspot.setFocusable(true);
		listspot.requestFocus();


		Cursor cursorboard = sessionbdd.GetAllBoard();

		cursorboard.moveToFirst();
		Integer ib= 0;
		while(!cursorboard.isAfterLast()) {
			boardArray.add(cursorboard.getString(1)); //Affichage du spinnner
			hmBoard.put(ib, Integer.parseInt(cursorboard.getString(0)));
			cursorboard.moveToNext();
			ib++;
		}

		ArrayAdapter <String> adapterboard =
				new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item , boardArray );

		adapterboard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listplanche.setAdapter(adapterboard);

		Cursor cursorsail = sessionbdd.GetAllSails();

		cursorsail.moveToFirst();
		Integer is= 0;
		while(!cursorsail.isAfterLast()) {
			sailArray.add(cursorsail.getString(1)); //add the item
			hmBoard.put(is, Integer.parseInt(cursorsail.getString(0)));
			cursorsail.moveToNext();
			is++;
		}

		ArrayAdapter <String> adaptersail =
				new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item , sailArray );

		adaptersail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listplanche.setAdapter(adaptersail);

		save.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				Session session = new Session();
				//session.setDate(datepicker);
				//session.setId_spot(listspot.getText().toString()); 
				String ventMin = ventmin.getEditableText().toString();
				if (!ventMin.isEmpty()){
					session.setVentMin(Integer.parseInt(ventMin));
				}
				String ventMax = ventmax.getEditableText().toString();
				if (!ventMax.isEmpty()){
					session.setVentMax(Integer.parseInt(ventMax));
				}
				//session.setId_orientation(id_orientation);
				Integer[] idPlanche = {hmBoard.get(listplanche.getSelectedItemPosition())};
				session.setId_planche(idPlanche); // Renvoi le nom de l'objet selectionné et pas l'ID
				Integer[] idVoile = {hmSail.get(listvoile.getSelectedItemPosition())};
				session.setId_voile(idVoile);
				//session.setId_mat(id_mat)
				//Ecrire classe pour générer un JSON. Puis faire un POST http
				ObjectMapper mapper = new ObjectMapper();
				try {
					mapper.writeValue(new File("/mnt/sdcard/MWS/session.json"), session);
					//makeRequest("http://192.168.0.1:3000/post/77/comments";);
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment((TextView) viewdate);
		newFragment.show(getFragmentManager(), "datePicker");
	}
	
	public static HttpResponse makeRequest(String uri, String json) {
		try {
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setEntity(new StringEntity(json));
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			return new DefaultHttpClient().execute(httpPost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
