package ca.bcit.ass2.chu_navarro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    // URL to get contacts JSON
    private static String SERVICE_URL;// = "https://restcountries.eu/rest/v2/region/europe";
    ArrayList<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String countriesURL = intent.getStringExtra("allCountries");
        SERVICE_URL = countriesURL;

        countryList = new ArrayList<Country>();
        lv = (ListView) findViewById(R.id.mainList);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                CountryDetailAdapter adapter = new CountryDetailAdapter(MainActivity.this, countryList);
                Intent intent = new Intent(MainActivity.this, CountryDetailActivity.class);
                intent.putParcelableArrayListExtra("countries", countryList);
                intent.putExtra("index", i);
                startActivity(intent);

//                String selectedCountry = (String) adapterView.getItemAtPosition(i);
//                countryList.get(i);
            }
        });

            new GetContacts().execute();
}

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray countryJsonArray = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < countryJsonArray.length(); i++) {
                        JSONObject c = countryJsonArray.getJSONObject(i);


                        String name = c.getString("name");
                        String capital;
                        if(c.get("capital") != null) {
                            capital = c.getString("capital");
                        } else {
                            capital = "n/a";
                        }


                        String region;

                        if(c.get("region") != null) {
                             region = c.getString("region");
                        } else {
                             region = "n/a";
                        }
                        String population;

                        if(c.get("population") != null) {
                            population = c.getString("population");
                        } else {
                            population = "n/a";
                        }

                        String area;
                        if(c.get("area") != null) {
                            area = c.getString("area");
                        } else {
                            area = "n/a";
                        }

                        String borders;
                        if(c.get("borders") != null) {
                            borders = c.getJSONArray("borders").toString();
                        } else {
                            borders = null;
                        }

                        String flag;

                        if(c.get("flag") != null) {
                            flag = c.getString("flag");
                        } else {
                            flag = "n/a";
                        }

                        // tmp hash map for single contact
                        Country country = new Country();

                        // adding each child node to HashMap key => value
                        country.setCountry(name);
                        country.setCapital(capital);
                        country.setRegion(region);
                        country.setPopulation(population);
                        country.setArea(area);
                        country.setBorders(borders);
                        country.setFlag(flag);

                        // adding contact to contact list
                        countryList.add(country);
                    }
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            //Toon[] toonArray = toonList.toArray(new Toon[toonList.size()]);
//            Intent intent = new Intent(MainActivity.this, CountryActivity.class);
//            intent.putExtra("countries", countryList);
//            startActivity(intent);

            CountryAdapter adapter = new CountryAdapter(MainActivity.this, countryList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }

    }
}
