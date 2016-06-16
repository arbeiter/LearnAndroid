package com.nomnommer.arbeiter.nomnommer.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.nomnommer.arbeiter.nomnommer.Models.Models.Noms.Nom;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chowman on 6/12/16.
 */
public class NomsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "postsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOMS = "Noms";
    private static final String KEY_NOM_ID = "id";
    private static final String KEY_NOM_NAME = "name";
    private static final String KEY_NOM_SNIPPET_URL = "snippetURL";

    private static NomsDatabaseHelper nomsDatabaseHelperInstance;

    public static synchronized NomsDatabaseHelper getInstance(Context context) {
        if (nomsDatabaseHelperInstance == null) {
            nomsDatabaseHelperInstance = new NomsDatabaseHelper(context.getApplicationContext());
        }
        return nomsDatabaseHelperInstance;
    }

    private NomsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOMS_TABLE = "CREATE TABLE " + TABLE_NOMS +
                "(" +
                KEY_NOM_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_NOM_NAME + " TEXT," +
                KEY_NOM_SNIPPET_URL + " TEXT"+
                ")";
        db.execSQL(CREATE_NOMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOMS);
            onCreate(db);
        }
    }

    public void addNom(Nom nom){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_NOM_NAME, nom.name);
            values.put(KEY_NOM_SNIPPET_URL, nom.snippet_image_url);
            db.insertOrThrow(TABLE_NOMS, null, values);
            db.setTransactionSuccessful();
        }
        catch (Exception e){
            Log.d("DB", "Error while trying to add post to the database");
        }
        finally{
            db.endTransaction();
        }
    }

    public List<Nom> getAllNoms(){
        List<Nom> noms = new ArrayList<Nom>();
        String NOMS_SELECT_QUERY = String.format("SELECT * FROM %s", TABLE_NOMS);
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(NOMS_SELECT_QUERY, null);
        try{
            if(cursor.moveToFirst()){
                do{
                    Nom newNom = new Nom();
                    newNom.name = cursor.getString(cursor.getColumnIndex(KEY_NOM_NAME));
                    newNom.snippet_image_url = cursor.getString(cursor.getColumnIndex(KEY_NOM_SNIPPET_URL));
                    newNom.photo = new GetImageTask().doInBackground(newNom.snippet_image_url);
                    noms.add(newNom);
                }while(cursor.moveToNext());
            }
        }
        catch(Exception e){
            Log.d("TAG", "Error while trying to get posts from database");
        }
        finally{
            if(cursor!=null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return noms;
    }

    private class GetImageTask extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                connection.setUseCaches(true);
                Bitmap response = BitmapFactory.decodeStream((InputStream)connection.getContent());
                return response;
            }
            catch(MalformedURLException ex) {
                return null;
            }
            catch(IOException ex) {
                return null;
            }
        }
    }
}
