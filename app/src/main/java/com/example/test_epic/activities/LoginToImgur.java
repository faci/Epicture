package com.example.test_epic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.test_epic.R;

import static com.example.test_epic.BuildConfig.CLIENT_ID;


public class LoginToImgur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_to_imgur);
        initControls();

    }

    private void initControls()
    {
        WebView imgurWebView = (WebView) findViewById(R.id.imgur_web_view);
        imgurWebView.setBackgroundColor(Color.TRANSPARENT);
        imgurWebView.loadUrl("https://api.imgur.com/oauth2/authorize?client_id="+CLIENT_ID+"&response_type=token");
        imgurWebView.getSettings().setJavaScriptEnabled(true);

        imgurWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("https://www.getpostman.com/oauth2/callback")) {
                    splitUrl(url, view);
                } else {
                    view.loadUrl(url);
                }

                return true;
            }

        });
    }

    private void splitUrl(String url, WebView view) {
        String[] outerSplit = url.split("\\#")[1].split("\\&");
        String username = null;
        String accessToken = null;
        String refreshToken = null;

        int index = 0;

        for (String s : outerSplit) {
            String[] innerSplit = s.split("\\=");

            switch (index) {
                // Access Token
                case 0:
                    accessToken = innerSplit[1];
                    break;

                // Refresh Token
                case 3:
                    refreshToken = innerSplit[1];
                    break;

                // Username
                case 4:
                    username = innerSplit[1];
                    break;
                default:

            }

            index++;
        }
        if (username != null && accessToken != null && refreshToken != null)
            redirectHome(username, accessToken, refreshToken);
        else {
            System.out.println(username);
            System.out.println(accessToken);
            System.out.println(refreshToken);
        }
    }

    private void redirectHome(String username, String accessToken, String refreshToken) {
        Intent intent = new Intent(LoginToImgur.this, bottom_bar.class);
        Bundle b = new Bundle();
        b.putString("username", username);
        b.putString("accessToken", accessToken);
        b.putString("refreshToken", refreshToken);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
}