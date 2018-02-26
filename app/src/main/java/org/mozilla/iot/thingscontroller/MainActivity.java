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
