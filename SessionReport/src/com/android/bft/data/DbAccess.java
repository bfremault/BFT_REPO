package com.android.bft.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAccess extends SQLiteOpenHelper {

	private static final String TABLE_SESSION = "table_session";
	private static final String TABLE_GALLERY = "table_gallery";
	private static final String TABLE_EQUIPMENT = "table_equipment";


	private static final String COL_ID = "_id";
	private static final String COL_ID_SESSION = "id_session";
	private static final String COL_ID_EQUIPMENT = "id_equipment";
	private static final String COL_LOCATION = "location";
	private static final String COL_SAIL = "sail";
	private static final String COL_BOARD = "board";
	private static final String COL_RATE = "rate";
	private static final String COL_COMMENT = "comment";
	private static final String COL_PICTURE_URI = "picture_uri";
	private static final String COL_DATE = "date";
	private static final String COL_WDDIR = "wind_direction";
	private static final String COL_WDPW = "wind_power";
	private static final String COL_EQUIPMENT = "equipment_label";
	private static final String COL_VOLUME = "equipment_volume";
	private static final String COL_EQUIPMENT_TYPE = "equipment_type";
	private static final String COL_ARCHIVE = "equipment_archive";

	
	
	private static final String CREATE_TABLE_SESSION = "CREATE TABLE " + TABLE_SESSION + "(" + 
	        COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LOCATION +
	         " TEXT, "+ COL_RATE +" TEXT, "+ COL_DATE +" DATE,"+ COL_SAIL +" INTEGER, "+ COL_BOARD +" INTEGER, "+
	        COL_COMMENT +" TEXT,"+ COL_WDDIR +" TEXT,"+COL_WDPW+" TEXT, " +
	        " FOREIGN KEY (" + COL_SAIL + ") REFERENCES table_equipment(_id), " + 
	        " FOREIGN KEY (" + COL_BOARD + ") REFERENCES table_equipment(_id));"; 
	
	private static final String CREATE_TABLE_GALLERY = "CREATE TABLE " + TABLE_GALLERY + "(" + 
    COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PICTURE_URI +
     " TEXT, "+ COL_ID_SESSION +" INTEGER, FOREIGN KEY ("+ COL_ID_SESSION +") REFERENCES table_session(_id));"; 
	
	private static final String CREATE_TABLE_EQUIPMENT = "CREATE TABLE " + TABLE_EQUIPMENT + "(" + 
		    COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_EQUIPMENT +
		     " TEXT, "+ COL_VOLUME + " FLOAT, " + COL_EQUIPMENT_TYPE +" INTEGER, "+ COL_ARCHIVE + " BOOLEAN);" ; 
			
	public DbAccess(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_SESSION);
		db.execSQL(CREATE_TABLE_GALLERY);
		db.execSQL(CREATE_TABLE_EQUIPMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE " + TABLE_SESSION + ";");
		db.execSQL("DROP TABLE " + TABLE_GALLERY + ";");
		db.execSQL("DROP TABLE " + TABLE_EQUIPMENT + ";");
		onCreate(db);
	}

}
