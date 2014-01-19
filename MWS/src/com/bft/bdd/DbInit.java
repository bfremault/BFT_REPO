package com.bft.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbInit extends SQLiteOpenHelper {

	private static final String TABLE_SPOTS = "table_spots";

	private static final String COL_ID = "_id";
	private static final String COL_ID_PAYS = "id_pays";
	private static final String COL_COMMENT = "commentaire";
	private static final String COL_LATITUDE = "latitude";
	private static final String COL_LONGITUDE = "longitude";
	private static final String COL_RATE = "notenavigation";
	private static final String COL_PICTURE_URI = "photosSpot";
	private static final String COL_REGION = "region";
	private static final String COL_SPOT = "spot";
	private static final String COL_VILLE ="ville";

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
	private static final String COL_VALUE = "value";
	
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
	
	
	private static final String CREATE_TABLE_SPOTS = "CREATE TABLE " + TABLE_SPOTS + "(" + 
	        COL_ID +" INTEGER PRIMARY KEY, " + 
	        COL_ID_PAYS + " INTEGER, "+ 
	        COL_COMMENT +" TEXT, "+
	        COL_LATITUDE +" TEXT, "+ 
	        COL_LONGITUDE +" TEXT, "+
	        COL_RATE +" TEXT,"+ 
	        COL_PICTURE_URI +" TEXT,"+
	        COL_REGION +" TEXT, " +
	        COL_SPOT +" TEXT, " +
	        COL_VILLE +" TEXT );";
	      //  " FOREIGN KEY (" + COL_SAIL + ") REFERENCES table_equipment(_id), " + 
	      //  " FOREIGN KEY (" + COL_BOARD + ") REFERENCES table_equipment(_id));"; 
	
	private static final String CREATE_TABLE_SESSIONS = "CREATE TABLE " + TABLE_SESSIONS + "(" + 
			COL_ID_SESSION  +" INTEGER PRIMARY KEY, " + 
			COL_DATE + " LONG, "+  
			COL_ID_SPOT + " INTEGER, "+ 
			COL_VENTMIN  + " INTEGER, "+ 
			COL_VENTMAX  + " INTEGER, "+ 
			COL_ID_ORIENTATION  + " INTEGER, "+ 
			COL_ID_PLANCHE  + " INTEGER, "+ 
			COL_ID_VOILE  + " INTEGER, "+ 
			COL_ID_AILERON  + " INTEGER, "+ 
			COL_ID_MAT + " INTEGER, "+ 
			COL_ID_DIVERS  + " INTEGER, "+ 
			COL_NOTE + " INTEGER, "+ 
			COL_COMMENTAIRE  + " TEXT, "+ 
			COL_VAGUE  + " INTEGER, "+ 
			COL_DUREE  + " INTEGER, "+ 
			COL_VMAX  + " INTEGER, "+ 
			COL_DISTANCE  + " INTEGER, "+ 
			COL_PHOTOS_SESSION  + " TEXT, "+ 
			COL_ALL_TYPES_NAV  +" INTEGER, "+
			" FOREIGN KEY (" + COL_ID_SPOT + ") REFERENCES table_spots(_id));";
	
	private static final String CREATE_TABLE_PARAMETER = "CREATE TABLE " + TABLE_PARAMETER + "(" + 
    COL_PARAMETER +" TEXT, " + COL_VALUE + " TEXT);";
	
	private static final String INIT_TABLE_PARAMETER = "INSERT INTO " + TABLE_PARAMETER + "(" + 
		    COL_PARAMETER + "," + COL_VALUE +") VALUES ('srvtime',0)"; 
	
	private static final String CREATE_TABLE_SAILS = "CREATE TABLE " + TABLE_SAILS + "(" + 
			COL_ID_SAIL  +" INTEGER PRIMARY KEY, " + 
			COL_MARQUE_SAIL  +" TEXT, " + 
			COL_MODELE_SAIL  +" TEXT, " + 
			COL_SURFACE  +" FLOAT, " + 
			COL_IMAGE_SAIL  +" TEXT, " + 
			COL_ANNEE_SAIL  +" INTEGER, " + 
			COL_ACQUISITION_SAIL  +" INTEGER, " + 
			COL_COMMENT_SAIL  +" TEXT); "; 
	
	private static final String CREATE_TABLE_BOARD = "CREATE TABLE " + TABLE_BOARD + "(" + 
			COL_ID_BOARD  +" INTEGER PRIMARY KEY, " + 
			COL_MARQUE_BOARD  +" TEXT, " + 
			COL_MODELE_BOARD  +" TEXT, " + 
			COL_VOLUME +" INTEGER, " + 
			COL_IMAGE_BOARD  +" TEXT, " + 
			COL_ANNEE_BOARD  +" INTEGER, " + 
			COL_PROGRAM_BOARD  +" INTEGER, " + 
			COL_ACQUISITION_BOARD  +" INTEGER, " + 
			COL_COMMENT_BOARD +" TEXT); "; 
		
	private static final String CREATE_TABLE_SPIN = "CREATE TABLE " + TABLE_SPIN + "(" + 
			COL_ID_SPIN  +" INTEGER PRIMARY KEY, " + 
			COL_MARQUE_SPIN  +" TEXT, " + 
			COL_MODELE_SPIN  +" TEXT, " + 
			COL_SIZE_SPIN +" FLOAT, " + 
			COL_IMAGE_SPIN  +" TEXT, " + 
			COL_ANNEE_SPIN  +" INTEGER, " + 
			COL_PROGRAM_SPIN  +" INTEGER, " + 
			COL_ACQUISITION_SPIN  +" INTEGER, " + 
			COL_COMMENT_SPIN +" TEXT); "; 
	
	private static final String CREATE_TABLE_MAST = "CREATE TABLE " + TABLE_MAST + "(" + 
			COL_ID_MAST  +" INTEGER PRIMARY KEY, " + 
			COL_MARQUE_MAST  +" TEXT, " + 
			COL_MODELE_MAST  +" TEXT, " + 
			COL_SIZE_MAST +" FLOAT, " + 
			COL_IMAGE_MAST  +" TEXT, " + 
			COL_ANNEE_MAST  +" INTEGER, " + 
			COL_PROGRAM_MAST  +" INTEGER, " + 
			COL_ACQUISITION_MAST  +" INTEGER, " + 
			COL_COMMENT_MAST +" TEXT); "; 	
	

	private static final String CREATE_TABLE_ORIENTATION = "CREATE TABLE " + TABLE_ORIENTATION + "(" + 
			COL_ID_ORIENT  +" INTEGER PRIMARY KEY, " + 
			COL_LABEL  +" TEXT, " + 
			COL_LABEL_SHORT  +" TEXT); ";
	
	private static final String INIT_TABLE_ORIENTATION = "INSERT INTO " + TABLE_ORIENTATION + "(" + 
			COL_ID_ORIENT + "," + COL_LABEL +" , " + COL_LABEL_SHORT +") VALUES " + 
			"(0,'North','N'),"+
			"(1,'North/North-East','NNE'),"+		
			"(2,'North-East','NE'),"+
			"(3,'East/North-East','ENE'),"+
			"(4,'East','E'),"+
			"(5,'East/South-East','ESE'),"+
			"(6,'South-East','SE'),"+
			"(7,'South/South-East','SSE'),"+
			"(8,'South','S'),"+
			"(9,'South/South-West','SSW'),"+
			"(10,'South-West','SW'),"+
			"(11,'West/South-West','WSW'),"+
			"(12,'West','W'),"+
			"(13,'West/North-West','WNW'),"+
			"(14,'North-West','NW'),"+
			"(15,'North/North-West','NNW')"; 
						
	public DbInit(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SPOTS);
		db.execSQL(CREATE_TABLE_SESSIONS);
		db.execSQL(CREATE_TABLE_PARAMETER);
		db.execSQL(INIT_TABLE_PARAMETER);
		db.execSQL(CREATE_TABLE_SAILS);
		db.execSQL(CREATE_TABLE_BOARD);
		db.execSQL(CREATE_TABLE_SPIN);
		db.execSQL(CREATE_TABLE_MAST);
		db.execSQL(CREATE_TABLE_ORIENTATION);
		db.execSQL(INIT_TABLE_ORIENTATION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLE_SPOTS + ";");
		db.execSQL("DROP TABLE " + TABLE_SESSIONS + ";");
		db.execSQL("DROP TABLE " + TABLE_PARAMETER + ";");
		db.execSQL("DROP TABLE " + TABLE_SAILS + ";");
		db.execSQL("DROP TABLE " + TABLE_BOARD + ";");
		db.execSQL("DROP TABLE " + TABLE_SPIN + ";");
		db.execSQL("DROP TABLE " + TABLE_MAST + ";");
		db.execSQL("DROP TABLE " + TABLE_ORIENTATION + ";");
		onCreate(db);
	}

}
