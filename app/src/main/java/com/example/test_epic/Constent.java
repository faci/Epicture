package com.example.test_epic;

import static com.example.test_epic.BuildConfig.CLIENT_ID;
import static com.example.test_epic.BuildConfig.CLIENT_SECRET;

public class Constent {
    /*
      Logging flag
     */
    public static final boolean LOGGING = false;

    /*
      Your imgur client id. You need this to upload to imgur.
      More here: https://api.imgur.com/
     */
    public static final String MY_IMGUR_CLIENT_ID = <MY_IMGUR_CLIENT_ID>;

    /*
      Redirect URL for android.
     */
    public static final String MY_IMGUR_REDIRECT_URL = "http://android";

    /*
      URL requete
     */
    public static final String URL_REQUEST = "https://api.imgur.com";


    /*
      Client Auth
     */
    public static String getClientAuth() {
        return "Client-ID " + MY_IMGUR_CLIENT_ID;
    }
}
