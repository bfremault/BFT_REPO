package com.bft.sessions;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;

import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bft.bdd.DatabaseHelper;
import com.bft.bo.Board;
import com.bft.bo.Mast;
import com.bft.bo.Orientations;
import com.bft.bo.Pays;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spin;
import com.bft.bo.Spot;
import com.bft.login.MWS;
import com.bft.mws.R;
import com.bft.utils.DateUtils;
import com.bft.utils.DownloadImageTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SessionActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	private EditText viewdate;
	private AutoCompleteTextView listspot;
	private EditText ventmin;
	private EditText ventmax;
	private Spinner listorientation;
	private Spinner listplanche;
	private Spinner listvoile;
	private Spinner listspin;
	private Spinner listmat;
	private ImageView imageplanche;
	private ImageView imagevoile;
	private ImageView imagemat;
	private ImageView imagespin;
	private ImageView drapeau;
	private GoogleMap mMap;
	private EditText commentaire;
	private RatingBar rate;
	private EditText vague;
	private EditText duration;
	private boolean isModifyStatus = true;

	Long timestamp = System.currentTimeMillis();
	int idSession = 0;
	Session session = new Session();
	Spot spot = null;
	Pays pays = null;
	Integer id_pays =null;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	List<Spot> spotlist = null; 
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);        
		if (Build.VERSION.SDK_INT >= 11) {
			  invalidateOptionsMenu();
		}
		
		setContentView(R.layout.session);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		viewdate = (EditText)findViewById(R.id.editText0);
		listspot = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		ventmin = (EditText)findViewById(R.id.editText1);
		ventmax = (EditText)findViewById(R.id.editText2);
		listorientation = (Spinner)findViewById(R.id.spinner1);
		listplanche = (Spinner)findViewById(R.id.spinner2);
		listvoile = (Spinner)findViewById(R.id.spinner3);
		listmat = (Spinner)findViewById(R.id.spinner4);
		listspin = (Spinner)findViewById(R.id.spinner5);
		imageplanche  = (ImageView)findViewById(R.id.imageView1);
		imagevoile = (ImageView)findViewById(R.id.imageView2);
		imagemat = (ImageView)findViewById(R.id.imageView3);
		imagespin = (ImageView)findViewById(R.id.imageView5);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		commentaire = (EditText)findViewById(R.id.editText3);
		rate = (RatingBar)findViewById(R.id.ratingBar1);
		vague = (EditText)findViewById(R.id.editText4);
		duration = (EditText)findViewById(R.id.editText5);
		drapeau = (ImageView)findViewById(R.id.imageView4);
		
		isModifyStatus = modifySession(true);
		
		viewdate.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				showDatePickerDialog(v);
			}
		}
		);
		
	    DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
		
		viewdate.setText(dateFormat.format(System.currentTimeMillis()));
		
		spotlist = ((MWS)getApplicationContext()).getSpotlist(); 
		
	    RuntimeExceptionDao<Spot, Integer> spotDao = getHelper().getSpotRuntimeExceptionDao();
	
		ArrayAdapter<Spot> adapterspot = new ArrayAdapter<Spot>(this, R.layout.list_item,spotlist);		
		listspot.setAdapter(adapterspot);
		
        listspot.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
					spot = (Spot) parent.getAdapter().getItem(position);
					LatLng spotlocation = new LatLng(spot.getLatitude(),spot.getLongitude());
				    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spotlocation, 10));
				    id_pays = spot.getId_pays();
				    
					if(id_pays != null){
					    RuntimeExceptionDao<Pays, Integer> countryDao = getHelper().getPaysRuntimeExceptionDao();
					    
						pays = countryDao.queryForId(id_pays);
						
						if (pays != null){
							new DownloadImageTask(drapeau).execute(pays.getDrapeau());
						}
					}
			}
		});
        
        listspot.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String val = listspot.getText() + "";         
            	    RuntimeExceptionDao<Spot, Integer> spotDao = getHelper().getSpotRuntimeExceptionDao();
                    QueryBuilder<Spot, Integer> qb = spotDao.queryBuilder();
                    
                    List<Spot> ls = null;
            	    try {
            		    qb.where().eq("spot", val);         		  
            		    PreparedQuery<Spot> preparedQuery = qb.prepare();
            		    ls = spotDao.query(preparedQuery);
            		} catch (SQLException e) {
            			e.printStackTrace();
            		}
            		if(ls.isEmpty()){
                    	listspot.setError(getString(R.string.invalid_spot));
                    	listspot.setText("");
                    	
                    }
                } else {
                	listspot.setError(null);
                }
            }
        });
        
  	
	    RuntimeExceptionDao<Board, Integer> boardDao = getHelper().getBoardRuntimeExceptionDao();
	    List<Board> boardlist = boardDao.queryForAll();
		
		ArrayAdapter <Board> adapterboard =
				new ArrayAdapter <Board> (this, android.R.layout.simple_spinner_item,boardlist);

		adapterboard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		listplanche.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			    Board board = (Board) parent.getAdapter().getItem(position);
			    //new DownloadImageTask(imageplanche).execute(board.getImage());
	            imageLoader.displayImage(board.getImage(), imageplanche);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		listplanche.setAdapter(adapterboard);

	    RuntimeExceptionDao<Sail, Integer> sailDao = getHelper().getSailRuntimeExceptionDao();
	    List<Sail> saillist = sailDao.queryForAll();
		
		ArrayAdapter <Sail> adaptersail =
				new ArrayAdapter <Sail> (this, android.R.layout.simple_spinner_item,saillist);
		
		adaptersail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		listvoile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Sail sail = (Sail) parent.getAdapter().getItem(position);
			   // new DownloadImageTask(imagevoile).execute(sail.getImage());	
	            imageLoader.displayImage(sail.getImage(), imagevoile);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		listvoile.setAdapter(adaptersail);
				
	    RuntimeExceptionDao<Mast, Integer> mastDao = getHelper().getMastRuntimeExceptionDao();
	    List<Mast> mastlist = mastDao.queryForAll();
	    
		ArrayAdapter <Mast> adaptermast =
				new ArrayAdapter <Mast> (this, android.R.layout.simple_spinner_item,mastlist);
		
		adaptermast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		listmat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Mast mast = (Mast) parent.getAdapter().getItem(position);
				//new DownloadImageTask(imagemat).execute(mat.getImage());
	            imageLoader.displayImage(mast.getImage(), imagemat);

				}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		
		listmat.setAdapter(adaptermast);
		
	    RuntimeExceptionDao<Spin, Integer> spinDao = getHelper().getSpinRuntimeExceptionDao();
	    List<Spin> spinlist = spinDao.queryForAll();
	    
		ArrayAdapter <Spin> adapterspin =
				new ArrayAdapter <Spin> (this, android.R.layout.simple_spinner_item,spinlist);
		
		adaptermast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		listspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Spin spin = (Spin) parent.getAdapter().getItem(position);
				//new DownloadImageTask(imagespin).execute(spin.getImage());
	            imageLoader.displayImage(spin.getImage(), imagespin);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		
		listspin.setAdapter(adapterspin);
		
	    RuntimeExceptionDao<Orientations, Integer> orientDao = getHelper().getOrientationsRuntimeExceptionDao();
	    List<Orientations> orientlist = orientDao.queryForAll();
		    
		ArrayAdapter <Orientations> adapterorient =
				new ArrayAdapter <Orientations> (this, android.R.layout.simple_spinner_item,orientlist);
		
		adapterorient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
	 	listorientation.setAdapter(adapterorient);
		
		Bundle b = getIntent().getExtras();
		
		if (b != null){
			isModifyStatus = modifySession(false);
			
			idSession = Integer.parseInt(b.getString("IDSESSION"));
		    RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();
		    session = sessionDao.queryForId(idSession);
		    
			timestamp = Long.parseLong(session.getDate().toString()) * 1000L;
			viewdate.setText(dateFormat.format(timestamp));
			
			spot = spotDao.queryForId((session.getId_spot()));
			
			listspot.setText(spot.getSpot());

			id_pays = spot.getId_pays();
			
			if(id_pays != null){
			    RuntimeExceptionDao<Pays, Integer> countryDao = getHelper().getPaysRuntimeExceptionDao();
			    
				pays = countryDao.queryForId(id_pays);
				
				if (pays != null){
					new DownloadImageTask(drapeau).execute(pays.getDrapeau());
				}
			}
	
			
			LatLng spotlocation = new LatLng(spot.getLatitude(),spot.getLongitude());
		    mMap.setMyLocationEnabled(true);
		    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spotlocation, 10));
		    UiSettings mUiSettings = mMap.getUiSettings();
		    mUiSettings.setZoomControlsEnabled(false);
		    mUiSettings.setMyLocationButtonEnabled(false);
		    mUiSettings.setAllGesturesEnabled(false);
		    
			if (session.getVentMin() != null){
				ventmin.setText(session.getVentMin().toString());
			}
	
			if (session.getVentMax() != null){
				ventmax.setText(session.getVentMax().toString());
			}	

			if (session.getId_planche()[0] != null){
				Board board = boardDao.queryForId(session.getId_planche()[0]);
				listplanche.setSelection(getIndex(listplanche,board.toString()));
				//new DownloadImageTask(imageplanche).execute(board.getImage());
	            imageLoader.displayImage(board.getImage(), imageplanche);

				}
			
			if (session.getId_voile()[0] != null){
				Sail sail = sailDao.queryForId(session.getId_voile()[0]);
				listvoile.setSelection(getIndex(listvoile,sail.toString()));
				//new DownloadImageTask(imagevoile).execute(sail.getImage());
	            imageLoader.displayImage(sail.getImage(), imagevoile);

			}
			
			if(session.getId_mat()[0] != null){
				Mast mast = mastDao.queryForId(session.getId_mat()[0]);
				listmat.setSelection(getIndex(listmat,mast.toString()));
				//new DownloadImageTask(imagemat).execute(mast.getImage());
	            imageLoader.displayImage(mast.getImage(), imagemat);
			}
			
			if(session.getId_aileron()[0] != null){
				Spin spin = spinDao.queryForId(session.getId_aileron()[0]);
				listspin.setSelection(getIndex(listspin,spin.toString()));
				//new DownloadImageTask(imagespin).execute(spin.getImage());
	            imageLoader.displayImage(spin.getImage(), imagespin);

			}
			
			if(session.getId_orientation() != null){
				listorientation.setSelection(session.getId_orientation()-1);
			}
			
			if(session.getNote() != null){
				float note = (float)session.getNote() / (float)2;
				rate.setRating(note);
			}
			
			if(session.getDuree() != null){
				duration.setText(session.getDuree().toString());
			}
			
			if(session.getVague() != null){
				vague.setText(session.getVague().toString());
			}
			
		    commentaire.setText(Html.fromHtml(session.getCommentaire()));
									
		}
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		   if (Build.VERSION.SDK_INT >= 11) {
		        selectMenu(menu);
		    }
		
			return(super.onCreateOptionsMenu(menu));
		}
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
			return(applyMenuChoice(item) ||
			super.onOptionsItemSelected(item));
		}
	
	
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (Build.VERSION.SDK_INT < 11) {
            selectMenu(menu);
        }
		return super.onPrepareOptionsMenu(menu);
    }
	
	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.modify:
			
			isModifyStatus = modifySession(true);
			return(true);

		case R.id.save:
		
			save(session);
			return(true);
		}
		return(false);
		}
	
	private void selectMenu(Menu menu) {
	    menu.clear();
		MenuInflater inflater = getMenuInflater();

	    if (isModifyStatus) {
	        inflater.inflate(R.menu.sessionmenu_modify, menu);
    	}
	    else {
	    	inflater.inflate(R.menu.sessionmenu_locked, menu); 		   
	    }
	}
	
	
	private boolean modifySession(Boolean status) {
		invalidateOptionsMenu();
		if (status){ 
			viewdate.setEnabled(true);
			listspot.setEnabled(true);
			listorientation.setEnabled(true);
			ventmin.setEnabled(true);
			ventmax.setEnabled(true);
			listorientation.setEnabled(true);
			listplanche.setEnabled(true);
			listvoile.setEnabled(true);
			listmat.setEnabled(true);
			listspin.setEnabled(true);
		//	imageplanche.setVisibility(View.GONE);
		//	imagevoile.setVisibility(View.GONE);
		//	imagemat.setVisibility(View.GONE);
		//	imagespin.setVisibility(View.GONE);
			commentaire.setEnabled(true);
			rate.setEnabled(true);
			duration.setEnabled(true);
			vague.setEnabled(true);
			drapeau.setVisibility(View.GONE);
			return true;
		}else{
			viewdate.setEnabled(false);
			listspot.setEnabled(false);
			listorientation.setEnabled(false);
			ventmin.setEnabled(false);
			ventmax.setEnabled(false);
			listorientation.setEnabled(false);
			listplanche.setEnabled(false);
			listvoile.setEnabled(false);
			listmat.setEnabled(false);
			listspin.setEnabled(false);
			imageplanche.setVisibility(View.VISIBLE);
			imagevoile.setVisibility(View.VISIBLE);
			imagemat.setVisibility(View.VISIBLE);
			imagespin.setVisibility(View.VISIBLE);
			commentaire.setEnabled(false);
			rate.setEnabled(false);
			duration.setEnabled(false);
			vague.setEnabled(false);
			drapeau.setVisibility(View.VISIBLE);
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void save(Session session) 
	{
		
		boolean cancel = false;
		View focusView = null;
		
		String spot_label = listspot.getText().toString();

		
		if (TextUtils.isEmpty(spot_label)) {
			listspot.setError(getString(R.string.error_field_required));
			focusView = listspot;
			cancel = true;
		}
		
		if (cancel) {
			focusView.requestFocus();
			isModifyStatus = modifySession(true);
		} else
		
		{
			
			Long timestamp = new DateUtils().dateToTimestamp(viewdate.getText().toString());
			session.setDate(timestamp);

			if (spot != null){
				session.setId_spot(spot.getId_spot());		
			}

			Integer[] idPlanche = new Integer[1];
			Board board = (Board)listplanche.getItemAtPosition(listplanche.getSelectedItemPosition());
			if (board != null){
				idPlanche[0] = board.getId_planche();
				session.setId_planche(idPlanche);
			}

			Integer[] idVoile  = new Integer[1];
			Sail sail = (Sail)listvoile.getItemAtPosition(listvoile.getSelectedItemPosition());
			if (sail != null){
				idVoile[0] = sail.getId_voile();
				session.setId_voile(idVoile);
			}

			Integer[] idSpin  = new Integer[1];
			Spin spin = (Spin)listspin.getItemAtPosition(listspin.getSelectedItemPosition());
			if (spin != null){
				idSpin[0] = spin.getId_aileron();
				session.setId_aileron(idSpin);
			}

			Integer[] idMat  = new Integer[1];
			Mast mast = (Mast)listmat.getItemAtPosition(listmat.getSelectedItemPosition());
			if (mast != null){
				idMat[0] = mast.getId_mat();
				session.setId_mat(idMat);
			}

			Orientations orient = (Orientations) listorientation.getItemAtPosition(listorientation.getSelectedItemPosition());
			if (orient != null){
				session.setId_orientation(orient.getId_orientation());
			}

			String ventMin = ventmin.getEditableText().toString();
			if (!ventMin.isEmpty()){
				session.setVentMin(Integer.parseInt(ventMin));
			}

			String ventMax = ventmax.getEditableText().toString();
			if (!ventMax.isEmpty()){
				session.setVentMax(Integer.parseInt(ventMax));
			}

			String vagueStr = vague.getEditableText().toString();
			if (!vagueStr.isEmpty()){
				session.setVague(Integer.parseInt(vagueStr));
			}

			String durationStr = duration.getEditableText().toString();
			if (!durationStr.isEmpty()){
				session.setDuree(Integer.parseInt(durationStr));
			}

			Float note = rate.getRating();
			if (note != null){
				session.setNote(Math.round(note)*2);
			}

			List<NameValuePair> nameValuePairs = new SessionUtils().sessionTonameValuePairs(session);	
			new SendPost().execute(nameValuePairs,null,null);

			RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();
			if(session.getId_session()!=null){
				sessionDao.update(session);									
			} else {
				Random r = new Random();
				session.setId_session(r.nextInt());
				sessionDao.create(session);									
			}
			
			isModifyStatus = modifySession(false);
		}
	}	
}