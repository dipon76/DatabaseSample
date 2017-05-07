package com.example.dipon.databasesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.dipon.databasesample.DatabaseContract.*;

/**
 * Created by Dipon on 5/4/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GroupContact.db";

    public static String TAG = "SQL queries" ;


    private static final String SQL_CREATE_ENTRIES1 =
            "CREATE TABLE " + GroupInfo.TABLE_NAME + " ( " +
                    GroupInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    GroupInfo.COLUMN_GROUP_NAME + " TEXT, " +
                    GroupInfo.COLUMN_GROUP_ADMIN + " TEXT, " +
                    GroupInfo.COLUMN_PARTICIPANTS_NO + " INTEGER )";

    private static final String SQL_DELETE_ENTRIES1 =
            "DROP TABLE IF EXISTS " + GroupInfo.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + ParticipantsInfo.TABLE_NAME + " ( " +
                    ParticipantsInfo._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ParticipantsInfo.COLUMN_PARTICIPANT_NAME + " TEXT, " +
                    ParticipantsInfo.COLUMN_PARTICIPANTS_NUMBER + " TEXT, " +
                    ParticipantsInfo.COLUMN_GROUP_NO + " INTEGER )";

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + ParticipantsInfo.TABLE_NAME;

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: " + SQL_CREATE_ENTRIES2 );
        db.execSQL(SQL_CREATE_ENTRIES1);
        db.execSQL(SQL_CREATE_ENTRIES2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES1);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);

    }

    public long insert() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GroupInfo.COLUMN_GROUP_NAME,Constants.GRP_NAME);
        values.put(GroupInfo.COLUMN_GROUP_ADMIN,Constants.GRP_ADMIN);
        values.put(GroupInfo.COLUMN_PARTICIPANTS_NO, Constants.PART_NO);
        long rowId = db.insert(GroupInfo.TABLE_NAME,null,values);

        Cursor cursor = getGroupId();
        if (cursor.getCount()>1) {
            Log.e(TAG, "insert: error duplicate");
        } else {
            cursor.moveToNext();
            rowId = cursor.getLong(cursor.getColumnIndexOrThrow(GroupInfo._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(GroupInfo.COLUMN_GROUP_NAME));
            Log.e(TAG, "inserted: "+name);
        }

        ContentValues values2 = new ContentValues();
        values2.put(ParticipantsInfo.COLUMN_PARTICIPANT_NAME , Constants.PART_NAME);
        values2.put (ParticipantsInfo.COLUMN_PARTICIPANTS_NUMBER , Constants.PART_NUM);
        values2.put(ParticipantsInfo.COLUMN_GROUP_NO , rowId );
        rowId = db.insert(ParticipantsInfo.TABLE_NAME , null, values2);
        return rowId;

    }

    public Cursor getGroupId ()
    {
        SQLiteDatabase readDb = this.getReadableDatabase();

        String[] projection = {
          GroupInfo._ID,
                GroupInfo.COLUMN_GROUP_NAME };
        String selection = GroupInfo.COLUMN_GROUP_NAME + "= ? AND " + GroupInfo.COLUMN_GROUP_ADMIN +"= ?"  ;
        String[] selectionArgs = { Constants.GRP_NAME, Constants.GRP_ADMIN};

        Cursor cursor = readDb.query(
                GroupInfo.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        return cursor;
    }
}
