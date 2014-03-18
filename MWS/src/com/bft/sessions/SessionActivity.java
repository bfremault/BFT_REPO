package com.bft.sessions;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.bft.bdd.DatabaseHelper;
import com.bft.bo.Board;
import com.bft.bo.Mast;
import com.bft.bo.Orientations;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spot;
import com.bft.mws.R;
import com.bft.utils.DownloadImageTask;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class SessionActivity extends OrmLiteBaseActivity<DatabaseHelper> {

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
	ImageView imageplanche;
	ImageView imagevoile;
	ImageView imagemat;

	
	Long timestamp = System.currentTimeMillis();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);        
		setContentView(R.layout.session);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
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
		imageplanche  = (ImageView)findViewById(R.id.imageView1);
		imagevoile = (ImageView)findViewById(R.id.imageView2);
		imagemat = (ImageView)findViewById(R.id.imageView3);
		
		
        RuntimeExceptionDao<Spot, Integer> spotDao = getHelper().getSpotRuntimeExceptionDao();
        List<Spot> spotlist = spotDao.queryForAll();

		ArrayAdapter<Spot> adapterspot = new ArrayAdapter<Spot>(this, R.layout.list_item,spotlist);

		listspot.setAdapter(adapterspot);
		listspot.setFocusable(true);
		listspot.requestFocus();

	    RuntimeExceptionDao<Board, Integer> boardDao = getHelper().getBoardRuntimeExceptionDao();
	    List<Board> boardlist = boardDao.queryForAll();
		
		ArrayAdapter <Board> adapterboard =
				new ArrayAdapter <Board> (this, android.R.layout.simple_spinner_item,boardlist);

		adapterboard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listplanche.setAdapter(adapterboard);

	    RuntimeExceptionDao<Sail, Integer> sailDao = getHelper().getSailRuntimeExceptionDao();
	    List<Sail> saillist = sailDao.queryForAll();
		
		ArrayAdapter <Sail> adaptersail =
				new ArrayAdapter <Sail> (this, android.R.layout.simple_spinner_item,saillist);
		
		adaptersail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listvoile.setAdapter(adaptersail);
				
	    RuntimeExceptionDao<Mast, Integer> mastDao = getHelper().getMastRuntimeExceptionDao();
	    List<Mast> mastlist = mastDao.queryForAll();
	    
		ArrayAdapter <Mast> adaptermast =
				new ArrayAdapter <Mast> (this, android.R.layout.simple_spinner_item,mastlist);
		
		adaptermast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listmat.setAdapter(adaptermast);
		
	    RuntimeExceptionDao<Orientations, Integer> orientDao = getHelper().getOrientationsRuntimeExceptionDao();
	    List<Orientations> orientlist = orientDao.queryForAll();
		    
		ArrayAdapter <Orientations> adapterorient =
				new ArrayAdapter <Orientations> (this, android.R.layout.simple_spinner_item,orientlist);
		
		adapterorient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listorientation.setAdapter(adapterorient);
		
		Bundle b = getIntent().getExtras();
		
		if (b != null){
		    DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
			
			int idSession = Integer.parseInt(b.getString("IDSESSION"));
		    RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();
		    Session session = sessionDao.queryForId(idSession);
			
			timestamp = Long.parseLong(session.getDate().toString()) * 1000L;
			viewdate.setText(dateFormat.format(timestamp));
			listspot.setText(spotDao.queryForId((session.getId_spot())).getSpot());
			if (session.getVentMin() != null){
				ventmin.setText(session.getVentMin().toString());
			}
			if (session.getVentMax() != null){
				ventmax.setText(session.getVentMax().toString());
			}			
			if (session.getId_planche()[0] != null){
				Board board = boardDao.queryForId(session.getId_planche()[0]);
				listplanche.setSelection(getIndex(listplanche,board.toString()));
				new DownloadImageTask(imageplanche).execute(board.getImage());

				}
			

			if (session.getId_voile()[0] != null){
				Sail sail = sailDao.queryForId(session.getId_voile()[0]);
				listvoile.setSelection(getIndex(listvoile,sail.toString()));
				new DownloadImageTask(imagevoile).execute(sail.getImage());

			}
			
			if(session.getId_mat()[0] != null){
				Mast mast = mastDao.queryForId(session.getId_mat()[0]);
				listmat.setSelection(getIndex(listmat,mast.toString()));
				new DownloadImageTask(imagemat).execute(mast.getImage());
			}
			
			if(session.getId_orientation() != null){
				listorientation.setSelection(session.getId_orientation());
			}
			
									
		}
		

		save.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				Session session = new Session();
				session.setDate(timestamp/1000);
				
				// Faire une table de correspondance hmSpot
				//session.setId_spot(sessionbdd.getSpotBySpot(listspot.getText().toString()).getId_spot()); 
			    RuntimeExceptionDao<Spot, Integer> spotDao = getHelper().getSpotRuntimeExceptionDao();
			    
			    QueryBuilder<Spot,Integer> queryBuilder = spotDao.queryBuilder();
			    queryBuilder.selectColumns("id_spot");
			    Where<Spot, Integer> where = queryBuilder.where();
			    try {
					where.eq(Spot.SPOT_FIELD_NAME, listspot.getText().toString());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    PreparedQuery<Spot> preparedQuery = null;
				try {
					preparedQuery = queryBuilder.prepare();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
					}
			
			    List<Spot> spots = spotDao.query(preparedQuery);
			    //Object[] spotsArray = spots.toArray();
			    //String[] spotStringArray = Arrays.copyOf(spotsArray, spotsArray.length, String[].class); 
				if (spots.size()>0){
					session.setId_spot(spots.get(0).getId_spot()); 
				}
				
				String ventMin = ventmin.getEditableText().toString();
				if (!ventMin.isEmpty()){
					session.setVentMin(Integer.parseInt(ventMin));
				}
				String ventMax = ventmax.getEditableText().toString();
				if (!ventMax.isEmpty()){
					session.setVentMax(Integer.parseInt(ventMax));
				}
				
				session.setId_orientation(listorientation.getSelectedItemPosition());
				
				Integer[] idPlanche = null;
				Board board = (Board)listplanche.getItemAtPosition(listplanche.getSelectedItemPosition());
				if (board != null){
					idPlanche[0] = board.getId_planche();
					session.setId_planche(idPlanche);
				}
				
				Integer[] idVoile = null;
				Sail sail = (Sail)listvoile.getItemAtPosition(listvoile.getSelectedItemPosition());
				if (sail != null){
					idVoile[0] = sail.getId_voile();
					session.setId_voile(idVoile);
				}
				
				Integer[] idMat = null;
				Mast mast = (Mast)listmat.getItemAtPosition(listmat.getSelectedItemPosition());
				if (mast != null){
					idMat[0] = mast.getId_mat();
					session.setId_mat(idMat);
				}
				
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
	private int getIndex(Spinner spinner, String myString)
	 {
	  int index = 0;

	  for (int i=0;i<spinner.getCount();i++){
		  String item = spinner.getItemAtPosition(i).toString();
		   if (item.equalsIgnoreCase(myString)){
		    index = i;
		    i=spinner.getCount();//will stop the loop, kind of break, by making condition false
		   }
	  }
	  return index;
	 } 
}
