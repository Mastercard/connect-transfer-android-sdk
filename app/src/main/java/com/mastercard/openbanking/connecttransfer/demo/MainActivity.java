package com.mastercard.openbanking.connecttransfer.demo;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mastercard.openbanking.connect.transfer.ui.activity.ConnectTransfer;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText mEditConnectUrl;

    private TextView textViewTitle;
    private EditText editConnectUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add click listener for EventListener
        Button mStartButtonEventHandler = findViewById(R.id.startButtonEventHandler);

        mStartButtonEventHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(new ConsoleConnectAtomicEventHandler());
            }
        });

        this.mEditConnectUrl = findViewById(R.id.editConnectUrl);

        textViewTitle = findViewById(R.id.textView);
        editConnectUrl = findViewById(R.id.editConnectUrl);
    }

    private void launchActivity(ConsoleConnectAtomicEventHandler eventHandler) {
        String url = getEditConnectUrl();

        if (url.length() > 0) {
            // Null out text so we can repeat with new link after Connect Activity closes.
            mEditConnectUrl.setText("");

            Log.i(TAG, ">>> Launching Connect activity");

            ConnectTransfer.start(this, url, new ConsoleConnectAtomicEventHandler());

        }
    }

    private String getEditConnectUrl() {
        String rawUrl = this.mEditConnectUrl.getText().toString();
        return rawUrl.replace("localhost:", "10.0.2.2:");
    }
}
