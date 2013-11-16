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

import android.os.Environment;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONutils {

	    private static final String DL_URL = "http://windsurf-sessions.eg2.fr/login_ip.php?login=bfremault&password=chouchou&last_update_rider=0&last_update_ref=0";

	    private ObjectMapper objectMapper = null;
	    private JsonFactory jsonFactory = null;
	    private JsonParser jp = null;
	    private ArrayList<User> userList = null;
	    private Data data = null;
	    private File jsonOutputFile;
	    private File jsonFile;


	    public JSONutils() {

	    objectMapper = new ObjectMapper();
	    objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
	  //  objectMapper.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);

	    jsonFactory = new JsonFactory();
	    }

	    public void init() {
	   // downloadJsonFile();
	   File test = new File("/mnt/sdcard/tutos-android/users.json");
		try {
			jp = jsonFactory.createJsonParser(test);
		    data = objectMapper.readValue(test, Data.class);
		  //  userList = user.get("user");
		} catch (JsonParseException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();	
		}
	    }
	    
	    private void downloadJsonFile() {
		try {
		    createFileAndDirectory();
		    URL url = new URL(JSONutils.DL_URL);
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
		final String extStorageDirectory = Environment
			.getExternalStorageDirectory().toString();
		final String meteoDirectory_path = extStorageDirectory + "/tutos-android";
		jsonOutputFile = new File(meteoDirectory_path, "/");
		if (jsonOutputFile.exists() == false)
		    jsonOutputFile.mkdirs();
		jsonFile = new File(jsonOutputFile, "users.json");
	    }

/*	    public ArrayList<User> findAll() {
		return userList;
	    }

	    public User findById(int id) {
		return userList.get(id);
	    }*/

	}
	
	


