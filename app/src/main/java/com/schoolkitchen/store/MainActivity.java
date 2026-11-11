package com.schoolkitchen.store;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;

/**
 * Opens the shared Kitchen Store page (hosted on Claude) in a Chrome Custom Tab.
 * The data lives online, so the phone app and the web version always show the same stock.
 */
public class MainActivity extends Activity {
    private boolean opened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) opened = savedInstanceState.getBoolean("opened", false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (opened) {
            // The person came back from the store page (pressed Back): close the app.
            finish();
            return;
        }
        opened = true;
        openStore();
    }

    @Override
    protected void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out);
        out.putBoolean("opened", opened);
    }

    private void openStore() {
        Uri url = Uri.parse(getString(R.string.store_url));
        CustomTabColorSchemeParams colors = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(Color.parseColor("#1F6F4A"))
                .build();
        CustomTabsIntent tab = new CustomTabsIntent.Builder()
                .setDefaultColorSchemeParams(colors)
                .setShowTitle(true)
                .setUrlBarHidingEnabled(true)
                .build();
        try {
            tab.launchUrl(this, url);
        } catch (ActivityNotFoundException e) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, url));
            } catch (ActivityNotFoundException e2) {
                Toast.makeText(this, "Please install Google Chrome to use Kitchen Store", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
