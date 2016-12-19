package com.braincorp.githandbook.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Meaning repository class
 * Created by Alan Camargo - December 2016
 */
public class MeaningRepository extends BaseRepository<String>
{

    private static MeaningRepository instance;

    /**
     * Singleton constructor - ensures there will only be
     * a single instance of MeaningRepository
     * @return instance
     */
    public static MeaningRepository getInstance(Context context)
    {
        if (instance == null)
            instance = new MeaningRepository(context);
        return instance;
    }

    private SQLiteDatabase reader;

    /**
     * Default constructor
     * @param context - the context
     */
    private MeaningRepository(Context context)
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
            ArrayList<String> meanings = new ArrayList<>();
            if (reader == null)
                reader = databaseHelper.getReadableDatabase();
            Cursor cursor = reader.query(MeaningsTable.TABLE_NAME, null,
                    String.valueOf(column) + " = " + String.valueOf(value),
                    null, null,
                    null, null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0)
            {
                do
                {
                    String meaning = cursor.getString(cursor.getColumnIndex(MeaningsTable.COLUMN_CONTENT));
                    meanings.add(meaning);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return meanings;
        }
        else
            return selectAll();
    }

    /**
     * Selects one instance where a given condition is met
     * @param columns - the columns
     * @param values - the values
     * @return instance
     */
    public String selectWhere(String[] columns, Object[] values)
    {
        if (columns != null && values != null)
        {
            String meaning = null;
            String v0 = String.valueOf(values[0]).equals("<NULL>") ? "IS NULL" : "= '" + values[0] + "'";
            String v1 = String.valueOf(values[1]).equals("<NULL>") ? "IS NULL" : "= '" + values[1] + "'";
            if (v0.equals("= '0'"))
                v0 = "IS NULL";
            if (v1.equals("= '0'"))
                v1 = "IS NULL";
            if (reader == null)
                reader = databaseHelper.getReadableDatabase();
            Cursor cursor = reader.query(MeaningsTable.TABLE_NAME, null,
                    String.valueOf(columns[0]) + " " + v0
                            + " AND " + String.valueOf(columns[1]) + " " + v1,
                    null, null,
                    null, null, "1");
            cursor.moveToFirst();
            if (cursor.getCount() > 0)
                meaning = cursor.getString(cursor.getColumnIndex(MeaningsTable.COLUMN_CONTENT));
            cursor.close();
            return meaning;
        }
        else
            return null;
    }

    /**
     * Gets all parameter IDs related to a command
     * @param commandId - the command ID
     * @return parameter IDs
     */
    public ArrayList<Integer> selectWhere(int commandId)
    {
        if (reader == null)
            reader = databaseHelper.getReadableDatabase();
        Cursor cursor = reader.query(MeaningsTable.TABLE_NAME, new String[] { MeaningsTable.COLUMN_PARAMETER },
                                     MeaningsTable.COLUMN_COMMAND + " = " + commandId, null,
                                     null, null, null);
        cursor.moveToFirst();
        ArrayList<Integer> paramIds = new ArrayList<>();
        do
        {
            int id = cursor.getInt(cursor.getColumnIndex(MeaningsTable.COLUMN_PARAMETER));
            paramIds.add(id);
        } while (cursor.moveToNext());
        cursor.close();
        return paramIds;
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
