package com.braincorp.githandbook.database;

import android.content.Context;
import com.braincorp.githandbook.interfaces.Repository;

import java.util.ArrayList;

/**
 * Base DB repository class
 * Created by Alan Camargo - December 2016
 */
abstract class BaseRepository<T> implements Repository<T>
{

    Context context;
    DatabaseHelper databaseHelper;

    /**
     * Creates an instance of BaseRepository
     * @param context - the context
     */
    BaseRepository(Context context)
    {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    /**
     * Selects all instances
     * @return all instances
     */
    public abstract ArrayList<T> selectAll();

    /**
     * Selects one instance
     * @param id - the ID of the item to be selected
     * @return instance
     */
    public abstract T select(int id);

    /**
     * Selects all instances where a condition is met
     * e.g.: SELECT * FROM [TABLE] WHERE columns = values
     * @param columns - the columns
     * @param values - the values
     * @return instances
     */
    public abstract ArrayList<T> selectWhere(Object[] columns, Object[] values);

    /**
     * Gets the ID of the last instance
     * @return last ID
     */
    public abstract int getLastId();

}
