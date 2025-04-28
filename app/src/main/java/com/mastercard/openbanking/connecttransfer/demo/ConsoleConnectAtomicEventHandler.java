package com.mastercard.openbanking.connecttransfer.demo;

import android.util.Log;

import com.mastercard.openbanking.connect.transfer.events.ConnectTransferEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class ConsoleConnectAtomicEventHandler implements ConnectTransferEventListener {
    private static final String TAG = "ConsoleEventHandler";

    @Override
    public void onInitializeConnectTransfer(JSONObject data) {
        logJsonEvent("onInitializeConnectTransfer", data);
    }

    @Override
    public void onTermsAndConditionsAccepted(JSONObject data) {
        logJsonEvent("onTermsAndConditionsAccepted", data);
    }

    @Override
    public void onLaunchTransferSwitch(JSONObject data) {
        logJsonEvent("onLaunchTransferSwitch", data);
    }

    @Override
    public void onTransferEnd(JSONObject data) {

        logJsonEvent("onTransferEnd", data);
    }

    @Override
    public void onUserEvent(JSONObject data) {
        logJsonEvent("onUserEvent", data);
    }

    @Override
    public void onErrorEvent(JSONObject data) {
        logJsonEvent("onErrorEvent", data);
    }

    private void logJsonEvent(String eventName, JSONObject data) {
        if (data == null) {
            Log.i(TAG, ">>> ConsoleEventHandler: Received " + eventName + " event: No data received");
            return;
        }

        String jsonString;
        try {
            jsonString = data.toString(4); // Pretty print with an indentation of 4 spaces
        } catch (JSONException e) {
            jsonString = data.toString(); // Fallback to unformatted JSON if an exception occurs
            Log.e(TAG, "Failed to format JSON data: " + e.getMessage());
        }

        Log.i(TAG, ">>> ConsoleEventHandler: Received " + eventName + " event:\n>>>>>> " + jsonString);
    }
}
