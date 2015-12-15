package orz.kassy.railsapi;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kashimoto on 2015/11/22.
 */
public class MyApi {

    private static final String USER_AGENT = "WeatherForecasts Sample";

    private static final String URL = "http://192.168.0.6:3000/people";

    public static String getApi() throws IOException, JSONException {
        StringBuilder sb = new StringBuilder();
        java.net.URL u = new URL(URL);
        InputStream is;
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        is = new BufferedInputStream(con.getInputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
