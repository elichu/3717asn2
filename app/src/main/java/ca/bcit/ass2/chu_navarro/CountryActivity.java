package ca.bcit.ass2.chu_navarro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by E on 2017-10-09.
 */

public class CountryActivity extends ListActivity {

    //ListView lv = (ListView) findViewById(R.id.country_list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_list);
       // ListView lv = (ListView) findViewById(R.id.country_list);
        Intent intent = getIntent();
        ArrayList countryList = intent.getStringArrayListExtra("countryList");
        CountryAdapter adapter = new CountryAdapter(CountryActivity.this, countryList);

       // ListView listCategory = getListView();
        setListAdapter(adapter);
        //CountryAdapter adapter = new CountryAdapter(CountryActivity.this, countryList);
        //lv.setAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
//        Intent i = new Intent(this, CountryDetailActivity.class);
//        i.putExtra("countryList", countryList);
//        startActivity(i);
    }
}
