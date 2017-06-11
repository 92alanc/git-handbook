package com.braincorp.githandbook.frontend;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.braincorp.githandbook.R;
import com.braincorp.githandbook.backend.BackEndTools;

import java.util.ArrayList;

/**
 * Parameter adapter
 * Created by Alan Camargo - December 2016
 */
public class ParameterAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resource;
    private ArrayList<String> objects;

    /**
     * Creates an instance of ParameterAdapter
     * @param context - the context
     * @param resource - the resource ID
     * @param objects - the objects
     */
    public ParameterAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParameterHolder holder = new ParameterHolder();
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            holder.parameterRow = (TextView)row.findViewById(R.id.paramRow);
            row.setTag(holder);
        } else
            holder = (ParameterHolder)row.getTag();
        String param = objects.get(position);
        if (param == null)
            param = "<NULL>";
        else
            param = context.getString(BackEndTools.getStringResourceKey(context, param));
        holder.parameterRow.setText(param);
        return row;
    }

    private static class ParameterHolder {

        TextView parameterRow;

    }

}
