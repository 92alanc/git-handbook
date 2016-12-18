package com.braincorp.githandbook.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.braincorp.githandbook.R;
import com.braincorp.githandbook.database.CommandRepository;
import com.braincorp.githandbook.frontend.CommandAdapter;
import com.braincorp.githandbook.model.Command;

import java.util.ArrayList;

/**
 * Main activity class
 * Created by Alan Camargo - December 2016
 */
public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.item_about:
                showAppInfo();
                break;
            case R.id.item_reference:
                openGitWebPage();
                break;
            case R.id.item_search:
                // TODO: search
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAppInfo()
    {
        try
        {
            PackageManager manager = getPackageManager();
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            String version = info.versionName;
            String title = String.format("%1$s %2$s", getString(R.string.app_name), version);
            AlertDialog.Builder dialogue = new AlertDialog.Builder(this);
            dialogue.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    // Do nothing
                }
            });
            dialogue.setTitle(title);
            dialogue.setMessage(R.string.about_text);
            dialogue.show();
        }
        catch (PackageManager.NameNotFoundException e)
        {
            // Damn! Something really wrong happened here
            e.printStackTrace();
        }
    }

    private void openGitWebPage()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.git_url)));
        startActivity(intent);
    }

    private void setListView()
    {
        ListView listView = (ListView)findViewById(R.id.mainList);
        ArrayList<Command> commands = CommandRepository.getInstance(this).selectAll();
        CommandAdapter adapter = new CommandAdapter(this,
                R.layout.list_view_item_command, commands);
        listView.setAdapter(adapter);
    }

}
