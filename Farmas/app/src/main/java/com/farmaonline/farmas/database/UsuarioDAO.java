package com.farmaonline.farmas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.farmaonline.farmas.model.Usuario;

/**
 * Created by Romano on 30/11/2017.
 */

public class UsuarioDAO {

    private static UsuarioDAO instance;

    private SQLiteDatabaseHandler dbManager;

    public static UsuarioDAO get (Context context) {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        SQLiteDatabaseHandler manager = new SQLiteDatabaseHandler(context);
        manager.getWritableDatabase();
        instance.setDbManager(manager);
        return instance;
    }

    private void setDbManager (SQLiteDatabaseHandler dbManager) {
        this.dbManager = dbManager;
    }

    public Usuario getUserByLogin (String login) {
        SQLiteDatabase db = this.dbManager.getReadableDatabase();
        Cursor cursor = db.query
                (SQLiteDatabaseHandler.TABLE_USER,
                SQLiteDatabaseHandler.ALL_COLUMNS_TABLE_USER,
                "USU_LOGIN = ?",
                new String[]{login},
                null,
                null,
                null,
                null);

        Usuario usuario = null;
        if (cursor != null && cursor.moveToFirst()) {
            usuario = new Usuario();
            usuario.setChavePrimaria(cursor.getLong(0));
            usuario.setLogin(cursor.getString(1));
            usuario.setSenha(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
        }

        return usuario;
    }

    public long save (Usuario usuario) {

        Log.i(SQLiteDatabaseHandler.TAG_BASE_NAME, "Saving user: " + usuario);
        try {
            SQLiteDatabase db = dbManager.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(SQLiteDatabaseHandler.ALL_COLUMNS_TABLE_USER[1], usuario.getLogin());
            values.put(SQLiteDatabaseHandler.ALL_COLUMNS_TABLE_USER[2], usuario.getSenha());
            values.put(SQLiteDatabaseHandler.ALL_COLUMNS_TABLE_USER[3], usuario.getEmail());

            long retorno = db.insert(SQLiteDatabaseHandler.TABLE_USER, null, values);

            db.close();

            return retorno;
        } catch (Exception e) {
            Log.e(SQLiteDatabaseHandler.TAG_BASE_NAME, "Error while save user: " + usuario.getLogin());
            e.printStackTrace();
        }
        return -10;
    }
}
