package com.braincorp.githandbook.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Database helper class
 * Created by Alan Camargo - December 2016
 */
class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_PATH = "/data/data/com.braincorp.githandbook/databases/";
    private static final String DB_NAME = "database.db";
    private SQLiteDatabase database;
    private Context context;

    /**
     * Creates an instance of DatabaseHelper
     * @param context - the context
     */
    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        try {
            createDatabase();
            openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an empty database and rewrites it with our own data
     * @throws IOException - if there's an error copying the database
     */
    private void createDatabase() throws IOException {
        boolean dbExists = checkDatabase();
        if (!dbExists) {
            getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Checks if the database exists
     * @return true if exists, otherwise false
     */
    private boolean checkDatabase() {
        SQLiteDatabase db = null;
        try {
            String path = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (db != null)
            db.close();
        return db != null;
    }

    /**
     * Copies the database file from the assets directory into the
     * newly created database in the system folder, from where it
     * can be accessed and handled
     * @throws IOException - if there's an error copying the file
     */
    private void copyDatabase() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream output = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024 * 50];
        int length;
        while ((length = input.read(buffer)) > 0)
            output.write(buffer, 0, length);

        output.flush();
        output.close();
        input.close();
    }

    /**
     * Opens the database
     * @throws SQLException - if there's an SQL exception
     */
    private void openDatabase() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (database != null)
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}
