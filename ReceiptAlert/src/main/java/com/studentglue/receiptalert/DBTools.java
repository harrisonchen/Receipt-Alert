package com.studentglue.receiptalert;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "date TEXT NOT NULL, pushed_back INTEGER DEFAULT 0, image BLOB, " +
                "FOREIGN KEY(bank_id) REFERENCES bank(bank_id)";

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

        values.put("name", queryValues.get("receipt_date"));
        values.put("cycle_date", queryValues.get("cycle_date"));

        database.insert("bank", null, values);

        database.close();
    }

    public void addReceipt(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("date", queryValues.get("receipt_date"));
        values.put("bank_id", queryValues.get("bank_id"));
        values.put("image", queryValues.get("image_value"));

        database.insert("receipt", null, values);

        database.close();
    }
}
