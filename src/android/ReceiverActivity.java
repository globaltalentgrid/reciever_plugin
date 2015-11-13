package org.example;

import android.app.Activity;
import android.content.Intent;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.IntentService;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.MediaStore;

import android.os.Bundle;
import android.webkit.WebView;


public class ReceiverActivity extends CordovaPlugin {
    
    @Override
    public void onNewIntent(Intent intent) {
        webView.loadUrl("javascript:alert('A23');");
	    String action = intent.getAction();
        String type = intent.getType();

    if (Intent.ACTION_SEND.equals(action) && type != null) {
        if ("text/plain".equals(type)) {
            	String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		    	if (sharedText != null) {webView.loadUrl("javascript:handleNewIntent('sharedtext', '" + sharedText + "');");}
        } else if (type.startsWith("image/")) {
                 Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
    			if (imageUri != null) {webView.loadUrl("javascript:handleNewIntent('singleimage', '" + imageUri + "');");}
        } else if (type.startsWith("video/")) {
                Uri videoUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
    			if (videoUri != null) {webView.loadUrl("javascript:handleNewIntent('singlevideo', '" + videoUri + "');");}
        }
    } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
        if (type.startsWith("image/")) {
                ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
			    if (imageUris != null) {webView.loadUrl("javascript:handleNewIntent('multipleimages', '" + imageUris + "');");}
        } else if (type.startsWith("video/")) {
			    ArrayList<Uri> videoUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
			    if (videoUris != null) {webView.loadUrl("javascript:handleNewIntent('multiplevideos', '" + videoUris + "');");}
        }
    } else {
        // Handle other intents, such as being started from the home screen
    }
        
        
        
    }

}
