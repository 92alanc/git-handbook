package com.braincorp.githandbook.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.braincorp.githandbook.model.Command;

import java.util.ArrayList;

/**
 * Command repository class
 * Created by Alan Camargo - December 2016
 */
public class CommandRepository extends BaseRepository<Command> {

    private static CommandRepository instance;

    /**
     * Singleton constructor - ensures there will only be
     * a single instance of CommandRepository
     * @return instance
     */
    public static CommandRepository getInstance(Context context) {
        if (instance == null)
            instance = new CommandRepository(context);
        return instance;
    }

    private SQLiteDatabase reader;

    /**
     * Default constructor
     * @param context - the context
     */
    private CommandRepository(Context context) {
        super(context);
        databaseHelper = new DatabaseHelper(context);
    }

    /**
     * Selects all instances
     * @return all instances
     */
    @Override
    public ArrayList<Command> selectAll() {
        ArrayList<Command> commands = new ArrayList<>();
        if (reader == null)
            reader = databaseHelper.getReadableDatabase();
        Cursor cursor = reader.query(CommandsTable.TABLE_NAME, null, null, null, null,
                                     null, CommandsTable.COLUMN_TITLE + " ASC");
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Command command;
            int id;
            String title;
            do {
                id = cursor.getInt(cursor.getColumnIndex(CommandsTable.COLUMN_ID));
                title = cursor.getString(cursor.getColumnIndex(CommandsTable.COLUMN_TITLE));
                command = new Command(id, title);
                commands.add(command);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return commands;
    }

    /**
     * Selects one instance
     * @param id - the ID of the item to be selected
     * @return instance
     */
    @Override
    public Command select(int id) {
        if (id > 0 && id <= getLastId()) {
            if (reader == null)
                reader = databaseHelper.getReadableDatabase();
            Cursor cursor = reader.query(CommandsTable.TABLE_NAME, null, CommandsTable.COLUMN_ID
                                                                         + " = " + String.valueOf(id),
                                         null, null, null, null, "1");
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                String title = cursor.getString(cursor.getColumnIndex(CommandsTable.COLUMN_TITLE));
                cursor.close();
                return new Command(id, title);
            } else {
                cursor.close();
                return null;
            }
        }
        else
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
    public ArrayList<Command> selectWhere(String column, Object value) {
        // Not necessary
        return null;
    }

    /**
     * Gets the ID of the last instance
     * @return last ID
     */
    @Override
    public int getLastId() {
        if (reader == null)
            reader = databaseHelper.getReadableDatabase();
        Cursor cursor = reader.query(CommandsTable.TABLE_NAME, new String[] { CommandsTable.COLUMN_ID },
                                     null, null, null, null,
                                     CommandsTable.COLUMN_ID + " DESC", "1");
        cursor.moveToFirst();
        int lastId = 0;
        if (cursor.getCount() > 0)
            lastId = cursor.getInt(cursor.getColumnIndex(CommandsTable.COLUMN_ID));
        cursor.close();
        return lastId;
    }

}
