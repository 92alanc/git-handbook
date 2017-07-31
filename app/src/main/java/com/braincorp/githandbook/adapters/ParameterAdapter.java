package com.braincorp.githandbook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.braincorp.githandbook.R;
import com.braincorp.githandbook.backend.BackEndTools;
import com.braincorp.githandbook.listeners.OnItemClickListener;

import java.util.List;

/**
 * Parameter adapter
 * Created by Alan Camargo - December 2016
 */
public class ParameterAdapter extends RecyclerView.Adapter<ParameterAdapter.ParameterHolder> {

    private Context context;
    private List<String> objects;
    private OnItemClickListener onItemClickListener;

    /**
     * Creates an instance of ParameterAdapter
     * @param context - the context
     * @param objects - the objects
     */
    public ParameterAdapter(Context context, List<String> objects,
                            OnItemClickListener onItemClickListener) {
        this.context = context;
        this.objects = objects;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ParameterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final boolean attachToRoot = false;
        View view = layoutInflater.inflate(R.layout.card_param, parent, attachToRoot);
        return new ParameterHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ParameterHolder holder, int position) {
        String param = objects.get(position);
        if (param == null)
            param = "<NULL>";
        else
            param = context.getString(BackEndTools.getStringResourceKey(context, param));
        holder.parameterRow.setText(param);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    static class ParameterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView parameterRow;
        View.OnClickListener onClickListener;
        OnItemClickListener onItemClickListener;

        ParameterHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            onClickListener = this;
            itemView.setOnClickListener(onClickListener);
            parameterRow = itemView.findViewById(R.id.paramRow);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getLayoutPosition());
        }

    }

}
