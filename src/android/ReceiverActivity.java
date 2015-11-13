package org.example;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.webkit.WebView;


import java.util.ArrayList;


public class ReceiverActivity extends CordovaPlugin {
    
    @Override
    public void onNewIntent(Intent intent) {
                    this.webView.loadUrl("javascript:alert('A23')");
	String action = intent.getAction();
        String type = intent.getType();

        if (action.equals(Intent.ACTION_SEND_MULTIPLE) && type != null) {
            if (type.equals("text/plain")) {

                String[] s = intent.getStringArrayExtra(Intent.EXTRA_TEXT);
				if(s!=null)
					this.webView.loadUrl("javascript:alert(' " + s[0] + "')");
            } else if (type.startsWith("image/")) {
                ArrayList<Uri> uri = (ArrayList) intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                /*for (int i = 0; i < uri.length; i++) {*/
                if (uri != null) {
                    //Log.d("URIIIII", "Thus is" + uri.toString());
                    //t.setText(uri.toString());
                    this.webView.loadUrl("javascript:alert('" + String.valueOf(uri) + "');");
					}
                //else Log.d("URIIIII", "Thus is null");
            }
        }

        if (action.equals(Intent.ACTION_SEND) && type != null) {
            if (type.equals("text/plain")) {

                String s = intent.getStringExtra(Intent.EXTRA_TEXT);
                //t.setText(s);
				this.webView.loadUrl("javascript:alert(' " + s + "')");
            } else if (type.startsWith("image/")) {
				  Uri uri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
 //               Log.d("URIIIII", "Thus is" + uri.toString());
   //             t.setText(uri.toString());
				  this.webView.loadUrl("javascript:alert('" + String.valueOf(uri) + "');");
            }
        }
    }

}
