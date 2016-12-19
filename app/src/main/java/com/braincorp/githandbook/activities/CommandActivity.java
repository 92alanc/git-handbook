package com.braincorp.githandbook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.braincorp.githandbook.R;
import com.braincorp.githandbook.backend.AppConstants;
import com.braincorp.githandbook.database.*;
import com.braincorp.githandbook.frontend.ParameterAdapter;
import com.braincorp.githandbook.model.Command;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;

public class CommandActivity extends AppCompatActivity
{

    private String commandTitle;
    private ListView listView;
    private MeaningRepository meaningRepository;
    private ParameterRepository parameterRepository;
    private int commandId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showAds();
        commandId = getIntent().getIntExtra(AppConstants.EXTRA_COMMAND_ID, AppConstants.DEFAULT_INT_EXTRA);
        commandTitle = getIntent().getStringExtra(AppConstants.EXTRA_COMMAND_TITLE);
        parameterRepository = ParameterRepository.getInstance(this);
        meaningRepository = MeaningRepository.getInstance(this);
        setListView();
        setTextViews();
    }

    private void showAds()
    {
        AdView adView = (AdView)findViewById(R.id.commandAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void setTextViews()
    {
        // Command
        TextView commandText = (TextView)findViewById(R.id.commandText);
        commandText.setText(commandTitle);
        CommandRepository commandRepository = CommandRepository.getInstance(this);
        Command command = commandRepository.select(commandId);

        // Parameter
        TextView paramText = (TextView)findViewById(R.id.parameterText);
        String param = (String)listView.getSelectedItem();
        paramText.setText(param);
        int paramId = parameterRepository.selectWhere(ParametersTable.COLUMN_ID, ParametersTable.COLUMN_CONTENT,
                param);

        // Meaning
        String meaning = meaningRepository.selectWhere(new String[]
                                                        {
                                                                MeaningsTable.COLUMN_COMMAND,
                                                                MeaningsTable.COLUMN_PARAMETER
                                                        },
                                                        new Object[]
                                                                {
                                                                        command.getId(),
                                                                        paramId
                                                                });
        TextView meaningText = (TextView)findViewById(R.id.meaningText);
        meaningText.setText(meaning);
    }

    private void setListView()
    {
        listView = (ListView)findViewById(R.id.paramsListView);
        ArrayList<String> objects = new ArrayList<>();
        ArrayList<Integer> paramIds = meaningRepository.selectWhere(commandId);
        for (int paramId : paramIds)
            objects.add(parameterRepository.select(paramId));
        ParameterAdapter adapter = new ParameterAdapter(this,
                                                        R.layout.list_view_item_param,
                                                        objects);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                // TODO: implement
                listView.setSelection(i);
            }
        });
    }

}
