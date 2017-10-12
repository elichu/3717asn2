package ca.bcit.ass2.chu_navarro;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by E on 2017-10-09.
 */

public class ContinentsTopLevel extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continent_list);

        ListView list_products = (ListView) findViewById(R.id.continent_list);
        list_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
}
