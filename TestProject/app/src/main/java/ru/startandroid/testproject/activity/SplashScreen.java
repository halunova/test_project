package ru.startandroid.testproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.startandroid.testproject.R;
import ru.startandroid.testproject.activity.MainActivity;

public class SplashScreen extends AppCompatActivity {
    private String BASE_URL = "https://github.com";
    private String clientId = "b0a3552b65ffe5311162";
    private String clientSecret = "0904d3e58d9141bc9aeafefa3aef405802f5dde2";
    private String redirectUri = "test-application://callback";
    private String access_token=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Intent githubIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize"
                            + "?client_id=" + clientId + "&scope=repo&redirect_uri=" + redirectUri));
                    startActivity(githubIntent);

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
    }
}
