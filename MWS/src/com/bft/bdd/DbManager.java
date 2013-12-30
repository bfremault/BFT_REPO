package com.bft.bdd;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bft.bo.Board;
import com.bft.bo.Data;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbManager {

	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "MWS.db";
	
	private static final String TABLE_SPOTS = "table_spots";
	private static final String COL_ID = "_id";
	private static final int NUM_COL_ID = 0;
	private static final String COL_ID_PAYS = "id_pays";
	private static final int NUM_COL_ID_PAYS = 1;
	private static final String COL_COMMENT = "commentaire";
	private static final int NUM_COL_COMMENT = 2;
	private static final String COL_LOCATION = "location";
	private static final int NUM_COL_LOCATION = 3;
	private static final String COL_LATITUDE = "latitude";
	private static final int NUM_COL_LATITUDE = 4;
	private static final String COL_LONGITUDE = "longitude";
	private static final int NUM_COL_LONGITUDE = 5;
	private static final String COL_RATE = "notenavigation";
	private static final int NUM_COL_RATE = 6;
	private static final String COL_PICTURE_URI = "photosSpot";
	private static final int NUM_COL_PICTURE_URI = 7;
	private static final String COL_REGION = "region";
	private static final int NUM_COL_REGION = 8;
	private static final String COL_SPOT = "spot";
	private static final int NUM_COL_SPOT = 9;
	private static final String COL_VILLE ="ville";
	private static final int NUM_COL_VILLE = 10;

	private static final String TABLE_SESSIONS = "table_sessions";
	private static final String COL_ID_SESSION = "_id";
	private static final String COL_DATE = "date";
	private static final String COL_ID_SPOT = "id_spot";
	private static final String COL_VENTMIN = "ventMin";
	private static final String COL_VENTMAX = "ventMax";
	private static final String COL_ID_ORIENTATION = "id_orientation";
	private static final String COL_ID_PLANCHE = "id_planche";
	private static final String COL_ID_VOILE = "id_voile";
	private static final String COL_ID_AILERON = "id_aileron";
	private static final String COL_ID_MAT ="id_mat";
	private static final String COL_ID_DIVERS ="id_divers";
	private static final String COL_NOTE ="note";
	private static final String COL_COMMENTAIRE ="commentaire";
	private static final String COL_VAGUE ="vague";
	private static final String COL_DUREE ="duree";
	private static final String COL_VMAX ="vmax";
	private static final String COL_DISTANCE ="distance";
	private static final String COL_PHOTOS_SESSION ="photos_session";
	private static final String COL_ALL_TYPES_NAV ="all_types_nav";

	
	private static final String TABLE_PARAMETER = "table_parameter";

	private static final String COL_PARAMETER = "parameter";
	private static final int NUM_COL_PARAMETER = 0;
	private static final String COL_VALUE = "value";
	private static final int NUM_COL_VALUE = 1;

	private static final String TABLE_SAILS = "table_sails";

	private static final String COL_ID_SAIL = "_id";
	private static final String COL_MARQUE_SAIL = "marque";
	private static final String COL_MODELE_SAIL = "modele";
	private static final String COL_SURFACE = "surface";
	private static final String COL_IMAGE_SAIL = "image";
	private static final String COL_ANNEE_SAIL = "annee";
	private static final String COL_ACQUISITION_SAIL = "acquisition";
	private static final String COL_COMMENT_SAIL = "commentaire";
	
	private static final String TABLE_BOARD = "table_boards";

	private static final String COL_ID_BOARD = "_id";
	private static final String COL_MARQUE_BOARD = "marque";
	private static final String COL_MODELE_BOARD = "modele";
	private static final String COL_VOLUME = "volume";
	private static final String COL_IMAGE_BOARD = "image";
	private static final String COL_ANNEE_BOARD = "annee";
	private static final String COL_PROGRAM_BOARD = "programme";
	private static final String COL_ACQUISITION_BOARD = "acquisition";
	private static final String COL_COMMENT_BOARD = "commentaire";
	
	
	private SQLiteDatabase bdd;
	 
	private DbInit DbAccess;
	
	public DbManager(Context context){
		//On créer la BD
		DbAccess = new DbInit(context, NOM_BDD, null, VERSION_BDD);
	}
	
	public void open(){
		//on ouvre la BDD en écriture
		bdd = DbAccess.getWritableDatabase();
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	public int insertSpot(Spot spot){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		
		values.put(COL_ID,spot.getId_spot());
		values.put(COL_ID_PAYS,spot.getId_pays());
		//values.put(COL_COMMENT,spot.getCommentaires());
		values.put(COL_LATITUDE,spot.getLatitude()) ;
		values.put(COL_LONGITUDE,spot.getLongitude()) ;
		//values.put(COL_RATE,spot.getNoteNavigation()) ;
		//values.put(COL_PICTURE_URI,spot.getPhotosSpot());
		values.put(COL_REGION,spot.getRegion());
		values.put(COL_SPOT,spot.getSpot()) ;
		values.put(COL_VILLE,spot.getVille());
		//on insère l'objet dans la BDD via le ContentValues
		return (int) bdd.insert(TABLE_SPOTS, null, values);
	}
	
	public int updateSpot(int id,Spot spot){
		ContentValues values = new ContentValues();
		values.put(COL_ID,spot.getId_spot());
		values.put(COL_ID_PAYS,spot.getId_pays());
		//values.put(COL_COMMENT,spot.getCommentaires());
		values.put(COL_LATITUDE,spot.getLatitude()) ;
		values.put(COL_LONGITUDE,spot.getLongitude()) ;
		//values.put(COL_RATE,spot.getNoteNavigation()) ;
		//values.put(COL_PICTURE_URI,spot.getPhotosSpot());
		values.put(COL_REGION,spot.getRegion());
		values.put(COL_SPOT,spot.getSpot()) ;
		values.put(COL_VILLE,spot.getVille());
		return (int) bdd.update(TABLE_SPOTS, values, COL_ID + " = '" +  id+"'", null);
	}
	
	public Cursor getIdSpot(int id){	
		Cursor result=  bdd.query(TABLE_SPOTS, new String[] {COL_ID_SESSION}, COL_ID_SESSION + " LIKE \"" + id +"\"", null, null, null, null);
		return result;
	}
	
	public Cursor GetAllSpots() {
		Cursor result=bdd.rawQuery("SELECT * FROM "+ TABLE_SPOTS, null);
		return result;
	}
	
	public int insertSession(Session session){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_ID_SESSION,session.getId_session()); 
		values.put(COL_DATE,session.getDate());
		values.put(COL_ID_SPOT,session.getId_spot()); 
		values.put(COL_VENTMIN,session.getVentMin());
		values.put(COL_VENTMAX,session.getVentMax());
		values.put(COL_ID_ORIENTATION,session.getId_orientation());
		values.put(COL_ID_PLANCHE,session.getId_planche()[0]);
		values.put(COL_ID_VOILE,session.getId_voile()[0]);
		values.put(COL_ID_AILERON,session.getId_aileron()[0]);
		values.put(COL_ID_MAT,session.getId_mat()[0]); 
		values.put(COL_ID_DIVERS,session.getId_divers()[0]); 
		values.put(COL_NOTE,session.getNote()); 
		values.put(COL_COMMENTAIRE,session.getCommentaire()); 
		values.put(COL_VAGUE,session.getVague()); 
		values.put(COL_DUREE,session.getDuree());
		values.put(COL_VMAX,session.getVentMax());
		values.put(COL_DISTANCE,session.getDistance());
		values.put(COL_PHOTOS_SESSION,session.getPhotos_session());
		values.put(COL_ALL_TYPES_NAV,session.getAll_types_nav()[0]); 
		//on insère l'objet dans la BDD via le ContentValues
		return (int) bdd.insert(TABLE_SESSIONS, null, values);
	}
	
	public int updateSession(int id,Session session){
		ContentValues values = new ContentValues();
		values.put(COL_ID_SESSION,session.getId_session()); 
		values.put(COL_DATE,session.getDate());
		values.put(COL_ID_SPOT,session.getId_spot()); 
		values.put(COL_VENTMIN,session.getVentMin());
		values.put(COL_VENTMAX,session.getVentMax());
		values.put(COL_ID_ORIENTATION,session.getId_orientation());
		values.put(COL_ID_PLANCHE,session.getId_planche()[0]);
		values.put(COL_ID_VOILE,session.getId_voile()[0]);
		values.put(COL_ID_AILERON,session.getId_aileron()[0]);
		values.put(COL_ID_MAT,session.getId_mat()[0]); 
		values.put(COL_ID_DIVERS,session.getId_divers()[0]); 
		values.put(COL_NOTE,session.getNote()); 
		values.put(COL_COMMENTAIRE,session.getCommentaire()); 
		values.put(COL_VAGUE,session.getVague()); 
		values.put(COL_DUREE,session.getDuree());
		values.put(COL_VMAX,session.getVentMax());
		values.put(COL_DISTANCE,session.getDistance());
		values.put(COL_PHOTOS_SESSION,session.getPhotos_session());
		values.put(COL_ALL_TYPES_NAV,session.getAll_types_nav()[0]); 
		return (int) bdd.update(TABLE_SESSIONS, values, COL_ID_SESSION + " = '" +  id+"'", null);
	}
	
	public Cursor getIdSession(int id){	
		Cursor result=  bdd.query(TABLE_SESSIONS, new String[] {COL_ID}, COL_ID + " LIKE \"" + id +"\"", null, null, null, null);
		return result;
	}
	
	public Cursor GetAllSessions() {
		Cursor result=bdd.rawQuery("SELECT * FROM "+ TABLE_SESSIONS, null);
		return result;
	}
	
	
	public Cursor GetAllSessions_date_spot() {
		Cursor result = bdd.rawQuery("SELECT "+ COL_DATE +","+ COL_SPOT +" FROM "+ TABLE_SESSIONS +","+ TABLE_SPOTS+" where "+COL_ID_SPOT+" = "+TABLE_SPOTS+"."+COL_ID, null);;
		return result;
	}
	
	public int updateParameter(String parameter , Integer srvtime){
		ContentValues values = new ContentValues();
		values.put(COL_PARAMETER, "srvtime");
		values.put(COL_VALUE, srvtime);
		return bdd.update(TABLE_PARAMETER, values, COL_PARAMETER + " = '" +  parameter+"'", null);
	}		

	
	public String getParameter(String parameter){
		Cursor c = bdd.query(TABLE_PARAMETER, new String[] {COL_PARAMETER , COL_VALUE}, COL_PARAMETER + " LIKE \"" + parameter +"\"", null, null, null, null);
		c.moveToFirst();
		return c.getString(NUM_COL_VALUE);
	}
	
	public int insertSail(Sail sail){
		ContentValues values = new ContentValues();
		values.put(COL_ID_SAIL,sail.getId_voile()); 
		values.put(COL_MARQUE_SAIL,sail.getMarque());
		values.put(COL_MODELE_SAIL,sail.getModele());
		values.put(COL_SURFACE,sail.getSurface());
		values.put(COL_IMAGE_SAIL,sail.getImage());
		values.put(COL_ANNEE_SAIL,sail.getAnnee());
		values.put(COL_ACQUISITION_SAIL,sail.getAcquisition());
		values.put(COL_COMMENT_SAIL,sail.getCommentaire());	
		return (int) bdd.insert(TABLE_SAILS, null, values);
	}
	
	public int updateSail(Integer id,Sail sail){
		ContentValues values = new ContentValues();
		values.put(COL_ID_SAIL,sail.getId_voile()); 
		values.put(COL_MARQUE_SAIL,sail.getMarque());
		values.put(COL_MODELE_SAIL,sail.getModele());
		values.put(COL_SURFACE,sail.getSurface());
		values.put(COL_IMAGE_SAIL,sail.getImage());
		values.put(COL_ANNEE_SAIL,sail.getAnnee());
		values.put(COL_ACQUISITION_SAIL,sail.getAcquisition());
		values.put(COL_COMMENT_SAIL,sail.getCommentaire());	
		return (int) bdd.update(TABLE_SAILS, values, COL_ID_SAIL + " = '" +  id+"'", null);
	}
	
	public Cursor getIdSail(int id){	
		Cursor result=  bdd.query(TABLE_SAILS, new String[] {COL_ID_SAIL}, COL_ID_SAIL + " LIKE \"" + id +"\"", null, null, null, null);
		return result;
	}
	
	public Cursor GetAllSails() {
		Cursor result=bdd.rawQuery("SELECT * FROM "+ TABLE_SAILS, null);
		return result;
	}

	public int insertBoard(Board board){
		ContentValues values = new ContentValues();
		values.put(COL_ID_BOARD,board.getId_planche()); 
		values.put(COL_MARQUE_BOARD,board.getMarque());
		values.put(COL_MODELE_BOARD,board.getModele());
		values.put(COL_VOLUME,board.getVolume());
		values.put(COL_IMAGE_BOARD,board.getImage());
		values.put(COL_ANNEE_BOARD,board.getAnnee());
		values.put(COL_ACQUISITION_BOARD,board.getAcquisition());
		values.put(COL_COMMENT_BOARD,board.getCommentaire());	
		return (int) bdd.insert(TABLE_BOARD, null, values);
	}
	
	public int updateBoard(Integer id, Board board){
		ContentValues values = new ContentValues();
		values.put(COL_ID_BOARD,board.getId_planche()); 
		values.put(COL_MARQUE_BOARD,board.getMarque());
		values.put(COL_MODELE_BOARD,board.getModele());
		values.put(COL_VOLUME,board.getVolume());
		values.put(COL_IMAGE_BOARD,board.getImage());
		values.put(COL_ANNEE_BOARD,board.getAnnee());
		values.put(COL_ACQUISITION_BOARD,board.getAcquisition());
		values.put(COL_COMMENT_BOARD,board.getCommentaire());	
		return (int) bdd.update(TABLE_BOARD, values, COL_ID_BOARD + " = '" +  id+"'", null);
	}
	
	public Cursor getIdBoard(int id){	
		Cursor result=  bdd.query(TABLE_BOARD, new String[] {COL_ID_BOARD}, COL_ID_BOARD + " LIKE \"" + id +"\"", null, null, null, null);
		return result;
	}
		
	public Cursor GetAllBoard() {
		Cursor result=bdd.rawQuery("SELECT * FROM "+ TABLE_BOARD, null);
		return result;
	}
}