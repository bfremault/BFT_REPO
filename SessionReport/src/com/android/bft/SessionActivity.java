package com.android.bft;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.android.bft.R;
import com.android.bft.data.Session;
import com.android.bft.data.SessionBDD;
import com.android.bft.media.PictureException;
import com.android.bft.media.PictureSession;
import com.android.bft.media.SessionGalleryActivity;


public class SessionActivity extends Activity {
	
	protected static final String PICTUREFOLDER = "SessionReport";
	protected static final String TAG = null;
	int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	AutoCompleteTextView location;
	RatingBar rate;
	Button save;
    Button pict;
    Button gal;
    Button wind;
    Button share;
    Spinner sail;
    Spinner board;
    EditText comment;
    Spinner windDirection;
    EditText windSpeed;
    Boolean saveState = false;
    int idSession;

    SessionBDD sessionbdd = new SessionBDD(this);
		
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       // SharedPreferences sp = getPreferences(0);
          
        setContentView(R.layout.session);
        
        // pour ne pas afficher le clavier au démarrage de l'activité
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        location=(AutoCompleteTextView)findViewById(R.id.editText2);
		rate=(RatingBar)findViewById(R.id.ratingBar1);
        save=(Button)findViewById(R.id.button1);
        pict=(Button)findViewById(R.id.button2);
		gal=(Button)findViewById(R.id.button3);
		share = (Button)findViewById(R.id.button4);
		sail = (Spinner)findViewById(R.id.spinner1);
		board = (Spinner)findViewById(R.id.spinner2);
		comment = (EditText)findViewById(R.id.editText1);
		windDirection =(Spinner)findViewById(R.id.spinner3);
		windSpeed =(EditText)findViewById(R.id.editText3);
		
		String[] spots = getResources().getStringArray(R.array.spots_array);
		
		ArrayAdapter<String> adapterspots = new ArrayAdapter<String>(this, R.layout.list_item, spots);
	    location.setAdapter(adapterspots);
	    location.setFocusable(true);
	    location.requestFocus();
	  	
		String[] direction = getResources().getStringArray(R.array.direction);
		ArrayAdapter<String> adapterwind = new ArrayAdapter<String>(this, R.layout.list_item, direction);
		windDirection.setAdapter(adapterwind);
		windDirection.setFocusable(true);
		
	    
		final Session session;
	    			
		sail.setFocusableInTouchMode(true);
		
		sessionbdd.open();

		Cursor cursorsail = sessionbdd.getAllActiveEquipment(1);

		ArrayList<String> sailArray = new ArrayList<String>();
		cursorsail.moveToFirst();
		while(!cursorsail.isAfterLast()) {
			sailArray.add(cursorsail.getString(cursorsail.getColumnIndex("equipment_label"))); //add the item
		     cursorsail.moveToNext();
		}
		
//		startManagingCursor(cursorsail);
//				
//		String[] from = new String[]{"equipment_label"};
//
//		int[] to = new int[]{android.R.id.text1};
//		
//		SimpleCursorAdapter adaptersail =
//				  new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursorsail, from, to );
		
//		final ArrayList<String> sailArray = prefToArray("idSail");
//							
//		if (sailArray.size() == 0)
//			{
//			sailArray.add(getString(R.string.nosail));
//			}
//		
		ArrayAdapter <String> adaptersail =
		new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item , sailArray );
		
		adaptersail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
		sail.setAdapter(adaptersail);
					
//		sail.setOnFocusChangeListener(new OnFocusChangeListener() 
//			        {
//
//						public void onFocusChange(View arg0, boolean hasfocus) {
//							if ((hasfocus)&&(sailArray.get(0)==getString(R.string.nosail))){
//							AlertDialog.Builder adb = new AlertDialog.Builder(SessionActivity.this);
//				    		adb.setTitle(getString(R.string.nosail));
//				    		adb.setPositiveButton("Ok", null);
//				    		adb.show();
//				    	    location.requestFocus();
//							}
//						}
//					  
//			        }
//			);
		
	    
//		board.setFocusableInTouchMode(true);
//	
//		final ArrayList<String> boardArray = prefToArray("idBoard");
//		
//		if (boardArray.size() == 0)
//		{
//					boardArray.add(getString(R.string.noboard));
//		}
		
		
		Cursor cursorboard = sessionbdd.getAllActiveEquipment(0);
		
		ArrayList<String> boardArray = new ArrayList<String>();
		cursorboard.moveToFirst();
		while(!cursorboard.isAfterLast()) {
			boardArray.add(cursorboard.getString(cursorboard.getColumnIndex("equipment_label"))); //add the item
		     cursorboard.moveToNext();
		}
		
//		startManagingCursor(cursorboard);
//		SimpleCursorAdapter adapterboard =
//				  new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursorboard, from, to );
//		
		ArrayAdapter <String> adapterboard =
			  new ArrayAdapter <String> (this, android.R.layout.simple_spinner_item , boardArray );
		
		adapterboard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		board.setAdapter(adapterboard);
		

		
//		board.setOnFocusChangeListener(new OnFocusChangeListener() 
//	        {
//
//				public void onFocusChange(View arg0, boolean hasfocus) {
//					if ((hasfocus)&&(boardArray.get(0)==getString(R.string.noboard))){
//					AlertDialog.Builder adb = new AlertDialog.Builder(SessionActivity.this);
//		    		adb.setTitle(getString(R.string.noboard));
//		    		adb.setPositiveButton("Ok", null);
//		    		adb.show();
//		    		location.requestFocus();
//					}
//				}
//			  
//	        }
//		);
		
		sessionbdd.close();

		
		final Bundle b = getIntent().getExtras();
		
		
		final int idSessionbdd;

		if (b != null){
			sessionbdd.open();
			idSessionbdd = b.getInt("IDSESSION");
			session = sessionbdd.getSessionWithId(idSessionbdd);
			location.setText(session.getLocation());
			rate.setRating(session.getRate());
			
			String saillabel = sessionbdd.getEquipmentWithId(session.getSail()).getEquipment();
			int sailPosition = adapterboard.getPosition(saillabel);
			sail.setSelection(sailPosition);
			

			
			String boardlabel = sessionbdd.getEquipmentWithId(session.getBoard()).getEquipment();
			int boardPosition = adapterboard.getPosition(boardlabel);
			board.setSelection(boardPosition);
			
			
			/*ArrayAdapter myAdap = (ArrayAdapter) board.getAdapter(); 

			int spinnerPosition = myAdap.getPosition();
			
			int position = (int) adapterboard.getItemId(session.getSail());
			board.setSelection(position);
			
			int boardPosition = adapterboard.getItem(position);
					board.setSelection(position);
					adapterboard;
					cursorboard.
					ArrayAdapter myAdap = (ArrayAdapter) board.getAdapter(); 

					int spinnerPosition = myAdap.getPosition();
					
					board.getItemIdAtPosition(adapterboard.getPosition(session.getBoard()));
				
					
					getPosition(session.getBoard());*/
			//windDirection.setSelection(boardPosition);
			comment.setText(session.getComment());
			int windDirectionPosition = adapterwind.getPosition(session.getWindDirection());
			windDirection.setSelection(windDirectionPosition);
			windSpeed.setText(session.getWindPower());
			sessionbdd.close();
			}
		else  {
			session = new Session();
			//idSessionbdd = (int) sessionbdd.insertSession(session);
		}
		
//		wind.setOnClickListener(new View.OnClickListener() 
//        {
//        	public void onClick(View v) 
//    		{
//        		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            	
//            	View popupView = inflater.inflate(R.layout.wind_popup,(ViewGroup)findViewById(R.layout.session));
//            	
//         	    final PopupWindow pw = new PopupWindow(
//         	    	popupView,
//         	    	100, 
//         	    	100, 
//         	    	true);
//
//         	    pw.showAtLocation(v, Gravity.CENTER, 0, 0);
//            		     	
//                EditText windDirection=(EditText)popupView.findViewById(R.id.editText1);
//                
//                String windDir = windDirection.getText().toString();
//                
//                EditText windForce=(EditText)popupView.findViewById(R.id.editText1);
//                
//                String windPw = windForce.getText().toString();
//                
//         	    Button exitbutton=(Button)popupView.findViewById(R.id.button5);    
//
//         	    exitbutton.setOnClickListener(
//         	    		new View.OnClickListener() {
//         	                public void onClick(View v) {
//         	                	  pw.dismiss();
//         	             }
//         	         }		
//         	    );
//         	    }
//        }
//		);
		 
		share.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
    		{
        		Session session = getSession();
        		
        		try {
					idSession = saveSession(session);

	        		
	        		saveState = true;
	        		
	        		ArrayList<String> alPict = pictCursorToArraylist(idSession);
	        		
	        		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
	        		sharingIntent.setType("*/*");
	        		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
	        		
	        		String shareText = session.getLocation()
	        		+" "
	        		+formater.format(session.getDate())
	        		+" ";
	        		
	        		if (session.getWindPower() != null){
	        			shareText.concat(
	        					" "
	        					+session.getWindPower()
	        	        		+" "
	        	        		+getResources().getString(R.string.knots)
	        	        		+" "
	        	        		+session.getWindDirection()
	        	        		);
	        		}
	        		
	        		
/*	        		if (session.getBoard() != getResources().getString(R.string.noboard)){
	        			shareText.concat(" "
	        		+session.getBoard());
	        		}
	        		
	        		if (session.getSail() != getResources().getString(R.string.nosail)){
	        			shareText.concat(" "
	        		+session.getSail());
	        		}*/
	        		
	        		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
	        		//sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
	        		if (alPict.size() != 0){
	        			for (int i = 0 ; i<alPict.size() ; i++){
	        				Uri screenshotUri = Uri.parse("file:///"+alPict.get(i));
	        				sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, screenshotUri);
	        			}
	        		}
	        		startActivity(Intent.createChooser(sharingIntent, "Share using"));
	        		
					
				} catch (SessionException e) {
					Toast.makeText(SessionActivity.this, getString(R.string.nolocation) , Toast.LENGTH_LONG).show();	            
				} catch (PictureException e) {
					Toast.makeText(SessionActivity.this, getString(R.string.nopicture) , Toast.LENGTH_LONG).show();	            
				}
       		}
        }
		);
		
		save.setOnClickListener(new View.OnClickListener() 
	        {
	        	public void onClick(View v) 
	    		{

	        		Session session = getSession();
	        				        		
	        		try {
						saveSession(session);
						
						Intent intent = new Intent(SessionActivity.this,SessionListActivity.class);
		        	
						startActivity(intent);	

	        		} catch (SessionException e) {
						Toast.makeText(SessionActivity.this, getString(R.string.nolocation) , Toast.LENGTH_LONG).show();	            
					}
	    			
		       		
//	        	else
//	        	{
//		        	Toast.makeText(SessionActivity.this, getString(R.string.nolocation) , Toast.LENGTH_LONG).show();	            
//	        	}	
	    		}
	        });
        
    	gal.setOnClickListener(new View.OnClickListener() 
        {
    		public void onClick(View v) {
    			   			
    			Session session = getSession();
    			
        		try {
					idSession = saveSession(session);
					
					saveState = true;
    			
					//cleanPicture(idSession);
					
					ArrayList<String> alPict = pictCursorToArraylist(idSession);
    			
					Intent intent = new Intent(SessionActivity.this,SessionGalleryActivity.class);
					
					intent.putExtra("IMAGESURI", alPict);

					startActivity(intent);
					} 
        		
        		catch (SessionException e) {
					
        			Toast.makeText(SessionActivity.this, getString(R.string.nolocation) , Toast.LENGTH_LONG).show();	            
					} catch (PictureException e) {
	        		Toast.makeText(SessionActivity.this, getString(R.string.nopicture) , Toast.LENGTH_LONG).show();	            
				}
        			
    			}
        });
        
        pict.setOnClickListener(new View.OnClickListener() 
        {
        	public void onClick(View v) 
    		{       	                	                	   
       		   
     		       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);	   
        		   
        		   Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        	
        		   Session session = getSession();
        		   
        		   try {
					idSession = saveSession(session);
					
					PictureSession picturesession = new PictureSession(fileUri.getPath(), idSession);
       		     		   
					intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

					startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        		
           		   	sessionbdd.open();						
						
           		   	sessionbdd.insertPictureSession(picturesession);
        		   
           		   	sessionbdd.close();
        		   	}
        		   
        		   catch (SessionException e) {
					Toast.makeText(SessionActivity.this, getString(R.string.nolocation) , Toast.LENGTH_LONG).show();	            
        		   	}
        		
    			}
        });
             
	}
	private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
	}
	

	
//	private ArrayList<String> prefToArray(String idPref) {
//		final ArrayList<String> prefArray = new ArrayList<String>();
//	    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//		
//		for (int i=1; i<6; i++){
//		String item = preferences.getString(idPref+i, null);
//			if ((item == null)||(item == "")){
//				
//			}
//			else
//			{
//				prefArray.add(item);
//			}
//		}			
//		return prefArray;
//	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {

	        	Toast.makeText(this, "Image saved" , Toast.LENGTH_LONG).show();	
	        	
	        } else if (resultCode == RESULT_CANCELED) {
	        	Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show();
	        } else {
	        	Toast.makeText(this, "Capture failed", Toast.LENGTH_LONG).show();
	        }	    
	    };
	}
	
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;

	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type){
	    
		String state = Environment.getExternalStorageState();
		File mediaFile = null;

	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), PICTUREFOLDER);
	
	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d(PICTUREFOLDER, "failed to create directory \n");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    	} 
	    else {
	        return null;
	    	}
	    }
	    return mediaFile;
	}
		/*public File getTempFile(Context context){
		  //it will return /sdcard/image.tmp
		  final File path = new File( Environment.getExternalStorageDirectory(), context.getPackageName() );
		  if(!path.exists()){
		    path.mkdir();
		  }
		  return new File(path, "image.tmp");
		}*/
		
		public Session getSession(){
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy"); 
			String s = dateFormat.format(new Date()); // convert date to string 
			Date d = null;
			try {
				d = dateFormat.parse(s);
			} catch (ParseException e) {
	            Log.e("MyLog", "Parse Data exception \n", e);

				} // convert string to date 
						
					int sailPosition = sail.getSelectedItemPosition();
					int boardPosition = board.getSelectedItemPosition();
							
							
			Session session = new Session(location.getText().toString(),
					rate.getRating(),
					//dateFormat.format(System.currentTimeMillis()),
					d,
					(int)sail.getItemIdAtPosition(sailPosition),
					(int)board.getItemIdAtPosition(boardPosition), 
					comment.getEditableText().toString(),
					windDirection.getSelectedItem().toString(),
					windSpeed.getEditableText().toString());			
			
			return session;
			
		};
	
	
		@SuppressLint("NewApi")
		public int saveSession(Session session) throws SessionException{
		
			int idSessionbdd;
			
			sessionbdd.open();
			Bundle b = getIntent().getExtras();
		
			if (!location.getText().toString().isEmpty())
			{	
				if (b != null)  {

					int idSessionbundle = b.getInt("IDSESSION");

					idSessionbdd = sessionbdd.updateSession(idSessionbundle , session);

				}
				
				else if (saveState.booleanValue() != false) {

					idSessionbdd = sessionbdd.updateSession(idSession , session);

				}

				else{

					idSessionbdd = sessionbdd.insertSession(session);

				}

				sessionbdd.close();
			}
			else
			{
				throw new SessionException();
			}


			return idSessionbdd;
		}
		
		private void cleanPicture(int id){
			
			sessionbdd.open();
			
			Cursor c = sessionbdd.GetAllPictureWithIdSession(id);
			
			c.moveToFirst();
			for (int i = 0; i < c.getCount(); i++){
				URL url = null;
					try {
						url = new URL("file:///"+c.getString(1));
					} catch (MalformedURLException e) {
						sessionbdd.removePictWithID(c.getInt(0));
				        Log.e("MyLog", "URL Exception \n", e);
					}
					
					try {
						 url.openConnection().getInputStream();
					} catch (IOException e) {
						sessionbdd.removePictWithID(c.getInt(0));
				        Log.e("MyLog", "IO Exception", e);
					}
					c.moveToNext();	
			}
			c.close();
			sessionbdd.close();
		}
		
		private ArrayList<String> pictCursorToArraylist(int id) throws PictureException {
			
			ArrayList<String> alPict = new ArrayList<String>();

			sessionbdd.open();
						
			Cursor c = sessionbdd.GetAllPictureWithIdSession(id);
			
			if (c.getCount()>0){
			
			c.moveToFirst();
			
			for (int i = 0; i < c.getCount(); i++){
				alPict.add(c.getString(1));// ca plante ici CursorOutofbonds
				c.moveToNext();
			}
			c.close();
			
			sessionbdd.close();
			}
			else
			{
				throw new PictureException();
			}

			return alPict;
		}
}