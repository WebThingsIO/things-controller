package org.mozilla.iot.thingscontroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoRuntimeSettings;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

import java.io.IOException;

/**
 * Things Controller.
 */
public class MainActivity extends Activity {

    private static final String HOME_PAGE = "https://mygatewayname.mozilla-iot.org";

    private View mContentView;
    private GeckoView geckoview;
    private GeckoSession session;
    private static GeckoRuntime runtime;
    private WebThingServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContentView = findViewById(R.id.main_content);

        // Find the GeckoView in our layout
        geckoview = (GeckoView) findViewById(R.id.geckoview);

        if (runtime == null) {
            final GeckoRuntimeSettings.Builder runtimeSettingsBuilder =
                    new GeckoRuntimeSettings.Builder();

            if (BuildConfig.DEBUG) {
                // In debug builds, we want to load JavaScript resources fresh with
                // each build.
                runtimeSettingsBuilder.arguments(new String[] { "-purgecaches" });
            }

            final Bundle extras = getIntent().getExtras();
            if (extras != null) {
                runtimeSettingsBuilder.extras(extras);
            }
            runtimeSettingsBuilder
                    .useContentProcessHint(false)
                    .remoteDebuggingEnabled(true)
                    .nativeCrashReportingEnabled(true)
                    .javaCrashReportingEnabled(true)
                    .crashReportingJobId(1024)
                    .consoleOutput(true)
                    .trackingProtectionCategories(GeckoSession.TrackingProtectionDelegate.CATEGORY_ALL);

            runtime = GeckoRuntime.create(this, runtimeSettingsBuilder.build());
        }

        // Attach the GeckoView to a new GeckoSession
        session = new GeckoSession();
        session.open(runtime);
        geckoview.setSession(session);

        // Load a URL
        session.loadUri(HOME_PAGE);
    }

    /**
     * Navigate to the home page.
     *
     * @param view
     */
    public void home(View view) {
        session.loadUri(HOME_PAGE);

    }

    /**
     * Navigate back in session history.
     *
     * @param view
     */
    public void back(View view) {
        session.goBack();
    }

    /**
     * Reload the current page.
     *
     * @param view
     */
    public void reload(View view) {
        session.reload();
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
