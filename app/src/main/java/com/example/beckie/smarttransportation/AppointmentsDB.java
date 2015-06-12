package com.example.beckie.smarttransportation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Beckie on 5/25/2015.
 */
public class AppointmentsDB {


        //when a cursor is made when retireving data from a database, use the values as indices if the cursor
        public static final int ID_CURSOR_INDEX = 0;
        public static final int NAME_CURSOR_INDEX = 1;
        public static final int NUMBER_CURSOR_INDEX = 2;
        public static final int MESSAGE_CURSOR_INDEX = 3;
        public static final int DATE_CURSOR_INDEX = 4;
        public static final int TIME_CURSOR_INDEX = 5;
        public static final int NATURE_CURSOR_INDEX = 6;
        public static final int DELIVERY_CURSOR_INDEX = 7;


        //these are columns in the table
        public static final String KEY_ROWID = "_id";
        public static final String KEY_DOCTOR_TYPE = "doctorType";
        public static final String KEY_HAS_CONFIRMED = "hasConfirmed";
        public static final String KEY_CONFIRMED_DATE = "confirmedDate";
        public static final String KEY_DATE = "date";
        public static final String KEY_TIME = "time";
        public static final String KEY_NATURE = "nature";//0=to phone route, 1=to contact(s), 2=to group, 3=to file
        //public static final String KEY_FILE_TOTAL = "file_sent_total";//if sent to file, will contain total route of recipients;
        //public static final String KEY_RES1 = "reserve1";
        public static final String KEY_DELIVERY = "delivery";//contains delivery info(time)

        //used to determine the nture of sms sent eg to a group, to a file, to a contact etc
        public static final int NATURE_PHONE_NUMBER = 0;
        public static final int NATURE_CONTACTS = 1;
        public static final int NATURE_GROUP = 2;
        public static final int NATURE_FILE = 3;

        //  private static final String TAG = "MessagesDatabase";
        private static final String DATABASE_NAME = "MainDatabase";
        private static final String DATABASE_TABLE = "mainTable";
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_CREATE =       //contains the SQL statement for creating the messages table within the MyDB database.
                "create table mainTable (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "doctorType VARCHAR,"
                        + "route VARCHAR," +
                        "message TEXT DEFAULT ''," +
                        "date TEXT NOT NULL," +
                        "time TEXT," +
                        "nature INTEGER NOT NULL DEFAULT 0," +
                        "delivery TEXT DEFAULT '');";//also read somewhere DEFAULT \'50\'

        private final Context context;
        private DatabaseHelper DBHelper;
        private SQLiteDatabase db;

        public AppointmentsDB(Context ctx) {
            this.context = ctx;
            DBHelper = new DatabaseHelper(context);
        }

        //---opens the database---
        public AppointmentsDB open() throws SQLException {
            db = DBHelper.getWritableDatabase();
            return this;
        }

        //---closes the database---
        public void close() {
            //Log.d("BSMS", "DB: close()");
            DBHelper.close();
        }

        //---insert a contact into the database---
        public long insertJourney(String from, String route, String message, String date, String time, int tag) {

            //Log.d("BSMS", "DB: insertMessage(..");
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_DOCTOR_TYPE, from);
            initialValues.put(KEY_HAS_CONFIRMED, route);
            initialValues.put(KEY_CONFIRMED_DATE, message);
            initialValues.put(KEY_DATE, date);
            initialValues.put(KEY_TIME, time);
            initialValues.put(KEY_NATURE, tag);
            initialValues.put(KEY_DELIVERY, "");
            return db.insert(DATABASE_TABLE, null, initialValues);
        }

        //---deletes a particular contact---
        public boolean deleteMessage(long rowId) {
            //Log.d("BSMS", "DB: deleteMessage(..");
            return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
        }

        //---retrieves all the messages---
        public Cursor getAllJourneys() {
            //Log.d("BSMS", "DB: getAllMessages()");
            return db.query(DATABASE_TABLE,
                    new String[]{KEY_ROWID, KEY_DOCTOR_TYPE, KEY_HAS_CONFIRMED, KEY_CONFIRMED_DATE, KEY_DATE, KEY_TIME, KEY_NATURE, KEY_DELIVERY},
                    null, null, null, null, null);
        }

        //---retrieves a particular contact---
        public Cursor getContact(long rowId) throws SQLException {
            //Log.d("BSMS", "DB: getContact(...");
            Cursor mCursor =
                    db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_DOCTOR_TYPE, KEY_HAS_CONFIRMED, KEY_CONFIRMED_DATE, KEY_DATE, KEY_TIME, KEY_NATURE, KEY_DELIVERY},
                            KEY_ROWID + "=" + rowId, null,
                            null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;
        }

        /**
         * Since delivery report is not available when sending the sms, the delivery field is only
         * available on this method of updating a message
         *
         * @param rowId
         * @param from
         * @param route
         * @param message
         * @param date
         * @param time
         * @param tag
         * @param delivery
         * @return
         */
        public boolean updateMessage(long rowId, String from, String route, String message, String date, String time, int tag, String delivery) {
            //Log.d("BSMS", "DB: updateMessage(...");
            ContentValues args = new ContentValues();
            args.put(KEY_DOCTOR_TYPE, from);
            args.put(KEY_HAS_CONFIRMED, route);
            args.put(KEY_CONFIRMED_DATE, message);
            args.put(KEY_DATE, date);
            args.put(KEY_TIME, time);
            args.put(KEY_NATURE, tag);
            args.put(KEY_DELIVERY, delivery);
            return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
        }

        //---updates a contact---

        /**
         * SQLiteOpenHelper class, which is a helper class in Android
         * to manage database creation and version management.
         */
        private static class DatabaseHelper extends SQLiteOpenHelper {
            DatabaseHelper(Context context) {

                super(context, DATABASE_NAME, null, DATABASE_VERSION);
                //Log.d("BSMS", "DB: in constructor");
                force_creatyion();
            }

            void force_creatyion() {

            }        /**
             * The onCreate() method creates a new database if the required database is not present. The
             * onUpgrade() method is called when the database needs to be upgraded. This is achieved by checking the
             * value defined in the DATABASE_VERSION constant. For this implementation of the onUpgrade() method,
             * you simply drop the table and create it again.
             *
             * @param db
             */
            @Override
            public void onCreate(SQLiteDatabase db) {
                //Log.d("BSMS", "DB: onCreate(SQLiteDatabase db)");
                try {
                    //Log.d("BSMS", "DB: MADE DB");
                    db.execSQL(DATABASE_CREATE);
                } catch (SQLException e) {
                    //Log.d("BSMS", "DB: MAKING DB FAILED");
                    e.printStackTrace();
                }
            }



            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                //    Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                //             + newVersion + ", which will destroy all old data");
                db.execSQL("DROP TABLE IF EXISTS mainTable");

                onCreate(db);
            }
        }

    }

