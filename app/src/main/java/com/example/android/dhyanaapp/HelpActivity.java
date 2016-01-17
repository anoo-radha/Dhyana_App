package com.example.android.dhyanaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This activity is called when the help is pressed from menu.
 */
public class HelpActivity extends AppCompatActivity {
    private static final boolean GOOGLE_PLAYSTORE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);
        String[] help_array = getResources().getStringArray(R.array.help_array);
        String[] features_array = getResources().getStringArray(R.array.features_array);
        ListView help_list = (ListView) findViewById(R.id.help_list);
        ListView features_list = (ListView) findViewById(R.id.features_list);
        help_list.setAdapter(new ArrayAdapter<>(this, R.layout.custom_bulleted_list, help_array));
        features_list.setAdapter(new ArrayAdapter<>(this, R.layout.custom_bulleted_list, features_array));
    }

    /**
     * This method creates a menu bar for the app.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_help, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This method is called when the menu options are clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contact_id:
                String recepientEmail = "anu_rad80@yahoo.com";
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"anu_rad80@yahoo.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(Intent.createChooser(emailIntent, "choose one"));
                } else {
                    Toast.makeText(this, "No applications to support email!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rating_id:
                if (GOOGLE_PLAYSTORE) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.example.android.dhyana"));
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "App can be rated after loading to GooglePlayStore", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
