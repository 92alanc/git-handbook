package com.braincorp.githandbook.database;

import android.content.Context;
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
        return null;
    }

    /**
     * Selects all instances where a condition is met
     * e.g.: SELECT * FROM [TABLE] WHERE columns = values
     * @param columns - the columns
     * @param values  - the values
     * @return instances
     */
    @Override
    public ArrayList<String> selectWhere(Object[] columns, Object[] values)
    {
        return null;
    }

    /**
     * Gets the ID of the last instance
     * @return last ID
     */
    @Override
    public int getLastId()
    {
        return 0;
    }

}
