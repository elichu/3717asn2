package ca.bcit.ass2.chu_navarro;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by E on 2017-10-09.
 */

public class CountryDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail);

        Intent intent = getIntent();
        final ArrayList countryList = intent.getParcelableArrayListExtra("countries");
        int position = intent.getIntExtra("index", 0);

        TextView countryName = (TextView) findViewById(R.id.country);
        TextView capital = (TextView) findViewById(R.id.capital);
        TextView region = (TextView) findViewById(R.id.region);
        TextView population = (TextView) findViewById(R.id.population);
        TextView area = (TextView) findViewById(R.id.area);
        TextView borders = (TextView) findViewById(R.id.borders);

        Country country = (Country)countryList.get(position);
        countryName.setText("Name: " + country.getCountry());
        capital.setText("Capital: " + country.getCapital());
        region.setText("Region: " + country.getRegion());
        population.setText("Population: " + country.getPopulation());
        area.setText("Area: " + country.getArea());
        borders.setText("Borders: " + country.getBorders());

        WebView flagImg = (WebView) findViewById(R.id.flag);
        flagImg.loadUrl(country.getFlag());
        flagImg.setBackgroundColor(Color.TRANSPARENT);
        flagImg.getSettings().setLoadWithOverviewMode(true);
        flagImg.getSettings().setUseWideViewPort(true);
        flagImg.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        flagImg.setInitialScale(1);
//        flagImg.loadUrl(country.getFlag());

    }
}
