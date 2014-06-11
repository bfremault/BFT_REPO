package com.bft.login;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import com.bft.bdd.DatabaseHelper;
import com.bft.bo.Board;
import com.bft.bo.Data;
import com.bft.bo.Mast;
import com.bft.bo.Pays;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spin;
import com.bft.bo.Spot;
import com.bft.bo.User;
import com.bft.listsessions.ListSessionsActivity;
import com.bft.mws.R;
import com.bft.utils.JSONutils;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends OrmLiteBaseActivity<DatabaseHelper> {
	
	public static String USER_SESSION ="";
	
	private UserLoginTask mAuthTask = null;

	// Values for Login and password at the time of the login attempt.
	private String mLogin;
	private String mPassword;
    private Boolean saveLogin;	

	// UI references.
	private EditText mLoginView;
	private EditText mPasswordView;
	private TextView mLink;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	private CheckBox saveLoginCheckBox;
	Editor loginPrefsEditor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
  
		setContentView(R.layout.login);

		// Set up the login form.
		mLoginView = (EditText) findViewById(R.id.login);
		mLoginView.setText(mLogin);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		saveLoginCheckBox = (CheckBox) findViewById(R.id.rememberme);

		SharedPreferences loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
        	mLoginView.setText(loginPreferences.getString("username", ""));
        	mPasswordView.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
		
        mLink = (TextView) findViewById(R.id.link);
        if (mLink != null) {
          mLink.setMovementMethod(LinkMovementMethod.getInstance());
        }
                
		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);
		
		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid Login, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mLoginView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mLogin = mLoginView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		}
		
		// Check for a valid Login address.
		if (TextUtils.isEmpty(mLogin)) {
			mLoginView.setError(getString(R.string.error_field_required));
			focusView = mLoginView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
	        
			 InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	         imm.hideSoftInputFromWindow(mLoginView.getWindowToken(), 0);

	            String username = mLoginView.getText().toString();
	            String password = mPasswordView.getText().toString();

	            if (saveLoginCheckBox.isChecked()) {
	                loginPrefsEditor.putBoolean("saveLogin", true);
	                loginPrefsEditor.putString("username", username);
	                loginPrefsEditor.putString("password", password);
	                loginPrefsEditor.commit();
	            } else {
	                loginPrefsEditor.clear();
	                loginPrefsEditor.commit();
	            }
			
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			SharedPreferences srvtime_pref = getSharedPreferences("srvtime", MODE_PRIVATE);
			String srvTimeStr = String.valueOf(srvtime_pref.getInt("srvtime", 0));
			mAuthTask.execute(mLogin,mPassword,srvTimeStr);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<String, String, Boolean> {
		protected Boolean doInBackground(String... params) {

		 	JSONutils jSon = new JSONutils(params);
    		Data data = jSon.init();
    		
    		if (data.getUser()!=null){
    			
    		USER_SESSION = data.getUser_session();
    		 		
            RuntimeExceptionDao<Spot, Integer> spotDao = getHelper().getSpotRuntimeExceptionDao();
            
    		List<Spot> spots = data.getListe_spots();
	    	if (spots != null) {	
    			Iterator<Spot> itSpot = spots.iterator();
	    		while(itSpot.hasNext()){
	    			Spot spot = (Spot)(itSpot.next());
	                spotDao.createIfNotExists(spot);
	    		}
	    	};
	    	
	    	MWS mws = ((MWS)getApplicationContext()); 
			mws.setSpotlist(spotDao.queryForAll());
	    	
    		RuntimeExceptionDao<Board, Integer> boardDao = getHelper().getBoardRuntimeExceptionDao();

    		List<Board> boards = data.getPlanches();
    		if (boards != null) {
    			Iterator<Board> itBoard = boards.iterator();
	    		while(itBoard.hasNext()){
	    			Board board = (Board)(itBoard.next());
	                boardDao.createIfNotExists(board);
	    		}
    		};
    		    		
            RuntimeExceptionDao<Sail, Integer> sailDao = getHelper().getSailRuntimeExceptionDao();

    		
    		List<Sail> sails = data.getVoiles();
    		if (sails != null) {
    			Iterator<Sail> itSail = sails.iterator();
	    		while(itSail.hasNext()){
	    			Sail sail = (Sail)(itSail.next());
	    			sailDao.createIfNotExists(sail);
	    		}
    		};
    		
            RuntimeExceptionDao<Mast, Integer> mastDao = getHelper().getMastRuntimeExceptionDao();

    		
    		List<Mast> masts = data.getMats();
    		if (masts != null) {
    			Iterator<Mast> itMast = masts.iterator();
	    		while(itMast.hasNext()){
	    			Mast mat = (Mast)(itMast.next());
	    			mastDao.createIfNotExists(mat);
	    		}
    		};
    		
    		
            RuntimeExceptionDao<Spin, Integer> spinDao = getHelper().getSpinRuntimeExceptionDao();

    		List<Spin> spins = data.getAilerons();
    		if (spins != null) {
    			Iterator<Spin> itSpin = spins.iterator();
	    		while(itSpin.hasNext()){
	    			Spin spin = (Spin)(itSpin.next());
	    			spinDao.createIfNotExists(spin);
	    		}
    		};
    		
            RuntimeExceptionDao<Session, Integer> sessionDao = getHelper().getSessionRuntimeExceptionDao();
    		
    		List<Session> sessions = data.getSessions();
    		if (sessions != null) {
    			Iterator<Session> itSession = sessions.iterator();
	    		while(itSession.hasNext()){
	    			Session session = (Session)(itSession.next());
                    QueryBuilder<Session, Integer> qb = sessionDao.queryBuilder();
                    
                    List<Session> ls = null;
            	    try {
            		    qb.where().eq("date", session.getDate());         		  
            		    PreparedQuery<Session> preparedQuery = qb.prepare();
            		    ls = sessionDao.query(preparedQuery);
            		} catch (SQLException e) {
            			e.printStackTrace();
            		}
            		if(ls.isEmpty()){
    	    			sessionDao.createIfNotExists(session);        
                    } else {
    	    			sessionDao.update(session);                            	
                    }
	    		}
    		};
    		
            RuntimeExceptionDao<User, Integer> userDao = getHelper().getUserRuntimeExceptionDao();

            User user = data.getUser();
            if(user != null){
            	userDao.createIfNotExists(user);
            }	
            
            RuntimeExceptionDao<Pays, Integer> countryDao = getHelper().getPaysRuntimeExceptionDao();

            List<Pays> listPays = data.getListe_pays();
    		if (listPays != null) {
    			Iterator<Pays> itPays = listPays.iterator();
	    		while(itPays.hasNext()){
	    			Pays pays = (Pays)(itPays.next());
	    			countryDao.createIfNotExists(pays);
	    		}
    		};
    		
    		SharedPreferences srvtime_pref = getSharedPreferences("srvtime", MODE_PRIVATE);
    		SharedPreferences.Editor prefsEditor; 
			prefsEditor = srvtime_pref.edit();
			prefsEditor.putInt("srvtime", data.getSrvtime());  
			prefsEditor.commit();
    		
    		return true;
    		}
    		else
    		{
    		return false;
    		}
	
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
	          	Intent intent = new Intent(LoginActivity.this,ListSessionsActivity.class);
		    	startActivity(intent);
			
			} else {
				mPasswordView.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
		
	}
}
