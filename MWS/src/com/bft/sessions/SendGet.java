package com.bft.sessions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;

public class SendGet extends AsyncTask<HashMap<String, String>, String, Boolean> {
 
	protected Boolean doInBackground(HashMap<String, String>... urls) {
		makeRequest(urls[0]);
        return true;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result) {
      //  showDialog("Downloaded " + result + " bytes");
    }
	
    public static HttpResponse makeRequest(HashMap<String, String> params) {
    	HttpResponse response = null;
    	try {
    		Builder uri = new Uri.Builder();
    		uri.scheme("http")
    	    .authority("windsurf-sessions.eg2.fr")
    	    .path("supp_session_ip.php")
    	    .appendQueryParameter("id_session", params.get("id_session"))
    	    .appendQueryParameter("user_session",params.get("user_session"))
    	    .build();
    		
			HttpGet httpGet = new HttpGet(uri.toString());
    		
			response = new DefaultHttpClient().execute(httpGet);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
