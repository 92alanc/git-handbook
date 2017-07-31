package com.braincorp.githandbook.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.braincorp.githandbook.R;
import com.braincorp.githandbook.adapters.ParameterAdapter;
import com.braincorp.githandbook.backend.AppConstants;
import com.braincorp.githandbook.backend.BackEndTools;
import com.braincorp.githandbook.database.MeaningRepository;
import com.braincorp.githandbook.database.MeaningsTable;
import com.braincorp.githandbook.database.ParameterRepository;
import com.braincorp.githandbook.database.ParametersTable;
import com.braincorp.githandbook.listeners.OnItemClickListener;
import com.braincorp.githandbook.model.Command;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class CommandActivity extends AppCompatActivity {

    private String meaning, example;
    private Command command;
    private MeaningRepository meaningRepository;
    private ParameterRepository parameterRepository;
    private int paramId;
    private TextView parameterText, meaningText, exampleText;
    private List<String> objects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        showAds();
        buildCommand();
        parameterRepository = ParameterRepository.getInstance(this);
        meaningRepository = MeaningRepository.getInstance(this);
        parameterText = findViewById(R.id.parameterText);
        meaningText = findViewById(R.id.meaningText);
        exampleText = findViewById(R.id.exampleText);
        populateRecyclerView();
        setTextViews();
    }

    private void buildCommand() {
        int commandId = getIntent().getIntExtra(AppConstants.EXTRA_COMMAND_ID, AppConstants.DEFAULT_INT_EXTRA);
        String commandTitle = getIntent().getStringExtra(AppConstants.EXTRA_COMMAND_TITLE);
        command = new Command(commandId, commandTitle);
    }

    private void showAds() {
        AdView adView = findViewById(R.id.commandAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void setTextViews() {
        // Command
        TextView commandText = findViewById(R.id.commandText);
        commandText.setText(command.getTitle());

        // Parameter
        String param = objects.get(0);
        paramId = parameterRepository.selectWhere(ParametersTable.COLUMN_ID, ParametersTable.COLUMN_CONTENT,
                                                  param);
        if (param != null)
            param = getString(BackEndTools.getStringResourceKey(getBaseContext(), param));
        parameterText.setText(param);

        // Meaning
        meaning = meaningRepository.selectWhere(new String[] {
                                                        MeaningsTable.COLUMN_COMMAND,
                                                        MeaningsTable.COLUMN_PARAMETER
                                                },
                                                new Object[] {
                                                        command.getId(),
                                                        paramId
                                                });
        example = meaningRepository.selectWhere(MeaningsTable.COLUMN_EXAMPLE,
                                                MeaningsTable.COLUMN_CONTENT, meaning).get(0);
        example = getString(BackEndTools.getStringResourceKey(getBaseContext(), example));
        exampleText.setText(example);
        meaning = getString(BackEndTools.getStringResourceKey(getBaseContext(), meaning));
        meaningText.setText(meaning);
    }

    private void populateRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_params);
        recyclerView.setLayoutManager(layoutManager);
        objects = new ArrayList<>();
        List<Integer> paramIds = meaningRepository.selectWhere(command.getId());
        for (int paramId : paramIds)
            objects.add(parameterRepository.select(paramId));
        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String param = objects.get(position);
                paramId = parameterRepository.selectWhere(ParametersTable.COLUMN_ID, ParametersTable.COLUMN_CONTENT,
                                                          param);
                if (param != null)
                    param = getString(BackEndTools.getStringResourceKey(getBaseContext(), param));
                parameterText.setText(param);

                meaning = meaningRepository.selectWhere(new String[] {
                                                                MeaningsTable.COLUMN_COMMAND,
                                                                MeaningsTable.COLUMN_PARAMETER
                                                        },
                                                        new Object[] {
                                                                command.getId(),
                                                                paramId
                                                        });
                example = meaningRepository.selectWhere(MeaningsTable.COLUMN_EXAMPLE,
                                                        MeaningsTable.COLUMN_CONTENT, meaning).get(0);
                example = getString(BackEndTools.getStringResourceKey(getBaseContext(), example));
                exampleText.setText(example);
                meaning = getString(BackEndTools.getStringResourceKey(getBaseContext(), meaning));
                meaningText.setText(meaning);
            }
        };
        ParameterAdapter adapter = new ParameterAdapter(this, objects, onItemClickListener);
        recyclerView.setAdapter(adapter);
    }

}
