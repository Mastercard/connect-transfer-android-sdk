# Connect Transfer Android SDK


## Using the Connect Transfer Android SDK

The Connect Transfer Android SDK is distributed as a compiled binary in Maven Central which allows you to easily integrate our SDK into your development projects.
Maven central is Android’s officially supported format for distributing binary libraries to multiple platforms and architectures in a single bundle.

## Compatibility

The Connect Android SDK supports the following Android versions.
* Android 7.0 (Lollipop) or later
* minSdkVersion 24 or later


## Install the Connect Transfer Android SDK

Install the Connect Android SDK using Maven Central
Modify your root-level Gradle file (build.gradle) as follows:

```
allprojects {
   repositories { {
       google()
       mavenCentral()
    }
}
```
Modify your app-level Gradle file (build.gradle) as follows:

```
android {
 defaultConfig {
   minSdkVersion 24 // or greater
 }
}


dependencies {
 // ...
 implementation 'com.mastercard.openbanking.connect:connect-transfer-sdk:<latest version>'
}
```
Note: The latest version of the Connect Transfer Android SDK can be found in [Maven Central](https://central.sonatype.com/artifact/com.mastercard.openbanking.connect/connect-transfer-sdk)


## Update Android application settings

The Connect Android SDK requires internet access to connect with our servers, so you need to add internet permissions to the AndroidManifest.xml file:

```
<uses-permission android:name="android.permission.INTERNET">
```



## Integration

**ConnectTransfer Class**

The ConnectTransfer class contains a start method that when called, starts an activity with the supplied event listener. The SDK only allows a single instance of the ConnectTransfer activity to run. If you start ConnectTransfer while a ConnectTransfer activity is already running, a RuntimeException is thrown.

The ConnectTransfer Android SDK’s main component is the ConnectTransfer class that contains a static start method, which runs an activity that connects with the ConnectTransferEventListener.

To access the APIs in the SDK include the following imports:

```
import com.mastercard.openbanking.connect.transfer.ui.activity.ConnectTransfer;
import com.mastercard.openbanking.connect.transfer.events.ConnectTransferEventListener;
```

The ConnectTransfer.start() method launches the activity, requiring:

* A valid Context.
* The Connect Transfer URL.
* An instance of ConnectTransferEventListener to handle SDK events.

**Java:**

```
ConnectTransfer.start(context, url, new ConnectTransferEventListener() {
    @Override
    public void onInitializeConnectTransfer(JSONObject data) {
        Log.d("ConnectTransfer", "onInitializeConnectTransfer: " + data.toString());
    }
 
    @Override
    public void onTermsAndConditionsAccepted(JSONObject data) {
        Log.d("ConnectTransfer", "onTermsAndConditionsAccepted: " + data.toString());
    }
 
    @Override
    public void onLaunchTransferSwitch(JSONObject data) {
        Log.d("ConnectTransfer", "onLaunchTransferSwitch: " + data.toString());
    }
 
    @Override
    public void onTransferEnd(JSONObject data) {
        Log.d("ConnectTransfer", "onTransferEnd: " + data.toString());
    }
 
    @Override
    public void onUserEvent(JSONObject data) {
        Log.d("ConnectTransfer", "onUserEvent: " + data.toString());
    }
});
```

**Kotlin:**

```
ConnectTransfer.start(context, url, object : ConnectTransferEventListener {
    override fun onInitializeConnectTransfer(data: JSONObject) {
        Log.d("ConnectTransfer", "onInitializeConnectTransfer: ${data.toString()}")
    }
 
    override fun onTermsAndConditionsAccepted(data: JSONObject) {
        Log.d("ConnectTransfer", "onTermsAndConditionsAccepted: ${data.toString()}")
    }
 
    override fun onLaunchTransferSwitch(data: JSONObject) {
        Log.d("ConnectTransfer", "onLaunchTransferSwitch: ${data.toString()}")
    }
 
    override fun onTransferEnd(data: JSONObject) {
        Log.d("ConnectTransfer", "onTransferEnd: ${data.toString()}")
    }
 
    override fun onUserEvent(data: JSONObject) {
        Log.d("ConnectTransfer", "onUserEvent: ${data.toString()}")
    }
})
```
Throughout Connect Transfer's flow events about the state of the application are sent as JSONObjects to the ConnectTransferEventListener methods.

The onInitializeConnectTransfer, onTermsAndConditionsAccepted, onLaunchTransferSwitch, onTransferEnd, and onUserEvent callback functions will have a JSONObject parameter that contains data about the event.

**Parameters**

Java:

`public static void start(Context context, String connectTransferUrl, ConnectTransferEventListener listener)
`

Kotlin:

`fun start(context: Context,connectTransferUrl: String, listener: ConnectTransferEventListener)
`

**Details:**

| Parameter  | Type     | Description                    |
|:-----------|:---------|:-------------------------------|
| `context`   | `Context` | The context from which the function is called, typically an Activity or Fragment context. |
| `connectTransferUrl`   | `String`    | The Connect Transfer URL required to initiate the ConnectTransfer flow. |
| `listener`   | `ConnectTransferEventListener`| An instance of ConnectTransferEventListener to receive callbacks for various events, such as when the transfer is initialized, terms are accepted, and the transfer is completed. |

