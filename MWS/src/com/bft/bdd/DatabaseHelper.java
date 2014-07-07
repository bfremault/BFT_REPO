package com.bft.bdd;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bft.bo.Board;
import com.bft.bo.Data;
import com.bft.bo.Mast;
import com.bft.bo.Orientations;
import com.bft.bo.Pays;
import com.bft.bo.ProgrammesLibelle;
import com.bft.bo.Sail;
import com.bft.bo.Session;
import com.bft.bo.Spin;
import com.bft.bo.Spot;
import com.bft.bo.Type_navigation;
import com.bft.bo.User;
import com.bft.mws.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

        // name of the database file for your application -- change to something appropriate for your app
        private static final String DATABASE_NAME = "MWS2.db";
        // any time you make changes to your database objects, you may have to increase the database version
        private static final int DATABASE_VERSION = 1;

        // the DAO object we use to access the Data table
        private Dao<Spot, Integer> spotDao = null;
        private RuntimeExceptionDao<Spot, Integer> spotRuntimeDao = null;
        private Dao<Type_navigation, Integer> type_navigationDao = null;
        private RuntimeExceptionDao<Type_navigation, Integer> type_navigationRuntimeDao = null;
        private Dao<Orientations, Integer> orientationsDao = null;
        private RuntimeExceptionDao<Orientations, Integer> orientationsRuntimeDao = null;
        private Dao<ProgrammesLibelle, Integer> programmesLibelleDao = null;
        private RuntimeExceptionDao<ProgrammesLibelle, Integer> programmesLibelleRuntimeDao = null;
        private Dao<Board, Integer> boardDao = null;
        private RuntimeExceptionDao<Board, Integer> boardRuntimeDao = null;
        private Dao<Sail, Integer> sailDao = null;
        private RuntimeExceptionDao<Sail, Integer> sailRuntimeDao = null;
        private Dao<Mast, Integer> mastDao = null;
        private RuntimeExceptionDao<Mast, Integer> mastRuntimeDao = null;
        private Dao<Spin, Integer> spinDao = null;
        private RuntimeExceptionDao<Spin, Integer> spinRuntimeDao = null;
        private Dao<Session, Integer> sessionDao = null;
        private RuntimeExceptionDao<Session, Integer> sessionRuntimeDao = null;
        private Dao<User, Integer> userDao = null;
        private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;
        private Dao<Pays, Integer> countryDao = null;
        private RuntimeExceptionDao<Pays, Integer> countryRuntimeDao = null;

        private static final String TABLE_TYPE_NAVIGATION = "type_navigation" ;
        private static final String COL_ID_TYPE_NAVIGATION = "type_nav";
        private static final String COL_LABEL_TYPE_NAVIGATION = "libelle";
        private static final String COL_CODE_TYPE_NAVIGATION = "code";
        private static final String COL_IMG_TYPE_NAVIGATION = "img";
        
    	private static final String INIT_TABLE_TYPE_NAVIGATION = "INSERT INTO " + TABLE_TYPE_NAVIGATION + "(" + 
    			COL_ID_TYPE_NAVIGATION + "," + COL_LABEL_TYPE_NAVIGATION +" , " + COL_CODE_TYPE_NAVIGATION +", " + COL_IMG_TYPE_NAVIGATION +") VALUES " + 
    			"(0,'- Undefined -','X','X'),"+
    			"(20,'windsurf','W','W'),"+		
    			"(21,'windsurf (waves)','W','V'),"+ 
    			"(22,'Windsurf(Slalom)','W','L'),"+
    			"(23,'Windsurf(Freeride)','W','G'),"+
		    	"(30,'Kitesurf','K','K'),"+
		    	"(31,'Kitesurf(Waves)','K','A'),"+
		    	"(32,'Kitesurf(Foil)','K','I'),"+
		    	"(40,'Paddle','P','P'),"+
		    	"(41,'Paddle(Waves)','P','C'),"+
		    	"(42,'Paddle(Ride)','P','D'),"+
		    	"(43,'Paddle(Sailing)','P','F'),"+
		    	"(44,'Paddle(Race)','P','H'),"+
		    	"(50,'Surf','S','S'),"+
		    	"(60,'Bodyboard','B','B'),"+
		    	"(70,'Speedsail','E','E'),"+
		    	"(80,'Snowboard','N','N'),"+
		    	"(90,'Snowkite','T','T'),"+
		    	"(100,'Mountainboard','M','M')";
    	
    	private static final String TABLE_ORIENTATION = "orientations";

    	private static final String COL_ID_ORIENT = "id_orientation";
    	private static final String COL_LABEL = "libelle";
    	private static final String COL_LABEL_SHORT_EN = "libelle_court_en";
    	private static final String COL_LABEL_SHORT_FR = "libelle_court_fr";
    	
    	
    	private static final String INIT_TABLE_ORIENTATION = "INSERT INTO " + TABLE_ORIENTATION + "(" + 
    			COL_ID_ORIENT + "," + COL_LABEL +" , " + COL_LABEL_SHORT_EN + " , "+ COL_LABEL_SHORT_FR +") VALUES " + 
    			"(1,'North','N','N'),"+
    			"(2,'North/North-East','NNE','NNE'),"+		
    			"(3,'North-East','NE','NE'),"+
    			"(4,'East/North-East','ENE','ENE'),"+
    			"(5,'East','E','E'),"+
    			"(6,'East/South-East','ESE','ESE'),"+
    			"(7,'South-East','SE','SE'),"+
    			"(8,'South/South-East','SSE','SSE'),"+
    			"(9,'South','S','S'),"+
    			"(10,'South/South-West','SSW','SSO'),"+
    			"(11,'South-West','SW','SO'),"+
    			"(12,'West/South-West','WSW','OSO'),"+
    			"(13,'West','W','O'),"+
    			"(14,'West/North-West','WNW','ONO'),"+
    			"(15,'North-West','NW','NO'),"+
    			"(16,'North/North-West','NNW','NNO')";
    	
    	private static final String TABLE_PROGRAMME = "programmes";
    	
     	private static final String COL_ID_PROGRAMME = "id";
    	private static final String COL_LABEL_PROGRAMME = "libelle";
    	    	
    	private static final String INIT_TABLE_PROGRAMME = "INSERT INTO " + TABLE_PROGRAMME + "(" + 
    			COL_ID_PROGRAMME + "," + COL_LABEL_PROGRAMME +") VALUES " + 
		    	"('-1', 'Doesn t work'),"+
		        "('0','---'),"+
		        "('1','Freeride'),"+
		        "('2','Wave'),"+
		        "('3','Slalom'),"+
		        "('4','Speed')";
    	
		    	
        public DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        }

        /**
         * This is called when the database is first created. Usually you should call createTable statements here to create
         * the tables that will store your data.
         */
        @Override
        public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
                try {
                        Log.i(DatabaseHelper.class.getName(), "onCreate");
                        TableUtils.createTable(connectionSource, Spot.class);
                        TableUtils.createTable(connectionSource, Type_navigation.class);
                        TableUtils.createTable(connectionSource, Orientations.class);
                        TableUtils.createTable(connectionSource, ProgrammesLibelle.class);
                        TableUtils.createTable(connectionSource, Board.class);
                        TableUtils.createTable(connectionSource, Sail.class);
                        TableUtils.createTable(connectionSource, Mast.class);
                        TableUtils.createTable(connectionSource, Spin.class);
                        TableUtils.createTable(connectionSource, Session.class);
                        TableUtils.createTable(connectionSource, User.class);
                        TableUtils.createTable(connectionSource, Pays.class);
                        db.execSQL(INIT_TABLE_TYPE_NAVIGATION);
                        db.execSQL(INIT_TABLE_ORIENTATION);
                        db.execSQL(INIT_TABLE_PROGRAMME);
                         
                } catch (SQLException e) {
                        Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
                        throw new RuntimeException(e);
                }
        }

        /**
         * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
         * the various data to match the new version number.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
                try {
                        Log.i(DatabaseHelper.class.getName(), "onUpgrade");
                        TableUtils.dropTable(connectionSource, Spot.class, true);
                        TableUtils.dropTable(connectionSource, Type_navigation.class, true);
                        TableUtils.dropTable(connectionSource, Orientations.class, true);                        
                        TableUtils.dropTable(connectionSource, ProgrammesLibelle.class, true);
                        TableUtils.dropTable(connectionSource, Board.class, true);
                        TableUtils.dropTable(connectionSource, Sail.class, true);
                        TableUtils.dropTable(connectionSource, Mast.class, true);
                        TableUtils.dropTable(connectionSource, Spin.class, true);
                        TableUtils.dropTable(connectionSource, Session.class, true);
                        TableUtils.dropTable(connectionSource, User.class, true);
                        TableUtils.dropTable(connectionSource, Pays.class, true);                        
                        // after we drop the old databases, we create the new ones
                        onCreate(db, connectionSource);
                } catch (SQLException e) {
                        Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
                        throw new RuntimeException(e);
                }
        }

        /**
         * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
         * value.
         */
        public Dao<Spot, Integer> getSpotDao() throws SQLException {
                if (spotDao == null) {
                	spotDao = getDao(Spot.class);
                }
                return spotDao;
        }
        
        public Dao<Type_navigation, Integer> getType_navigationDao() throws SQLException {
            if (type_navigationDao == null) {
            	type_navigationDao = getDao(Type_navigation.class);
            }
            return type_navigationDao;
        }
        
        public Dao<Orientations, Integer> getOrientationsDao() throws SQLException {
            if (orientationsDao == null) {
            	orientationsDao = getDao(Orientations.class);
            }
            return orientationsDao;
        }
        
        public Dao<ProgrammesLibelle, Integer> getProgrammesLibelleDao() throws SQLException {
            if (programmesLibelleDao == null) {
            	programmesLibelleDao = getDao(ProgrammesLibelle.class);
            }
            return programmesLibelleDao;
        }
        
        public Dao<Board, Integer> getBoardDao() throws SQLException {
            if (boardDao == null) {
            	boardDao = getDao(Board.class);
            }
            return boardDao;
        }
        
        public Dao<Sail, Integer> getSailDao() throws SQLException {
            if (sailDao == null) {
            	sailDao = getDao(Sail.class);
            }
            return sailDao;
        }
        
        public Dao<Mast, Integer> getMastDao() throws SQLException {
            if (mastDao == null) {
            	mastDao = getDao(Mast.class);
            }
            return mastDao;
        }
        
        public Dao<Spin, Integer> getSpinDao() throws SQLException {
            if (spinDao == null) {
            	spinDao = getDao(Spin.class);
            }
            return spinDao;
        }
        
        public Dao<Session, Integer> getSessionDao() throws SQLException {
            if (sessionDao == null) {
            	sessionDao = getDao(Session.class);
            }
            return sessionDao;
        }
        
        public Dao<User, Integer> getUserDao() throws SQLException {
            if (userDao == null) {
            	userDao = getDao(User.class);
            }
            return userDao;
        }

        public Dao<Pays, Integer> getPaysDao() throws SQLException {
            if (countryDao == null) {
            	countryDao = getDao(Pays.class);
            }
            return countryDao;
        }
        
        
        /**
         * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
         * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
         */
        public RuntimeExceptionDao<Spot, Integer> getSpotRuntimeExceptionDao() {
            if (spotRuntimeDao == null) {
                    spotRuntimeDao = getRuntimeExceptionDao(Spot.class);
            }
            return spotRuntimeDao;
        }

        public RuntimeExceptionDao<Type_navigation, Integer> getType_navigationRuntimeExceptionDao() {
            if (type_navigationRuntimeDao == null) {
            		type_navigationRuntimeDao = getRuntimeExceptionDao(Type_navigation.class);
            }
            return type_navigationRuntimeDao;
        }
        
        public RuntimeExceptionDao<Orientations, Integer> getOrientationsRuntimeExceptionDao() {
            if (orientationsRuntimeDao == null) {
            		orientationsRuntimeDao = getRuntimeExceptionDao(Orientations.class);
            }
            return orientationsRuntimeDao;
        }
        
        public RuntimeExceptionDao<ProgrammesLibelle, Integer> getProgrammesLibelleRuntimeExceptionDao() {
            if (programmesLibelleRuntimeDao == null) {
            		programmesLibelleRuntimeDao = getRuntimeExceptionDao(ProgrammesLibelle.class);
            }
            return programmesLibelleRuntimeDao;
        }
        
        public RuntimeExceptionDao<Board, Integer> getBoardRuntimeExceptionDao() {
            if (boardRuntimeDao == null) {
            		boardRuntimeDao = getRuntimeExceptionDao(Board.class);
            }
            return boardRuntimeDao;
        }
        
        public RuntimeExceptionDao<Sail, Integer> getSailRuntimeExceptionDao() {
            if (sailRuntimeDao == null) {
            		sailRuntimeDao = getRuntimeExceptionDao(Sail.class);
            }
            return sailRuntimeDao;
        }
        
        public RuntimeExceptionDao<Mast, Integer> getMastRuntimeExceptionDao() {
            if (mastRuntimeDao == null) {
            		mastRuntimeDao = getRuntimeExceptionDao(Mast.class);
            }
            return mastRuntimeDao;
        }
        
        public RuntimeExceptionDao<Spin, Integer> getSpinRuntimeExceptionDao() {
            if (spinRuntimeDao == null) {
            		spinRuntimeDao = getRuntimeExceptionDao(Spin.class);
            }
            return spinRuntimeDao;
        }
        
        public RuntimeExceptionDao<Session, Integer> getSessionRuntimeExceptionDao() {
            if (sessionRuntimeDao == null) {
            		sessionRuntimeDao = getRuntimeExceptionDao(Session.class);
            }
            return sessionRuntimeDao;
        }
        
        public RuntimeExceptionDao<User, Integer> getUserRuntimeExceptionDao() {
            if (userRuntimeDao == null) {
            		userRuntimeDao = getRuntimeExceptionDao(User.class);
            }
            return userRuntimeDao;
        }
        
        public RuntimeExceptionDao<Pays, Integer> getPaysRuntimeExceptionDao() {
            if (countryRuntimeDao == null) {
            		countryRuntimeDao = getRuntimeExceptionDao(Pays.class);
            }
            return countryRuntimeDao;
        }
        
        /**
         * Close the database connections and clear any cached DAOs.
         */
        @Override
        public void close() {
                super.close();
                spotDao = null;
                spotRuntimeDao = null;
                type_navigationDao = null;
                type_navigationRuntimeDao = null;
                programmesLibelleDao=null;
                programmesLibelleRuntimeDao=null;
                boardDao=null;
                boardRuntimeDao=null;
                sailDao=null;
                sailRuntimeDao=null;
                mastDao=null;
                mastRuntimeDao=null;
                spinDao=null;
                spinRuntimeDao=null;
                sessionDao=null;
                sessionRuntimeDao=null;
                userDao=null;
                userRuntimeDao=null;
                countryDao=null;
                countryRuntimeDao=null;
        }
}
