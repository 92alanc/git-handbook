package com.braincorp.githandbook.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.braincorp.githandbook.R;
import com.braincorp.githandbook.adapters.CommandAdapter;
import com.braincorp.githandbook.backend.AppConstants;
import com.braincorp.githandbook.database.CommandRepository;
import com.braincorp.githandbook.listeners.OnItemClickListener;
import com.braincorp.githandbook.model.Command;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

/**
 * Main activity class
 * Created by Alan Camargo - December 2016
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        populateRecyclerView();
        showAds();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_about:
                showAppInfo();
                break;
            case R.id.item_reference:
                openGitWebPage();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAppInfo() {
        try {
            PackageManager manager = getPackageManager();
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            String version = info.versionName;
            String title = String.format("%1$s %2$s", getString(R.string.app_name), version);
            AlertDialog.Builder dialogue = new AlertDialog.Builder(this);
            dialogue.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Do nothing
                }
            });
            dialogue.setTitle(title);
            dialogue.setMessage(R.string.about_text);
            dialogue.setIcon(R.mipmap.ic_launcher);
            dialogue.show();
        } catch (PackageManager.NameNotFoundException e) {
            // Damn! Something really wrong happened here
            e.printStackTrace();
        }
    }

    private void openGitWebPage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.git_url)));
        startActivity(intent);
    }

    private void populateRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_main);
        recyclerView.setLayoutManager(layoutManager);
        final List<Command> commands = CommandRepository.getInstance(this).selectAll();
        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, CommandActivity.class);
                Command command = commands.get(position);
                intent.putExtra(AppConstants.EXTRA_COMMAND_ID, command.getId());
                intent.putExtra(AppConstants.EXTRA_COMMAND_TITLE, command.getTitle());
                startActivity(intent);
            }
        };
        CommandAdapter adapter = new CommandAdapter(this, commands, onItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void showAds() {
        AdView adView = findViewById(R.id.homeAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

}
