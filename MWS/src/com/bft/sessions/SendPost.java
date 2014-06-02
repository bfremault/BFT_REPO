package com.bft.sessions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class SendPost extends AsyncTask<List<NameValuePair>, String, Boolean> {
 
	protected Boolean doInBackground(List<NameValuePair>... urls) {
		makeRequest(urls[0]);
        return true;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result) {
      //  showDialog("Downloaded " + result + " bytes");
    }
	
    public static HttpResponse makeRequest(List<NameValuePair> nameValuePairs) {
		try {
			HttpPost httpPost = new HttpPost("http://windsurf-sessions.eg2.fr/valide_session_ip.php");
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
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
