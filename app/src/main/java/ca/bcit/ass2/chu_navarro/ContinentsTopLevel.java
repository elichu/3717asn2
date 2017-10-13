package ca.bcit.ass2.chu_navarro;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by E on 2017-10-09.
 */

public class ContinentsTopLevel extends Activity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    // URL to get contacts JSON
    private static String SERVICE_URL;// = "https://restcountries.eu/rest/v2/region/europe";
    ArrayList<String> continentURLs = new ArrayList<String>();
    ArrayList<String> continentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continent_list);

        Intent intent = getIntent();
        String countriesURL = intent.getStringExtra("allCountries");
        SERVICE_URL = countriesURL;

        continentURLs.add("https://restcountries.eu/rest/v2/region/americas");
        continentURLs.add("https://restcountries.eu/rest/v2/region/europe");
        continentURLs.add("https://restcountries.eu/rest/v2/region/asia");
        continentURLs.add("https://restcountries.eu/rest/v2/region/africa");
        continentURLs.add("https://restcountries.eu/rest/v2/region/oceania");
        continentURLs.add("https://restcountries.eu/rest/v2/region/polar");

        lv = (ListView) findViewById(R.id.continent_list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0) {
                    Intent intent = new Intent(ContinentsTopLevel.this, MainActivity.class);
                    intent.putExtra("allCountries", "https://restcountries.eu/rest/v2/region/americas");
                    startActivity(intent);
                }
                if (i==1) {
                    Intent intent = new Intent(ContinentsTopLevel.this, MainActivity.class);
                    intent.putExtra("allCountries", "https://restcountries.eu/rest/v2/region/europe");
                    startActivity(intent);
                }
                if (i==2) {
                    Intent intent = new Intent(ContinentsTopLevel.this, MainActivity.class);
                    intent.putExtra("allCountries", "https://restcountries.eu/rest/v2/region/asia");
                    startActivity(intent);
                }
                if (i==3) {
                    Intent intent = new Intent(ContinentsTopLevel.this, MainActivity.class);
                    intent.putExtra("allCountries", "https://restcountries.eu/rest/v2/region/africa");
                    startActivity(intent);
                }
                if (i==4) {
                    Intent intent = new Intent(ContinentsTopLevel.this, MainActivity.class);
                    intent.putExtra("allCountries", "https://restcountries.eu/rest/v2/region/oceania");
                    startActivity(intent);
                }
                if (i==5) {
                    Intent intent = new Intent(ContinentsTopLevel.this, MainActivity.class);
                    intent.putExtra("allCountries", "https://restcountries.eu/rest/v2/region/polar");
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ContinentsTopLevel.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            for (String url : continentURLs) {
                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        // Getting JSON Array node
                        JSONArray regionArray = new JSONArray(jsonStr);

                        JSONObject c = regionArray.getJSONObject(0);
                        String region = c.getString("region");

                        continentList.add(region);

                    } catch (final JSONException e) {
                        Log.e(TAG, "Json parsing error: " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "Couldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            }
            return null;

        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            ContinentAdapter adapter = new ContinentAdapter(ContinentsTopLevel.this, continentList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }

    }
}
