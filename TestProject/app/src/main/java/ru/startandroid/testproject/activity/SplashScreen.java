package ru.startandroid.testproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.startandroid.testproject.R;
import ru.startandroid.testproject.activity.MainActivity;

public class SplashScreen extends AppCompatActivity {

    // TODO naming JLC violation
    private String BASE_URL = "https://github.com";
    private String clientId = "b0a3552b65ffe5311162";
    private String clientSecret = "0904d3e58d9141bc9aeafefa3aef405802f5dde2";
    private String redirectUri = "test-application://callback";
    private String access_token=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // TODO if you need delay use Handler.postDelayed(int delay, runnable) instead of Thread sleep.
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    // TODO Magic number.
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    // TODO avoid String hardcode use constants instead.
                    Intent githubIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize"
                            + "?client_id=" + clientId + "&scope=repo&redirect_uri=" + redirectUri));
                    startActivity(githubIntent);

                    finish();
                } catch (InterruptedException e) {
                    // TODO do not ignore exceptions
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
    }
}
