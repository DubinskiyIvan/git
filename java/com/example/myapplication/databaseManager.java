package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class databaseManager {
    private databaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public databaseManager(Context c) {
        context = c;
    }

    public databaseManager open() throws SQLException {
        dbHelper = new databaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private void insert(String op1, String op2, String function, String result) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(databaseHelper.OPERAND1, op1);
        contentValue.put(databaseHelper.OPERAND2, op2);
        contentValue.put(databaseHelper.FUNCTION, function);
        contentValue.put(databaseHelper.RESULT, result);
        database.insert(databaseHelper.TABLE_NAME, null, contentValue);
    }

    private Cursor fetch() {
        String[] columns = new String[] { databaseHelper._ID,
                databaseHelper.OPERAND1,
                databaseHelper.OPERAND2,
                databaseHelper.FUNCTION,
                databaseHelper.RESULT};
        Cursor cursor = database.query(databaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    private int update(long _id, String op1, String op2, String function, String result) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.OPERAND1, op1);
        contentValues.put(databaseHelper.OPERAND2, op2);
        contentValues.put(databaseHelper.FUNCTION, function);
        contentValues.put(databaseHelper.RESULT, result);
        int i = database.update(databaseHelper.TABLE_NAME, contentValues, databaseHelper._ID + " = " + _id, null);
        return i;
    }

    private void delete(long _id) {
        database.delete(databaseHelper.TABLE_NAME, databaseHelper._ID + "=" + _id, null);
    }


    public void insert(historyItem item) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(databaseHelper.OPERAND1, item.getOperand1());
        contentValue.put(databaseHelper.OPERAND2, item.getOperand2());
        contentValue.put(databaseHelper.FUNCTION, item.getFunction());
        contentValue.put(databaseHelper.RESULT, item.getResult());
        database.insert(databaseHelper.TABLE_NAME, null, contentValue);
    }

    public void deleteAll() {
        database.delete(databaseHelper.TABLE_NAME,"",null);
    }

    public String getAllAsText(){
        String text = "";
        Cursor cursor = fetch();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String op1, op2, function, result;
            op1 = cursor.getString(cursor.getColumnIndex(databaseHelper.OPERAND1));
            op2 = cursor.getString(cursor.getColumnIndex(databaseHelper.OPERAND2));
            function = cursor.getString(cursor.getColumnIndex(databaseHelper.FUNCTION));
            result = cursor.getString(cursor.getColumnIndex(databaseHelper.RESULT));
            historyItem item = new historyItem(op1, op2, function, result);
            text += (item.getTextRepresentation() + "\n");
            cursor.moveToNext();
        }
        return text;
    }
}
