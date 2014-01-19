package com.bft.sessions;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.bft.bdd.DbManager;
import com.bft.bo.Board;
import com.bft.bo.Mast;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.mws.R;

import android.app.Activity;
import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
	
	Long timestamp;

	final DbManager sessionbdd = new DbManager(this);
	final Map<Integer,Integer> hmBoard = new HashMap <Integer,Integer>(); // Correspondance id board - position Spinner
	final Map<Integer,Integer> hmSail = new HashMap <Integer,Integer>(); // Correspondance id sail - position Spinner
	final Map<Integer,Integer> hmMast = new HashMap <Integer,Integer>(); // Correspondance id mast - position Spinner
	final Map<Integer,Integer> hmOrient = new HashMap <Integer,Integer>(); // Correspondance id mast - position Spinner

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);        
		setContentView(R.layout.session);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
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
		ArrayList<String> mastArray = new ArrayList<String>();
		ArrayList<String> orientArray = new ArrayList<String>();

		
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
			hmSail.put(is, Integer.parseInt(cursorsail.getString(0)));
			cursorsail.moveToNext();
			is++;
		}

		ArrayAdapter <String> adaptersail =
				new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item , sailArray );

		adaptersail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listvoile.setAdapter(adaptersail);
		
		Cursor cursormast = sessionbdd.getAllMasts();
		
		cursormast.moveToFirst();
		Integer im= 0;
		while(!cursormast.isAfterLast()) {
			mastArray.add(cursormast.getString(1)); //add the item
			hmMast.put(im, Integer.parseInt(cursormast.getString(0)));
			cursormast.moveToNext();
			im++;
		}
		
		ArrayAdapter <String> adaptermast =
				new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item , mastArray );

		adaptermast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listmat.setAdapter(adaptermast);
		
		Cursor cursororient = sessionbdd.getAllOrient();
		
		cursororient.moveToFirst();
		Integer io= 0;
		while(!cursororient.isAfterLast()) {
			orientArray.add(cursororient.getString(1)); //add the item
			hmOrient.put(io, Integer.parseInt(cursororient.getString(0)));
			cursororient.moveToNext();
			io++;
		}
		
		ArrayAdapter <String> adapterorient =
				new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item , orientArray );

		adapterorient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listorientation.setAdapter(adapterorient);
		
		Bundle b = getIntent().getExtras();
		
		if (b != null){
		    DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
			
			int idSessionbdd = Integer.parseInt(b.getString("IDSESSION"));
			//sessionbdd.open();    		
			Session session = sessionbdd.getSessionWithId(idSessionbdd);
			timestamp = Long.parseLong(session.getDate().toString()) * 1000L;
			viewdate.setText(dateFormat.format(timestamp));
			listspot.setText(sessionbdd.getSpotById(session.getId_spot()).getSpot());
			ventmin.setText(session.getVentMin().toString());
			ventmax.setText(session.getVentMax().toString());
			
			Board board = sessionbdd.getBoardById(session.getId_planche());
			if (board != null){
				int board_position = adapterboard.getPosition(board.getModele());
				listplanche.setSelection(board_position);
			}
			
			Sail sail = sessionbdd.getsailById(session.getId_voile());
			if (sail != null){
				int sail_position = adaptersail.getPosition(sail.getModele());
				listvoile.setSelection(sail_position);
			}
			
			Mast mast = sessionbdd.getMatById(session.getId_mat());
			if (mast != null){
				int mast_position = adaptermast.getPosition(mast.getModele());
				listmat.setSelection(mast_position);
			}
			
			listorientation.setSelection(session.getId_orientation());
						
		}
		

		save.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				Session session = new Session();
				session.setDate(timestamp/1000);
				// Faire une table de correspondance hmSpot
				session.setId_spot(sessionbdd.getSpotBySpot(listspot.getText().toString()).getId_spot()); 
				String ventMin = ventmin.getEditableText().toString();
				if (!ventMin.isEmpty()){
					session.setVentMin(Integer.parseInt(ventMin));
				}
				String ventMax = ventmax.getEditableText().toString();
				if (!ventMax.isEmpty()){
					session.setVentMax(Integer.parseInt(ventMax));
				}
				session.setId_orientation(listorientation.getSelectedItemPosition());
				Integer[] idPlanche = {hmBoard.get(listplanche.getSelectedItemPosition())};
				session.setId_planche(idPlanche); // Renvoi le nom de l'objet selectionné et pas l'ID
				Integer[] idVoile = {hmSail.get(listvoile.getSelectedItemPosition())};
				session.setId_voile(idVoile);
				Integer[] idMat = {hmMast.get(listmat.getSelectedItemPosition())};				
				session.setId_mat(idMat);
				ObjectMapper mapper = new ObjectMapper();
				try {
					String strSession =mapper.writeValueAsString(session);
					String url = "http://windsurf-sessions.eg2.fr/valide_session_ip.php"; 
					//mapper.writeValue(new File("/mnt/sdcard/MWS/session.json"), session);
					new sendPost().execute(url,strSession,null);
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
}
