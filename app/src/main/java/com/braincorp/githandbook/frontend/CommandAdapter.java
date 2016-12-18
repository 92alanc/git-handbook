package com.braincorp.githandbook.frontend;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.braincorp.githandbook.R;
import com.braincorp.githandbook.model.Command;

import java.util.ArrayList;

/**
 * Command adapter class
 * Created by Alan Camargo - December 2016
 */
public class CommandAdapter extends ArrayAdapter<Command>
{

    private Context context;
    private int resource;
    private ArrayList<Command> objects;

    /**
     * Creates an instance of CommandAdapter
     * @param context - the context
     * @param resource - the resource ID
     * @param objects - the objects
     */
    public CommandAdapter(Context context, int resource,
                          ArrayList<Command> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CommandHolder holder = new CommandHolder();
        View row = convertView;
        if (row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            holder.titleRow = (TextView)row.findViewById(R.id.commandTitleRow);
            holder.parametersRow = (TextView)row.findViewById(R.id.commandParamsRow);
            row.setTag(holder);
        }
        else
            holder = (CommandHolder)row.getTag();
        Command command = objects.get(position);
        holder.titleRow.setText(command.getTitle());
        // TODO: set parameter
        return row;
    }

    /**
     * Command holder class
     */
    private static class CommandHolder
    {

        TextView titleRow, parametersRow;

    }

}
