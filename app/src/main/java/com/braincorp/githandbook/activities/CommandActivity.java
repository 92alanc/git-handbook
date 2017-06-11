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
import com.braincorp.githandbook.backend.BackEndTools;
import com.braincorp.githandbook.database.*;
import com.braincorp.githandbook.frontend.ParameterAdapter;
import com.braincorp.githandbook.model.Command;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;

public class CommandActivity extends AppCompatActivity {

    private String meaning, example;
    private Command command;
    private ListView listView;
    private MeaningRepository meaningRepository;
    private ParameterRepository parameterRepository;
    private int paramId;
    private TextView parameterText, meaningText, exampleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showAds();
        buildCommand();
        parameterRepository = ParameterRepository.getInstance(this);
        meaningRepository = MeaningRepository.getInstance(this);
        parameterText = (TextView)findViewById(R.id.parameterText);
        meaningText = (TextView)findViewById(R.id.meaningText);
        exampleText = (TextView)findViewById(R.id.exampleText);
        setListView();
        setTextViews();
    }

    private void buildCommand() {
        int commandId = getIntent().getIntExtra(AppConstants.EXTRA_COMMAND_ID, AppConstants.DEFAULT_INT_EXTRA);
        String commandTitle = getIntent().getStringExtra(AppConstants.EXTRA_COMMAND_TITLE);
        command = new Command(commandId, commandTitle);
    }

    private void showAds() {
        AdView adView = (AdView)findViewById(R.id.commandAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void setTextViews() {
        // Command
        TextView commandText = (TextView)findViewById(R.id.commandText);
        commandText.setText(command.getTitle());

        // Parameter
        String param = (String)listView.getItemAtPosition(0);
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

    private void setListView() {
        listView = (ListView)findViewById(R.id.paramsListView);
        ArrayList<String> objects = new ArrayList<>();
        ArrayList<Integer> paramIds = meaningRepository.selectWhere(command.getId());
        for (int paramId : paramIds)
            objects.add(parameterRepository.select(paramId));
        ParameterAdapter adapter = new ParameterAdapter(this,
                                                        R.layout.list_view_item_param,
                                                        objects);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String param = (String)listView.getItemAtPosition(i);
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
        });
    }

}
