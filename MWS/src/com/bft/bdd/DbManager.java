package com.bft.bdd;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.bft.bo.Board;
import com.bft.bo.Data;
import com.bft.bo.Mast;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spin;
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
	private static final String COL_LATITUDE = "latitude";
	private static final int NUM_COL_LATITUDE = 3;
	private static final String COL_LONGITUDE = "longitude";
	private static final int NUM_COL_LONGITUDE = 4;
	private static final String COL_RATE = "notenavigation";
	private static final int NUM_COL_RATE = 5;
	private static final String COL_PICTURE_URI = "photosSpot";
	private static final int NUM_COL_PICTURE_URI = 6;
	private static final String COL_REGION = "region";
	private static final int NUM_COL_REGION = 7;
	private static final String COL_SPOT = "spot";
	private static final int NUM_COL_SPOT = 8;
	private static final String COL_VILLE ="ville";
	private static final int NUM_COL_VILLE = 9;

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
	
	private static final String TABLE_SPIN = "table_spins";

	private static final String COL_ID_SPIN = "_id";
	private static final String COL_MARQUE_SPIN = "marque";
	private static final String COL_MODELE_SPIN = "modele";
	private static final String COL_SIZE_SPIN = "taille";
	private static final String COL_IMAGE_SPIN = "image";
	private static final String COL_ANNEE_SPIN = "annee";
	private static final String COL_PROGRAM_SPIN = "programme";
	private static final String COL_ACQUISITION_SPIN = "acquisition";
	private static final String COL_COMMENT_SPIN = "commentaire";
	
	private static final String TABLE_MAST = "table_masts";

	private static final String COL_ID_MAST = "_id";
	private static final String COL_MARQUE_MAST = "marque";
	private static final String COL_MODELE_MAST = "modele";
	private static final String COL_SIZE_MAST = "taille";
	private static final String COL_IMAGE_MAST = "image";
	private static final String COL_ANNEE_MAST = "annee";
	private static final String COL_PROGRAM_MAST = "programme";
	private static final String COL_ACQUISITION_MAST = "acquisition";
	private static final String COL_COMMENT_MAST = "commentaire";
	
	private static final String TABLE_ORIENTATION = "table_orientation";

	private static final String COL_ID_ORIENT = "_id";
	private static final String COL_LABEL = "libelle";
	private static final String COL_LABEL_SHORT = "libelle_court";
	
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
	
	public Spot getSpotById(int id){		
		Cursor c =  bdd.query(TABLE_SPOTS, new String[] {COL_ID,COL_ID_PAYS,COL_COMMENT,COL_LATITUDE,COL_LONGITUDE,COL_RATE,COL_PICTURE_URI,COL_REGION,COL_SPOT,COL_VILLE}, COL_ID_SESSION + " LIKE \"" + id +"\"", null, null, null, null);
		return cursorToSpot(c);
	}
	
	public Spot getSpotBySpot(String spot) {
		Cursor c =  bdd.query(TABLE_SPOTS, new String[] {COL_ID,COL_ID_PAYS,COL_COMMENT,COL_LATITUDE,COL_LONGITUDE,COL_RATE,COL_PICTURE_URI,COL_REGION,COL_SPOT,COL_VILLE}, COL_SPOT + " LIKE \"" + spot +"\"", null, null, null, null);
		return cursorToSpot(c);
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
	//	values.put(COL_PHOTOS_SESSION,session.getPhotos_session());
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
	//	values.put(COL_PHOTOS_SESSION,session.getPhotos_session());
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
		Cursor result = bdd.rawQuery("SELECT "+TABLE_SESSIONS+"."+COL_ID_SESSION+","+ COL_DATE +","+ COL_SPOT +" FROM "+ TABLE_SESSIONS +","+ TABLE_SPOTS+" where "+COL_ID_SPOT+" = "+TABLE_SPOTS+"."+COL_ID, null);;
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
	
	public Cursor getIdMast(Integer id_mat) {
		Cursor result=  bdd.query(TABLE_MAST, new String[] {COL_ID_MAST}, COL_ID_MAST + " LIKE \"" + id_mat +"\"", null, null, null, null);
		return result;
	}
	
	public int insertMast(Mast mat) {
		ContentValues values = new ContentValues();
		values.put(COL_ID_MAST,mat.getId_mat()); 
		values.put(COL_MARQUE_MAST,mat.getMarque());
		values.put(COL_MODELE_MAST,mat.getModele());
		values.put(COL_SIZE_MAST,mat.getTaille());
		values.put(COL_IMAGE_MAST,mat.getImage());
		values.put(COL_ANNEE_MAST,mat.getAnnee());
		values.put(COL_ACQUISITION_MAST,mat.getAcquisition());
		values.put(COL_COMMENT_MAST,mat.getCommentaire());	
		return (int) bdd.insert(TABLE_MAST, null, values);
	}

	public int updateMast(Integer id_mat, Mast mat) {
		ContentValues values = new ContentValues();
		values.put(COL_ID_MAST,mat.getId_mat()); 
		values.put(COL_MARQUE_MAST,mat.getMarque());
		values.put(COL_MODELE_MAST,mat.getModele());
		values.put(COL_SIZE_MAST,mat.getTaille());
		values.put(COL_IMAGE_MAST,mat.getImage());
		values.put(COL_ANNEE_MAST,mat.getAnnee());
		values.put(COL_ACQUISITION_MAST,mat.getAcquisition());
		values.put(COL_COMMENT_MAST,mat.getCommentaire());
		return (int) bdd.update(TABLE_MAST, values, COL_ID_MAST + " = '" +  id_mat +"'", null);		
	}
	
	public Cursor getAllMasts() {
		Cursor result=bdd.rawQuery("SELECT * FROM "+ TABLE_MAST, null);
		return result;
	}
	
	public Cursor getIdSpin(Integer id_spin) {
		Cursor result=  bdd.query(TABLE_SPIN, new String[] {COL_ID_SPIN}, COL_ID_SPIN + " LIKE \"" + id_spin +"\"", null, null, null, null);
		return result;
	}
	
	public int insertSpin(Spin spin) {
		ContentValues values = new ContentValues();
		values.put(COL_ID_SPIN,spin.getId_spin()); 
		values.put(COL_MARQUE_SPIN,spin.getMarque());
		values.put(COL_MODELE_SPIN,spin.getModele());
		values.put(COL_SIZE_SPIN,spin.getTaille());
		values.put(COL_IMAGE_SPIN,spin.getImage());
		values.put(COL_ANNEE_SPIN,spin.getAnnee());
		values.put(COL_ACQUISITION_SPIN,spin.getAcquisition());
		values.put(COL_COMMENT_SPIN,spin.getCommentaire());	
		return (int) bdd.insert(TABLE_SPIN, null, values);
	}

	public int updateSpin(Integer id_spin, Spin spin) {
		ContentValues values = new ContentValues();
		values.put(COL_ID_SPIN,spin.getId_spin()); 
		values.put(COL_MARQUE_SPIN,spin.getMarque());
		values.put(COL_MODELE_SPIN,spin.getModele());
		values.put(COL_SIZE_SPIN,spin.getTaille());
		values.put(COL_IMAGE_SPIN,spin.getImage());
		values.put(COL_ANNEE_SPIN,spin.getAnnee());
		values.put(COL_ACQUISITION_SPIN,spin.getAcquisition());
		values.put(COL_COMMENT_SPIN,spin.getCommentaire());
		return (int) bdd.update(TABLE_SPIN, values, COL_ID_SPIN + " = '" +  id_spin +"'", null);		
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
	
	public Cursor getAllOrient() {
		Cursor result=bdd.rawQuery("SELECT * FROM "+ TABLE_ORIENTATION, null);
		return result;
	}

	public Session getSessionWithId(int id) {
		Cursor c = bdd.query(TABLE_SESSIONS, new String[] {
				COL_ID_SESSION
				,COL_DATE
				,COL_ID_SPOT 
				,COL_VENTMIN 
				,COL_VENTMAX 
				,COL_ID_ORIENTATION 
				,COL_ID_PLANCHE 
				,COL_ID_VOILE
				,COL_ID_AILERON
				,COL_ID_MAT
				,COL_ID_DIVERS 
				,COL_NOTE 
				,COL_COMMENTAIRE 
				,COL_VAGUE 
				,COL_DUREE 
				,COL_VMAX 
				,COL_DISTANCE 
				,COL_PHOTOS_SESSION
				,COL_ALL_TYPES_NAV 
		}, COL_ID + " LIKE \"" + String.valueOf(id) +"\"", null, null, null, null);

		return cursorToSession(c);
	}

	public Board getBoardById(Integer[] id_planche) {
		Cursor c = bdd.query(TABLE_BOARD, new String[]{COL_ID_BOARD,COL_MARQUE_BOARD,COL_MODELE_BOARD,COL_VOLUME,COL_IMAGE_BOARD,COL_ANNEE_BOARD,COL_PROGRAM_BOARD,COL_ACQUISITION_BOARD,COL_COMMENT_BOARD}, COL_ID_BOARD + " LIKE \"" + String.valueOf(id_planche) +"\"", null, null, null, null);
		return cursorToBoard(c);
	}
	
	public Sail getsailById(Integer[] id_voile) {
		Cursor c = bdd.query(TABLE_SAILS, new String[]{COL_ID_SAIL,COL_MARQUE_SAIL,COL_MODELE_SAIL,COL_SURFACE,COL_IMAGE_SAIL,COL_ANNEE_SAIL,COL_ACQUISITION_SAIL,COL_COMMENT_SAIL}, COL_ID_SAIL + " LIKE \"" + String.valueOf(id_voile) +"\"", null, null, null, null);
		return cursorToSail(c);
	}
	

public Mast getMatById(Integer[] id_mat) {
	Cursor c = bdd.query(TABLE_MAST, new String[]{COL_ID_MAST,COL_MARQUE_MAST,COL_MODELE_MAST,COL_SIZE_MAST,COL_IMAGE_MAST,COL_ANNEE_MAST,COL_PROGRAM_MAST,COL_ACQUISITION_MAST,COL_COMMENT_MAST}, COL_ID_MAST + " LIKE \"" + String.valueOf(id_mat) +"\"", null, null, null, null);
	return cursorToMast(c);
}
	
private Mast cursorToMast(Cursor c) {
	if (c.getCount() == 0)
		return null;
	c.moveToFirst();
	Mast mast = new Mast();
	mast.setId_mat(c.getInt(0));
	mast.setMarque(c.getString(1));
	mast.setModele(c.getString(2));
	mast.setTaille(c.getInt(3));
	mast.setImage(c.getString(4));
	mast.setAnnee(c.getInt(5));
	//manque programme
	mast.setAcquisition(c.getInt(7));
	mast.setCommentaire(c.getString(8));
	return mast;
}

private Sail cursorToSail(Cursor c) {
	if (c.getCount() == 0)
		return null;
	c.moveToFirst();
	Sail sail = new Sail();
	sail.setId_voile(c.getInt(0));
	sail.setMarque(c.getString(1));
	sail.setModele(c.getString(2));
	sail.setSurface(c.getFloat(3));
	sail.setImage(c.getString(4));
	sail.setAnnee(c.getInt(5));
	sail.setAcquisition(c.getInt(6));
	sail.setCommentaire(c.getString(7));
	c.close();
	return sail;
	}

private Board cursorToBoard(Cursor c) {
	if (c.getCount() == 0)
		return null;
	c.moveToFirst();
	Board board = new Board();
	board.setId_planche(c.getInt(0));
	board.setMarque(c.getString(1));
	board.setModele(c.getString(2));
	board.setVolume(c.getInt(3));
	board.setImage(c.getString(4));
	board.setAnnee(c.getInt(5));
	//board.setProgramme(c.getString(6));
	board.setAcquisition(c.getInt(7));
	board.setCommentaire(c.getString(8));
	c.close();
	return board;
	}

private Session cursorToSession(Cursor c){
	if (c.getCount() == 0)
		return null;
	c.moveToFirst();
	Session session = new Session();
	Integer[] planche = {c.getInt(6)};
	Integer[] voile = {c.getInt(7)};
	Integer[] aileron = {c.getInt(8)};
	Integer[] mat = {c.getInt(9)};
	Integer[] divers = {c.getInt(10)};
	Integer[] all_types_nav = {c.getInt(18)};
	
	session.setId_session(c.getInt(0));
	session.setDate(c.getLong(1));
	session.setId_spot(c.getInt(2));
	session.setVentMin(c.getInt(3));
	session.setVentMax(c.getInt(4));
	session.setId_orientation(c.getInt(5));
	session.setId_planche(planche);
	session.setId_voile(voile);
	session.setId_aileron(aileron);
	session.setId_mat(mat);
	session.setId_divers(divers);
	session.setNote(c.getInt(11));
	session.setCommentaire(c.getString(12));
	session.setVague(c.getInt(13));
	session.setDuree(c.getInt(14));
	session.setVmax(c.getInt(15));
	session.setDistance(c.getInt(16));
//	session.setPhotos_session(c.getString(17));
	session.setAll_types_nav(all_types_nav);
	c.close();
	return session;
}
private Spot cursorToSpot(Cursor c) {
	if (c.getCount() == 0)
		return null;
	c.moveToFirst();
	Spot spot = new Spot();
	spot.setId_spot(c.getInt(0));
	spot.setId_pays(c.getInt(1));
	//spot.s => gérer commentaire spot
	spot.setLatitude(c.getFloat(2));
	spot.setLongitude(c.getFloat(3));
	//spot.getNoteNavigation(c))
	//spot.
	spot.setRegion(c.getString(7));
	spot.setSpot(c.getString(8));
	spot.setVille(c.getString(9));
	
	return spot;
}

}