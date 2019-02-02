package com.android.hvs.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = (EditText)findViewById(R.id.website_edittext);
        mLocationEditText = (EditText)findViewById(R.id.location_edittext);
        mShareEditText = (EditText)findViewById(R.id.share_text_edittext);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webPage = Uri.parse(url);

        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);

        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        } else {
            Log.d("IMPLICIT INTENTS", "Can't handle this intent");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri address = Uri.parse("geo:0,0?q=" + loc);

        Intent locationIntent = new Intent(Intent.ACTION_VIEW, address);

        if (locationIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(locationIntent);
        } else {
            Log.d("IMPLICIT INTENTS","Can't handle this intent");
        }
    }

    public void shareText(View view) {
        String text = mShareEditText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(text)
                .startChooser();
    }
}
