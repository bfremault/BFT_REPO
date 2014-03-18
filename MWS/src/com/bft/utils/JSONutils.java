package com.bft.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.*;

import com.bft.bo.Data;
import com.bft.bo.Spot;
import com.bft.bo.User;

import android.os.Environment;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONutils {

	    private static final String BEGIN_URL = "http://windsurf-sessions.eg2.fr/login_ip.php?login=";
	    private static final String MIDDLE_URL = "&password=";
	    private static final String END_URL = "&last_update_rider=";
	    private static final String END_URL_2 = "&last_update_ref=";

	    private ObjectMapper objectMapper = null;
	    private JsonFactory jsonFactory = null;
	    private JsonParser jp = null;
	    private Data data = null;
	    private File jsonOutputFile;
	    private File jsonFile;
		private String[] args;
	    		
	    public JSONutils() {
			super();
		}

		public JSONutils(String[] args) {
			super();
	    	this.args = args;
	    }

	    public Data init() {
	        objectMapper = new ObjectMapper();
		    objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		    //  objectMapper.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);
		    jsonFactory = new JsonFactory();
	    	
	    	downloadJsonFile();
		    //File file = new File("/storage/extSdCard/users1.json");
		    File file = new File("/mnt/sdcard/MWS/users.json");
			try {
				jp = jsonFactory.createJsonParser(file);
			    data = objectMapper.readValue(file, Data.class);
				} catch (JsonParseException e) {
				    e.printStackTrace();
				} catch (IOException e) {
				    e.printStackTrace();	
				}
				return data;
		    }
	    
	    private void downloadJsonFile() {
		try {
		    createFileAndDirectory();
		    URL url = new URL(JSONutils.BEGIN_URL+args[0]+MIDDLE_URL+args[1]+END_URL+args[2]+END_URL_2+args[2]);
		    HttpURLConnection urlConnection;
		    urlConnection = (HttpURLConnection) url.openConnection();
		    urlConnection.setRequestMethod("GET");
		    urlConnection.setDoOutput(true);
		    urlConnection.connect();
		    FileOutputStream fileOutput = new FileOutputStream(jsonFile);
		    InputStream inputStream = urlConnection.getInputStream();
		    byte[] buffer = new byte[1024];
		    int bufferLength = 0;
		    while ((bufferLength = inputStream.read(buffer)) > 0) {
			fileOutput.write(buffer, 0, bufferLength);
		    }
		    fileOutput.close();
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    
	    private void createFileAndDirectory() throws FileNotFoundException {
		final String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
		final String path = extStorageDirectory + "/MWS";
		jsonOutputFile = new File(path, "/");
		if (jsonOutputFile.exists() == false)
		    jsonOutputFile.mkdirs();
		jsonFile = new File(jsonOutputFile, "users.json");
	    }

	}
	
	


