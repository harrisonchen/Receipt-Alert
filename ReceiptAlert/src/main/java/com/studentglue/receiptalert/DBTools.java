package com.studentglue.receiptalert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBTools extends SQLiteOpenHelper {

    public DBTools(Context applicationContext) {
        super(applicationContext, "receiptalert.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String createBankQuery = "CREATE TABLE bank(bank_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, cycle_date TEXT NOT NULL)";

        String createReceiptQuery = "CREATE TABLE receipt(receipt_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT NOT NULL, pushed_back INTEGER DEFAULT 0, " +
                "image BLOB, " +
                "bank_id INTEGER DEFAULT -1, FOREIGN KEY (bank_id) REFERENCES bank(bank_id))";

        database.execSQL(createBankQuery);
        database.execSQL(createReceiptQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i2) {

        String query = "DROP TABLE IF EXISTS receiptalert";

        database.execSQL(query);

        onCreate(database);
    }

    public void addBank(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", queryValues.get("bank_name"));
        values.put("cycle_date", queryValues.get("cycle_date"));

        database.insert("bank", null, values);

        database.close();
    }

    public ArrayList<HashMap<String, String>> getAllBanks() {

        ArrayList<HashMap<String, String>> bankArrayList = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase database = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM bank ORDER BY bank_id DESC";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {

            do {
                HashMap<String, String> bankMap = new HashMap<String, String>();

                bankMap.put("bank_id", cursor.getString(0));
                bankMap.put("bank_name", cursor.getString(1));
                bankMap.put("cycle_date", cursor.getString(2));

                bankArrayList.add(bankMap);
            } while(cursor.moveToNext());
        }

        database.close();

        return bankArrayList;
    }

    public void addReceipt(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("date", queryValues.get("receipt_date"));
        values.put("bank_id", queryValues.get("receipt_bank"));
        values.put("image", queryValues.get("image_value"));

        database.insert("receipt", null, values);

        database.close();
    }

    public ArrayList<HashMap<String, String>> getAllReceipts() {

        ArrayList<HashMap<String, String>> receiptArrayList = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase database = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM receipt ORDER BY receipt_id DESC";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {

            do {
                HashMap<String, String> receiptMap = new HashMap<String, String>();

                receiptMap.put("receipt_id", cursor.getString(0));
                receiptMap.put("receipt_date", cursor.getString(1));
                receiptMap.put("image", cursor.getString(3));

                receiptArrayList.add(receiptMap);
            } while(cursor.moveToNext());
        }

        database.close();

        return receiptArrayList;
    }

    public ArrayList<HashMap<String, String>> getAllReceiptsFromBank(String bank_id) {

        ArrayList<HashMap<String, String>> receiptArrayList = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase database = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM receipt WHERE bank_id=" + bank_id + " ORDER BY receipt_id DESC";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {

            do {
                HashMap<String, String> receiptMap = new HashMap<String, String>();

                receiptMap.put("receipt_id", cursor.getString(0));
                receiptMap.put("receipt_date", cursor.getString(1));
                receiptMap.put("image", cursor.getString(3));

                receiptArrayList.add(receiptMap);
            } while(cursor.moveToNext());
        }

        database.close();

        return receiptArrayList;
    }
}
