package org.mozilla.iot.thingscontroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

/**
 * Things Controller.
 */
public class MainActivity extends Activity {

    private static final String HOME_PAGE = "https://mygatewayname.mozilla-iot.org";

    private View mContentView;
    private WebView webview;
    private WebThingServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContentView = findViewById(R.id.main_content);
        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setInitialScale(100);
        webview.loadUrl(HOME_PAGE);
    }

    /**
     * Navigate to the home page.
     *
     * @param view
     */
    public void home(View view) {
        webview.loadUrl(HOME_PAGE);
    }

    /**
     * Navigate back in session history.
     *
     * @param view
     */
    public void back(View view) {
        webview.goBack();
    }

    /**
     * Reload the current page.
     *
     * @param view
     */
    public void reload(View view) {
        webview.reload();
    }

    /**
     *  Start web thing server when app is resumed.
     */
    public void onResume() {
        super.onResume();
        try {
            server = new WebThingServer();
        } catch (IOException e) {
            System.out.println("Failed to instantiate Web Thing Server");
        }
        try {
            server.start();
        } catch (IOException e) {
            System.out.println("Failed to start Web Thing Server");
            e.printStackTrace();
        }
    }

    /**
     *  Stop web thing server when app is paused.
     */
    public void onPause() {
        super.onPause();
        if(server != null) {
            server.stop();
        }
    }

    /**
     *  Stop web thing server when app is destroyed.
     */
    public void onDestroy() {
        super.onDestroy();
        if(server != null) {
            server.stop();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mContentView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
}
