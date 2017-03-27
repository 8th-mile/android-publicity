package com.a8thmile.rvce.a8thmile.models;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.a8thmile.rvce.a8thmile.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.a8thmile.rvce.a8thmile.ui.Activities.HomeActivity.context;

/**
 * Created by vignesh on 21/3/17.
 */

public class EventDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Events.db";
    private static final int DATABASE_VERSION = 1;
    private  static final String TABLE_NAME="eventlists";
    // For all Primary Keys _id should be used as column name
    public static final String EVENT_ID = "_id";

    // Definition of table and column names of Products table
    public static final String EVENT_NAME = "name";
    public static final String EVENT_DATE = "date";
    public static final String EVENT_TYPE = "type";
    public static final String EVENT_PRICE = "price";

    // Definition of table and column names of Transactions table
    public static final String EVENT_ABOUT = "about";
    public static final String EVENT_RULES = "rules";
    public static final String FIRST_PRIZE = "first_prize";
    public static final String SECOND_PRIZE = "second_prize";
    public static final String COORD_ONE="coord1";
    public static final String COORD_TWO="coord2";
    public static final String PHONE_ONE="cphone1";
    public static final String PHONE_TWO="cphone2";
    public static final String IMAGE_ID="imgid";
    private static String DB_PATH = "/data/data/com.a8thmile.rvce.a8thmile/databases/";

    private static String DB_NAME = "eventlists.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;


    // Create Statement for Products Table
  /*  private static final String CREATE_TABLE_EVENTS = "CREATE TABLE " + TABLE_NAME + "  (" +
            EVENT_ID + " VARCHAR(3) , " +
            EVENT_NAME + " TEXT, " +
            EVENT_DATE + " TEXT, " +
            EVENT_TYPE + " INTEGER" +
            EVENT_PRICE + " TEXT" +
            EVENT_ABOUT + " TEXT" +
            EVENT_RULES + " TEXT" +
            FIRST_PRIZE + " TEXT" +
            SECOND_PRIZE + " TEXT" +
            COORD_ONE + " TEXT" +
            COORD_TWO + " TEXT" +
            PHONE_ONE + " TEXT" +
            PHONE_TWO + " TEXT" +
            IMAGE_ID+"INTEGER"+
            ");";
*/


    public EventDb(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        myContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // onCreate should always create your most up to date database
        // This method is called when the app is newly installed
       // db.execSQL(CREATE_TABLE_EVENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade is responsible for upgrading the database when you make
        // changes to the schema. For each version the specific changes you made
        // in that version have to be applied.
        if (oldVersion >= newVersion)
            return;
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        //onCreate(db);
    }

    // Getting single Reminder
    public EventFields getEvent(String id){
        SQLiteDatabase db = this.myDataBase;

        Cursor cursor = db.query(TABLE_NAME, new String[]
                        {
                                EVENT_ID,
                                EVENT_NAME,
                                EVENT_DATE,
                                EVENT_TYPE,
                                EVENT_PRICE,
                                EVENT_ABOUT,
                                EVENT_RULES,
                                FIRST_PRIZE,
                                SECOND_PRIZE,
                                COORD_ONE,
                                COORD_TWO,
                                PHONE_ONE,
                                PHONE_TWO,
                                IMAGE_ID
                        }, EVENT_ID + "=?",

                new String[] {id}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        EventFields events = new EventFields(Integer.toString(cursor.getInt(0)), cursor.getString(1),
                cursor.getString(2), cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)
        ,cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12)
        ,context.getResources().getIdentifier(cursor.getString(13),"drawable",context.getPackageName()));


        return events;
    }
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            File file = new File(myPath);
            if (file.exists() && !file.isDirectory())
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }
    // Getting all Reminders
    public List<EventFields> getEvents(){
        List<EventFields> eventlist = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.myDataBase;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                EventFields events = new EventFields();
                events.setId(Integer.toString(cursor.getInt(0)));
                events.setName(cursor.getString(1));
                events.setDate(cursor.getString(2));
                events.setType(cursor.getInt(3));
                events.setPrice(cursor.getString(4));
                events.setAbout(cursor.getString(5));
                events.setRules(cursor.getString(6));
                events.setFirst_prize(cursor.getString(7));
                events.setSecond_prize(cursor.getString(8));
                events.setCoord1(cursor.getString(9));
                events.setCoord2(cursor.getString(10));
                events.setCphone1(cursor.getString(11));
                events.setCphone2(cursor.getString(12));
                if(cursor.getString(13)!=null)
                events.setImgid(myContext.getResources().getIdentifier(cursor.getString(13),"drawable",myContext.getPackageName()));
                else
                events.setImgid(0);
                // Adding Reminders to list
                eventlist.add(events);
            } while (cursor.moveToNext());
        }
        return eventlist;
    }

}