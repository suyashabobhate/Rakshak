package com.example.rakshak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "C";
    private static final String TABLE_User = "User";
    private static final String TABLE_Reports = "Reports";
    private static final String TABLE_Missing = "Missing";
    private static final String KEY_ID = "id";
    private static final String KEY_Name = "name";
    private static final String KEY_Age = "age";
    private static final String KEY_MobileNo = "mobileno";
    private static final String KEY_MailID = "mailid";
    private static final String KEY_Password = "password";
    private static final String KEY_category = "category";
    private static final String KEY_Location = "location";
    private static final String KEY_Time = "time";
    private static final String KEY_Date = "date";
    private static final String KEY_Summary = "summary";
    private static final String KEY_Suspects = "suspects";
    private static final String KEY_Gender = "gender";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE1 = "CREATE TABLE " + TABLE_User+ "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Name + " TEXT,"
                +  KEY_Age + " TEXT," + KEY_MobileNo + " TEXT," + KEY_MailID + " TEXT," + KEY_Password + " TEXT"
        + ")";
        db.execSQL(CREATE_CONTACTS_TABLE1);

        String CREATE_CONTACTS_TABLE2 = "CREATE TABLE " + TABLE_Reports + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_category + " TEXT," + KEY_Location + " TEXT," + KEY_Time + " TEXT," + KEY_Date + " TEXT," + KEY_Summary + " TEXT," + KEY_Suspects + " TEXT"
                +  ")";
        db.execSQL(CREATE_CONTACTS_TABLE2);

        String CREATE_CONTACTS_TABLE3= "CREATE TABLE " + TABLE_Missing + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_Name + " TEXT," +  KEY_Age + " TEXT," + KEY_MobileNo + " TEXT,"+ KEY_Location + " TEXT," + KEY_Time + " TEXT," + KEY_Gender +" TEXT"
                +  ")";
        db.execSQL(CREATE_CONTACTS_TABLE3);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_User);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Reports);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Missing);

        // Create tables again
        onCreate(db);
    }

    public void  deleteTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_User);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Reports);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Missing);

        // Create tables again
        onCreate(db);
    }
    // code to add the new crime

    void addDataToUser(String name, String age, String mobileno, String mailid, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, name);// Contact Name
        values.put(KEY_Age, age);
        values.put(KEY_MobileNo, mobileno);
        values.put(KEY_MailID, mailid);
        values.put(KEY_Password, password);


        // Inserting Row
        db.insert("User", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    void addDataToReports(String category, String time, String date, String summary, String suspects) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_category, category);// Contact Name
        //values.put(KEY_Location, location);
        values.put(KEY_Time, time);
        values.put(KEY_Date, date);
        values.put(KEY_Summary, summary);
        values.put(KEY_Suspects,suspects);

        // Inserting Row
        db.insert("Reports", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    void addDataToMissing(String name, String age, String mobileno, String gender, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, name);// Contact Name
        values.put(KEY_Age, age);
        values.put(KEY_MobileNo, mobileno);
        values.put(KEY_Gender, gender);
        values.put(KEY_Time, time);
        values.put(KEY_Date, date);


        // Inserting Row
        db.insert("Missing", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public boolean checkForTableExists(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+table+"'";
        Cursor mCursor = db.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }

   /* public void addSelected(ArrayList<String> selList) {

        int size = selList.size();

        SQLiteDatabase db = getWritableDatabase();
        try {
            for (int i = 0; i < size; i++) {
                ContentValues cv = new ContentValues();
                cv.put(KEY_ID, i);
                cv.put(KEY_Name, selList.get(i));
                Log.d("Added ", "" + cv);
                db.insertOrThrow(TABLE_List, null, cv);
            }
            db.close();
        } catch (Exception e) {
            Log.e("Problem", e + " ");
        }
    }*/

   Boolean getDataToVerify(String name, String password){
       String query = "SELECT "+KEY_Password +" FROM "+TABLE_User+" WHERE name= '" + name+" ' ";
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor mCursor = db.rawQuery(query, null);
       mCursor.moveToFirst();
       //Log.println(1,"cursor",cursor.toString());
       if(mCursor.getCount()>0) {
           Log.w("mycursorvalue",mCursor.getString(4));
           if (mCursor.getString(4).equals(password)) {
               mCursor.close();
               return true;
           }
       }
       return false;
   }


    public int getCrimeCount() {
        String countQuery = "SELECT  * FROM " + TABLE_User;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }


    //
    String getList(int id,String table) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table, new String[] { KEY_ID,
                        KEY_Name }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        String s=cursor.getString(1);
        // return contact
        return s;
    }

    public int getId(String s){
        SQLiteDatabase db = this.getReadableDatabase();
        s=s.replaceAll("'","\\'");
        String q = "select id from ToWatch where name = ?";
        Cursor mCursor =db.rawQuery(q, new String[] { s});
        //Cursor mCursor = db.rawQuery("SELECT id  FROM  Towatch WHERE name= '"+s+"'" , null);
        int id=0;
        if (mCursor != null)
        {
            mCursor.moveToFirst();
            id=mCursor.getInt(0);
        }
        return id;
    }


    // code to get all
    public List<Crime> getAllCrime(String table) {
        List<Crime> list = new ArrayList<Crime>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + table;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Crime crime = new Crime();
                crime.setID(Integer.parseInt(cursor.getString(0)));
                crime.setName(cursor.getString(1));
                list.add(crime);
            } while (cursor.moveToNext());
        }

        return list;
    }
}