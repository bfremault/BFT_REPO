package com.bft.sessions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

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
			HttpGet httpGet = new HttpGet("http://windsurf-sessions.eg2.fr/supp_session_ip.php");
    		
    		HttpParams httpParams = null;
    		httpParams.setParameter("id_session", params.get("id_session"));
    		httpParams.setParameter("user_session",params.get("user_session"));
			
			httpGet.setParams(httpParams);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-type", "application/json");
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
