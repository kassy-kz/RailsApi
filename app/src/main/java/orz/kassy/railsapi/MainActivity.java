package orz.kassy.railsapi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import net.vvakame.util.jsonpullparser.JsonFormatException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import orz.kassy.railsapi.json.FeedJson;
import orz.kassy.railsapi.json.FeedJsonGen;
import orz.kassy.railsapi.json.FeedJsonList;
import orz.kassy.railsapi.json.FeedJsonListGen;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ButterKnife.inject(this);

    }

    @OnClick(R.id.btnApiGet)
    void onClickApiGet() {
        new GetApiTask(this).execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetApiTask extends AsyncTask<String, Void, String> {
        private Context mContext;

        public GetApiTask(Context context) {
            mContext = context;
        }

        @Override
        protected String doInBackground(String... params) {
            String str = null;
            try {
                str = MyApi.getApi();
                Log.i(TAG, "APIGET : " + str);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.i(TAG, "s:" + s);
                List<FeedJson> list = FeedJsonGen.getList(s);
                for(int i=0; i<list.size(); i++) {
                    Log.i(TAG, list.get(i).getName());
                    Log.i(TAG, list.get(i).getMemo());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JsonFormatException e) {
                e.printStackTrace();
            }
        }
    }

}
