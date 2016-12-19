package com.braincorp.githandbook.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Parameter repository class
 * Created by Alan Camargo - December 2016
 */
public class ParameterRepository extends BaseRepository<String>
{

    private static ParameterRepository instance;

    /**
     * Singleton constructor - ensures there will only be
     * a single instance of ParameterRepository
     * @return instance
     */
    public static ParameterRepository getInstance(Context context)
    {
        if (instance == null)
            instance = new ParameterRepository(context);
        return instance;
    }

    private SQLiteDatabase reader;

    /**
     * Default constructor
     * @param context - the context
     */
    private ParameterRepository(Context context)
    {
        super(context);
        databaseHelper = new DatabaseHelper(context);
    }

    /**
     * Selects all instances
     * @return all instances
     */
    @Override
    public ArrayList<String> selectAll()
    {
        // Not necessary
        return null;
    }

    /**
     * Selects one instance
     * @param id - the ID of the item to be selected
     * @return instance
     */
    @Override
    public String select(int id)
    {
        // Not necessary
        return null;
    }

    /**
     * Selects all instances where a condition is met
     * e.g.: SELECT * FROM [TABLE] WHERE column = value
     * @param column - the column
     * @param value  - the value
     * @return instances
     */
    @Override
    public ArrayList<String> selectWhere(String column, Object value)
    {
        if (column != null && value != null)
        {
            ArrayList<String> params = new ArrayList<>();
            if (reader == null)
                reader = databaseHelper.getReadableDatabase();
            Cursor cursor = reader.query(ParametersTable.TABLE_NAME, null,
                                         String.valueOf(column) + " = " + String.valueOf(value),
                                         null, null,
                                         null, null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0)
            {
                do
                {
                    String param = cursor.getString(cursor.getColumnIndex(ParametersTable.COLUMN_CONTENT));
                    params.add(param);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return params;
        }
        else
            return selectAll();
    }

    /**
     * Selects the value of a specific column
     * @param columnToSelect - the column to select
     * @param column - the column
     * @param value - the value
     * @return instance
     */
    public int selectWhere(String columnToSelect, String column, Object value)
    {
        if (reader == null)
            reader = databaseHelper.getReadableDatabase();
        Cursor cursor = reader.query(ParametersTable.TABLE_NAME, new String[] { columnToSelect },
                                     column + " = " + value, null, null,
                                      null, null, "1");
        cursor.moveToFirst();
        int id = 0;
        if (cursor.getCount() > 0)
            id = cursor.getInt(cursor.getColumnIndex(columnToSelect));
        cursor.close();
        return id;
    }

    /**
     * Gets the ID of the last instance
     * @return last ID
     */
    @Override
    public int getLastId()
    {
        // Not necessary
        return 0;
    }

}
