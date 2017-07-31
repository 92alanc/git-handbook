package com.braincorp.githandbook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.braincorp.githandbook.R;
import com.braincorp.githandbook.database.MeaningRepository;
import com.braincorp.githandbook.database.MeaningsTable;
import com.braincorp.githandbook.listeners.OnItemClickListener;
import com.braincorp.githandbook.model.Command;

import java.util.List;

/**
 * Command adapter class
 * Created by Alan Camargo - December 2016
 */
public class CommandAdapter extends RecyclerView.Adapter<CommandAdapter.CommandHolder> {

    private Context context;
    private List<Command> objects;
    private OnItemClickListener onItemClickListener;

    /**
     * Creates an instance of CommandAdapter
     * @param context - the context
     * @param objects - the objects
     * @param onItemClickListener - the item click listener
     */
    public CommandAdapter(Context context, List<Command> objects,
                          OnItemClickListener onItemClickListener) {
        this.context = context;
        this.objects = objects;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public CommandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final boolean attachToRoot = false;
        View view = inflater.inflate(R.layout.card_command, parent, attachToRoot);
        return new CommandHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(CommandHolder holder, int position) {
        Command command = objects.get(position);
        holder.titleRow.setText(command.getTitle());
        int commands = MeaningRepository.getInstance(context)
                                        .selectWhere(MeaningsTable.COLUMN_COMMAND,
                                                     command.getId()).size();
        if (commands == 1)
            holder.commandsRow.setText(R.string.command);
        else {
            String text = String.format(context.getString(R.string.commands), commands);
            holder.commandsRow.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    /**
     * Command holder class
     */
    static class CommandHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleRow, commandsRow;
        View.OnClickListener onClickListener;
        OnItemClickListener onItemClickListener;

        CommandHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            onClickListener = this;
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(onClickListener);
            titleRow = itemView.findViewById(R.id.commandTitleRow);
            commandsRow = itemView.findViewById(R.id.commandsRow);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getLayoutPosition());
        }

    }

}
