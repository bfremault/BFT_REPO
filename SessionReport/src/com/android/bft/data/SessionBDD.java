package com.android.bft.data;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.android.bft.media.PictureSession;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SessionBDD {

	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "sessions.db";
	
	private static final String TABLE_SESSION = "table_session";
	private static final String COL_ID = "_id";
	private static final int NUM_COL_ID = 0;
	private static final String COL_LOCATION = "location";
	private static final int NUM_COL_LOCATION = 1;
	private static final String COL_RATE = "rate";
	private static final int NUM_COL_RATE = 2;
	private static final String COL_DATE = "date";
	private static final int NUM_COL_DATE = 3;
	private static final String COL_SAIL = "sail";
	private static final int NUM_COL_SAIL = 4;
	private static final String COL_BOARD = "board";
	private static final int NUM_COL_BOARD = 5;
	private static final String COL_COMMENT = "comment";
	private static final int NUM_COL_COMMENT = 6;
	private static final String COL_WDDIR = "wind_direction";
	private static final int NUM_COL_WDDIR = 7;
	private static final String COL_WDPW = "wind_power";
	private static final int NUM_COL_WDPW = 8;
	
	private static final String TABLE_GALLERY = "table_gallery";
	private static final String COL_GALLERY_ID = "_id";
	private static final int NUM_COL_GALLERY_ID = 0;
	private static final String COL_PICT_URI = "picture_uri";
	private static final int NUM_COL_PICT_URI = 1;
	private static final String COL_ID_SESSION = "id_session";
	private static final int NUM_COL_ID_SESSION = 2;
	
	private static final String TABLE_EQUIPMENT = "table_equipment";
	private static final String COL_EQUIPMENT_ID = "_id";
	private static final int NUM_COL_EQUIPMENT_ID = 0;
	private static final String COL_EQUIPMENT = "equipment_label";
	private static final int NUM_COL_EQUIPMENT = 1;
	private static final String COL_EQUIPMENT_VOLUME = "equipment_volume";
	private static final int NUM_COL_EQUIPMENT_VOLUME = 2;
	private static final String COL_EQUIPMENT_TYPE = "equipment_type";
	private static final int NUM_COL_EQUIPMENT_TYPE = 3;
	private static final String COL_EQUIPMENT_ARCHIVE = "equipment_archive";
	private static final int NUM_COL_EQUIPMENT_ARCHIVE = 4;	
	
	private SQLiteDatabase bdd;
	 
	private DbAccess DbAccess;
	
	public SessionBDD(Context context){
		//On créer la BDD et sa table
		DbAccess = new DbAccess(context, NOM_BDD, null, VERSION_BDD);
	}
	
	public void open(){
		//on ouvre la BDD en écriture
		bdd = DbAccess.getWritableDatabase();
	//	DbAccess.onUpgrade(bdd, 0, 1);
	}
 
	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	public int insertSession(Session session){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_LOCATION, session.getLocation());
		values.put(COL_RATE, session.getRate());
		//values.put(COL_DATE, dateFormat.format(System.currentTimeMillis()));
		values.put(COL_DATE, dateFormat.format(session.getDate()));
		values.put(COL_SAIL, session.getSail());
		values.put(COL_BOARD, session.getBoard());
		values.put(COL_COMMENT, session.getComment());
		values.put(COL_WDDIR, session.getWindDirection());
		values.put(COL_WDPW, session.getWindPower());

		//on insère l'objet dans la BDD via le ContentValues
		return (int) bdd.insert(TABLE_SESSION, null, values);
	}

	public int updateSession(int id, Session session){
		//La mise à jour d'une session dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simple préciser quelle session on doit mettre à jour grâce à l'ID
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		ContentValues values = new ContentValues();
		values.put(COL_LOCATION, session.getLocation());
		values.put(COL_RATE, session.getRate());
		//values.put(COL_DATE, dateFormat.format(System.currentTimeMillis()));
		//values.put(COL_DATE, session.getDate().toString());
		values.put(COL_SAIL, session.getSail());
		values.put(COL_BOARD, session.getBoard());
		values.put(COL_COMMENT, session.getComment());
		values.put(COL_WDDIR, session.getWindDirection());
		values.put(COL_WDPW, session.getWindPower());
		return bdd.update(TABLE_SESSION, values, COL_ID + " = " +id, null);
	}
 
	public int removeSessionWithID(int id){
		//Suppression d'une session de la BDD grâce à l'ID
		return bdd.delete(TABLE_SESSION, COL_ID + " = " +id, null);
	}
 
	public Session getSessionWithId(int id){
		//Récupère dans un Cursor les valeur correspondant à une session contenu dans la BDD (ici on sélectionne la session grâce à son ID)
		Cursor c = bdd.query(TABLE_SESSION, new String[] {COL_ID, COL_LOCATION, COL_RATE, COL_DATE,COL_SAIL, COL_BOARD, COL_COMMENT, COL_WDDIR,COL_WDPW}, COL_ID + " LIKE \"" + String.valueOf(id) +"\"", null, null, null, null);
		return cursorToSession(c);
	}
	
	public Session getSessionWithLocation(String location){
		//Récupère dans un Cursor les valeur correspondant à une session contenu dans la BDD (ici on sélectionne la session grâce à sa location)
		Cursor c = bdd.query(TABLE_SESSION, new String[] {COL_ID, COL_LOCATION, COL_RATE, COL_DATE,COL_SAIL, COL_BOARD, COL_COMMENT, COL_WDDIR,COL_WDPW}, COL_LOCATION + " LIKE \"" + location +"\"", null, null, null, null);
		return cursorToSession(c);
	}

	public Cursor GetAllSessionName() {
		Cursor result=bdd.rawQuery("SELECT * FROM "+ TABLE_SESSION, null);
		return result;
	}
	
	//Cette méthode permet de convertir un cursor en une session
	private Session cursorToSession(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé une session
		Session session = new Session();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		session.setId(c.getInt(NUM_COL_ID));
		session.setLocation(c.getString(NUM_COL_LOCATION));
		session.setRate(c.getFloat(NUM_COL_RATE));
		session.setDate(stringToDate(c.getString(NUM_COL_DATE)));
		session.setSail(c.getInt(NUM_COL_SAIL));
		session.setBoard(c.getInt(NUM_COL_BOARD));
		session.setComment(c.getString(NUM_COL_COMMENT));
		session.setWindDirection(c.getString(NUM_COL_WDDIR));
		session.setWindPower(c.getString(NUM_COL_WDPW));
		//On ferme le cursor
		c.close();
 
		//On retourne la session
		return session;
	}
	
	public long insertPictureSession(PictureSession picturesession){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_PICT_URI, picturesession.getPicture_uri());
		values.put(COL_ID_SESSION, picturesession.getId_session());
		//on insère l'objet dans la BDD via le ContentValues
		return  bdd.insert(TABLE_GALLERY, null, values);
	}
	
	public PictureSession getPictureSessionWithId(int id){
		Cursor c = bdd.query(TABLE_GALLERY, new String[] {COL_GALLERY_ID, COL_PICT_URI, COL_ID_SESSION}, COL_GALLERY_ID + " LIKE \"" + String.valueOf(id) +"\"", null, null, null, null);
		return cursorToPictureSession(c);
	}
	
	public Cursor GetAllPictureWithIdSession(int id) {
		Cursor c = bdd.query(TABLE_GALLERY, new String[] {COL_GALLERY_ID, COL_PICT_URI, COL_ID_SESSION}, COL_ID_SESSION + " LIKE \"" + String.valueOf(id) +"\"", null, null, null, null);
		return c;
	}
	public int removePictWithID(int id){
		//Suppression d'une session de la BDD grâce à l'ID
		return bdd.delete(TABLE_GALLERY, COL_GALLERY_ID + " = " +id, null);
	}
	
	private PictureSession cursorToPictureSession(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;
		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé une session
		PictureSession picturesession = new PictureSession();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		picturesession.setId(c.getInt(NUM_COL_GALLERY_ID));
		picturesession.setPicture_uri(c.getString(NUM_COL_PICT_URI));
		picturesession.setId_session(c.getInt(NUM_COL_ID_SESSION));
		//On ferme le cursor
	
		c.close();
 
		//On retourne la session
		return picturesession;
	}
	
	public int insertEquipment(Equipment equipment){
		ContentValues values = new ContentValues();
		values.put(COL_EQUIPMENT, equipment.getEquipment());
		values.put(COL_EQUIPMENT_VOLUME, equipment.getEquipment_volume());
		values.put(COL_EQUIPMENT_TYPE, equipment.getEquipment_type());
		values.put(COL_EQUIPMENT_ARCHIVE, 0);
		
		return (int) bdd.insert(TABLE_EQUIPMENT, null, values);
	
	};
	
/*	public int updateEquipment(int id,Equipment equipment){
		ContentValues values = new ContentValues();
		values.put(COL_EQUIPMENT, equipment.getEquipment());
		values.put(COL_EQUIPMENT_VOLUME, equipment.getEquipment_volume());
		values.put(COL_EQUIPMENT_TYPE, equipment.getEquipment_type());
		values.put(COL_EQUIPMENT_ARCHIVE, 0);
		
		return (int) bdd.update(TABLE_EQUIPMENT, values,COL_ID + " = " +id,null);
	
	};*/
	
	
	public int removeEquipmentWithId(int id){
		ContentValues values = new ContentValues();
		values.put(COL_EQUIPMENT_ARCHIVE, 1);
		return (int) bdd.update(TABLE_EQUIPMENT, values,COL_ID + " = " +id,null);
	
	};
	
	
	public Equipment getEquipmentWithId(int id){
		Cursor c = bdd.query(TABLE_EQUIPMENT, new String[]{COL_EQUIPMENT_ID ,  COL_EQUIPMENT , COL_EQUIPMENT_VOLUME ,COL_EQUIPMENT_TYPE, COL_EQUIPMENT_ARCHIVE}, COL_EQUIPMENT_ID + " LIKE \"" + String.valueOf(id) +"\"", null, null, null, null);
		return cursorToEquipment(c);
	};
	
	public Equipment getEquipmentWithLabel(String label){
		Cursor c = bdd.query(TABLE_EQUIPMENT, new String[]{COL_EQUIPMENT_ID ,  COL_EQUIPMENT , COL_EQUIPMENT_VOLUME ,COL_EQUIPMENT_TYPE, COL_EQUIPMENT_ARCHIVE}, COL_EQUIPMENT + " LIKE \"" + label +"\"", null, null, null, null);
		return cursorToEquipment(c);
	};
	
	public Cursor getAllActiveEquipment(int equipment_type){
		Cursor result=bdd.rawQuery("SELECT * FROM "+ TABLE_EQUIPMENT+ " WHERE " +COL_EQUIPMENT_TYPE+ "=" + String.valueOf(equipment_type) + " AND " + COL_EQUIPMENT_ARCHIVE+ "= 0" , null); // Pas de boolean dans sqlite => True = 1
		return result;
	};
	
	private Equipment cursorToEquipment(Cursor c) {
		if (c.getCount() == 0){
			return null;
			}
		else
		{
		Equipment equipment = new Equipment();
		c.moveToFirst();
		equipment.setId(c.getInt(NUM_COL_EQUIPMENT_ID));
		equipment.setEquipment(c.getString(NUM_COL_EQUIPMENT));
		equipment.setEquipment_volume(c.getFloat(NUM_COL_EQUIPMENT_VOLUME));
		equipment.setEquipment_type(c.getInt(NUM_COL_EQUIPMENT_TYPE));
		boolean b = convertIntToBoolean(c.getInt(NUM_COL_EQUIPMENT_ARCHIVE));
		equipment.setEquipment_archive(b);
		return equipment;
		}
	}
	
	private boolean convertIntToBoolean(int intValue)
		{
			return (intValue != 0);
		}


	public static Date stringToDate(String sDate){
		Date d = null;
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	//ParsePosition pos = new ParsePosition(0);
        	d = (Date) formatter.parse(sDate);
        } catch (RuntimeException e) {
        	//e.printStackTrace();
            Log.e("MyLog", "FileHandler exception \n", e);
        } catch (ParseException e) {
			//e.printStackTrace();
            Log.e("MyLog", "FileHandler exception \n", e);
		}
        return d;
	}
}