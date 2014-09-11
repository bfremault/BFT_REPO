package com.bft.sessions;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.bft.bo.Session;
import com.bft.login.LoginActivity;

public class SessionUtils {

	public List<NameValuePair> sessionTonameValuePairs(Session session) {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        nameValuePairs.add(new BasicNameValuePair("date", session.getDate().toString()));
        
		if(session.getId_spot()!=null){
			nameValuePairs.add(new BasicNameValuePair("id_spot",session.getId_spot().toString()));
		}
		
		if(session.getAll_types_nav()!= null){
		    nameValuePairs.add(new BasicNameValuePair("type_nav", session.getAll_types_nav()[0].toString()));		
		}
        
		if(session.getId_planche()!=null){
	        nameValuePairs.add(new BasicNameValuePair("planche0", session.getId_planche()[0].toString()));	
		}
        
		if(session.getId_voile()!=null){
	        nameValuePairs.add(new BasicNameValuePair("voile0", session.getId_voile()[0].toString()));			
		}
        
		if(session.getId_mat()!=null){
	        nameValuePairs.add(new BasicNameValuePair("mat0", session.getId_mat()[0].toString()));			
		}
		
		if(session.getId_aileron()!=null){
	        nameValuePairs.add(new BasicNameValuePair("aileron0", session.getId_aileron()[0].toString()));			
		}
        
        
		if(session.getId_orientation()!=null){
			nameValuePairs.add(new BasicNameValuePair("id_orientation", session.getId_orientation().toString()));			
		}
        
		if(session.getVentMin()!=null){
			nameValuePairs.add(new BasicNameValuePair("vent_min", session.getVentMin().toString()));			
		}
        
		if(session.getVentMax()!=null){
	        nameValuePairs.add(new BasicNameValuePair("vent_max", session.getVentMax().toString()));			
		}

		if(session.getVague()!=null){
	        nameValuePairs.add(new BasicNameValuePair("vagues", session.getVague().toString()));			
		}

		if (session.getDuree()!= null){
			nameValuePairs.add(new BasicNameValuePair("duree", session.getDuree().toString()));		
		}
        
		nameValuePairs.add(new BasicNameValuePair("commentaire", session.getCommentaire()));

		if(session.getNote()!=null){
		    nameValuePairs.add(new BasicNameValuePair("note", session.getNote().toString()));			
		}

		if (session.getVmax() != null){
		    nameValuePairs.add(new BasicNameValuePair("vmax", session.getVmax().toString()));			
		}
        
		if (session.getDistance() != null){
		    nameValuePairs.add(new BasicNameValuePair("distance", session.getDistance().toString()));		
		}
		
		if (session.getId_session() > 0){
		    nameValuePairs.add(new BasicNameValuePair("id_session", session.getId_session().toString()));		
		}
		
	    nameValuePairs.add(new BasicNameValuePair("user_session", LoginActivity.USER_SESSION));

		return nameValuePairs;
	}
}
