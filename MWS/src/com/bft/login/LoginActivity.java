package com.bft.login;

import java.util.Iterator;
import java.util.List;

import com.bft.bdd.DbManager;
import com.bft.bo.Board;
import com.bft.bo.Data;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spot;
import com.bft.mws.R;
import com.bft.sessions.ListSessionsActivity;
import com.bft.utils.JSONutils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity {
	
	private UserLoginTask mAuthTask = null;

	// Values for Login and password at the time of the login attempt.
	private String mLogin;
	private String mPassword;

	// UI references.
	private EditText mLoginView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

    final DbManager sessionbdd = new DbManager(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

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
	        sessionbdd.open();    		
	        final String srvtime = sessionbdd.getParameter("srvtime");
			
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute(mLogin,mPassword,srvtime);
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
			// TODO: attempt authentication against a network service.

		 	JSONutils jSon = new JSONutils(params);
    		Data data = jSon.init();
    		
    		if (data.getUser()!=null){
    		 		
    		sessionbdd.open();    		

    		List<Spot> spots = data.getListe_spots();
	    	if (spots != null) {	
    			Iterator<Spot> it = spots.iterator();
	    		while(it.hasNext()){
	    			Spot spot = (Spot)(it.next());
	    			Integer id_spot = spot.getId_spot();
	    			Cursor cursor = sessionbdd.getIdSpot(id_spot);
	    			cursor.moveToFirst();
	    			if (cursor.getCount() == 0){
		    			sessionbdd.insertSpot(spot);
	    			}
	    			else{
		    			sessionbdd.updateSpot(id_spot,spot);  	
	    			}
	    			cursor.close();
	    		}
	    	};
    		
    		List<Session> sessions = data.getSessions();
    		if (sessions != null) {
    			Iterator<Session> it = sessions.iterator();
	    		while(it.hasNext()){
	    			Session session = (Session)(it.next());
	    			Integer id_session = session.getId_session();
	    			Cursor cursor = sessionbdd.getIdSession(id_session);
	    			cursor.moveToFirst();
	    			if (cursor.getCount() == 0){
		    			sessionbdd.insertSession(session);
	    			}
	    			else{
		    			sessionbdd.updateSession(id_session,session);  	
	    			}
	    			cursor.close();
	    		}
    		};
    		
    		List<Board> boards = data.getPlanches();
    		if (boards != null) {
    			Iterator<Board> it = boards.iterator();
	    		while(it.hasNext()){
	    			Board board = (Board)(it.next());
	    			Integer id_board = board.getId_planche();
	    			Cursor cursor = sessionbdd.getIdBoard(id_board);
	    			cursor.moveToFirst();
	    			if (cursor.getCount() == 0){
		    			sessionbdd.insertBoard(board);
	    			}
	    			else{
		    			sessionbdd.updateBoard(id_board,board);  	
	    			}
	    			cursor.close();
	    		}
    		};
    		
    		List<Sail> sails = data.getVoiles();
    		if (sails != null) {
    			Iterator<Sail> it = sails.iterator();
	    		while(it.hasNext()){
	    			Sail sail = (Sail)(it.next());
	    			Integer id_sail = sail.getId_voile();
	    			Cursor cursor = sessionbdd.getIdSail(id_sail);
	    			cursor.moveToFirst();
	    			if (cursor.getCount() == 0){
		    			sessionbdd.insertSail(sail);
	    			}
	    			else{
		    			sessionbdd.updateSail(id_sail,sail);  	
	    			}
	    			cursor.close();
	    		}
    		};
    		
    		Integer srvtime = data.getSrvtime();
    		sessionbdd.updateParameter("srvtime", srvtime);
    		
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
				mPasswordView
						.setError(getString(R.string.error_incorrect_password));
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
