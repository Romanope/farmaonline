package com.farmaonline.farmas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.farmaonline.farmas.model.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Romano on 30/11/2017.
 */

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final String     SCRIPT_FILE_NAME = "script.sql";
    private static final String     BASE_NAME = "farma.db";
    private static final int        BASE_VERSION = 2;
    public  static final String     TAG_BASE_NAME = "DB_FARMA";
    public  static final String     TABLE_USER = "USUARIO";
    public  static final String[]   ALL_COLUMNS_TABLE_USER = new String[] {"USU_ID", "USU_LOGIN", "USU_SENHA", "USU_EMAIL"};
    public SQLiteDatabaseHandler (Context context) {
        super(context, BASE_NAME, null, BASE_VERSION);
        SQLiteDatabase manager = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            String script = "CREATE TABLE USUARIO (" +
                    "    USU_ID    INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    USU_LOGIN TEXT NOT NULL," +
                    "    USU_SENHA TEXT NOT NULL," +
                    "    USU_EMAIL TEXT NULL" +
                    ");";
            db.execSQL(script);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        this.onCreate(db);
    }

    private String getScript () {

        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream input = new FileInputStream(new File(SCRIPT_FILE_NAME));

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();

        } catch (FileNotFoundException e) {
            Log.e(TAG_BASE_NAME, "Script for creation database not found!!!");
        } catch (IOException e) {
            Log.e(TAG_BASE_NAME, "Error to closed bufferedReader!!");
        }

        Log.i(TAG_BASE_NAME, "Script to criate database: " + sb);
        return sb.toString();
    }

    public boolean save (Usuario usuario) {

        Log.i(SQLiteDatabaseHandler.TAG_BASE_NAME, "Saving user: " + usuario);
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(SQLiteDatabaseHandler.ALL_COLUMNS_TABLE_USER[1], usuario.getLogin());
            values.put(SQLiteDatabaseHandler.ALL_COLUMNS_TABLE_USER[2], usuario.getSenha());
            values.put(SQLiteDatabaseHandler.ALL_COLUMNS_TABLE_USER[3], usuario.getEmail());

            db.insert(SQLiteDatabaseHandler.TABLE_USER, null, values);

            db.close();

            return true;
        } catch (Exception e) {
            Log.e(SQLiteDatabaseHandler.TAG_BASE_NAME, "Error while save user: " + usuario.getLogin());
            e.printStackTrace();
        }
        return false;
    }
}
