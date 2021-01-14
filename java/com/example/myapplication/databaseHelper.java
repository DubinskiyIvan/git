package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "history";

    // Table columns
    public static final String _ID = "_id";
    public static final String OPERAND1 = "operand1";
    public static final String OPERAND2 = "operand2";
    public static final String FUNCTION = "function";
    public static final String RESULT = "result";

    // Database Information
    static final String DB_NAME = "history_myapplication.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "Create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + OPERAND1 + " TEXT NOT NULL, "
            + OPERAND2 + " TEXT NOT NULL, "
            + FUNCTION + " TEXT NOT NULL, "
            + RESULT + " TEXT NOT NULL"
            + ");";

    public databaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
